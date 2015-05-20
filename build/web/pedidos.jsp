<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    edu.uniminuto.DAO.PedidoDAO pdao = 
            new edu.uniminuto.DAO.PedidoDAO();
    
    java.util.List<edu.uniminuto.persistencia.Pedido> pedidos = 
            new java.util.ArrayList<edu.uniminuto.persistencia.Pedido>();
    
    if (request.getSession().getAttribute("usuario") != null) {
        pedidos = pdao.getPedidos(((edu.uniminuto.persistencia.Persona)request.getSession().getAttribute("usuario")).getId());
    }
    request.setAttribute("pedidos", pedidos);
%>
<c:if test="${empty pedidos}">
    <h1>No hay pedidos</h1>
</c:if>
<c:if test="${!empty pedidos}">
<ul>
<c:forEach var="pedido" items="${pedidos}">
    <li style="border-bottom: 1px dotted gray;margin-bottom: 2em;">
        <p>
            <strong>Fecha de pedido: </strong> ${pedido.fechapedido}<br />
            <strong>Total: </strong> ${pedido.total}
        </p>
        
        <p>
        <c:if test="${!empty pedido.pedidocancions}">
        <c:forEach var="item" items="${pedido.pedidocancions}">
            <strong>Canción: </strong> ${item.cancion.nombre} (${item.cancion.precio})
            <br />
        </c:forEach>
        </c:if>
        <c:if test="${!empty pedido.pedidodiscos}">
        <c:forEach var="item" items="${pedido.pedidodiscos}">
            <strong>Vinilo: </strong> ${item.discopropietario.disco.nombre} (${item.discopropietario.precio})
            
            <a href="elliminarpedido?id=${item.id}">cancelar</a>
            <a href="elliminarpedido?id=${item.id}">acusar de recibido</a>
            <br />
        </c:forEach>
        </c:if>
        </p>
        
        <p><a href="elliminarpedido?id=${pedido.id}">cancelar</a></p>
    </li>
</c:forEach>
</ul>
</c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>