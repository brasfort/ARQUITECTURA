<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    String id = request.getParameter("id");
    edu.uniminuto.DAO.CancionDAO cdao = new edu.uniminuto.DAO.CancionDAO();
    edu.uniminuto.persistencia.Cancion cancion = cdao.getCancion(Long.valueOf(id));
    
    request.setAttribute("cancion", cancion);
    request.setAttribute("accion", request.getParameter("accion"));
    
    
    if (request.getParameter("accion") != null) {
        if (request.getParameter("accion").equals("agregar")) {

            long idCancion = Long.valueOf(request.getParameter("id"));
            int vinilo = Integer.valueOf(request.getParameter("disco"));

            edu.uniminuto.persistencia.Discocancion dc = new edu.uniminuto.persistencia.Discocancion(cdao.getCancion(idCancion), cdao.getDisco(vinilo));

            cdao.guardar(dc);
        }

    }
    
    edu.uniminuto.persistencia.Persona p = 
            (request.getSession().getAttribute("usuario") != null)
            ?
            ((edu.uniminuto.persistencia.Persona)request.getSession().getAttribute("usuario")) : null;
    
    java.util.Set<edu.uniminuto.persistencia.Recopilacion> r =
            (p != null) ? p.getRecopilacions() : null;
    
    
    
    
    java.util.List<edu.uniminuto.persistencia.Recopilacion> recos = 
            new java.util.ArrayList<edu.uniminuto.persistencia.Recopilacion>();
    for (edu.uniminuto.persistencia.Recopilacion reco : r) {
        recos.add(reco);
    }
   

    java.util.List<edu.uniminuto.persistencia.Recopilacion> relacionadas =
            cdao.getRecopilacionesAgregadas(p.getId(), cancion);
    
    
    for(edu.uniminuto.persistencia.Recopilacion re: relacionadas) {
        for(edu.uniminuto.persistencia.Recopilacion re2: recos) {
            if (re2.getId().intValue() == re.getId().intValue()) {
                recos.remove(re2);
                break;
            }
        }
    }
//    recos.removeAll(relacionadas);
    
    if (request.getSession().getAttribute("usuario") != null) {
        request.setAttribute("discos", cdao.getListadeDiscos(((edu.uniminuto.persistencia.Persona) request.getSession().getAttribute("usuario")).getId()));
    }
    request.setAttribute("recos",recos);
    request.setAttribute("rels",relacionadas);
%>
            <h1>Canción</h1>
            <ul>
                <li><a href="fcancion.jsp?accion=crear">agregar</a></li>
                <li><a href="canciones.jsp">Listado</a></li>
            </ul>

            <fieldset>
                <legend>Detalle Cancion</legend>

                <p>
                    <strong>Nombre: </strong> ${cancion.nombre}<br />
                    <strong>Duración: </strong> ${cancion.duracion}<br />
                    <strong>Calidad: </strong> ${cancion.calidad} kbps<br />
                    <strong>Peso: </strong> ${cancion.peso} MB<br />
                    <strong>Precio: </strong> ${cancion.precio}<br />
                    <strong>Año: </strong> ${cancion.anhio}<br />
                    <strong>Intérprete:  </strong> <a href="interprete.jsp?id=${cancion.interprete.id}">${cancion.interprete.nombre}</a> <br />
                    <a href="fcancion.jsp?accion=editar&id=${cancion.id}">editar</a> | 
                    <a href="cancion.jsp?accion=borrar&id=${cancion.id}">eliminar</a>
                </p>
                <c:if test="${!empty recos}">
                <p>
                    Puede agregarla a las siguientes listas <br />
                    <c:forEach items="${recos}" var="reco">
                     ${reco.nombre} 
                    <a href="cancionreco?accion=agregar&cancion=${cancion.id}&recopilacion=${reco.id}">agregar</a> | 
                    <a href="recopilacion.jsp?id=${reco.id}">ver detalles</a><br />
                    </c:forEach>
                </p>    
                </c:if>
                
                <c:if test="${!empty rels}">
                <p>
                    Esta canción ya está presente en tus listas: <br />
                    <c:forEach items="${rels}" var="reco">
                     ${reco.nombre} 
                    <a href="cancionreco?accion=borrar&cancion=${cancion.id}&recopilacion=${reco.id}">borrar</a> | 
                    <a href="recopilacion.jsp?id=${reco.id}">ver detalles</a><br />
                    </c:forEach>
                </p>    
                </c:if>
            </fieldset>

            <c:if test="${rol eq 2 and !empty discos}">
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
                        <a href="cancion.jsp?accion=agregar&id=${cancion.id}&disco=${disco.id}">Agregar al vinilo ${disco.nombre}</a><br />
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${(not empty accion) && (accion == 'borrar')}">
            <fieldset>
                <legend>Borrar registro</legend>
                <form action="${pageContext.request.contextPath}/procesacancion" method="post">
                    <input type="hidden" id="id" name="id" value="${cancion.id}" />
                    <input type="hidden" id="accion" name="accion" value="borrar" />
                    <p>¿Realmente desea borrar éste registro? <em>Tenga en cuenta que si este registro está conectado con otros registro no podrá eliminiarlo hasta que elimine previamente los datos relacionados</em>.</p>
                    <div class="center">
                        <input type="submit" value="sí" />
                    </div>
                </form>
            </fieldset>
            </c:if>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>