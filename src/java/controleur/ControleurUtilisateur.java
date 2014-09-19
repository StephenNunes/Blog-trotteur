package controleur;

import constantes.IConstantes;
import static constantes.IConstantes.PAGE_ERREUR;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.Article;
import modele.Commentaire;
import modele.DateFormate;
import modele.Persistance;

/**
 * Contrôleur des actions de l'utilisateur
 * @author raynaud
 */
public class ControleurUtilisateur extends HttpServlet implements IConstantes
{
   /** Numéro de serialisation */
   private static final long serialVersionUID = 4206366924844675378L;
   
   /** Numéro de la page en cours */
   private int numeroDePageEnCours = 1;
   /* ______________________________________________________ */
   /** Constructeur d'un contrôleur utilisateur
    */
   public ControleurUtilisateur()
   {
      super();
   }
   
   
   /* ______________________________________________________ */
   /**
    * Méthode doGet qui appelle doPost
    *
    * @param request la requête
    * @param response la réponse
    * @see
    * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
    * javax.servlet.http.HttpServletResponse)
    */
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   {
      doPost(request, response);
   }
   
   
   /* ______________________________________________________ */
   /**
    * Méthode qui réalise les actions Post
    *
    * @param request la requête
    * @param response la réponse
    * @see
    * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
    * javax.servlet.http.HttpServletResponse)
    */
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
   {
      String action = request.getParameter("action");
      if (action == null || action.equals("recharger"))
      {
         try
         {
            actionAfficherPagePrincipale(request, response, numeroDePageEnCours);
         }
         catch (ClassNotFoundException e)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException e)
         {
            request.setAttribute("erreur", "Erreur SQL sur l'affichage de la page principale, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
      }
      else if (action.equals("pagePrecedente"))
      {
         if (numeroDePageEnCours > 1)
         {
            numeroDePageEnCours -= 1;
         }
         try
         {
            actionAfficherPagePrincipale(request, response, numeroDePageEnCours);
         }
         catch (ClassNotFoundException e)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException e)
         {
            request.setAttribute("erreur", "Erreur SQL sur l'affichage de la page principale, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
      }
      else if (action.equals("pageSuivante"))
      {
         numeroDePageEnCours += 1;
         try
         {
            actionAfficherPagePrincipale(request, response, numeroDePageEnCours);
         }
         catch (ClassNotFoundException e)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException e)
         {
            request.setAttribute("erreur", "Erreur SQL sur l'affichage de la page principale, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
      }
      else if (action.equals("rechercherArticle"))
      {
         try
         {
            actionAfficherResultatRecherche(request, response);
         }
         catch (ClassNotFoundException ex)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException ex)
         {
            request.setAttribute("erreur", "Erreur SQL sur l'affichage d'un résultat du recherche, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
      }
      else if (action.equals("pageConnexion"))
      {
         redirectionPageJsp(PAGE_CONNEXION, request, response);
      }
      else if (action.equals("Commentez cet article"))
      {
         String titreArticle = request.getParameter("titreArticle");
         String contenuArticle = request.getParameter("contenuArticle");
         String dateArticle = request.getParameter("dateArticle");
         String auteurArticle = request.getParameter("auteurArticle");
         try
         {
            Integer idArticle = Persistance.getIdentifiantArticle(titreArticle);
            request.setAttribute("idArticle", idArticle.toString());
            request.setAttribute("titreArticle", titreArticle);
            request.setAttribute("auteurArticle", auteurArticle);
            request.setAttribute("dateArticle", dateArticle);
            request.setAttribute("contenuArticle", contenuArticle);
         }
         catch (SQLException ex)
         {
            request.setAttribute("erreur", "Erreur SQL sur l'obtention de l'identifiant d'un article, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (ClassNotFoundException ex)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         redirectionPageJsp(PAGE_AJOUTER_COMMENTAIRE, request, response);
      }
      else if (action.equals("ajouterCommentaire"))
      {
         try
         {
            actionAjouterCommentaire(request, response);
         }
         catch (ClassNotFoundException ex)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException ex)
         {
            request.setAttribute("erreur", "Erreur SQL sur l'ajout d'un commentaire, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         request.setAttribute("confirmationAjouterCommentaire", "success");
         redirectionPageJsp(PAGE_CONFIRMATION, request, response);
      }
   }
   
   
   /* ______________________________________________________ */
   /**
    * Méthode qui effectue la recherche et affiche le résultat
    *
    * @param request la requête
    * @param response la réponse
    */
   private void actionAfficherResultatRecherche(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException
   {
      String dateRecherche = request.getParameter("recherche");
      Map<Article, List<Commentaire>> articlesCommentes = new HashMap<Article, List<Commentaire>>();
      List<Article> resultatRecherche = Article.actionRechercherArticle(dateRecherche);
      
      
      for (Article articleCourant : resultatRecherche)
      {
         int idArticle = Persistance.getIdentifiantArticle(articleCourant.getTitre());
         List<Commentaire> lesCommentairesDeLArticle = Persistance.listerCommentaire(idArticle);
         articlesCommentes.put(articleCourant, lesCommentairesDeLArticle);
      }
      request.setAttribute("articlesCommentes", articlesCommentes);
      redirectionPageJsp(PAGE_PRINCIPALE, request, response);
   }
   
   
   /* ______________________________________________________ */
   /**
    * Méthode qui affiche la page principale avec toutes les fonctionalités
    * attendues
    *
    * @param request la requête
    * @param response la réponse
    * @param numeroPage numéro de la page à afficher
    * @throws ClassNotFoundException levée si la classe de référence n'est pas
    * trouvée
    * @throws SQLException levée s'il y a une erreur de requête SQL
    */
   public void actionAfficherPagePrincipale(HttpServletRequest request, HttpServletResponse response, int numeroPage)
           throws ClassNotFoundException, SQLException
   {
      Map<Article, List<Commentaire>> articlesCommentes = new HashMap<Article, List<Commentaire>>();
      
      List<Article> lesArticles = Article.actionListerArticles(numeroPage);
      for (Article articleCourant : lesArticles)
      {
         int idArticle = Persistance.getIdentifiantArticle(articleCourant.getTitre());
         List<Commentaire> lesCommentairesDeLArticle = Persistance.listerCommentaire(idArticle);
         articlesCommentes.put(articleCourant, lesCommentairesDeLArticle);
      }
      request.setAttribute("articlesCommentes", articlesCommentes);
      redirectionPageJsp(PAGE_PRINCIPALE, request, response);
   }
   
   
   /** Méthode de redirection vers la page spécifiée
    * @param nomFichier le nom du fichier
    * @param request la requête
    * @param response la réponse
    */
   public void redirectionPageJsp(String nomFichier, HttpServletRequest request, HttpServletResponse response)
   {
      try
      {
         getServletContext().getRequestDispatcher("/WEB-INF/" + nomFichier + ".jsp").forward(request, response);
      } 
      catch (ServletException e)
      {
         request.setAttribute("erreur", "Erreur sur la servlet lors de la redirection vers une page JSP");
      } 
      catch (IOException e) {
         request.setAttribute("erreur", "Erreur d'entrée/sortie lors de la redirection vers une page JSP");
         //redirectionPageJsp(PAGE_ERREUR, request, response);
      }
   }
   
   /**
    * Action d'un utilisateur d'ajouter un commentaire à un article
    * @param request objet request
    * @param response objet response
    * @throws ClassNotFoundException Erreur si le driver n'est pas chargé
    * @throws SQLException Erreur d'SQL
    */
   private void actionAjouterCommentaire(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException 
   {
      String auteur = request.getParameter("auteurCommentaire");
      String contenu = request.getParameter("contenuCommentaire");
      String idArticle = request.getParameter("idArticle");
      Integer idArticleInt = Integer.parseInt(idArticle);
      Commentaire.actionAjouterCommentaire(auteur, DateFormate.dateDuJourFormatMySQL(), contenu, idArticleInt);
   }
}
