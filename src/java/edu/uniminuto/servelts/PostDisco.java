/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.DiscoDAO;
import edu.uniminuto.persistencia.Disco;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "PostDisco", urlPatterns = {"/procesardisco"})
public class PostDisco extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int ultimoId = Integer.valueOf(getParameter(request, "ultimoId"));
        String letra = getParameter(request, "letra");

        DiscoDAO discoDAO = new DiscoDAO();

        List<Disco> discos = discoDAO.getDiscos(letra, ultimoId);

        response.setContentType("text/html;charset=UTF-8");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            for (Disco disco : discos) {
                
                out.println("<div class=\"li\">");
//                out.println("<li>");
                out.print("<image src=\""+request.getContextPath()+"/"+disco.getImagen()+"\" class=\"total\" />");
                out.print("<div class=\"absolute over\">");
                cal.setTime(disco.getAnhio());
                out.print("<h2>"+disco.getNombre()+" <em>("+cal.get(java.util.Calendar.YEAR)+")</em></h2>");
                out.print("<p>");
                out.print("Genero: "+disco.getGenero().getNombre()+"<br />");
//                out.print("Precio: "+disco.getPrecio()+"<br />");
                out.print("de: <a href=\""+request.getContextPath()+"/interprete.jsp?id="+disco.getInterprete().getId()+"\">"+disco.getInterprete().getNombre()+"</a><br />");
                
                
                
//                out.print("<a href=\""+request.getContextPath()+"/fdisco.jsp?accion=editar&id="+disco.getId()+"\">editar</a> | ");
//                out.print("<a href=\""+request.getContextPath()+"/disco.jsp?accion=borrar&id="+disco.getId()+"\">borrar</a> | ");
                out.print("<a href=\""+request.getContextPath()+"/disco.jsp?id="+disco.getId()+"\">detalles</a>");
                out.print("</p>");
                out.print("</div>");
                out.println("</div>");
//                out.println("</li>");
            }
        }
        
            
        
        
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }


    private String getParameter(HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }
// </editor-fold>

}
