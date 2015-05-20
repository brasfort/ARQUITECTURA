<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    String accion = "crear";
    String id = (request.getParameter("id") != null) ? 
            request.getParameter("id") : "";
    
    String nombre = (request.getParameter("nombre") != null) 
            ? request.getParameter("nombre").toString() : "";
    
    edu.uniminuto.persistencia.Interprete interprete = new 
            edu.uniminuto.persistencia.Interprete(nombre);
    
    if (!id.isEmpty() && !id.equals("null")) {
        accion = "editar";
        edu.uniminuto.DAO.InterpreteDAO interpreteDAO = 
                new edu.uniminuto.DAO.InterpreteDAO();
        interprete = interpreteDAO.getInterprete(Integer.valueOf(id));
    }
    
    request.setAttribute("interprete", interprete);
    request.setAttribute("accion", accion);
    request.setAttribute("error", request.getParameter("error"));
%>


            <c:if test="${not empty $error}">
                <p class="error"> ${error}</p>
            </c:if>

            <form id="form" action="${pageContext.request.contextPath}/procesarinterprete" method="post">
                <fieldset>
                    
                    <legend>Datos de Interprete</legend>

                    <label title="" for="nombre">Nombre: </label>
                    <input title="" type="text" id="nombre" name="nombre" value="${interprete.nombre}" class="required alphanumeric" maxlength="100" />
                    <br />
                    
                    <div class="center">
                        <input id="id" name="id" type="hidden" value="${interprete.id}" />
                        <input type="submit" value="${accion}" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>
