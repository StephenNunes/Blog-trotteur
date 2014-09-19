/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package controleur;

import constantes.IConstantes;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Administrateur;

/**
 * Premier contrôleur appelé permettant de faire la redirection vers les deux autres contrôleurs
 * @author raynaud
 */
public class FrontController extends HttpServlet implements IConstantes
{
   
   
   /** Méthode doGet pour toutes les actions de Get
    * @param request la requête
    * @param response la réponse
    */
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
   {
      ControleurAdministrateur ca = new ControleurAdministrateur();
      ControleurUtilisateur cu = new ControleurUtilisateur();
      HttpSession session = request.getSession(true);
      if (session.getAttribute("administrateurConnecte") == null)
      {
         redirectionControleur(null, cu, request, response);
      }
      else
      {
         String action = request.getParameter("action");
         if (Administrateur.getListeActionsAdministrateur().contains(action))
         {
            redirectionControleur(ca, null, request, response);
         }
         else
         {
            redirectionControleur(null, cu, request, response);
         }
      }
   }
   
   
   /** Méthode doPost pour toutes les actions de Post
    * @param request la requête
    * @param response la réponse
    * @throws ServletException en cas d'erreur de servlet
    * @throws IOException en cas d'erreur d'E/S
    */
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      doGet(request, response);
   }
   
   
   /** Méthode de redirection vers le bon controleur
    * @param ca le controleur administrateur si non null
    * @param cu le controleur utilisateur si non null
    * @param request la requête
    * @param response la réponse
    */
   private void redirectionControleur(ControleurAdministrateur ca, ControleurUtilisateur cu, HttpServletRequest request, HttpServletResponse response)
   {
      try
      {
         if (ca != null)
            getServletContext().getRequestDispatcher("/administrateur").forward(request, response);
         else
            getServletContext().getRequestDispatcher("/utilisateur").forward(request, response);
      } catch (ServletException e) {
         request.setAttribute("erreur", "Erreur sur la servlet lors de son accès");
         redirectionPageJsp(PAGE_ERREUR, request, response);
      } catch (IOException e)
      {
         request.setAttribute("erreur", "Erreur d'entrée/sortie pour la redirection vers une servlet");
         redirectionPageJsp(PAGE_ERREUR, request, response);
      }
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
      } catch (ServletException e) {
         request.setAttribute("erreur", "Erreur sur la servlet lors de la redirection vers une page JSP");
      } catch (IOException e)
      {
         request.setAttribute("erreur", "Erreur d'entrée/sortie lors de la redirection vers une page JSP");
      }
   }
}
