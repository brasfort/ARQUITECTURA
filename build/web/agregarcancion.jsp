<%@page import="edu.uniminuto.persistencia.Discocancion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    edu.uniminuto.DAO.CancionDAO cdao
            = new edu.uniminuto.DAO.CancionDAO();

    java.util.List<edu.uniminuto.persistencia.Disco> discos
            = new java.util.ArrayList<edu.uniminuto.persistencia.Disco>();

    java.util.List<edu.uniminuto.persistencia.Cancion> canciones = null;
    
    if (request.getParameter("accion") != null) {
        if (request.getParameter("accion").equals("agregar")) {

            long idCancion = Long.valueOf(request.getParameter("cancion"));
            int vinilo = Integer.valueOf(request.getParameter("disco"));

            Discocancion dc = new Discocancion(cdao.getCancion(idCancion), cdao.getDisco(vinilo));

            cdao.guardar(dc);
        }

    }

    if (request.getParameter("qu") != null) {
        canciones = cdao.getCanciones(request.getParameter("qu"));
        edu.uniminuto.persistencia.Persona p;
        if (request.getSession().getAttribute("usuario") != null) {

            discos = cdao.getListadeDiscos(((edu.uniminuto.persistencia.Persona) request.getSession().getAttribute("usuario")).getId());
        }
    }

    request.setAttribute("mensaje", request.getParameter("mensaje"));
    request.setAttribute("qu", request.getParameter("qu"));
    request.setAttribute("canciones", canciones);
    request.setAttribute("discos", discos);
    request.setAttribute("relacionado", false);
%>
<form action="agregarcancion.jsp">
    <label>Buscar canciones: </label>
    <input type="text" name="qu" value="${qu}"/><br />
    <input type="submit" value="buscar" />
</form>

<ul>
    <c:forEach items="${canciones}" var="cancion">
        <li>
            ${cancion.nombre} <a href="cancion.jsp?id=${cancion.id}">detalles</a>
            <p>

                <c:forEach items="${discos}" var="disco">

                    <c:set var="relacionado" value="false" />
                    
                    <c:if test="${!empty cancion.discocancions}">    
                        <c:forEach items="${cancion.discocancions}" var="dc">
                            
                            <c:if test="${dc.disco.id eq disco.id and !relacionado}">
                                <c:set var="relacionado" value="true" />
                                Ya agregado a <a href="disco.jsp?id=${disco.id}">${disco.nombre}</a> <br />
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${!relacionado}">
                        <a href="agregarcancion.jsp?accion=agregar&qu=${qu}&cancion=${cancion.id}&disco=${disco.id}">Agregar al vinilo ${disco.nombre}</a><br />
                    </c:if>
                </c:forEach>
            </p>
        </li>
    </c:forEach>
</ul>

<jsp:include page="/styles/templates/foot.jsp"></jsp:include>