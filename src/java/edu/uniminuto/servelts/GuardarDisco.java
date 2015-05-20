/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.servelts;

import edu.uniminuto.DAO.DiscoDAO;
import edu.uniminuto.persistencia.Disco;
import edu.uniminuto.persistencia.Discopropietario;
import edu.uniminuto.persistencia.Persona;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Jhon Gómez
 */
@WebServlet(name = "GuardarDisco", urlPatterns = {"/guardardisco"})
public class GuardarDisco extends HttpServlet {
    public static final String RUTA = "H:\\NetbeansProjects\\SongStock\\web\\images\\";

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

        String nombre = "";
        long precio = 0;
        int anhio = 0;
        short genero = 1;
        int interprete = 1
                ;
        
        
        
//        String nombre = getParameter(request, "nombre");
//        long precio = Long.valueOf(getParameter(request, "precio"));
//        int anhio = Integer.valueOf(getParameter(request, "anhio"));
//
//        int genero = Integer.valueOf(getParameter(request, "genero"));
//        int interprete = Integer.valueOf(getParameter(request, "interprete"));
        
            String url = "";
        try {

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            String imagen = "images/";

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory(1024 * 4, new File("c;//tmp"));

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
                    if (item.getFieldName().equals("nombre")) {
                        nombre =item.getString();
                    } else if (item.getFieldName().equals("anhio")) {
                        anhio =Integer.valueOf(item.getString());
                    } else if (item.getFieldName().equals("genero")) {
                        genero =Short.valueOf(item.getString());
                    } else if (item.getFieldName().equals("interprete")) {
                        interprete =Integer.valueOf(item.getString());
                    } else if (item.getFieldName().equals("precio")) {
                        precio =Long.valueOf(item.getString());
                    } 
                    
                } else {
                    String fieldName = item.getFieldName();
                    String fileName = item.getName();
                    String contentType = item.getContentType();
                    boolean isInMemory = item.isInMemory();
                    long sizeInBytes = item.getSize();

                    
//                    InputStream uploadedStream = item.getInputStream();
//                    uploadedStream.close();
//                    InputStream uploadedStream = item.getInputStream();
//                    uploadedStream.close();
                    imagen = imagen + fileName;
                    File uploadedFile = new File(RUTA+fileName);
                    
                    item.write(uploadedFile);
//                    } else {
//                        
//                    }
                }
            }
            
            java.util.Calendar cl = java.util.Calendar.getInstance();
            cl.set(anhio, 0, 0, 0, 0, 0);
            
            DiscoDAO ddao = new DiscoDAO();
            Disco disco = new Disco(ddao.getGenero(genero), ddao.getInterprete(interprete), nombre, imagen, cl.getTime());
            if (ddao.guardar(disco)) {
            
                if (disco.getId() != null) {

                    Discopropietario dp = new Discopropietario(disco, (Persona)request.getSession().getAttribute("usuario"), precio, 0);
                    ddao.guardar(dp);
                    url = "disco.jsp?id=" + disco.getId();
                }
            } else {
                url = "fdisco.jsp?nombre=" + nombre +
                "&precio=" + precio +
                "&anhio=" + anhio +
                "&genero=" + genero +
                "&interprete=" + interprete;
            }

        } catch (FileUploadException ex) {
            Logger.getLogger(GuardarDisco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GuardarDisco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        response.sendRedirect(url);

    }

    private String getParameter(
            HttpServletRequest request, String parametername) {
        String ret = "";
        if (request.getAttribute(parametername) != null) {
            ret = request.getAttribute(parametername).toString();
        }
        return ret;
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
