package edu.uniminuto.carro;

public class Elemento {
    private String descripcion;
    private Integer discoId;
    private Long cancionId;
    private Integer almacenado;
    private Integer precio;

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the discoId
     */
    public Integer getDiscoId() {
        return discoId;
    }

    /**
     * @param discoId the discoId to set
     */
    public void setDiscoId(Integer discoId) {
        this.discoId = discoId;
    }

    /**
     * @return the cancionId
     */
    public Long getCancionId() {
        return cancionId;
    }

    /**
     * @param cancionId the cancionId to set
     */
    public void setCancionId(Long cancionId) {
        this.cancionId = cancionId;
    }

    /**
     * @return the almacenado
     */
    public Integer getAlmacenado() {
        return almacenado;
    }

    /**
     * @param almacenado the almacenado to set
     */
    public void setAlmacenado(Integer almacenado) {
        this.almacenado = almacenado;
    }
    
    /**
     * @return the precio
     */
    public Integer getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
