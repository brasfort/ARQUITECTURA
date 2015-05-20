<%@page import="edu.uniminuto.carro.Elemento"%>
<%@page import="edu.uniminuto.carro.ListaElementos"%>
<%@page import="org.apache.taglibs.standard.functions.Functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="es" xml:lang="es">
<head>
    <title>Song Stock</title>
    <meta charset="UTF-8" />
    <meta http-equiv="content-type" />
    <meta content="text/html" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <c:set var="baseURL" value="${fn:replace(pageContext.request.requestURI, pageContext.request.requestURI, pageContext.request.contextPath)}" />
    <link rel="stylesheet" type="text/css" href="${baseURL}/styles/styles.css" />
</head>
<%
    
    boolean esInvitado = request.getSession().getAttribute("usuario") == null;
    request.setAttribute("esInvitado", esInvitado);
    
    int rol = -1;
    if (!esInvitado) {
        rol = ((edu.uniminuto.persistencia.Persona)request.getSession().
                getAttribute("usuario")).getRol().getId();
    }
    request.setAttribute("rol", rol);
        
    
    boolean hayElementos = false;
    edu.uniminuto.carro.ListaElementos le = new ListaElementos();
    if (session.getAttribute("carro") != null) {
        hayElementos = true;
        le = (ListaElementos)session.getAttribute("carro");
        
        request.setAttribute("listado", le.getElementos());
        request.setAttribute("cuenta", le.getElementos().size());
        request.setAttribute("total", le.getTotal());
    } 
//        else {
//        
//        Elemento elemento = new Elemento();
//        elemento.setCantidad(4);
//        elemento.setPrecio(3000);
//        
//        le.getElementos().add(elemento);
//        elemento = new Elemento();
//        elemento.setCantidad(1);
//        elemento.setPrecio(10000);
//        
//        le.getElementos().add(elemento);
//        
//        session.setAttribute("carro", le);
//    }
    request.setAttribute("hayElementos", hayElementos);
    
    request.setAttribute("dato", (request.getSession().getAttribute("usuario") != null) ? "Bienvenido: " + 
            ((edu.uniminuto.persistencia.Persona)request.getSession().getAttribute("usuario")).getNombres() : "");
    
    
%>
<body>
    <div id="page">
        <div id="header">
            <div class="band">
                <h1 class="title">
                    Song <span class="special">| Stock</span>
                </h1>
                <p>${dato}</p>
                <div id="car">
                    <a href="${baseURL}/carro.jsp" id="carlink">
                    <c:if test="${!hayElementos}">
                        <img src="${pageContext.request.contextPath}/images/carro.png"  class="vertical" />
                        <div id="car-info"><p>vacío</p></div>
                    </c:if>
                    <c:if test="${hayElementos}">
                        <img src="${pageContext.request.contextPath}/images/carro-lleno.png"  class="vertical" />
                        <div id="car-info">
                            <p>
                                ${cuenta} elemento(s)<br />
                                Total: $${total}                                
                            </p>
                        </div>
                    </c:if>
                    </a>
                    
                </div>
                
            </div>
        </div><!-- end header -->
        <div id="content">
            <div id="main">