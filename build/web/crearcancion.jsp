<%-- 
    Document   : crearcancion
    Created on : 23-feb-2015, 19:14:15
    Author     : Jhon Gómez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/styles/templates/header.jsp" />
            <form id="form" action="http://http://localhost:8084/SongStock//cancion/<?php echo $this->getOperation();?>" method="post">
                <fieldset>
                    <legend>Datos de Cancion</legend>

                    <label title="" for="Nombre">Nombre: </label>
                    <input title="" type="text" id="Nombre" name="Nombre" value="<?php echo $cancion->getNombre();?>" class="required alphanumeric" maxlength="45" />
                    <br />

                    <label title="" for="Anhio">Anhio: </label>
                    <input title="" type="text" id="Anhio" name="Anhio" value="<?php echo $cancion->getAnhio();?>" class="required alphanumeric" maxlength="4" />
                    <br />

                    <label title="" for="Album">Album: </label>
                    <input title="" type="text" id="Album" name="Album" value="<?php echo $cancion->getAlbum();?>" class="required digits" />
                    <br />

<?php               $SelectInterprete->show(5);?>

                    <div class="center">
                        <input id="Id" name="Id" type="hidden" value="<?php echo $cancion->getId(); ?>" />
                        <input type="submit" value="<?php echo $this->getButtonText(); ?>" />
                    </div>
                </fieldset>
            </form>
<jsp:include page="/styles/templates/header.jsp" />