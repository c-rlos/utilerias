/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.qubez.utilierias.conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mx.qubez.utilierias.dto.ModuloDTO;
import mx.qubez.utilierias.dto.PerfilDTO;
import mx.qubez.utilierias.dto.UsuarioDTO;
import mx.qubez.utilierias.dto.CampoDTO;

/**
 *
 * @author carlos.juarez
 */
public class AccesoDAO {

    public UsuarioDTO getUsuario(String user, String pass, Connection con) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT \n"
                + "	id_usuario, usuario, nombre, a_paterno, a_materno, e_mail, direccion, \n"
                + "     telefono_1, telefono_2, fecha_nacimiento, estatus, id_perfil \n"
                + "FROM ss_usuario \n"
                + "WHERE usuario = ? AND password = ? ";
        UsuarioDTO usuario = null;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioDTO();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setNombre(rs.getString("nombre") + " " + rs.getString("a_paterno") + " " + rs.getString("a_materno"));
                usuario.setEmail(rs.getString("e_mail"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setTelefono1(rs.getString("telefono_1"));
                usuario.setTelefono2(rs.getString("telefono_2"));
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                usuario.setPerfil(getPerfil(rs.getInt("id_perfil"), con));
            }
        } catch (Exception e) {
            System.out.println("Error en AccesoDAO.getUsuario: " + e.getMessage());
        }
        return usuario;
    }

    public PerfilDTO getPerfil(int idPerfil, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT \n"
                + "	id_perfil, nombre, descripcion, modulo_inicio \n"
                + "FROM ss_perfil \n"
                + "WHERE id_perfil = ? ";
        PerfilDTO perfil = null;
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idPerfil);
            rs = ps.executeQuery();
            while (rs.next()) {
                perfil = new PerfilDTO();
                perfil.setIdPerfil(rs.getInt("id_perfil"));
                perfil.setNombre(rs.getString("nombre"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setModuloInicio(rs.getString("modulo_inicio"));
                perfil.setListaModulos(getModulos(rs.getInt("id_perfil"), con));
            }
        } catch (Exception e) {
            System.out.println("Error en getPerfil: " + e.getMessage());
        }
        return perfil;
    }

    public List<ModuloDTO> getModulos(int idPerfil, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT \n"
                + "	pm.id_perfil, m.id_modulo, m.nombre, m.descripcion, m.url \n"
                + "FROM ss_perfil_modulo pm \n"
                + "INNER JOIN ss_modulo m ON pm.id_modulo = m.id_modulo \n"
                + "WHERE pm.id_perfil = ? ";
        List<ModuloDTO> listaModulos = new ArrayList<ModuloDTO>();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idPerfil);
            rs = ps.executeQuery();
            while (rs.next()) {
                ModuloDTO m = new ModuloDTO();
                m.setIdModulo(rs.getInt("id_modulo"));
                m.setNombre(rs.getString("nombre"));
                m.setDescripcion(rs.getString("descripcion"));
                m.setUrl(rs.getString("url"));
                m.setListaDeCampos(getCampos(idPerfil, m.getIdModulo(), con));
                listaModulos.add(m);
            }
        } catch (Exception e) {
            System.out.println("Error en getModulos: " + e.getMessage());
        }
        return listaModulos;
    }

    public List<CampoDTO> getCampos(int idPerfil, int idModulo, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query
                = "SELECT \n"
                + "     pmc.id_modulo, pmc.id_campo, nc.nombre, nc.descripcion \n"
                + "FROM ss_perfil_modulo_campo pmc \n"
                + "INNER JOIN ss_campo nc ON pmc.id_campo = nc.id_campo \n"
                + "WHERE pmc.id_modulo = ? \n"
                + "AND pmc.id_perfil = ? ";

        List<CampoDTO> listaCampos = new ArrayList<CampoDTO>();
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idModulo);
            ps.setInt(2, idPerfil);
            rs = ps.executeQuery();
            while (rs.next()) {
                CampoDTO c = new CampoDTO();
                c.setIdCampo(rs.getInt("id_campo"));
                c.setNombre(rs.getString("nombre"));
                c.setDescripcion(rs.getString("descripcion"));
                listaCampos.add(c);
            }
        } catch (Exception e) {
            System.out.println("Error en AccesoDAO.getCampos: " + e.getMessage());
        }
        return listaCampos;
    }
}
