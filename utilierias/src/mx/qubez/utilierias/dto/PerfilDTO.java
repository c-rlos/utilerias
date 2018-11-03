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
public class PerfilDTO {
    
    private int idPerfil;
    private String nombre;
    private String descripcion;
    private String moduloInicio;
    private List<ModuloDTO> listaModulos;
    

    public PerfilDTO() {
    
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
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

    public String getModuloInicio() {
        return moduloInicio;
    }

    public void setModuloInicio(String moduloInicio) {
        this.moduloInicio = moduloInicio;
    }

    public List<ModuloDTO> getListaModulos() {
        return listaModulos;
    }

    public void setListaModulos(List<ModuloDTO> listaModulos) {
        this.listaModulos = listaModulos;
    }
    
    
    
    
}
