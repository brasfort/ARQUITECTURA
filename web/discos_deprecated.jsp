<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp" />
<jsp:useBean id="bdisco" class="edu.uniminuto.beans.DiscoBean" />
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
                        $('div#loader').html('<img src="http://localhost/songstock/images/load.gif" alt="Cargando...">');
                        $.ajax({
                          type: 'POST',
                          url: 'http://localhost/songstock/disco',
                          data: 'lastQueryId=' + lastId + '&asList=true',
                          success: function(msg){
                            msg = msg.replace(/^\s+/g,'').replace(/\s+$/g,'');
                            if (msg.length > 0) {
                                $("ul#discos li:last").after(msg);
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
                
                <div id="discos">
                    <c:forEach var="disco" items="${bdisco.discos}">
                    <div class="li">
                        <image src="<c:out value="${disco.imagen}" />" class="total" />
                        <p>


                            ${disco.precio}<br />
                            ${disco.stock}<br />
                            ${disco.anhio}<br />
                            ${disco.genero.nombre}<br />
                            ${disco.interprete.nombre}<br />
                            <a href="http://localhost/songstock/disco/actualizar/<?php echo $disco->getId(); ?>">editar</a> | 
                            <a href="http://localhost/songstock/disco/borrar/<?php echo $disco->getId(); ?>">borrar</a> | 
                            <a href="http://localhost/songstock/disco/detalle/<?php echo $disco->getId(); ?>">detalles</a>
                        </p>
                    </div>
                    </c:forEach>
                </div>
                
                <!--<ul class="" id="discos">
                    <li>
                        <p>
                            
                        </p>
                    </li>
                </ul>-->
                <div id="loader"></div>

                <h1>Login succefull</h1>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec laoreet semper euismod. Donec rhoncus pulvinar consequat. Vestibulum in dui nec nulla malesuada auctor vitae ut nunc. Ut eget turpis imperdiet, viverra nisi eget, laoreet tortor. Phasellus in tristique sem. Fusce sed consectetur turpis. Mauris ultrices nec ante id bibendum. In in blandit massa, sit amet interdum ex. Sed non lacus nisl. Nunc venenatis ut nisl nec eleifend. Donec consectetur odio fringilla, aliquet nulla at, fermentum turpis. Curabitur finibus dignissim neque, sit amet volutpat neque finibus a. In vel tincidunt mi, sed imperdiet mi. Nullam eget ligula commodo, bibendum ex id, venenatis urna.</p>
                <p>Nullam id lacus luctus, ornare magna vel, scelerisque felis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Sed aliquet, nunc eu posuere rhoncus, justo tellus varius lacus, ac efficitur enim augue vitae lectus. Proin dolor nunc, imperdiet sit amet tempor tincidunt, dapibus vel est. Duis aliquam sagittis urna eu egestas. Proin eget lacus sed felis condimentum varius. Donec rutrum sed diam eu sodales. Nam non dui risus.</p>
                <p>Vestibulum convallis metus eget est sagittis, gravida convallis magna volutpat. Vestibulum maximus ex viverra velit malesuada dictum. Sed at lacus aliquam, finibus ligula sit amet, gravida eros. Cras luctus pretium metus, non maximus erat imperdiet nec. Cras pellentesque porttitor tincidunt. Nunc scelerisque dolor vel porttitor feugiat. Sed lacinia enim id tortor tristique rutrum. Quisque odio enim, eleifend eu feugiat id, consequat vitae tellus. Pellentesque congue a lacus rhoncus finibus.</p>
                <p>Cras suscipit felis non metus sollicitudin, ut venenatis sapien blandit. Aenean eget placerat enim. Quisque dignissim, eros eu vestibulum ullamcorper, ante ante vehicula quam, sit amet maximus quam leo vitae ligula. Ut venenatis consectetur enim, quis iaculis massa eleifend quis. Curabitur feugiat lacinia ligula, et lobortis odio maximus quis. Praesent sit amet feugiat lectus. Integer sed eros velit. Nulla fringilla mauris vel auctor tempus. Nam dictum nisl et orci volutpat, nec tincidunt est finibus. Morbi leo est, tempor in iaculis at, sodales a odio. Donec vel bibendum est. Etiam in metus in est consequat accumsan non in quam.</p>
                <p>Aliquam ac mi nec arcu aliquam euismod sit amet ut urna. Nulla facilisi. Praesent imperdiet lacinia dolor, ut bibendum augue dignissim in. Nulla facilisi. Sed finibus libero dolor, vitae elementum enim pharetra eget. Nunc diam velit, consectetur id ultrices in, facilisis in erat. Sed convallis felis et malesuada vestibulum. Nulla sodales vehicula quam, vel malesuada enim semper at. Curabitur vulputate lectus sit amet convallis fermentum. Mauris congue dignissim arcu, sed viverra dolor condimentum at. Fusce at lacinia mauris. Curabitur auctor turpis ante. Suspendisse odio dolor, viverra eget mauris quis, vestibulum vulputate justo. Sed ipsum dui, semper non dolor ut, malesuada tincidunt urna. Nunc lacus tortor, semper eu sapien quis, tincidunt volutpat purus. Quisque id consequat diam. </p>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>