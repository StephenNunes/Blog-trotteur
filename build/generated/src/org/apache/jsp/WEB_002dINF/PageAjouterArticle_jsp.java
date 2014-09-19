package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class PageAjouterArticle_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/WEB-INF/./style.css");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Ajoute un article</title>\n");
      out.write("        <style>");
      out.write("/* \n");
      out.write("    Created on : 10 mars 2014, 10:19:08\n");
      out.write("    Author     : stephen\n");
      out.write("*/\n");
      out.write("\n");
      out.write("body\n");
      out.write("{\n");
      out.write("    font-size: 100%;\n");
      out.write("    font-family: Segoe UI;\n");
      out.write("}\n");
      out.write("\n");
      out.write("nav \n");
      out.write("{\n");
      out.write("    width: 100%;\n");
      out.write("    text-align: center;\n");
      out.write("}\n");
      out.write("\n");
      out.write("nav ul\n");
      out.write("{\n");
      out.write("    list-style: none;\n");
      out.write("}\n");
      out.write("\n");
      out.write("nav ul li \n");
      out.write("{\n");
      out.write("    display: inline;\n");
      out.write("    padding: 10px;\n");
      out.write("}\n");
      out.write("\n");
      out.write("nav ul li a\n");
      out.write("{\n");
      out.write("    font-size: 120%;\n");
      out.write("    color: #000000;\n");
      out.write("    text-decoration: none;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <nav>\n");
      out.write("        <ul>\n");
      out.write("            <form method=\"post\" name=\"formulaireNavigation\" action=\"./frontcontroller\">\n");
      out.write("                <li><a href=\"javascript:document.formulaireNavigation.submit()\">Page précédente</a></li>\n");
      out.write("                <li><a href=\"javascript:document.formulaireNavigation.submit()\">Page suivante</a></li>\n");
      out.write("                <li><a href=\"javascript:document.formulaireNavigation.submit()\">Page principale</a></li>\n");
      out.write("                <li><a href=\"javascript:document.formulaireNavigation.submit()\">Ajouter un article</a></li>\n");
      out.write("                <li><a href=\"javascript:document.formulaireNavigation.submit()\">Rechercher</a></li>\n");
      out.write("                <li><input type=\"date\" id=\"recherche\" name=\"recherche\"/></li>\n");
      out.write("                <li><a href=\"javascript:document.formulaireNavigation.submit()\">Connexion</a></li>\n");
      out.write("                <li><a href=\"javascript:document.formulaireNavigation.submit()\">Déconnexion</a></li>\n");
      out.write("            </form>\n");
      out.write("        </ul>\n");
      out.write("    </nav>\n");
      out.write("    <article>\n");
      out.write("        <form method=\"post\" name=\"formulaireAjouterArticle\" action=\"\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td><label value=\"Titre de l'article\" /><input type=\"date\" id=\"recherche\" name=\"recherche\"/></td>\n");
      out.write("                    <td></td>\n");
      out.write("                    <td></td>\n");
      out.write("                </tr>\n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("    </article>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
