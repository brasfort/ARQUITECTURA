/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.InterpreteDAO;
import edu.uniminuto.persistencia.Interprete;
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
@WebServlet(name = "PostInterprete", urlPatterns = {"/procesarinterprete"})
public class PostInterprete extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PostInterprete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PostInterprete at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
//        processRequest(request, response);

        int ultimoId = Integer.valueOf(getParameter(request, "ultimoId"));

        InterpreteDAO interpreteDAO = new InterpreteDAO();

        List<Interprete> interpretes = interpreteDAO.getInterpretes(ultimoId);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            for (Interprete interprete : interpretes) {
                out.println("<li>");
                out.println("<p>");
                out.println(interprete.getNombre() + "<br />");
                out.println("<a href=\"" + request.getContextPath() + "/interprete.jsp?id=" + interprete.getId() + "\">ver detalles</a> | ");
                out.println("<a href=\"" + request.getContextPath() + "/finterprete.jsp?accion=editar&id=" + interprete.getId() + "\">actualizar</a> | ");
                out.println("<a href=\"" + request.getContextPath() + "/interprete.jsp?accion=borrar&id=" + interprete.getId() + "\">borrar</a>");
                out.println("</p>");
                out.println("</li>");
            }

        }

//        <li>
//            <p>
//                ${interprete.nombre}<br />
//                <a href="${pageContext.request.contextPath}/interprete.jsp?id=${interprete.id}">ver detalles</a> | 
//                <a href="${pageContext.request.contextPath}/finterprete.jsp?accion=editar&id=${interprete.id}">actualizar</a> |
//                <a href="${pageContext.request.contextPath}/interprete.jsp?accion=borrar&id=${interprete.id}">borrar</a>
//            </p>
//        </li>
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

        String url = "interprete.jsp?id=";
        String id = getParameter(request, "id");
        String accion = getParameter(request, "accion");
        InterpreteDAO idao = new InterpreteDAO();

        Interprete interprete = new Interprete();
        if (!id.isEmpty() && !id.equals("null")) {
            interprete = idao.getInterprete(Integer.valueOf(id));
        }

        if (accion != null && accion.equals("borrar")
                && !id.equals("null")) {

            idao = new InterpreteDAO();
            if (!idao.borrar(interprete)) {
                url = "interprete.jsp?accion=borrar&error="
                        + idao.getError() + "&id=" + interprete.getId();
            } else {
                url = "interpretes.jsp?mensaje=Registro eliminado";
            }
        } else {
            String nombre = getParameter(request, "nombre");

            interprete.setNombre(nombre);
            if (!idao.isValid(interprete) || !idao.guardar(interprete)) {
                url = "finterprete.jsp?&error="
                        + idao.getError() + "&id=" + interprete.getId();
            } else {
                url = url + interprete.getId();
            }
        }

        response.sendRedirect(url);
//        request.getRequestDispatcher(url + interprete.getId()).
//                forward(request, response);
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
