/* 
 * Les fonctions de vérification de formulaires en javascript
 */

/*
 * Vérifie que l'argument comment bien par une lettre
 */
function verificationNomAuteur() 
{
    var regExp = /^[a-z]{1,}.*$/i;
    if (!regExp.test("aaa"))
    {
        alert("Le nom doit commencer par une lettre miniscule ou majuscule");
        return false;
    }
    return true;
}

/*
 * Vérifie que la chaîne passée en paramètre à sa 1er lettre en majuscule
 */
function verificationTitreArticle() 
{
    alert("la");
    var regExp = /^[A-Z]{1,}.*$/;
    if (!regExp.test("aaa"))
    {
        alert("Le titre doit commencer par une lettre majuscule");
        return false;
    }
    return true;
}

