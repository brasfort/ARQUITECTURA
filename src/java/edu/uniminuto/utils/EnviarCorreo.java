/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniminuto.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EnviarCorreo {
    
    private final String USUARIO = "juancriollojuan";  // GMail user name (just the part before "@gmail.com")
    private final String CLAVE = "sinClave"; // GMail password

    public  EnviarCorreo() {
    }

    public void enviarMensaje(String para) {
        this.para = para;
        enviarMensaje();
    }

    public void enviarMensaje(String para, String asunto, String mensaje) {
        this.para = para;
        this.asunto = asunto;
        this.mensaje = mensaje;
        enviarMensaje();
    }
    
    
    public void enviarMensaje() {
        Properties props = System.getProperties();
        
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", USUARIO);
        props.put("mail.smtp.password", CLAVE);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(props);
        MimeMessage objetoMensaje = new MimeMessage(sesion);

        try {
            objetoMensaje.setFrom(new InternetAddress(USUARIO));
            
            InternetAddress direccion = new InternetAddress(para);
            objetoMensaje.addRecipient(Message.RecipientType.TO, direccion);
            

            objetoMensaje.setSubject(asunto);
            objetoMensaje.setText(mensaje);
            Transport transport = sesion.getTransport("smtp");
            transport.connect(host, de, CLAVE);
            transport.sendMessage(objetoMensaje, objetoMensaje.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    
    
    private String para;
    private String de ;
    private String host = "smtp.gmail.com";
    private String asunto;
    private String mensaje;
    
    /**
     * @return the para
     */
    public String getPara() {
        return para;
    }

    /**
     * @param para the para to set
     */
    public void setPara(String para) {
        this.para = para;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return de;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.de = from;
    }

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the asunto
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * @param asunto the asunto to set
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
