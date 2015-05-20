<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    edu.uniminuto.DAO.CancionDAO cdao = 
            new edu.uniminuto.DAO.CancionDAO();
    
    request.setAttribute("canciones", cdao.getCanciones()); 
    request.setAttribute("mensaje", request.getParameter("mensaje"));
%>
            <h1>Listado de Cancions</h1>
            <ul>
                <li><a href="${pageContext.request.contextPath}/fcancion.jsp">Agregar</a></li>
            </ul>
            
            <c:if test="${not empty mensaje}">
                <p class="info">${mensaje}</p>
            </c:if>
                
            <ul class="lists">
                <li><a href="#" id="clist" title="Cambiar a lista"></a></li>
                <li><a href="#" id="ctlist" title="Cambiar a dos columnas"></a></li>
                <li><a href="#" id="ctrlist" title="Cambiar a tres columnas"></a></li>
            </ul>
            <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript">
                 window.doQuery = true;
                 $(document).on("ready", function() {
                    $('#clist').bind('click', function(event) {     
                        event.preventDefault();
                        $('#cancions').removeClass('three');
                        $('#cancions').removeClass('two');
                    });
                    $('#ctlist').bind('click', function(event) {     
                        event.preventDefault();
                        $('#cancions').removeClass('three');
                        $('#cancions').addClass('two');
                    });
                    $('#ctrlist').bind('click', function(event) {     
                        event.preventDefault();
                        $('#cancions').removeClass('two');
                        $('#cancions').addClass('three');
                    });
                    if ($('ul#cancions li').size() < 20) {
                            return;
                    }
                    $(window).scroll(function(){
                        if  ($(window).scrollTop() == $(document).height() - $(window).height()){
                           loadMoreCancions();
                        }
                    }); 
                });

                function loadMoreCancions() {
                    if (window.doQuery == false) {
                        $('div#loader').html('No se encontraron más registros');
                        return;
                    }

                    var lastId = $('#cancions li').size();
                    $('div#loader').html('<img class="center" src="http://localhost/songstock/images/load.gif" alt="Cargando...">');
                    $.ajax({
                      type: 'GET',
                      url: '${pageContext.request.contextPath}/procesarcancion',
                      data: 'ultimoId=' + lastId + '&asList=true',
                      success: function(msg){
                        msg = msg.replace(/^\s+/g,'').replace(/\s+$/g,'');
                        if (msg.length > 0) {
                            $("ul#cancions li:last").after(msg);
                            $('div#loader').empty();
                        } else {
                            window.doQuery = false;
                            $('div#loader').html('No se encontraron más registros');
                        }
                      },
                      error: function(XMLHttpRequest, textStatus, errorThrown) {
                          console.log(XMLHttpRequest);
                         alert("some error" + XMLHttpRequest + "; " + textStatus + "; " + errorThrown);
                         window.doQuery = false;
                         $('div#loader').empty();
                      }
                    });
                }
            </script>
            
            <ul class="three" id="cancions">
                <c:forEach items="${canciones}" var="cancion">
                <li>
                    <p>
                        ${cancion.nombre}<br />
                        ${cancion.anhio}<br />
                        Duración: ${cancion.duracion}<br />
                        Calidad: ${cancion.calidad} kbps<br />
                        Precio: ${cancion.precio}<br />
                        ${cancion.interprete.nombre}<br />
<!--                        <a href="fcancion.jsp?accion=editar&id=${cancion.id}">editar</a> | 
                        <a href="cancion.jsp?accion=borrar&id=${cancion.id}">eliminar</a> |  -->
                        <a href="cancion.jsp?id=${cancion.id}">detalles</a> 
                    </p>
                    <form action="/SongStock/agregarcancion" method="post" class="inline">
                        <input type="hidden" name="nombre" value="${cancion.nombre}" />
                        <input type="hidden" name="id" value="${cancion.id}" />
                        <input type="hidden" name="precio" value="${cancion.precio}" />
                        <input type="submit" value="agregar al carrito" class="link" />
                    </form>
                </li>
                </c:forEach>
            </ul>
            <div id="loader"></div>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>