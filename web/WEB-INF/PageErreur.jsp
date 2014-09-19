<%-- 
    Document   : PageErreur
    Created on : 7 mars 2014, 11:47:02
    Author     : stephen
    Page d'erreur du site internet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog-Trotteur | Page d'erreur</title>
        <style><%@include file="./style.css" %></style>
    </head>
    <body>
        <nav>
        <table>
            <tr><form method="post" name="formNavPagePrincipale" action="./">
                <input type="hidden" name="action" value="recharger" />
                &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavPagePrincipale.submit()">Page principale</a>&nbsp;&nbsp;&nbsp;
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
                    %><tr><form method="post" name="formNavConnexion" action="./">
                    <input type="hidden" name="action" value="pageConnexion" />
                    &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavConnexion.submit()">Connexion</a>&nbsp;&nbsp;&nbsp;
                    </form></tr><%
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
        <p class="titre">Erreur survenue</p>
        <%
            String erreur = (String) request.getAttribute("erreur");
            out.println(erreur);
        %>
    </article>
    </body>
</html>
