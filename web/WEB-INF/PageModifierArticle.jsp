<%-- 
    Document   : PageModifierArticle
    Created on : 7 mars 2014, 11:52:29
    Author     : stephen
    Page permettant de modifier un article du blog
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog-Trotteur | Modifier article</title>
        <style><%@include file="./style.css" %></style>
    </head>
    <body>
        <script>
        /*
         * Vérifie que la chaîne passée en paramètre à sa 1er lettre en majuscule
         */
        function verificationTitreArticle() 
        {
            var regExp = /^[A-Z]{1,}.*$/;
            if (!regExp.test(document.getElementById("titreArticle").value))
            {
                alert("Le titre doit commencer par une lettre majuscule");
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
                    &nbsp;&nbsp;&nbsp;<a class="lienNavigation" href="javascript:document.formNavPageAjouterArticle.submit()">Ajouter un autre article</a>&nbsp;&nbsp;&nbsp;
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
            <p class="titre">Modification d'un article</p>
            <form method="post" name="formulaireAjouterArticle" action="" onsubmit="return verificationTitreArticle()">
                <table>
                    <tr>
                        <td><label>Titre de l'article &nbsp;</label></td>
                        <td><label>Nom de l'auteur &nbsp;</label></td>
                        <td><label>Date de l'article &nbsp;</label></td>
                    </tr>
                    <tr><!-- récupérer le titre et le mettre dans placeholder -->
                        <td><% out.println("<input type='text' value='" + request.getAttribute("titreArticleAModifier") + "' id='titreArticle' name='titreArticle'/>"); %></td>
                        <td><%= request.getAttribute("auteurArticleAModifier") %></td>
                        <% out.println("<input type='hidden' name='auteurArticle' value='" + request.getAttribute("auteurArticleAModifier") + "' />"); %>
                        <td><%= request.getAttribute("dateArticleAModifier") %></td>
                        <% out.println("<input type='hidden' name='dateArticle' value='" + request.getAttribute("dateArticleAModifier") + "' />"); %>
                    </tr>
                </table>
                <table>
                    <tr>
                        <td></td>
                        <td><label>Contenu de l'article &nbsp;</label></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <% out.println("<textarea id='contenuArticle' name='contenuArticle'>" + request.getAttribute("contenuArticleAModifier") +"</textarea>"); %>
                        </td>
                    </tr>
                </table>
                <table>
                    <tr>
                        <% out.println("<input type='hidden' name='idArticle' value='" + request.getAttribute("idArticleAModifier") + "' />"); %>
                        <td><input type="hidden" name="action" value="modifierArticle" />  </td>
                        <td><input type="submit" value="Modifier cette article"</td>
                    </tr>
                </table>
            </form>
        </article>
    </body>
</html>
