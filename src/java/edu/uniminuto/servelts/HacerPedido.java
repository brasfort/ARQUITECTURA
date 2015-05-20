/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.CancionDAO;
import edu.uniminuto.DAO.CompraDAO;
import edu.uniminuto.DAO.PedidoDAO;
import edu.uniminuto.carro.Elemento;
import edu.uniminuto.carro.ListaElementos;
import edu.uniminuto.persistencia.Cancion;
import edu.uniminuto.persistencia.Compra;
import edu.uniminuto.persistencia.Compracancion;
import edu.uniminuto.persistencia.Discopropietario;
import edu.uniminuto.persistencia.Pedido;
import edu.uniminuto.persistencia.Pedidocancion;
import edu.uniminuto.persistencia.Pedidodisco;
import edu.uniminuto.persistencia.Persona;
import edu.uniminuto.utils.EnviarCorreo;
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
@WebServlet(name = "HacerPedido", urlPatterns = {"/hacerpedido"})
public class HacerPedido extends HttpServlet {

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

        String url = "entrar.jsp?error=Para poder hacer el pedido debe haber iniciado sesion";
        if (request.getSession().getAttribute("usuario") != null) {

            Persona usuario = (Persona) request.getSession().getAttribute("usuario");

            ListaElementos le = new ListaElementos();
            if (request.getSession().getAttribute("carro") != null) {
                le = (ListaElementos) request.getSession().getAttribute("carro");
            }

            if (!le.isEmpty()) {

                if (!le.hayDiscos()) {

                    CancionDAO candao = new CancionDAO();
                    CompraDAO cdao = new CompraDAO();

                    Cancion cancion;
                    Compra c = new Compra(usuario, new java.util.Date(), le.getTotal());
                    cdao.guardar(c);

                    Compracancion cc;

                    for (Elemento item : le.getElementos()) {
                        cancion = candao.getCancion(item.getCancionId());
                        cc = new Compracancion(cancion, c, cancion.getPrecio());
                        cdao.guardar(cc);
                    }

                } else {

                    PedidoDAO pdao = new PedidoDAO();
                    Pedido pedido = new Pedido(usuario, new java.util.Date(),
                            Integer.valueOf(le.getTotal()).longValue(), 0, null, null);
                    pdao.guardar(pedido);

                    Pedidocancion pcancion;
                    Pedidodisco pdisco;
                    Cancion cancion;
                    Discopropietario dpro;

                    CancionDAO cdao = new CancionDAO();

                    for (Elemento elemento : le.getElementos()) {
                        if (elemento.getCancionId() != null) {
                            cancion = cdao.getCancion(elemento.getCancionId());

                            pcancion = new Pedidocancion(cancion, pedido);
                            pdao.guardar(pcancion);

                        } else if (elemento.getDiscoId() != null) {

                            dpro = pdao.getDiscopropietario(elemento.getDiscoId());

                            pdisco = new Pedidodisco(dpro, pedido, 0, 0);
                            pdao.guardar(pdisco);
                            
                            EnviarCorreo ec = new EnviarCorreo();

                            ec.enviarMensaje(dpro.getPersona().getCorreo(), "SongStock! se ha hecho un pedido", 
                                    "Se ha solicitado el disco " + dpro.getDisco().getNombre() + "<br />" +
                                            "Lo solicitó " + usuario.getNombres());
                            
//                           

                        }
                    }
                }
                request.getSession().setAttribute("carro", null);
                url = "canciones.jsp";
            } else {
                url = "carro.jsp";
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
    }// </editor-fold>

}
