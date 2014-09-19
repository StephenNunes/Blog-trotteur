/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Classe permettant de formater les classes selon le besoin (soit pour un utilisateur soit pour la base de données MySQL)
 * @author stephen
 */
public class DateFormate 
{
   /** Méthode permettant de formater la date du jour à un format d'affichage français
    * @return Date formatée */
   public static String dateDuJourFormatAffichage()
   {
       Locale locale = Locale.getDefault();
       Date dateActuelle = new Date();
       SimpleDateFormat monFormatDeDate = new SimpleDateFormat("dd/MM/yyyy");
       String dateFormate = monFormatDeDate.format(dateActuelle);
       return dateFormate;
   }
   
   /** Méthode permettant de formater la date du jour à un format permettant l'insertion dans une base de données MySQL
    * @return Date formatée */
   public static String dateDuJourFormatMySQL()
   {
      Locale locale = Locale.getDefault();
      Date dateActuelle = new Date();
      SimpleDateFormat monFormatDeDate = new SimpleDateFormat("yyyy-MM-dd");
      String dateFormate = monFormatDeDate.format(dateActuelle);
      return dateFormate;
   }
}
