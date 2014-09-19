<%-- 
    Document   : PageConnexion
    Created on : 7 mars 2014, 11:51:46
    Author     : stephen
    Page permettant à un administrateur de se connecter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog-Trotteur | Connexion d'un administrateur</title>
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
      <p class="titre">Connexion d'un administrateur</p>
        <form method="post" name="formulaireConnexion" action="administrateur">
            <table>
                <tr>
                    <td><label>Identifiant (pseudonyme) &nbsp;</label></td>
                    <td><label>Mot de passe &nbsp;</label></td>
                </tr>
                <tr>
                    <td><input type="text" id="pseudonymeAdministrateur" name="pseudonymeAdministrateur"/></td>
                    <td><input type="password" id="motDePasseAdministrateur" name="motDePasseAdministrateur"</td>
                    <td><input type="submit" value="Se connecter"</td>
                    <input type="hidden" name="action" value="connexion" />
                </tr>
            </table>
        </form>
        <%  if (request.getAttribute("erreurConnexion") != null) 
            {
               out.println("Echec de la connexion, identifiant et/ou mot de passe invalide");
            }
        %>
    </article>
    </body>
</html>
