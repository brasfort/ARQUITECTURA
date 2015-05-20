<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    String id = request.getParameter("id");
    edu.uniminuto.DAO.DiscoDAO discoDAO = new edu.uniminuto.DAO.DiscoDAO();
    edu.uniminuto.persistencia.Disco disco = discoDAO.getDisco(Integer.valueOf(id));
    
    long idSesion = -1;
    if (request.getSession().getAttribute("usuario") != null) {
        idSesion = ((edu.uniminuto.persistencia.Persona)
                request.getSession().getAttribute("usuario")).getId();
    }
    
    request.setAttribute("disco", disco);
    request.setAttribute("idsesion", idSesion);
%>
                <h1>Disco</h1>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/fdisco.jsp?accion=crear">agregar</a></li>
                    <li><a href="${pageContext.request.contextPath}/discos.jsp">Listado</a></li>
                </ul>

                <fieldset>
                    <legend>Detalle Disco</legend>
                    
                    <ul class="three">
                        <li><img class="total" src="${pageContext.request.contextPath}/${disco.imagen}" /></li>
                        
                        <li style="width: 60%;">
                            <p>
                                <strong>Nombre: </strong> ${disco.nombre}<br />
                                <!--<strong>Precio: </strong> <br />-->
                                <strong>Año: </strong> ${disco.anhio}<br />
                                <strong title="">Genero: </strong> ${disco.genero.nombre}
                                <a href="${pageContext.request.contextPath}/dgenero.jsp?id=${disco.genero.id}">ver</a>
                                <br />

                                <strong title="">Interprete </strong> ${disco.interprete.nombre} 
                                <a href="${pageContext.request.contextPath}/dinterprete.jsp?id=${disco.interprete.id}">ver</a>
                                <br />

                                <a href="${pageContext.request.contextPath}/fdisco.jsp?accion=editar&id=${disco.id}">actualizar</a> | 
                                <a href="${pageContext.request.contextPath}/ddisco.jsp?accion=borrar&id=${disco.id}">borrar</a>
                            </p>
                            
                            <form action="procesarvinilo" method="post">  
                                <h2>Tengo este vinilo y quiero venderlo</h2>
                                <label for="precio">Precio: </label>
                                <input type="text" name="precio" id="precio" />
                                <input type="hidden" name="disco" value="${disco.id}" />
                                <input type="submit" value="ofrecer" />
                            </form>
                        </li>
                    </ul>
                            
                    <c:if test="${!empty disco.discocancions}">
                    <h1> Listado de canciones</h1>
                    <ol>
                    <c:forEach var="item" items="${disco.discocancions}">
                        <li>
                        ${item.cancion.nombre} (${item.cancion.interprete.nombre})
                        </li>
                    </c:forEach>
                    </ol>
                    </c:if>
                            
                    <c:if test="${!empty disco.discopropietarios}">
                    <h1> Listado de vendedores</h1>
                    <ol>
                    <c:forEach var="item" items="${disco.discopropietarios}">
                        <c:if test="${item.vendido eq 0}">
                        <li>
                        <c:if test="${idsesion != item.persona.id}">
                        ${item.persona.nombres} ($ ${item.precio})
                        <form id="vender" action="/SongStock/agregardisco" method="post" class="inline">
                            <input type="hidden" name="nombre" value="${item.disco.nombre}" />
                            <input type="hidden" name="id" value="${item.id}" />
                            <input type="hidden" name="precio" value="${item.precio}" />
                            <input type="submit" value="agregar al carrito" class="link" />
                        </form>
                        </c:if>
                        <c:if test="${idsesion == item.persona.id}">
                        Tú ($ ${item.precio})
                        <form action="procesarvinilo" method="post" class="inline">
                            <input type="hidden" name="id" value="${item.id}" />
                            <input type="hidden" name="disco" value="${disco.id}" />
                            <input type="hidden" name="accion" value="borrar" />
                            <input type="submit" value="quitar" class="link" />
                        </form>
                        </c:if>
                        </li>
                        </c:if>
                    </c:forEach>
                    </ol>
                    </c:if>
                </fieldset>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>