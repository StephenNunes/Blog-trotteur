/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/** Classe persistance possedant tous les accès et les modification de la base de données */
public class Persistance 
{
   /**
    * Permet d'ajouter un nouvel article à la base de données
    * @param titre Titre de l'article
    * @param date Date de l'article
    * @param contenu Contenu de l'article
    * @param administrateur Nom de l'administrateur
    * @throws SQLException Erreur de SQL
    * @throws ClassNotFoundException Erreur de driver MySQL
    */
   public static void ajouterArticle(String titre, String date,  String contenu, String administrateur) throws SQLException, ClassNotFoundException
   {
      Integer idAdmin = getIdentifiantAdministrateur(administrateur);
      
      String requete = "Insert into article (titre, date, contenu, idAdministrateur) Values(?, ?, ?, ?)";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setString(1, titre);
      ps.setString(2, date);
      ps.setString(3, contenu);
      ps.setInt(4, idAdmin);
      
      ps.executeUpdate();
      ps.close();
   }
   
   /**
    * Modifie un article de la base de données
    * @param idArticle Identifiant de l'article
    * @param titre Titre de l'article
    * @param contenu Contenu de l'article
    * @throws ClassNotFoundException Erreur de driver JDBC
    * @throws SQLException Erreur SQL
    */
   public static void modifierArticle(int idArticle, String titre, String contenu) throws ClassNotFoundException, SQLException
   {
      Integer idArt = new Integer(idArticle);
      
      String requete = "Update article set titre = ?, contenu = ? "
              + " where identifiant = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setString(1, titre);
      ps.setString(2, contenu);
      ps.setString(3, idArt.toString());
      ps.executeUpdate();
      ps.close();
      
   }
   
   /**
   * Supprime l'article de la base données 
   * @param idArticle Identifiant de l'article
   * @throws ClassNotFoundException Erreur de driver JDBC
   * @throws SQLException Erreur de SQL
   */
   public static void supprimerArticle(int idArticle) throws ClassNotFoundException, SQLException
   {
      String requete = "Delete From article Where identifiant = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setInt(1, idArticle);
      // Test le résultat
      ps.executeUpdate();
      ps.close();
      
   }
   
   /**
    * Connexion d'un administrateur
    * @param pseudonyme Pseudonyme de l'administrateur
    * @param motDePasse Mot de passe de l'administrateur
    * @return L'administrateur créé s'il est connecté
    * @throws ClassNotFoundException Problème de driver JDBC
    * @throws SQLException Erreur SQL
    */
   public static Administrateur connexionAdministrateur(String pseudonyme, String motDePasse) throws ClassNotFoundException, SQLException
   {
      String requeteConnexion = "Select pseudonyme, motdepasse From Administrateur Where pseudonyme = ? And motdepasse= ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requeteConnexion);
      ps.setString(1, pseudonyme);
      ps.setString(2, motDePasse);
      ResultSet rs = ps.executeQuery();
      Administrateur adminCree = Fabrique.fabriquerAdministrateur(rs);
      rs.close();
      ps.close();
      return adminCree;
   }
   
   /**
    *  Ajout d'un commentaire à la base données
    * @param auteur Auteur du commentaire
    * @param date Date du commentaire
    * @param contenu Contenu du commentaire
    * @param idArticle identifiant de l'Article
    * @throws ClassNotFoundException Erreur de driver JDBC
    * @throws SQLException Erreur de SQL
    */
   public static void ajouterCommentaire(String auteur, String date, String contenu, int idArticle) throws ClassNotFoundException, SQLException
   {
      String requete = "Insert into commentaire (auteur, date, contenu, idArticle) Values (?, ?, ?, ?)";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setString(1, auteur);
      ps.setString(2, date);
      ps.setString(3, contenu);
      ps.setInt(4, idArticle);
      ps.executeUpdate();
      ps.close();      
   }
   
   /**
    * Suppression du commentaire de la base de données
    * @param idCommentaire Identifiant du commentaire
    * @throws ClassNotFoundException Erreur de driver JDBC
    * @throws SQLException Erreur de SQL
    */
   public static void supprimerCommentaire(int idCommentaire) throws ClassNotFoundException, SQLException
   {
      String requete = "Delete From commentaire Where identifiant = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setInt(1, idCommentaire);
      ps.executeUpdate();
      ps.close();
   }
   
   /**
    * Recherche de plusieurs articles sur la date
    * @param dateArticle Date des articles recherchés
    * @return La liste des articles
    * @throws ClassNotFoundException Erreur de driver JDBC
    * @throws SQLException Erreur de SQL
    */
   public static List<Article> rechercherArticle(String dateArticle) throws ClassNotFoundException, SQLException
   {
      String requete = "Select * From article Where date = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setString(1, dateArticle);
      ResultSet rs = ps.executeQuery();
      
      List<Article> listeArticle = Fabrique.fabriquerPlusieursArticle(rs);
      rs.close();
      ps.close();
      return listeArticle;
   }
   
