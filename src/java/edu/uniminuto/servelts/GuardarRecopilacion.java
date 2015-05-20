/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.RecopilacionDAO;
import edu.uniminuto.persistencia.Persona;
import edu.uniminuto.persistencia.Recopilacion;
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
@WebServlet(name = "GuardarRecopilacion", urlPatterns = {"/guardarrecopilacion"})
public class GuardarRecopilacion extends HttpServlet {

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

        String url = "recopilacion.jsp";
        if (request.getSession().getAttribute("usuario") == null) {
            url = "entrar.jsp?error=Debe haber iniciado sesion";
        } else {

            Persona usuario = (Persona) request.getSession().getAttribute("usuario");
            
            String nombre = getParameter(request, "nombre");
            String accion = getParameter(request, "accion");
            int publica = (request.getParameter("publica") != null) ? 1 : 0;

            Recopilacion reco;
            RecopilacionDAO rdao = new RecopilacionDAO();

            if (accion.equals("crear")) {
                reco = new Recopilacion(usuario, nombre, new java.util.Date(), publica);
            } else {
                int id = Integer.valueOf(getParameter(request, "id"));
                reco = rdao.getRecopilacion(id);

                reco.setNombre(nombre);
                reco.setPublica(publica);
            }

            if (rdao.guardar(reco)) {
                url = "recopilacion.jsp?id=" + reco.getId();
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

    private String getParameter(HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getParameter(parametername) != null) {
            ret = request.getParameter(parametername);
        }
        return ret;
    }// </editor-fold>

}
