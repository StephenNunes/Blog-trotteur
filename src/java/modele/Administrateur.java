/* ______________________________________________________ */
/**
 * Fichier : ModeleAdministrateur.java
 *
 * Crée le 25 févr. 2014 à 15:12:56
 *
 * Auteur : NUNES Stephen
 */
package modele;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* ______________________________________________________ */
/** Classe représentant un Administrateur
 */
public class Administrateur
{
   /** Pseudonyme de l'administrateur */
   private String pseudonyme;
   
   /** Mot de passe de connexion de l'administrateur */
   private String motDePasse;
   
   /** Nombre de commentaire postés */
   private int nombreDeCommentaire;
   
   /** Liste des actions du joueurs */
   private static final List<String> listeAction = new ArrayList<String>(Arrays.asList(
      "deconnexion", "ajouterArticle", "supprimerArticle", "modifierArticle",
      "supprimerCommentaire", "pageAjouterArticle", "Modifier l'article", 
      "Supprimer l'article", "Supprimer le commentaire"));

   public Administrateur(String pseudonyme, String motDePasse)
   {
      setPseudonyme(pseudonyme);
      setMotDePasse(motDePasse);
   }
   
   /**
    * @return the pseudonyme
    */
   public String getPseudonyme() {
      return pseudonyme;
   }

   /**
    * @param pseudonyme the pseudonyme to set
    */
   public void setPseudonyme(String pseudonyme) {
      this.pseudonyme = pseudonyme;
   }

   /**
    * @return the motDePasse
    */
   public String getMotDePasse() {
      return motDePasse;
   }

   /**
    * @param motDePasse the motDePasse to set
    */
   public void setMotDePasse(String motDePasse) {
      this.motDePasse = motDePasse;
   }

   /**
    * @return the nombreDeCommentaire
    */
   public int getNombreDeCommentaire() {
      return nombreDeCommentaire;
   }

   /**
    * @param nombreDeCommentaire the nombreDeCommentaire to set
    */
   public void setNombreDeCommentaire(int nombreDeCommentaire) {
      this.nombreDeCommentaire = nombreDeCommentaire;
   }

   public static ArrayList<String> getListeActionsAdministrateur() {
      return new ArrayList<String>(listeAction);
   }
   
   public static Administrateur actionConnexion(String pseudonyme, String motDePasse) throws ClassNotFoundException, SQLException
   {
      return Persistance.connexionAdministrateur(pseudonyme, motDePasse);
   }
   
   
}

/*__________________________________________________________*/
/* Fin du fichier ModeleAdministrateur.java. */
/*__________________________________________________________*/