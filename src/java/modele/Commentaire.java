/* ______________________________________________________ */
/**
 * Fichier : ModeleCommentaire.java
 *
 * Crée le 25 févr. 2014 à 15:12:42
 *
 * Auteur : NUNES Stephen
 */
package modele;

import java.sql.SQLException;
import java.util.List;


/* ______________________________________________________ */
/** Commentaire d'un Article
 */
public class Commentaire
{
   /** Date de création du commentaire */
   private String dateCommentaire;
   
   /** Contenu du commentaire */
   private String contenu;
   
   /** Auteur du commentaire */
   private String auteur;
   
   /** Nombre de commentaire total du site */
   private static int nombreCommentaireTotal;
   
   /** 
    * Constructeur d'un commentaire
    * @param date Date du commentaire
    * @param contenu Contenu du commentaire
    * @param auteur Auteur du commentaire
    */
   public Commentaire(String auteur, String date, String contenu)
   {
      this.dateCommentaire = date;
      this.contenu = contenu;
      this.auteur = auteur;
      nombreCommentaireTotal += 1;
   }
   
   
   /** Accesseur en lecteur de la date du commentaire
    * @return la date du commentaire 
    */
   public String getDateCommentaire() {
      return dateCommentaire;
   }

   /** Accesseur en écriture de la date du commentaire
    * @param dateCommentaire la date du commentaire à définir
    */
   public void setDateCommentaire(String dateCommentaire) {
      this.dateCommentaire = dateCommentaire;
   }

   /** Accesseur en lecteur du contenu du commentaire
    * @return le contenu à définir
    */
   public String getContenu() {
      return contenu;
   }

   /** Accesseur en écriture du contenu du commentaire
    * @param contenu le contenu à définir
    */
   public void setContenu(String contenu) {
      this.contenu = contenu;
   }
   
   /** Accesseur en lecteur de l'auteur
    * @return le nom de l'auteur
    */
   public String getAuteur() {
      return auteur;
   }

   /** Accesseur en écriture de l'auteur
    * @param auteur nom de l'auteur
    */
   public void setAuteur(String auteur) {
      this.auteur = auteur;
   }
   
   /**
    * @return the nombreCommentaireTotal
    */
   public static int getNombreCommentaireTotal() {
      return nombreCommentaireTotal;
   }

   /**
    * @param aNombreCommentaireTotal the nombreCommentaireTotal to set
    */
   public static void setNombreCommentaireTotal(int aNombreCommentaireTotal) {
      nombreCommentaireTotal = aNombreCommentaireTotal;
   }
   
   public static void actionAjouterCommentaire(String auteur, String date, String contenu, int idArticle) throws ClassNotFoundException, SQLException
   {
      Persistance.ajouterCommentaire(auteur, date, contenu, idArticle);
   }
   
   
   /**
    * Méthode permettantde liste les commentaire pour un article donné
    * @param idArticle Identifiant de l'article
    * @return la liste des commentaires
    * @throws ClassNotFoundException Erreur sur le driver JDBC
    * @throws SQLException Erreur SQL
    */
   public static List<Commentaire> actionListerCommentaire(int idArticle) throws ClassNotFoundException, SQLException
   {
      return Persistance.listerCommentaire(idArticle);
   }
   
   public static void actionSupprimerCommentaire(int idCommentaire) throws ClassNotFoundException, SQLException
   {
      Persistance.supprimerCommentaire(idCommentaire);
   }
   
}

/*__________________________________________________________*/
/* Fin du fichier ModeleCommentaire.java. */
/*__________________________________________________________*/