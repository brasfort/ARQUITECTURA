/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.uniminuto.servelts;

import edu.uniminuto.DAO.PersonaDAO;
import edu.uniminuto.persistencia.Persona;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jhon
 */
@WebServlet(name = "IniciodeSesion", urlPatterns = {"/entrar"})
public class IniciodeSesion extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
        
//        String destino = "index.jsp";
//        String correo = request.getParameter("correo");
//        String clave = request.getParameter("clave");
//        
//        PersonaJpaController controller = new PersonaJpaController(
//                HibernateUtils.getEntityManagerFactory());
//        
//        Persona persona = controller.iniciarSesion(correo, clave);
//        if (persona == null) {
//            destino = "entrar.jsp?correo="+correo+"&error=Datos erroneos";
//        }
//        
//        response.sendRedirect(destino);
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
        
        String correo = getParameter(request, "correo");
        String clave = getParameter(request, "clave");
        
        if (correo.isEmpty()){
            
            response.sendRedirect("entrar.jsp?correo="+correo+"&error=El correo no puede estar en blanco");
        } 
        
        if (clave.isEmpty()) {
            response.sendRedirect("entrar.jsp?correo="+correo+"&error=La clave no puede estar en blanco");
        }
        
        PersonaDAO pdao = new PersonaDAO();
        
        Persona usuario = pdao.getPersona(correo, clave);
        if (usuario != null && usuario.getId() != null) {
            
//            try {
//                request.getSession().setAttribute("usuario", usuario.clone());
//            }catch(Exception ex) {
                request.getSession().setAttribute("usuario", usuario);
//            }
            response.sendRedirect("canciones.jsp");
        } else {
            response.sendRedirect("entrar.jsp?correo="+correo+"&error=Los datos no coinciden, por favor, ingrese un correo y una clave validos");
        }
        
        
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
