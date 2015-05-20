/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.DiscoDAO;
import edu.uniminuto.persistencia.Discopropietario;
import edu.uniminuto.persistencia.Persona;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "ProcesarVinilo", urlPatterns = {"/procesarvinilo"})
public class ProcesarVinilo extends HttpServlet {

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
        String url = "entrar.jsp";

        if (request.getSession().getAttribute("usuario") != null) {

            String accion = getParameter(request, "accion");
            DiscoDAO ddao = new DiscoDAO();

            long id = ((Persona) request.getSession().getAttribute("usuario")).getId();
            int vinilo = Integer.valueOf(getParameter(request, "disco"));
            
            if (accion.equals("borrar")) {

                int dpId = Integer.valueOf(getParameter(request, "id"));
                
                url = "disco.jsp?id=" + vinilo;
                if (!ddao.borrar(ddao.getDiscoPropietario(dpId))) {
                    url = "&error=Hubo un error al guadar";
                }
            } else {

                long precio = Long.valueOf(getParameter(request, "precio"));
                

                Discopropietario dp = new Discopropietario(ddao.getDisco(vinilo),
                        ddao.getPersona(id), precio, 0);

                url = "disco.jsp?id=" + vinilo;

                if (!ddao.guardar(dp)) {
                    url = url + "&error=Hubo un error al guadar";
                }
            }
        }
        response.sendRedirect(url);
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

    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }// </editor-fold>

}
