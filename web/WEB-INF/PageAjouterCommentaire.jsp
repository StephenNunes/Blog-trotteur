<%-- 
    Document   : AjouterCommentaire
    Created on : 7 mars 2014, 11:52:07
    Author     : stephen
    Page permettant à un utilisateur d'ajouter un commentaire
--%>

<%@page import="modele.DateFormate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog-Trotteur | Ajouter un commentaire à l'article</title>
        <style><%@include file="./style.css" %></style>
    </head>
    <body>
        <script>
        /*
        * Vérifie que le nom de l'auteur commence bien par une lettre minuscule ou majuscule
        */
        function verificationNomAuteur() 
        {
            var regExp = /^[a-z]{1,}.*$/i;
            if (!regExp.test(document.getElementById("auteurCommentaire").value))
            {
                alert("Le nom doit commencer par une lettre miniscule ou majuscule");
                return false;
            }
            return true;
        }
        </script>
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
                out.println("<p class='connecte'>Vous êtes actuellement connecté en tant que : <span class='nomAdministrateur'>" + sessionCourante.getAttribute("administrateurConnecte")+ "</span>");
            }
        %>
        <p class="titre">Ajout d'un nouveau commentaire</p>
        <form method="post" name="formulaireAjouterCommentaire" action="" onsubmit="return verificationNomAuteur()">
            <table>
                <tr>
                    <td><label>Titre de l'article correspondant &nbsp;</label></td>
                    <td><label>Nom de l'auteur de l'article &nbsp;</label></td>
                    <td><label>Date de l'article &nbsp;</label></td>
                </tr>
                
                <tr>
                    <td><%= request.getAttribute("titreArticle") %></td>
                    <td><%= request.getAttribute("auteurArticle") %></td>
                    <td><%= request.getAttribute("dateArticle") %></td>
                    <% out.println("<input type='hidden' name='idArticle' value='" + request.getAttribute("idArticle") + "' />"); %>
                </tr>
                <tr>
                    <td></td>
                    <td>Contenu de l'article à commenter</td>
                </tr>
             </table>
             <table>
                 <tr>
                    <td><%= request.getAttribute("contenuArticle") %></td>
                </tr>
             </table>
            <table>
                <tr>
                    <td><label>Nom de l'auteur du commentaire &nbsp;</label></td>
                    <td><label>Date du commentaire &nbsp;</label></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" id="auteurCommentaire" name="auteurCommentaire"/></td>
                    <td><label><% out.println(DateFormate.dateDuJourFormatAffichage()); %></label></td>
                    <td></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><label>Contenu du commentaire &nbsp;</label></td>
                </tr>
                <tr>
                    <td><textarea id="contenuCommentaire" name="contenuCommentaire"></textarea></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><input type="hidden" name="action" value="ajouterCommentaire" />  </td>
                    <td><input type="submit" value="Ajouter ce commentaire"</td>
                </tr>
            </table>
        </form>
    </article>
    </body>
</html>
