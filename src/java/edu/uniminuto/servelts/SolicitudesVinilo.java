/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.CompraDAO;
import edu.uniminuto.DAO.PedidoDAO;
import edu.uniminuto.persistencia.Pedido;
import edu.uniminuto.persistencia.Pedidodisco;
import edu.uniminuto.utils.EnviarCorreo;
import java.io.IOException;
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
@WebServlet(name = "SolicitudesVinilo", urlPatterns = {"/solicitud"})
public class SolicitudesVinilo extends HttpServlet {

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

                    java.text.SimpleDateFormat sdt
                            = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String accion = getParameter(request, "accion");
            int id = Integer.valueOf(getParameter(request, "id"));
            PedidoDAO pdao = new PedidoDAO();
            Pedidodisco pd = pdao.getPedidoDisco(id);

            if (accion.equals("enviar")) {

                try {
                    pd.setFechaenvio(sdt.parse(getParameter(request, "fecha")));
                } catch (Exception ex) {
                    pd.setFechaenvio(new java.util.Date());
                }
                pd.setEnviado(1);
            } else if (accion.equals("rechazar")) {
                pd.setRechazado(1);
                pd.setComentario(getParameter(request, "comentario"));
            }
            pdao.guardar(pd);

            EnviarCorreo ec = new EnviarCorreo();
            
            if (accion.equals("enviar")) {
                ec.enviarMensaje(pd.getPedido().getPersona().getCorreo(), "El disco ha sido confirmado", 
                        pd.getDiscopropietario().getPersona().getNombres() + " ha confirmado el envío del disco " + pd.getDiscopropietario().getDisco().getNombre() + ","
                                + "estima que lo enviará en " +sdt.format(pd.getFechaenvio()));
            } else {
                ec.enviarMensaje(pd.getPedido().getPersona().getCorreo(), "El vendedor del disco no quiso venderlo", 
                        pd.getDiscopropietario().getPersona().getNombres() + " ha confirmado el rechazo de venta del disco " + pd.getDiscopropietario().getDisco().getNombre() + ".");
            }
                    
                    
            if (accion.equals("enviar")) {
                
                
                pd.getDiscopropietario().setVendido(1);
                pdao.guardar(pd.getDiscopropietario());
                
                List<Pedidodisco> pedidos = pdao.getSolicitudes(id);

                for (Pedidodisco pedidodisco : pedidos) {
                    
                    pedidodisco.setRechazado(1);
                    pedidodisco.setComentario("Ya se vendió.");
                    pdao.guardar(pedidodisco);

                    boolean hacerCompra = true;
                    
                    
                    for (Pedidodisco pd2 : pedidodisco.getPedido().getPedidodiscos()) {
                        if (pd2.getEnviado() == 0
                                && pd2.getRechazado() == 0) {
                            hacerCompra = false;
                            break;
                        }
                    }

                    if (hacerCompra) {
                        CompraDAO cdao = new CompraDAO();
                        cdao.hacerCompra(pedidodisco.getPedido());
                    }

                }

            }

            Pedido p = pd.getPedido();

            boolean hacerCompra = true;
            for (Pedidodisco pedidodisco : p.getPedidodiscos()) {
                if (pedidodisco.getEnviado() == 0
                        && pedidodisco.getRechazado() == 0) {
                    hacerCompra = false;
                    break;
                }
            }

            if (hacerCompra) {
                CompraDAO cdao = new CompraDAO();
                cdao.hacerCompra(p);
            }
            url = "solicitudes.jsp";
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
