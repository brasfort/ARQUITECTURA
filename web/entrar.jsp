<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp" />
<% 
   
    String error = (request.getParameter("error") != null) ? request.getParameter("error") : ""; 
    String correo = (request.getParameter("correo") != null) ? request.getParameter("correo") : ""; 

    request.setAttribute("correo", correo);
    request.setAttribute("error", error);
%>

    <div class="center error">${error}</div>
    <form action="entrar" method="post">
        <fieldset>
            <legend>Iniciar sesión</legend>

                <label for="correo">Correo: </label>
                <input type="text" name="correo" id="correo" value="${correo}" class="no-null" />
                <br />

                <label for="clave">Contraseña: </label>
                <input type="password" name="clave" id="clave" value="" class="no-null" />
                <br />
                
                <div class="center">
                    <input type="hidden" name="url" id="url" value="" />
                    <input type="submit" value="entrar" />
                </div>
        </fieldset>
    </form>
<jsp:include page="/styles/templates/foot.jsp" />