package controleur;
import constantes.IConstantes;
import static constantes.IConstantes.PAGE_ERREUR;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Administrateur;
import modele.Article;
import modele.Commentaire;
import modele.DateFormate;
import modele.Persistance;

/**
 * Contrôleur des actions réalisées par un administrateur
 * @author raynaud
 */
public class ControleurAdministrateur extends HttpServlet implements IConstantes
{
   
   /** Numéro de serialisation */
   private static final long serialVersionUID = -4785998869110939756L;
   
   /* ______________________________________________________ */
   /** Constructeur
    */
   public ControleurAdministrateur()
   {
      super();
   }
   
   
   /* ______________________________________________________ */
   /** Méthode doGet qui appelle doPost
    * @param request la requête
    * @param response la réponse
    * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   {
      doPost(request, response);
   }
   
   
   /* ______________________________________________________ */
   /** Méthode qui réalise les actions Post
    * @param request la requête
    * @param response la réponse
    * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
   {
      String action = request.getParameter("action");
      
      if (action.equals("pageAjouterArticle"))
      {
         redirectionPageJsp(PAGE_AJOUTER_ARTICLE, request, response);
      }
      else if (action.equals("Modifier l'article"))
      {
         String titreArticleAModifier = request.getParameter("titreArticle");
         String contenuArticleAModifier = request.getParameter("contenuArticle");
         String dateArticleAModifier = request.getParameter("dateArticle");
         String auteurArticleAModifier = request.getParameter("auteurArticle");
         try
         {
            Integer idArticle = Persistance.getIdentifiantArticle(titreArticleAModifier);
            request.setAttribute("idArticleAModifier", idArticle.toString());
            request.setAttribute("titreArticleAModifier", titreArticleAModifier);
            request.setAttribute("auteurArticleAModifier", auteurArticleAModifier);
            request.setAttribute("dateArticleAModifier", dateArticleAModifier);
            request.setAttribute("contenuArticleAModifier", contenuArticleAModifier);
         }
         catch (SQLException ex)
         {
            request.setAttribute("erreur", ex.toString());
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (ClassNotFoundException ex)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         
         redirectionPageJsp(PAGE_MODIFIER_ARTICLE, request, response);
      }
      else if (action.equals("connexion"))
      {
         try 
         {
            actionConnexionAdministrateur(request, response);
         }
         catch (ClassNotFoundException ex) 
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException ex) 
         {
            request.setAttribute("erreur", "Erreur SQL sur la connexion de l'administrateur, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
      }
      else if (action.equals("deconnexion"))
      {
         HttpSession sessionCourante = request.getSession(false);
         request.setAttribute("confirmationDeconnexion", sessionCourante.getAttribute("administrateurConnecte"));
         sessionCourante.invalidate();
         redirectionPageJsp(PAGE_CONFIRMATION, request, response);
      }
      else if (action.equals("ajouterArticle"))
      {
         try 
         {
            actionAjouterArticle(request, response);
         }
         catch (ClassNotFoundException ex)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException ex)
         {
            request.setAttribute("erreur", "Erreur SQL sur l'ajout d'un nouvel article, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         request.setAttribute("confirmationAjouterArticle", "success");
         redirectionPageJsp(PAGE_CONFIRMATION, request, response);
      }
      else if (action.equals("modifierArticle"))
      {
         try
         {
            actionModifierArticle(request, response);
         }
         catch (ClassNotFoundException ex) 
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException ex) 
         {
            request.setAttribute("erreur", "Erreur SQL sur la modification d'un artice, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         request.setAttribute("confirmationModificationArticle", "success");
         redirectionPageJsp(PAGE_CONFIRMATION, request, response);
      }
      else if (action.equals("Supprimer l'article"))
      {
         try 
         {
            actionSupprimerArticle(request, response);
         }
         catch (ClassNotFoundException ex)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException ex)
         {
            request.setAttribute("erreur", "Erreur SQL sur la suppression d'un article, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         request.setAttribute("confirmationSuppressionArticle", "success");
         redirectionPageJsp(PAGE_CONFIRMATION, request, response);
      }
      else if (action.equals("Supprimer le commentaire"))
      {
         try 
         {
            actionSupprimerCommentaire(request, response);
         }
         catch (ClassNotFoundException ex)
         {
            request.setAttribute("erreur", "Erreur de chargement du driver MySQL, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         catch (SQLException ex)
         {
            request.setAttribute("erreur", "Erreur SQL sur la suppression d'un commentaire, veuillez contacter l'administrateur du site");
            redirectionPageJsp(PAGE_ERREUR, request, response);
         }
         request.setAttribute("confirmationSuppressionCommentaire", "success");
         redirectionPageJsp(PAGE_CONFIRMATION, request, response);
      }
   }
   
   
   /* ______________________________________________________ */
   /** Méthode qui supprime un article et affiche la page principale mise à jour
    * @param request la requête
    * @param response la réponse
    * @throws ClassNotFoundException levée si la classe de référence n'est pas trouvée
    * @throws SQLException levée s'il y a une erreur de requête SQL
    */
   private void actionSupprimerArticle(HttpServletRequest request, HttpServletResponse response)
           throws ClassNotFoundException, SQLException
   {
      String titreArticleAModifier = request.getParameter("titreArticle");
      int idArticle = Persistance.getIdentifiantArticle(titreArticleAModifier);
      Article.actionSupprimerArticle(idArticle);
   }
   
   
   /* ______________________________________________________ */
   /** Méthode qui ajoute un article et affiche la page principale mise à jour
    * @param request la requête
    * @param response la réponse
    * @throws ClassNotFoundException levée si la classe de référence n'est pas trouvée
    * @throws SQLException levée s'il y a une erreur de requête SQL
    */
   private void actionAjouterArticle(HttpServletRequest request, HttpServletResponse response)
           throws ClassNotFoundException, SQLException
   {
      HttpSession sessionCourante = request.getSession(false);
      String auteur = (String) sessionCourante.getAttribute("administrateurConnecte");
      String titre = request.getParameter("titreArticle");
      String contenu = request.getParameter("contenuArticle");
      Article.actionAjouterArticle(titre, DateFormate.dateDuJourFormatMySQL(), contenu, auteur);
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
      } catch (IOException e) {
         request.setAttribute("erreur", "Erreur d'entrée/sortie lors de la redirection vers une page JSP");
      }
   }
   
   /**
    * Permet de modifier un article 
    * @param request l'objet de requête
    * @param response l'objet réponse
    * @throws ClassNotFoundException Erreur de chargement du driver JDBC
    * @throws SQLException Erreur SQL
    */
   private void actionModifierArticle(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException
   {
      String titre = request.getParameter("titreArticle");
      String contenu = request.getParameter("contenuArticle");
      String idArticleString = request.getParameter("idArticle");
      int idArticle = Integer.parseInt(idArticleString);
      Article.actionModifierArticle(idArticle, titre, contenu);
   }
   
   /**
    * L'action de connexion d'un administrateur
    * @param request objet request
    * @param response objet response
    * @throws ClassNotFoundException Erreur de chargement du driver JDBC
    * @throws SQLException Erreur SQL
    */
   private void actionConnexionAdministrateur(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
      String pseudonymeAdministrateur = request.getParameter("pseudonymeAdministrateur");
      String motDePasseAdministrateur = request.getParameter("motDePasseAdministrateur");
      Administrateur nouvelAdministrateur = Administrateur.actionConnexion(pseudonymeAdministrateur, motDePasseAdministrateur);
      if (nouvelAdministrateur != null)
      {
         HttpSession maSession = request.getSession(true);
         maSession.setAttribute("administrateurConnecte", pseudonymeAdministrateur);
         request.setAttribute("confirmationConnexion", pseudonymeAdministrateur);
         redirectionPageJsp(PAGE_CONFIRMATION, request, response);
      }
      else
      {
         String erreurConnexion = "invalide";
         request.setAttribute("erreurConnexion", erreurConnexion);
         redirectionPageJsp(PAGE_CONNEXION, request, response);
      }
   }
   
   /**
    * Action de suppression commentaire
    * @param request 
    * @param response
    * @throws ClassNotFoundException
    * @throws SQLException 
    */
   private void actionSupprimerCommentaire(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException
   {
      String auteurCommentaire = request.getParameter("auteurCommentaire");
      String dateCommentaire = request.getParameter("dateCommentaire");
      int idCommentaire = Persistance.getIdentifiantCommentaire(auteurCommentaire, dateCommentaire);
      Commentaire.actionSupprimerCommentaire(idCommentaire);
   }
}

