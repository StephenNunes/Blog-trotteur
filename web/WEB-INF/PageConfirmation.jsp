<%-- 
    Document   : PageConfirmation
    Created on : 15 mars 2014, 10:47:12
    Author     : stephen
    Page permettant de notifier le bon déroulement de certains événements sur le site web
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog-Trotteur | Confirmation</title>
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
        </table>
    </nav>
    <article>
        <p class="titre">Blog-Trotteur, l'actualité mondiale</p>
        <%
                if (request.getAttribute("confirmationConnexion") != null)
                {
                    HttpSession sessionCourante = request.getSession(false);
                    out.println("Vous venez de vous connecter en tant que : <span class='nomAdministrateur'>" + sessionCourante.getAttribute("administrateurConnecte") + "</span>");
                }
                else if (request.getAttribute("confirmationDeconnexion") != null)
                {
                    out.println("Vous venez bien de vous deconnecter du blog");
                }
                else if (request.getAttribute("confirmationAjouterArticle") != null)
                {
                    HttpSession sessionCourante = request.getSession(false);
                    out.println("<span class='nomAdministrateur'>" +  sessionCourante.getAttribute("administrateurConnecte") + "</span>, votre article à bien été ajouté avec succès");
                }
                else if (request.getAttribute("confirmationModificationArticle") != null)
                {
                    out.println("Vous venez bien de modifier votre article");
                }
                else if (request.getAttribute("confirmationAjouterCommentaire") != null)
                {
                   out.println("Votre commentaire a bien été pris en compte, nous vous remercions");
                }
                else if (request.getAttribute("confirmationSuppressionArticle") != null)
                {
                   out.println("L'article a été supprimé");
                }
                else if (request.getAttribute("confirmationSuppressionCommentaire") != null)
                {
                   out.println("Le commentaire a été supprimé");
                }
        %>
        <br/>
        <br/>
        <br/>
        <form method="post" name="formRedirectionPagePrincipale" action="./">
                <input type="hidden" name="action" value="recharger" />
                <a class="lienNavigation" href="javascript:document.formRedirectionPagePrincipale.submit()">Retour vers la page principale</a>
            </form></tr>
    </article>
    </body>
</html>
