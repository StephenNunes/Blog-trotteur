/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Fabrique des instances des classes du modèle en utilisant des ResultatSet */
public class Fabrique 
{
   
   /** Crée une instance d'un article avec le résultat d'une requête SQL
    * @param resultatRequeteSQL la requête SQL
    * @return  l'instance créée
    * @throws java.sql.SQLException erreur SQL
    * @throws java.lang.ClassNotFoundException */
   public static Article fabriquerArticle(ResultSet resultatRequeteSQL) throws SQLException, ClassNotFoundException
   {
      resultatRequeteSQL.next();
      Article nouvelArticle = new Article(resultatRequeteSQL.getString("titre"),
              resultatRequeteSQL.getString("date"), resultatRequeteSQL.getString("contenu"), Persistance.getPseudonymeAdministrateur(resultatRequeteSQL.getInt("administrateur")));
      resultatRequeteSQL.close();
      return nouvelArticle;
   }
   
   /** Crée une liste d'instance de Commentaire avec le résultat d'une requête SQL
    * @param resultatRequeteSQL la requête SQL
    * @return la liste des instances créées
    * @throws java.sql.SQLException erreur SQL */
   public static List<Commentaire> fabriquerCommentaire(ResultSet resultatRequeteSQL) throws SQLException
   {
      List<Commentaire> listeCommentaire = new ArrayList<Commentaire>();
      while(resultatRequeteSQL.next())
      {
         listeCommentaire.add(new Commentaire(resultatRequeteSQL.getString("date"),
              resultatRequeteSQL.getString("contenu"), resultatRequeteSQL.getString("auteur")));
      }
      resultatRequeteSQL.close();
      return listeCommentaire;
   }
   
   /** Crée une liste d'instance d''Article avec le résultat de la requête SQl
    * @param resultatRequeteSQL la requête SQL
    * @return la liste des instances créées
    * @throws java.sql.SQLException erreur SQL
    * @throws java.lang.ClassNotFoundException */
   public static List<Article> fabriquerPlusieursArticle(ResultSet resultatRequeteSQL) throws SQLException, ClassNotFoundException
   {
      List<Article> listeArticle = new ArrayList<Article>();
      while (resultatRequeteSQL.next())
      {
         listeArticle.add(new Article(resultatRequeteSQL.getString("titre"),
                 resultatRequeteSQL.getString("date"), resultatRequeteSQL.getString("contenu"), Persistance.getPseudonymeAdministrateur(resultatRequeteSQL.getInt("idAdministrateur"))));
      }
      resultatRequeteSQL.close();
      return listeArticle;
   }
   
   public static List<Commentaire> fabriquerPlusieursCommentaires(ResultSet resultatRequeteSQL) throws SQLException, ClassNotFoundException
   {
      List<Commentaire> listeCommentaire = new ArrayList<Commentaire>();
      while (resultatRequeteSQL.next())
      {
         listeCommentaire.add(new Commentaire(resultatRequeteSQL.getString("auteur"),
                 resultatRequeteSQL.getString("date"), resultatRequeteSQL.getString("contenu")));
      }
      resultatRequeteSQL.close();
      return listeCommentaire;
   }
   
   /**
    * Permet de fabriquer un administrateur à l'aide d'un résultat d'une requête SQL
    * @param resultatRequeteSQL la requête SQL
    * @return l'administrateur créé
    * @throws java.sql.SQLException Erreur SQL
    */
   public static Administrateur fabriquerAdministrateur(ResultSet resultatRequeteSQL) throws SQLException
   {
      Administrateur nouvelAdministrateur = null;
      if (resultatRequeteSQL.next()) 
      {
          nouvelAdministrateur = new Administrateur(resultatRequeteSQL.getString("pseudonyme"),
              resultatRequeteSQL.getString("motdepasse"));
      }
      resultatRequeteSQL.close();
      return nouvelAdministrateur;
   }
   
   
   
}
