<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    String id = request.getParameter("id");
    edu.uniminuto.DAO.InterpreteDAO interpreteDAO = 
            new edu.uniminuto.DAO.InterpreteDAO();
    edu.uniminuto.persistencia.Interprete interprete = 
            interpreteDAO.getInterprete(Integer.valueOf(id));
    
    request.setAttribute("interprete", interprete);
    request.setAttribute("accion", request.getParameter("accion"));
%>
            <h1>Interprete</h1>
            <ul>
                <li><a href="${pageContext.request.contextPath}/finterprete.jsp">Agregar</a></li>
                <li><a href="${pageContext.request.contextPath}/interpretes.jsp">Listado</a></li>
            </ul>

            <fieldset>
                <legend>Detalle Interprete</legend>
                <p>
                    <strong>Nombre: </strong> ${interprete.nombre}<br />
                    <a href="${pageContext.request.contextPath}/finterprete.jsp?accion=actualizar&id=${interprete.id}">actualizar</a> | 
                    <a href="${pageContext.request.contextPath}/interprete.jsp?accion=borrar&id=${interprete.id}">borrar</a>
                </p>
            </fieldset>

            <c:if test="${not empty interprete.discos}">
            <fieldset>
                <legend>Listado de álbumes</legend>
                <ol>
                    <c:forEach var="disco" items="${interprete.discos}">
                    <li>
                        <strong>Nombre: </strong> ${disco.nombre} 
                        (<a href="${pageContext.request.contextPath}/disco.jsp?&id=${disco.id}">ver</a>)<br />
                    </li>
                    </c:forEach>
                </ol>
            </fieldset>
            </c:if>
            
            <c:if test="${(not empty accion) && (accion == 'borrar')}">
            <fieldset>
                <legend>Borrar registro</legend>
                <form action="${pageContext.request.contextPath}/procesarinterprete" method="post">
                    <input type="hidden" id="id" name="id" value="${interprete.id}" />
                    <input type="hidden" id="accion" name="accion" value="borrar" />
                    <p>¿Realmente desea borrar éste registro? <em>Tenga en cuenta que si este registro está conectado con otros registro no podrá eliminiarlo hasta que elimine previamente los datos relacionados</em>.</p>
                    <div class="center">
                        <input type="submit" value="sí" />
                    </div>
                </form>
            </fieldset>
            </c:if>

<jsp:include page="/styles/templates/foot.jsp"></jsp:include>