   /**
    * Liste les articles présent dans la base de données
    * @param numeroPage Numéro de page afin de délimiter le nombre d'articles affichés
    * @return la liste des articles
    * @throws ClassNotFoundException Erreur de driver JDBC
    * @throws SQLException Erreur SQL survenue
    */
   public static List<Article> listerArticle(int numeroPage) throws ClassNotFoundException, SQLException
   {
      Integer numeroDePage = new Integer(numeroPage * 10 - 10);
      String requete = "Select * From article Limit ?, 10";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setInt(1, numeroDePage);
      ResultSet rs = ps.executeQuery();
      List<Article> listeArticle = Fabrique.fabriquerPlusieursArticle(rs);
      rs.close();
      ps.close();
      return listeArticle;
   }
   
   /**
    * Liste les commentaires pour un article donné
    * @param idArticle l'identifiant de l'Article où il faut lister ses commentaires
    * @return la liste des commentaires
    * @throws ClassNotFoundException Erreur sur le driver JDBC
    * @throws SQLException Erreur SQL
    */
   public static List<Commentaire> listerCommentaire(int idArticle) throws ClassNotFoundException, SQLException
   {
      String requete = "Select auteur, date, contenu From commentaire Where idArticle = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setInt(1, idArticle);
      ResultSet rs = ps.executeQuery();
      List<Commentaire> listeCommentaire = Fabrique.fabriquerPlusieursCommentaires(rs);
      rs.close();
      ps.close();
      return listeCommentaire;
   }
   
   /**
    * Permet d'obtenir l'identifiant d'un administrateur depuis son pseudonyme
    * @param pseudonymeAdministrateur Pseudonyme de l'administrateur
    * @return l'identifiant de l'administrateur
    * @throws SQLException Erreur SQL
    * @throws ClassNotFoundException Erreur de driver JDBC
    */
   public static int getIdentifiantAdministrateur(String pseudonymeAdministrateur) throws SQLException, ClassNotFoundException
   {
      String requete = "Select identifiant From administrateur Where pseudonyme = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setString(1, pseudonymeAdministrateur);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int identifiantAdministrateur = rs.getInt(1);
      rs.close();
      ps.close();
      return identifiantAdministrateur;
   }
   
   /**
    * Permet d'obtenir l'identifiant de l'Article en lui passant le titre de l'article
    * @param titreArticle Titre de l'article passé en paramètre
    * @return l'identifiant de l'article
    * @throws SQLException Erreur SQL
    * @throws ClassNotFoundException Erreur du driver JDBC
    */
   public static int getIdentifiantArticle(String titreArticle) throws SQLException, ClassNotFoundException
   {
      String requete = "Select identifiant From article Where titre = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setString(1, titreArticle);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int identifiantArticle = rs.getInt(1);
      rs.close();
      ps.close();
      return identifiantArticle;
   }
   
   /**
    * Permet d'obtenir l'identifiant d'un commentaire en lui passant l'auteur ainsi que la date
    * @param auteurCommentaire Auteur du commentaire
    * @param dateCommentaire Date du commentaire
    * @return l'identifiant du commentaire
    * @throws ClassNotFoundException Erreur du driver JDBC
    * @throws SQLException Erreur SQL
    */
   public static int getIdentifiantCommentaire(String auteurCommentaire, String dateCommentaire) throws ClassNotFoundException, SQLException
   {
      String requete = "Select identifiant From commentaire Where auteur = ? And date = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setString(1, auteurCommentaire);
      ps.setString(2, dateCommentaire);
      ResultSet rs = ps.executeQuery();
      rs.next();
      int identifiantCommentaire = rs.getInt(1);
      rs.close();
      ps.close();
      return identifiantCommentaire;
   }
   
   /**
    * Permet d'obtenir le pseudonyme de l'administrateur en ayant son identifiant
    * @param idAdministrateur Identifiant de l'administrateur
    * @return le pseudonyme de l'administrateur
    * @throws SQLException Erreur SQL
    * @throws ClassNotFoundException Erreur du driver JDBC
    */
   public static String getPseudonymeAdministrateur(int idAdministrateur) throws SQLException, ClassNotFoundException
   {
      String requete = "Select pseudonyme From administrateur Where identifiant = ?";
      PreparedStatement ps = BaseDeDonnees.getInstance().getPreparedStatement(requete);
      ps.setInt(1, idAdministrateur);
      ResultSet rs = ps.executeQuery();
      rs.next();
      String pseudonymeRecupere = rs.getString(1);
      rs.close();
      ps.close();
      return pseudonymeRecupere;
   }
}
