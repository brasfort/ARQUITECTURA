/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.CancionDAO;
import edu.uniminuto.persistencia.Cancion;
import edu.uniminuto.persistencia.Discocancion;
import edu.uniminuto.persistencia.Discopropietario;
import edu.uniminuto.utils.SongStockUtils;
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
@WebServlet(name = "PostCancion", urlPatterns = {"/procesarcancion"})
public class PostCancion extends HttpServlet {


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

        int ultimoId = Integer.valueOf(SongStockUtils.getParameter(request, "ultimoId"));
//        String letra = SongStockUtils.getParameter(request, "letra");

        CancionDAO cancionDAO = new CancionDAO();

        List<Cancion> canciones = cancionDAO.getCanciones(ultimoId);

        response.setContentType("text/html;charset=UTF-8");
//        java.util.Calendar cal = java.util.Calendar.getInstance();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            for (Cancion cancion : canciones) {
                out.println("<li>");

                out.println("<p>");
                out.println(cancion.getNombre() + "<br />");
                out.println(cancion.getAnhio() + "<br />");
                out.println("Duración: " + cancion.getDuracion() + "<br />");
                out.println("Calidad: " + cancion.getCalidad() + "<br />");
                out.println("Precio: " + cancion.getPrecio() + "<br />");
                out.println(cancion.getInterprete().getNombre() + "<br />");
//                out.println("<a href=\"fcancion.jsp?accion=editar&id="
//                        + cancion.getId() + "\">editar</a> | ");
//                out.println("<a href=\"cancion.jsp?accion=borrar&id="
//                        + cancion.getId() + "\">eliminar</a> | ");
                out.println("<a href=\"cancion.jsp?id="
                        + cancion.getId() + "\">detalle</a>");
                out.println("</p>");

                out.println("<form action=\"/SongStock/agregarcancion\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"nombre\" value=\"" + cancion.getNombre() + "\" />");
                out.println("<input type=\"hidden\" name=\"id\" value=\"" + cancion.getId() + "\" />");
                out.println("<input type=\"hidden\" name=\"precio\" value=\"" + cancion.getPrecio() + "\" />");
                out.println("<input type=\"submit\" value=\"agregar al carrito\" class=\"link\" />");
                out.println("</form>");

                
                if (!cancion.getDiscocancions().isEmpty()) {
                    out.println("<p>Presente en los siguientes discos: </p>");
                }
                for (Discocancion dc : cancion.getDiscocancions()) {
                    
                    
                    
                    
                    
                    for (Discopropietario dp : dc.getDisco().getDiscopropietarios()) {
                        out.println("<form class=\"inline\" action=\"/SongStock/agregardisco\" method=\"post\">");
                        out.println("<input type=\"hidden\" name=\"nombre\" value=\"" + dp.getDisco().getNombre() + "\" />");
                        out.println("<input type=\"hidden\" name=\"id\" value=\"" + dp.getId() + "\" />");
                        out.println("<input type=\"hidden\" name=\"precio\" value=\"" + dp.getPrecio() + "\" />");
                        out.println("<p>" + dp.getDisco().getNombre()  
                            + "<input type=\"submit\" value=\"agregar vinilo \" class=\"link\" />"
                            + "</p>");
                        out.println("</form>");
                    }
                    
                    
                    
                    

                }

                out.println("</li>");
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
    }// </editor-fold>

}
