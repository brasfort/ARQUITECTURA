<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    edu.uniminuto.DAO.CompraDAO cdao = 
            new edu.uniminuto.DAO.CompraDAO();
    
    java.util.List<edu.uniminuto.persistencia.Compra> compras = 
            new java.util.ArrayList<edu.uniminuto.persistencia.Compra>();
    
    if (request.getSession().getAttribute("usuario") != null) {
        compras = cdao.getCompras(((edu.uniminuto.persistencia.Persona)request.getSession().getAttribute("usuario")).getId());
    }
    
    request.setAttribute("compras", compras);
%>

<c:if test="${empty compras}">
    <h1>Aún no has realizado compras</h1>
</c:if>
<c:if test="${!empty compras}">
<ul>
<c:forEach items="${compras}" var="compra">
    <li>
        <p>
            <strong>Total: </strong>$ ${compra.total}<br />
            <strong>Fecha: </strong>${compra.fecha}<br />
        </p>
        <p>
        <c:forEach items="${compra.compracancions}" var="item">
            ${item.cancion.nombre} <a href="cancion.jsp?id=${item.cancion.id}">detalle</a> (cancion)<br />
        </c:forEach>
        <c:forEach items="${compra.compradiscos}" var="item">
            ${item.disco.nombre} <a href="disco.jsp?id=${item.disco.id}">detalle</a>  (vinilo) <br />
        </c:forEach>
        </p>
        <p>${compra.observaciones}</p>
    </li>
</c:forEach>
</ul>
</c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>