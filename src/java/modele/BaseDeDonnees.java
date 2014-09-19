package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe de base de données permet de stocker l'instance de la base de données
 * @author stephen
 */
public class BaseDeDonnees {
   
   /** Instance unique de la base de données */
   private static BaseDeDonnees instance;
   
   /** Connexion établie avec la base de données */
   private Connection connexionEtablie;
   
   /** Constructeur privée de la classe */
   private BaseDeDonnees() throws SQLException, ClassNotFoundException
   {
      Class.forName("com.mysql.jdbc.Driver");
     
      connexion();
      
   }
   
   /** Retour l'instance unique de la base de données, et l'instancie si elle est inexistante
    * @return l'instance unique
    * @throws java.sql.SQLException Erreur de SQL
    * @throws java.lang.ClassNotFoundException Driver non chargé
    */
   public static BaseDeDonnees getInstance() throws SQLException, ClassNotFoundException
   {
      if (instance == null)
         instance = new BaseDeDonnees();
      return instance;
   }
   
   
   /** Destructeur de l'instance de la base de données
    * @throws java.sql.SQLException Erreur de SQL
    */
   @Override
   public void finalize() throws SQLException, Throwable 
   {
      
      super.finalize();
      
      instance = null;
      deconnexion();
   }
   
   /** Méthode permettant de se connecter à la base de données
    * @throws java.sql.SQLException Erreur d'SQL
    */
   private void connexion() throws SQLException
   {
      connexionEtablie = DriverManager.getConnection("jdbc:mysql://localhost/blogprojet", "blogprojet", "blogprojet");
      
   }
   
   /** Méthode permettant de se déconnecter de la base de données
    * @throws java.sql.SQLException Erreur de fermeture de la connexion à la base de données
    */
   public void deconnexion() throws SQLException
   {
      connexionEtablie.close();
   }
   
   /** Permet d'obtenir une requête pré-compilé
    * @param requete La requête à pré-compiler
    * @return la requête pré-compilé
    * @throws java.sql.SQLException Erreur de SQL */
   public PreparedStatement getPreparedStatement(String requete) throws SQLException
   {
      return connexionEtablie.prepareStatement(requete);
   }
}
