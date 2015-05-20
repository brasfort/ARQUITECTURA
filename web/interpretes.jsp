<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    edu.uniminuto.DAO.InterpreteDAO interpreteDAO = 
            new edu.uniminuto.DAO.InterpreteDAO();
    
    request.setAttribute("interpretes", interpreteDAO.getInterpretes()); 
    request.setAttribute("mensaje", request.getParameter("mensaje"));
%>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript">
                 window.doQuery = true;
                 $(document).on("ready", function() {
//                    $('#clist').bind('click', function(event) {     
//                        event.preventDefault();
//                        $('#interpretes').removeClass('three');
//                        $('#interpretes').removeClass('two');
//                    });
//                    $('#ctlist').bind('click', function(event) {     
//                        event.preventDefault();
//                        $('#interpretes').removeClass('three');
//                        $('#interpretes').addClass('two');
//                    });
//                    $('#ctrlist').bind('click', function(event) {     
//                        event.preventDefault();
//                        $('#interpretes').removeClass('two');
//                        $('#interpretes').addClass('three');
//                    });
	                if ($('ul#interpretes li').size() < 20) {
	                	return;
	                }
                    $(window).scroll(function(){
                        if  ($(window).scrollTop() == $(document).height() - $(window).height()){
                           loadMoreInterpretes();
                        }
                    }); 
                });

                function loadMoreInterpretes() {
                    if (window.doQuery == false) {
                        $('div#loader').html('No se encontraron más registros');
                        return;
                    }

                    var lastId = $('#interpretes li').size();
                    $('div#loader').html('<img class="center" src="${pageContext.request.contextPath}/images/load.gif" alt="Cargando...">');
                    $.ajax({
                      type: 'GET',
                      url: '${pageContext.request.contextPath}/procesarinterprete',
                      data: 'ultimoId=' + lastId,
                      success: function(msg){
                        msg = msg.replace(/^\s+/g,'').replace(/\s+$/g,'');
                        if (msg.length > 0) {
                            $("ul#interpretes li:last").after(msg);
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


                        <c:if test="${not empty mensaje}">
                            <p class="error">${mensaje}</p>
                        </c:if>

                        <h1>Interpretes</h1>
                        <ul class="three" id="interpretes">
                            <c:forEach items="${interpretes}" var="interprete">
                                <li>
                                    <p>
                                        ${interprete.nombre}<br />
                                        <a href="${pageContext.request.contextPath}/interprete.jsp?id=${interprete.id}">ver detalles</a> | 
                                        <a href="${pageContext.request.contextPath}/finterprete.jsp?accion=editar&id=${interprete.id}">actualizar</a> |
                                        <a href="${pageContext.request.contextPath}/interprete.jsp?accion=borrar&id=${interprete.id}">borrar</a>
                                    </p>
                                </li>
                            </c:forEach>
			</ul>
			<div id="loader"></div>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>