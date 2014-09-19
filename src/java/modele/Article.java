/* ______________________________________________________ */
/**
 * Fichier : ModeleArticle.java
 *
 * Crée le 25 févr. 2014 à 15:12:30
 *
 * Auteur : NUNES Stephen
 */
package modele;

import java.sql.SQLException;
import java.util.List;

/* ______________________________________________________ */
/** Classe du modèle permettant de stocker un article du blog
 */
public class Article
{
   /** Titre de l'article */
   private String titre;
   
   /** Date de création de l'article */
   private String dateArticle;
   
   /** Contenu de l'article */
   private String contenu;
   
   /** Nom de l'administrateur ayant ajouté cet article */
   private String administrateur;

   public Article(String titre, String date, String contenu, String admin)
   {
      this.titre = titre;
      this.dateArticle = date;
      this.contenu = contenu;
      this.administrateur = admin;
   }
   
   /** Ajoute l'article à la base de données
    * @param titre
    * @param dateArticle
    * @param contenu
    * @param administrateur
    * @throws java.sql.SQLException Erreur SQL  
    * @throws java.lang.ClassNotFoundException  
    * */
   public static void actionAjouterArticle(String titre, String dateArticle, String contenu, String administrateur) throws SQLException, ClassNotFoundException
   {
      Persistance.ajouterArticle(titre, dateArticle, contenu, administrateur);
   }
   
   /** Accesseur en lecteur du titre
    * @return le titre
    */
   public String getTitre() {
      return titre;
   }

   /** Accesseur en écriture du titre
    * @param titre le titre à définir
    */
   public void setTitre(String titre) {
      this.titre = titre;
   }

   /** Accesseur en lecteur de la date de l'article
    * @return la date de l'Article
    */
   public String getDateArticle() {
      return dateArticle;
   }

   /** Accesseur en écriture de la date de l'article
    * @param dateArticle the dateArticle to set
    */
   public void setDateArticle(String dateArticle) {
      this.dateArticle = dateArticle;
   }

   /** Accesseur en lecteur du contenu de l'article
    * @return le contenu à définir
    */
   public String getContenu() {
      return contenu;
   }

   /** Accesseur en écriture du contenu de l'article
    * @param contenu le contenu à définir
    */
   public void setContenu(String contenu) {
      this.contenu = contenu;
   }
   
   /** Permet de lister tous les articles à un certain numéro de page
    * @param numeroPage numéro de la page
    * @return La liste des article à une certaine page
    * @throws SQLException
    * @throws ClassNotFoundException 
    */
   public static List<Article> actionListerArticles(int numeroPage) throws SQLException, ClassNotFoundException
   {
      return Persistance.listerArticle(numeroPage);
   }

   /**
    * @return the Administrateur
    */
   public String getAdministrateur() {
      return administrateur;
   }

   /**
    * @param admin 
    */
   public void setAdministrateur(String admin) {
      this.administrateur = admin;
   }
   
   /** Modifie l'article ayant l'identifiant article passé en paramètre
    * @param idArticle
    * @param titre
    * @param contenu
    * @throws java.lang.ClassNotFoundException
    * @throws java.sql.SQLException 
    */
   public static void actionModifierArticle(int idArticle, String titre, String contenu) throws ClassNotFoundException, SQLException
   {
      Persistance.modifierArticle(idArticle, titre, contenu);
   }
   
   public static void actionSupprimerArticle(int idArticle) throws ClassNotFoundException, SQLException
   {
      Persistance.supprimerArticle(idArticle);
   }
   
   public static List<Article> actionRechercherArticle(String dateRecherche) throws ClassNotFoundException, SQLException
   {
      return Persistance.rechercherArticle(dateRecherche);
   }
   
}

/*__________________________________________________________*/
/* Fin du fichier ModeleArticle.java. */
/*__________________________________________________________*/