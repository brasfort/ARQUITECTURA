<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<% 
    boolean editando = false;
    
    edu.uniminuto.DAO.DiscoDAO discoDAO = new edu.uniminuto.DAO.DiscoDAO();
    edu.uniminuto.persistencia.Disco disco = new edu.uniminuto.persistencia.Disco();
    if (request.getParameter("id") != null) {
        
        int id = Integer.valueOf(request.getParameter("id"));
        disco = discoDAO.getDisco(id);
        editando = true;
    }
    
    if (request.getParameter("nombre") != null) {
        disco.setNombre(request.getParameter("nombre").toString());
    }
    
    if (request.getParameter("anhio") != null) {
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.set(Integer.valueOf(request.getParameter("anhio").toString()), 0, 0, 0, 0, 0);
        disco.setAnhio(c.getTime());
    }
    
    
    
    if (request.getParameter("genero") != null) {
        disco.setGenero(discoDAO.getGenero(Short.valueOf(request.getParameter("genero").toString())));
    }
    if (request.getParameter("interprete") != null) {
        disco.setInterprete(discoDAO.getInterprete(Integer.valueOf(request.getParameter("interprete").toString())));
        
    }
    
    
    request.setAttribute("disco", disco);
    request.setAttribute("precio", (request.getParameter("precio") != null) ? request.getParameter("precio") : 0);
    request.setAttribute("editando", editando);
    request.setAttribute("generos", discoDAO.getGeneros());
    request.setAttribute("interpretes", discoDAO.getInterpretes());
%>

            <h1>Disco</h1>
            <ul>
                <li><a href="${pageContext.request.contextPath}/fdisco.jsp?accion=crear">Agregar</a></li>
                <c:if test="${editando}">
                    <li><a href="${pageContext.request.contextPath}/ddisco.jsp?id=${id}">ver detalles</a></li>
                </c:if>        
            </ul>

            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/disco.js"></script>
            <script type="text/javascript">
                $(document).on("ready", function() {
                    assignEvents();
                });
            </script>

            <form id="form" action="guardardisco" method="post" enctype="multipart/form-data">
                <fieldset>
                    <legend>Datos de Disco</legend>

                    <label title="" for="Nombre">Nombre: </label>
                    <input title="" type="text" id="Nombre" name="nombre" value="${disco.nombre}" class="required alphanumeric" maxlength="200" />
                    <br />

                    <label title="" for="Imagen">Imagen: </label>
                    <input title="" type="file" id="Imagen" name="imagen" />
                    <br />

                    <label title="" for="Precio">Precio: </label>
                    <input title="" type="text" id="Precio" name="precio" value="${precio}" class="required digits" />
                    <br />

                    <label title="" for="Anhio">Anhio: </label>
                    <input title="" type="text" id="Anhio" name="anhio" value="${disco.anhio}" class="required" />
                    <br />
                    
                    <label title="" for="genero">Género: </label>
                    <select name="genero">
                        <c:if test="${!empty disco.genero}">
                        <option value="${disco.genero.id}" selected>${disco.genero.nombre}</option>
                        </c:if>
                        <c:forEach items="${generos}" var="genero">
                        <c:if test="${disco.genero.id != genero.id}">
                        <option value="${genero.id}">${genero.nombre}</option>
                        </c:if>
                        </c:forEach>
                    </select>
                    <br />
                    
                    <label title="" for="interprete">Intérprete </label>
                    <select name="interprete">
                        <c:if test="${!empty disco.interprete}">
                        <option value="${disco.interprete.id}" selected>${disco.interprete.nombre}</option>
                        </c:if>
                        <c:forEach items="${interpretes}" var="ints">
                        <c:if test="${disco.interprete.id != ints.id}">
                        <option value="${ints.id}">${ints.nombre}</option>
                        </c:if>
                        </c:forEach>
                    </select>
                    <br />
                    
                    <div class="center">
                        <input id="Id" name="id" type="hidden" value="${id}" />
                        <input type="submit" value="guardar" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>