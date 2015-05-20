<%@page import="edu.uniminuto.persistencia.Recopilacion"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    edu.uniminuto.DAO.RecopilacionDAO rdao = 
            new edu.uniminuto.DAO.RecopilacionDAO();
    
    
    String accion = (request.getParameter("accion") != null) ? request.getParameter("accion") : "";
    
    int id = (request.getParameter("id") != null) ? Integer.valueOf(request.getParameter("id")) : -1;
    if (accion.equals("borrarc")) {
        int rel = (request.getParameter("relacion") != null) ? Integer.valueOf(request.getParameter("relacion")) : -1;
                
        
        if (rel > 0) {
            rdao.borrarRecocancion(rdao.getRecocancion(rel));
        }
    } 
    else if (accion.equals("borrar")) {
        Recopilacion r = rdao.getRecopilacion(id);
        
        if (rdao.borrar(r)) {
            response.sendRedirect("recopilaciones.jsp");
            return;
        }
    }
    
    long uid = 
            (request.getSession().getAttribute("usuario") != null) ?
            ((edu.uniminuto.persistencia.Persona)request.getSession().getAttribute("usuario")).getId() : -1;
    
    
    request.setAttribute("reco", rdao.getRecopilacion(id)); 
    request.setAttribute("uid", uid); 
    
    request.setAttribute("mensaje", request.getParameter("mensaje"));
%>
            
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
            <c:if test="${not empty mensaje}">
                <p class="error">${mensaje}</p>
            </c:if>

            <h1>Recopilacion</h1>
                        
                        <h2>${reco.nombre}</h2>
                        <p>
                            Creado por: ${reco.persona.nombres} (${reco.fecha})<br />
                        </p>
                        <form action="cancionreco" method="post">
                            <input type="hidden" name="recopilacion" value="${reco.id}" />
                            <label>Nombre de la nueva lista: </label>
                            <input type="text" name="nombre" />
                            <input type="submit" value="copiar esta lista" />
                        </form>
                        
                        
                        <p>
                            <c:forEach items="${reco.recocancions}" var="rc"> 
                            ${rc.cancion.nombre} <a href="cancion.jsp?id=${rc.cancion.id}">ver</a> | 
                            <a href="recopilacion.jsp?accion=borrarc&id=${reco.id}&relacion=${rc.id}">borrar de la lista</a><br />
                            </c:forEach>    
                        </p>
                        
                        <p>
                            <a href="recopilacion.jsp?id=${reco.id}">ver detalles</a> | 
                            <c:if test="${uid eq reco.persona.id}">
                            <a href="frecopilacion.jsp?accion=editar&id=${reco.id}">actualizar</a> |
                            <a href="recopilacion.jsp?accion=borrar&id=${reco.id}">borrar</a>
                            </c:if>
                        </p>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>