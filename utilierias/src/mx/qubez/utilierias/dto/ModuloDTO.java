/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.qubez.utilierias.dto;

import java.util.List;

/**
 *
 * @author carlos.juarez
 */
public class ModuloDTO {
 
    private int idModulo;
    private String nombre;
    private String descripcion;
    private String url;
    private List<CampoDTO> listaDeCampos;

    public ModuloDTO() {
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CampoDTO> getListaDeCampos() {
        return listaDeCampos;
    }

    public void setListaDeCampos(List<CampoDTO> listaDecampos) {
        this.listaDeCampos = listaDecampos;
    }


    
}
