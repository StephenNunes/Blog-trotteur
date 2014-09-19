<%-- 
    Document   : PageConnexion
    Created on : 7 mars 2014, 11:47:35
    Author     : stephen
    Page principale affichant les articles et leurs commentaires
--%>

<%@page import="java.util.Map"%>
<%@page import="modele.Commentaire"%>
<%@page import="modele.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog-Trotteur | Page principale</title>
        <style><%@include file="./style.css" %></style>
    </head>
    <body>
    <nav>
        <table>
            <tr><form method="post" name="formNavPagePrincipale" action="./">
                <input type="hidden" name="action" value="recharger" />
                &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavPagePrincipale.submit()">Page principale</a>&nbsp;&nbsp;&nbsp;
            </form></tr>
            <tr><form method="post" name="formNavPagePrecedente" action="./">
                <input type="hidden" name="action" value="pagePrecedente" />
                <input type="hidden" name="numeroPage" value="pagePrecedente" />
                &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavPagePrecedente.submit()">Page précédente</a>&nbsp;&nbsp;&nbsp;
            </form></tr>
            <tr><form method="post" name="formuNavPageSuivante" action="./">
                <input type="hidden" name="action" value="pageSuivante" />
                <input type="hidden" name="numeroPage" value="pageSuivante" />
                &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formuNavPageSuivante.submit()">Page suivante</a>&nbsp;&nbsp;&nbsp;
            </form></tr>
            <tr><form method="post" name="formNavRechercherArticle" action="./">
                <input type="hidden" name="action" value="rechercherArticle" />
                &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavRechercherArticle.submit()">Rechercher un article</a>&nbsp;
                <input type="date" id="recherche" name="recherche"/>&nbsp;&nbsp;&nbsp;
            </form></tr>
            <%
                HttpSession sessionCourante = request.getSession(false);
                if (sessionCourante.getAttribute("administrateurConnecte") == null)
                {
                    %>
                    <tr><form method="post" name="formNavConnexion" action="./">
                    <input type="hidden" name="action" value="pageConnexion" />
                    &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavConnexion.submit()">Connexion</a>&nbsp;&nbsp;&nbsp;
                    </form></tr>
                    <%
                }
                else
                {
                    %>
                    <tr><form method="post" name="formNavPageAjouterArticle" action="./">
                    <input type="hidden" name="action" value="pageAjouterArticle" />
                    &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavPageAjouterArticle.submit()">Ajouter un article</a>&nbsp;&nbsp;&nbsp;
                    </form></tr>
                    
                    <tr><form method="post" name="formNavDeconnexion" action="./">
                    <input type="hidden" name="action" value="deconnexion" />
                    &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavDeconnexion.submit()">Déconnexion</a>&nbsp;&nbsp;&nbsp;
                    </form></tr>
                    <%
                }
            %>
        </table>
    </nav>
    <article>
        <%
           sessionCourante = request.getSession(false);
           if (sessionCourante.getAttribute("administrateurConnecte") != null)
           {
              out.println("<p class='connecte'>Vous êtes actuellement connecté en tant que : <span class='nomAdministrateur'>" + sessionCourante.getAttribute("administrateurConnecte") + "</span>");
           }
        %>
        <p class="titre">Blog-Trotteur, l'actualité mondiale</p>
        <%
           
           Map<Article, List<Commentaire>> articlesCommente = (Map<Article, List<Commentaire>>) request.getAttribute("articlesCommentes");
           if (articlesCommente != null)
           {
           
            for (Article a : articlesCommente.keySet())
            {
               %>
              <form action="./" method="post">
              <table class="contour">
                  <tr>
                    <td class="information">Titre</td>
                    <td class="information">Auteur</td>
                    <td class="information">Date</td>
                </tr>
                <tr>
                    <td><%= a.getTitre() %></td>
                    <td><%= a.getAdministrateur() %></td>
                    <td><%= a.getDateArticle() %></td>
                </tr>
                <tr>
                    <%
                        if (sessionCourante.getAttribute("administrateurConnecte") != null)
                        {
                           %> 
                           <td><input type="submit" name="action" value="Modifier l'article" /></td>
                           
                           <td><input type="submit" name="action" value="Supprimer l'article" /></td>
                           <%
                        }
                    %>
                    <td><input type="submit" name="action" value="Commentez cet article" /></td>
                    <% 
                        out.println("<input type='hidden' name='titreArticle' value='" + a.getTitre() + "' />"); 
                        out.println("<input type='hidden' name='auteurArticle' value='" + a.getAdministrateur()+ "' />"); 
                        out.println("<input type='hidden' name='dateArticle' value='" + a.getDateArticle()+ "' />");
                        out.println("<input type='hidden' name='contenuArticle' value='" + a.getContenu()+ "' />"); 
                    %>
                
                </tr>
                </table>
                <table class="contour">
                <tr>
                    <td></td>
                    <td><%= a.getContenu() %> </td>
                    <td></td>
                </tr>
                </table>
                <br/>
                <br/>
                </form>
              <%
              for (Commentaire c : articlesCommente.get(a))
              {
                 %>
                 <form action="./" method="post">
                 <table class="contour">
                  <tr>
                    <td>Commentaire de l'article</td>
                    <td class="information">Auteur du commentaire</td>
                    <td class="information">Date du commentaire</td>
                </tr>
                <tr>
                    <td></td>
                    <td><%= c.getAuteur() %></td>
                    <td><%= c.getDateCommentaire() %></td>
                    
                </tr>
                <tr>
                    <%
                        if (sessionCourante.getAttribute("administrateurConnecte") != null)
                        {
                           %> 
                           <td><input type="submit" name="action" value="Supprimer le commentaire" /></td>
                           <%
                        }
                        out.println("<input type='hidden' name='auteurCommentaire' value='" + c.getAuteur() + "' />"); 
                        out.println("<input type='hidden' name='dateCommentaire' value='" + c.getDateCommentaire() + "' />");
                        out.println("<input type='hidden' name='contenuCommentaire' value='" + c.getContenu() + "' />"); 
                    %>
                
                </tr>
                </table>
                <table class="contour">
                <tr>
                    <td></td>
                    <td><%= c.getContenu() %> </td>
                    <td></td>
                </tr>
                </table>
                <br/>
                </form>
               <%
              }
            }
            %>
            <br/>
            <br/>
            <br/>
            <br/>
            <%
          }
        %>
        
    </article>
</body>
</html>