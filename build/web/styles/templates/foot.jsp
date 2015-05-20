<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
            </div><!-- end main -->
            <div id="sidebar">
                <ol>
                    <c:if test="${esInvitado}">
                    <li><a href="entrar.jsp">Entrar</a></li>
                    <li><a href="registro.jsp">Registrarme</a></li>
                    <li><a href="canciones.jsp">Canciones</a></li>
                    <li><a href="index.jsp">Discos</a></li>
                    <li><a href="interpretes.jsp">Interpretes</a></li>
                    </c:if>
                    <c:if test="${!esInvitado}">
                    <li><a href="canciones.jsp">Canciones</a></li>
                    <li><a href="index.jsp">Discos</a></li>
                    <li><a href="interpretes.jsp">Interpretes</a></li>
                    <li><a href="recopilaciones.jsp">Recopilaciones</a></li>
                    <c:if test="${rol eq 1}">
                        <li><a href="pedidos.jsp">Mis pedidos</a></li>
                        <li><a href="compras.jsp">Mis compras</a></li>
                    </c:if>
                    <c:if test="${rol eq 2}">
                    
                        <li><a href="agregarcancion.jsp">Buscar canciones</a></li>
                        <li><a href="fdisco.jsp">Agregar Disco</a></li>
                        <li><a href="fcancion.jsp">Agregar Canción</a></li>
                    
                        <li><a href="solicitudes.jsp">Solicitudes (vinilos)</a></li>
                        <li><a href="fdisco.jsp">Quiero vender un vinilo</a></li>
                    
                    </c:if>
                    <li><a href="registro.jsp">Yo</a></li>
                    <li><a href="salir">Salir</a></li>
                    </c:if>
                </ol>
            </div><!-- end sidebar -->
        </div><!-- end content -->
        <div id="foot">
            <div class="band"> 
                <p>Todos los derechos reservados &copy; 2015</p>
            </div>
        </div><!-- end foot -->
    </div><!-- end page -->
</body>
</html>
