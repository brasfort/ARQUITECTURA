<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<%
    
    
    edu.uniminuto.DAO.CancionDAO cdao = new edu.uniminuto.DAO.CancionDAO();
    edu.uniminuto.persistencia.Cancion cancion = 
            new edu.uniminuto.persistencia.Cancion();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
    if (request.getParameter("id") != null) {
        String id = request.getParameter("id");
        cancion = cdao.getCancion(Long.valueOf(id));
    }
    
    
    
    
    if (request.getParameter("nombre") != null) {
        cancion.setNombre(request.getParameter("nombre"));
    }
    
    if (request.getParameter("anhio") != null) {
        cancion.setAnhio(request.getParameter("anhio"));
    }
    
    if (request.getParameter("duracion") != null) {
        
        cancion.setDuracion(sdf.parse(request.getParameter("duracion")));
    }
    
    if (request.getParameter("peso") != null) {
        cancion.setPeso(request.getParameter("peso"));
    }
    
    if (request.getParameter("calidad") != null) {
        
        cancion.setCalidad(request.getParameter("calidad"));
    }
    
    if (request.getParameter("precio") != null) {
        cancion.setPrecio(Integer.valueOf(request.getParameter("precio")));
    }
    
    if (request.getParameter("interprete") != null) {
        
        cancion.setInterprete(cdao.getInterprete(
                Integer.valueOf(request.getParameter("interprete"))));
    }
    
    
            
    
    request.setAttribute("cancion", cancion);
    request.setAttribute("interpretes", cdao.getInterpretes());
    request.setAttribute("accion", request.getParameter("accion"));
    
%>
            

            <h1>Canción</h1>
            <ul>
                <li><a href="fcancion.jsp">Agregar</a></li>
            </ul>

            <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="js/cancion.js"></script>
            <script type="text/javascript">
                $(document).on("ready", function() {
                    assignEvents();
                });
            </script>

            <form id="form" action="admcancion" method="post">
                <fieldset>
                    <legend>Datos de Cancion</legend>

                    <label title="" for="Nombre">Nombre: </label>
                    <input title="" type="text" id="Nombre" name="nombre" value="${cancion.nombre}" class="required alphanumeric" maxlength="250" />
                    <br />

                    <label title="" for="Anhio">Anhio: </label>
                    <input title="" type="text" id="Anhio" name="anhio" value="${cancion.anhio}" class="required alphanumeric" maxlength="4" />
                    <br />

                    <label title="" for="Duracion">Duracion: </label>
                    <input title="" type="text" id="Duracion" name="duracion" value="${cancion.duracion}" class="required" />
                    <br />
                    <label title="" for="Duracion">Formato HH:mm</label>
                    <br />
                    
                    
                    <label title="" for="Peso">Peso: </label>
                    <input title="" type="text" id="Peso" name="peso" value="${cancion.peso}" class="required alphanumeric" maxlength="7" /> 
                    <span>en <span title="MegaBytes">MB</span></span>
                    <br />

                    <label title="" for="Calidad">Calidad: </label>
                    <select name="calidad">
                        <option value="128">128</option>
                        <option value="192">192</option>
                        <option value="256">256</option>
                        <option value="320">320</option>
                    </select>
                    <span>en <span title="kiloBytes">kb</span>/<span title="segundos">s</span></span>
                    <br />

                    <label title="" for="Precio">Precio: </label>
                    <input title="" type="text" id="Precio" name="precio" value="${cancion.precio}" class="required digits" />
                    <br />

                    <label title="" for="interprete">Intérprete </label>
                    <select name="interprete">
                        <c:if test="${!empty cancion.interprete}">
                        <option value="${cancion.interprete.id}" selected>${cancion.interprete.nombre}</option>
                        </c:if>
                        <c:forEach items="${interpretes}" var="inter">
                        <c:if test="${cancion.interprete.id != inter.id}">
                        <option value="${inter.id}">${inter.nombre}</option>
                        </c:if>
                        </c:forEach>
                    </select>
                    <br />
                    
                    <div class="center">
                        <input id="Id" name="Id" type="hidden" value="${cancion.id}" />
                        <input type="submit" value="guardar" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>
