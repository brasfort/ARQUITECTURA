/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.CancionDAO;
import edu.uniminuto.persistencia.Cancion;
import edu.uniminuto.persistencia.Persona;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.persistence.expressions.ExpressionOperator;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "AdministrarCancion", urlPatterns = {"/admcancion"})
public class AdministrarCancion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

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
        processRequest(request, response);
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
            Cancion cancion = new Cancion();
            CancionDAO cdao = new CancionDAO();

            java.text.SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

            if (accion.equals("editar")) {
                long id = Long.valueOf(getParameter(request, "id"));
                cancion = cdao.getCancion(id);
            }

            if (!getParameter(request, "nombre").isEmpty()) {
                cancion.setNombre(getParameter(request, "nombre"));
            }

            if (!getParameter(request, "anhio").isEmpty()) {
                cancion.setAnhio(getParameter(request, "anhio"));
            }

            if (!getParameter(request, "duracion").isEmpty()) {

                try {
                    cancion.setDuracion(sdf.parse(getParameter(request, "duracion")));
                } catch (Exception ex) {
                    java.util.Calendar c = java.util.Calendar.getInstance();
                    c.set(java.util.Calendar.HOUR_OF_DAY, 0);
                    c.set(java.util.Calendar.MINUTE, 4);

                    cancion.setDuracion(c.getTime());
                }
            }

            if (!getParameter(request, "peso").isEmpty()) {
                cancion.setPeso(getParameter(request, "peso"));
            }

            if (!getParameter(request, "calidad").isEmpty()) {

                cancion.setCalidad(getParameter(request, "calidad"));
            }

            if (!getParameter(request, "precio").isEmpty()) {
                cancion.setPrecio(Integer.valueOf(getParameter(request, "precio")));
            }

            if (!getParameter(request, "interprete").isEmpty()) {

                cancion.setInterprete(cdao.getInterprete(
                        Integer.valueOf(getParameter(request, "interprete"))));
            }

            if (cdao.guardar(cancion)) {
                url = "cancion.jsp?id=" + cancion.getId();
            } else {
                url = url + "?nombre=" + getParameter(request, "cancion")
                        + "&anhio=" + getParameter(request, "anhio")
                        + "&peso=" + getParameter(request, "peso")
                        + "&calidad=" + getParameter(request, "calidad")
                        + "&precio=" + getParameter(request, "precio")
                        + "&interprete=" + getParameter(request, "interprete")
                        + "&error=Hubo un fallo al momento de guardar los datos";

                if (accion.equals("editar")) {
                    url = url + "&id=" + cancion.getId();
                }

                try {
                    url = url + "&duracion=" + sdf.parse(getParameter(request, "duracion"));
                } catch (Exception ex) {
                    
                    java.util.Calendar c = java.util.Calendar.getInstance();
                    c.set(java.util.Calendar.HOUR_OF_DAY, 0);
                    c.set(java.util.Calendar.MINUTE, 4);

                    url = url + "&duracion=" + sdf.format(c.getTime());
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
