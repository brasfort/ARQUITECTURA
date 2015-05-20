<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp" />
<%
    
    String letra = (request.getAttribute("letra") != null) ? 
            request.getAttribute("letra").toString() : "";
    
    edu.uniminuto.DAO.DiscoDAO discoDAO = new edu.uniminuto.DAO.DiscoDAO();
    java.util.List<edu.uniminuto.persistencia.Disco> discos;
    discos = discoDAO.getDiscos(letra);
    request.setAttribute("discos", discos);
%>

                <ul class="lists">
                    <li><a href="#" id="clist" title="Cambiar a lista"></a></li>
                    <li><a href="#" id="ctlist" title="Cambiar a dos columnas"></a></li>
                    <li><a href="#" id="ctrlist" title="Cambiar a tres columnas"></a></li>
                </ul>
                
                <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
                <!--<script type="text/javascript" src="js/jquery.lazyload.min.1.9.4.js"></script>-->
                <script type="text/javascript">
                     window.doQuery = true;
                     $(document).on("ready", function() {
                        $('#clist').bind('click', function(event) {     
                            event.preventDefault();
                            $('#discos').removeClass('three');
                            $('#discos').removeClass('two');
                        });
                        $('#ctlist').bind('click', function(event) {     
                            event.preventDefault();
                            $('#discos').removeClass('three');
                            $('#discos').addClass('two');
                        });
                        $('#ctrlist').bind('click', function(event) {     
                            event.preventDefault();
                            $('#discos').removeClass('two');
                            $('#discos').addClass('three');
                        });
                        /*$('img.lazy').lazyload({
                            effect: 'fadeIn',
                            placeHolder: 'images/load.gif',
                            threshold: 100
                            
                        });*/
                        if ($('#discos div.li').size() < 20) {
                                return;
                        }
                        $(window).scroll(function(){
                            if  ($(window).scrollTop() == $(document).height() - $(window).height()){
                               loadMoreDiscos();
                            }
                        }); 
                    });

                    function loadMoreDiscos() {
                        if (window.doQuery == false) {
                            $('div#loader').html('No se encontraron más registros');
                            return;
                        }

                        var lastId = $('#discos div.li').size();
                        $('div#loader').html('<img class="center" src="images/load.gif" alt="Cargando...">');
                        $.ajax({
                          type: 'GET',
                          url: '${pageContext.request.contextPath}/procesardisco',
                          data: 'ultimoId=' + lastId,
                          success: function(msg){
                            msg = msg.replace(/^\s+/g,'').replace(/\s+$/g,'');
                            if (msg.length > 0) {
                                $("div#discos div.li:last").after(msg);
                                $('div#loader').empty();
                            } else {
                                window.doQuery = false;
                                $('div#loader').html('No se encontraron más registros');
                            }
                          },
                          error: function(XMLHttpRequest, textStatus, errorThrown) {
                             alert("some error" + XMLHttpRequest + "; " + textStatus + "; " + errorThrown);
                             window.doQuery = false;
                             $('div#loader').empty();
                          }
                        });
                    }
                </script>
                
                <h1>Discos disponibles</h1>
                <div id="discos" class="three">
                    <c:forEach var="disco" items="${discos}">
                    <div class="li">
                        <fmt:formatDate pattern="yyyy" var="year" value="${disco.anhio}" />
                        
                        <image src="${disco.imagen}" class="lazy total" />
                        <div class="absolute over">
                            
                            <h2>${disco.nombre} <em>(${year})</em></h2>
                            <p>
                                Genero: ${disco.genero.nombre}<br />
                                <!-- disponible(s)<br />-->
                                de: <a href="${baseURL}/interprete.jsp?id=${disco.id}">${disco.interprete.nombre}</a><br />

                                <c:if test="${rol eq 3}">
                                <a href="${pageContext.request.contextPath}/fdisco.jsp?accion=editar&id=${disco.id}">editar</a> | 
                                <a href="${pageContext.request.contextPath}/disco.jsp?accion=borrar&id=${disco.id}">borrar</a> | 
                                </c:if>
                                <a href="${pageContext.request.contextPath}/disco.jsp?id=${disco.id}">detalles</a>| 
                                <a href="${pageContext.request.contextPath}/disco.jsp?id=${disco.id}">Tengo este vinilo y quiero venderlo</a>
                            </p>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                    
                <div id="loader"></div>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>