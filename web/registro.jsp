<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp"></jsp:include>
<% 
    
    edu.uniminuto.DAO.PersonaDAO pdao = new edu.uniminuto.DAO.PersonaDAO();
    edu.uniminuto.persistencia.Persona persona = new edu.uniminuto.persistencia.Persona();
    java.text.SimpleDateFormat sdt = new java.text.SimpleDateFormat("yyyy-MM-dd");
    
    boolean administrador = false;
    if (request.getSession().getAttribute("usuario") != null) {
        persona = (edu.uniminuto.persistencia.Persona)request.getSession().getAttribute("usuario");
        administrador = persona.getRol().getId() == 3;
    }
    
    if (request.getParameter("nombres") != null) {
        persona.setNombres(request.getParameter("nombres").toString());
    }
    
    if (request.getParameter("apellidos") != null) {
        persona.setApellidos(request.getParameter("apellidos").toString());
    }
    
    if (request.getParameter("nacimiento") != null) {
        
        
        try {
            
            persona.setNacimiento(
                    sdt.parse(request.getParameter("nacimiento").toString()));
        } catch (Exception ex) {
            
        }
    }
    
    if (request.getParameter("correo") != null) {
        persona.setCorreo(request.getParameter("correo").toString());
    }
    
    if (request.getParameter("rol") != null) {
        int rol = 1;
        
        if (request.getParameter("rol").equals("vendedor")) {
            rol = 2;
        } else if (request.getParameter("rol").equals("administrador")) {
            rol = 3;
        } 
        persona.setRol(pdao.getRol(rol));
    }
    
    
    request.setAttribute("persona", persona);
    request.setAttribute("admin", administrador);
    request.setAttribute("nacimiento", (persona.getNacimiento() != null) ? sdt.format(persona.getNacimiento()): null);
%>


            <h1>Información personal</h1>


            <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
            <script type="text/javascript" src="js/jquery.validate.min.js"></script>
            <script type="text/javascript" src="js/persona.js"></script>
            <script type="text/javascript">
                $(document).on("ready", function() {
                    assignEvents();
                });
            </script>

            <form id="form" action="administrarpersonas" method="post">
                <fieldset>
                    <legend>Información</legend>

                    <label title="" for="Nombres">Nombres: </label>
                    <input title="" type="text" id="Nombres" name="nombres" value="${persona.nombres}" class="required alphanumeric" maxlength="45" />
                    <br />

                    <label title="" for="Apellidos">Apellidos: </label>
                    <input title="" type="text" id="Apellidos" name="apellidos" value="${persona.apellidos}" class="required alphanumeric" maxlength="45" />
                    <br />

                    <label title="" for="Nacimiento">Nacimiento: </label>
                    <input title="" type="text" id="Nacimiento" name="nacimiento" value="${nacimiento}" class="required dateISO" />
                    <br />

                    <label title="" for="Nacimiento">formato <em>aaaa-mm-dd</em></label>
                    <br />
                    
                    <label title="" for="Correo">Correo: </label>
                    <input title="" type="text" id="Correo" name="correo" value="${persona.correo}" class="required alphanumeric" maxlength="100" />
                    <br />

                    <label title="" for="Clave">Clave: </label>
                    <input title="" type="password" id="Clave" name="clave" value="" class="required alphanumeric" maxlength="45" />
                    <br />

                    <c:if test="${!admin and esInvitado}">
                    <label title="" for="vendedor">Soy vendedor: </label>
                    <input title="" type="radio" id="vendedor" name="rol" value="vendedor" />
                    <br />
                    
                    <label title="" for="comprador">Soy comprador: </label>
                    <input title="" type="radio" id="comprador" name="rol" value="comprador" />
                    <br />
                    </c:if>
                    <c:if test="${admin}">
                    <label>Eres administador: </label>
                    <input title="" type="radio" id="comprador" name="rol" checked="checked" value="administrador" disabled="disabled" />
                    <br />
                    </c:if>
                    
                    <div class="center">
                        <input id="Id" name="Id" type="hidden" value="<?php echo $persona->getId(); ?>" />
                        <input type="submit" value="guardar" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/foot.jsp"></jsp:include>