<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="styles/styles.css" />
</head>
<%
    
    boolean hayElementos = false;
    edu.uniminuto.carro.ListaElementos le = new edu.uniminuto.carro.ListaElementos();
    if (session.getAttribute("carro") != null) {
        hayElementos = true;
        le = (edu.uniminuto.carro.ListaElementos)session.getAttribute("carro");
    }
    
    
    int id = (request.getParameter("id") != null) ? Integer.valueOf(request.getParameter("id")) : -1;
    String accion = (request.getParameter("accion") != null) ? request.getParameter("accion") : "";
    
    if (accion.equals("borrar") && id > 0 && id < le.getElementos().size() ) {
        le.getElementos().remove(id);
    }
    
    if (hayElementos) {
        
        request.setAttribute("listado", le.getElementos());
        request.setAttribute("cuenta", le.getElementos().size());
        request.setAttribute("total", le.getTotal());
    } 
    request.setAttribute("hayElementos", hayElementos);
%>
<body>
    <div id="page">
        <div id="header">
            <div class="band">
                <h1 class="title">
                    Song <span class="special">| Stock</span>
                </h1>                
                <div id="car">
                    <a href="carro.jsp" id="carlink">
                    <c:if test="${!hayElementos}">
                        <img src="images/carro.png"  class="vertical" />
                        <div id="car-info"><p>vacío</p></div>
                    </c:if>
                    <c:if test="${hayElementos}">
                        <img src="images/carro-lleno.png"  class="vertical" />
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
                <c:if test="${hayElementos}">
                <table>
                    <caption>Carro de compra</caption>
                    <thead>
                        <tr>
                            <th>Item</th>
                            <th>Precio</th>
                            <th>eliminar</th>
                            <th>Subtotal</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th colspan="3">Total: </th>
                            <th>${total}</th>
                        </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach var="item" items="${listado}" varStatus="loop">
                        <tr>
                            <td>${item.descripcion}
                            <c:if test="${!empty item.discoId}">(vinilo)</c:if>
                            <c:if test="${!empty item.cancionId}">(cancion)</c:if>
                            </td>
                            <td>${item.precio}</td>
                            <td><a href="carro.jsp?accion=borrar&id=${loop.index}">borrar</a></td>
                            <td>${item.precio}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <form action="hacerpedido" method="post">
                    <input type="submit" class="link" value="hacer pedido">
                </form>
                </c:if>
                <c:if test="${!hayElementos}">
                <img src="images/carro.png" alt="carro vacío" />
                <p><strong>No hay elementos en el carro de compra.</strong></p>
                </c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>
