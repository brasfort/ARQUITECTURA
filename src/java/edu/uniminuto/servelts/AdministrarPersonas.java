/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.PersonaDAO;
import edu.uniminuto.DAO.RecopilacionDAO;
import edu.uniminuto.persistencia.Persona;
import edu.uniminuto.persistencia.Recopilacion;
import edu.uniminuto.persistencia.Rol;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "AdministrarPersonas", urlPatterns = {"/administrarpersonas"})
public class AdministrarPersonas extends HttpServlet {

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
        String url = "registro.jsp";
        Persona persona = new Persona();

        boolean logeado = request.getSession().getAttribute("usuario") != null;
        if (logeado) {
            persona = (Persona) request.getSession().getAttribute("usuario");
        }

        java.text.SimpleDateFormat sdt = new java.text.SimpleDateFormat("yyyy-MM-dd");

        String nombres = getParameter(request, "nombres");
        String apellidos = getParameter(request, "apellidos");
        Date nacimiento = new Date(0);
        try {
            nacimiento = sdt.parse(getParameter(request, "nacimiento"));
        } catch (Exception ex) {

        }
        String correo = getParameter(request, "correo");
        String clave = getParameter(request, "clave");

        if (!nombres.isEmpty()) {
            persona.setNombres(nombres);
        }
        if (!apellidos.isEmpty()) {
            persona.setApellidos(apellidos);
        }
        if (nacimiento.getTime() != (new Date()).getTime()) {
            persona.setNacimiento(nacimiento);
        }
        if (!correo.isEmpty()) {
            persona.setCorreo(correo);
        }
        if (!clave.isEmpty()) {
            persona.setClave(clave);
        }

        PersonaDAO pdao = new PersonaDAO();

        if (!logeado) {
            String rol = getParameter(request, "rol");
            
            int rolId = 1;
            if (rol.equals("vendedor")) {
                rolId = 2;
            }
            persona.setRol(pdao.getRol(rolId));
        }
        
        
        if (pdao.guardar(persona)) {
            if (logeado) {
                url = "index.jsp";
            } else {
                url = "entrar.jsp?correo=" + correo;
            }
        } else {
            url = url + "?nombres=" + nombres
                    + "&apellidos=" + apellidos
                    + "&nacimiento=" + sdt.format(nacimiento)
                    + "&correo=" + correo
                    + "&error=Hubo un fallo al momento de guardar los datos";
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
