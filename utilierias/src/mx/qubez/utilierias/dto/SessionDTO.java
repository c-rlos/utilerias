/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.qubez.utilierias.dto;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpSession;
import mx.qubez.utilierias.conexion.AccesoDAO;
import javax.faces.context.FacesContext;

/**
 *
 * @author carlos.juarez
 */
public class SessionDTO implements Serializable {

    private HttpSession session;
//    private FacesContext context;
    private String nombreModulo;
    private String sessionId;
    private UsuarioDTO user;
    private AccesoDAO accesoDao;

    public SessionDTO(String nombreModulo) {
        this.nombreModulo = nombreModulo;
        accesoDao = new AccesoDAO();
    }

    public String validaAcceso(String usuario, String password, Connection con) {
        this.user = accesoDao.getUsuario(usuario, password, con);
        if (user != null) {
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            sessionId = session.getId();
            session.setAttribute("objetoSesion", this);
            return user.getPerfil().getModuloInicio();
        } else {
            return "acceso.jsf";
        }
    }

    public String getIdSession() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        SessionDTO sessionAlmacenada = (SessionDTO) session.getAttribute("objetoSesion");
        if (sessionAlmacenada != null) {
            if (sessionAlmacenada.getSessionId() != null && sessionAlmacenada.getUser() != null) {
                return sessionAlmacenada.getSessionId()+"-"+sessionAlmacenada.getUser().getPerfil().getModuloInicio();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public UsuarioDTO getUsersSession() {
        if (session != null) {
            SessionDTO sessionLocal = (SessionDTO) session.getAttribute("objetoSesion");
            if (sessionLocal.getUser() != null) {
                return sessionLocal.getUser();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void validaSession() {
        try {
            String[] sessionStatus = getIdSession().split("-");
            if (!sessionStatus[0].equals("")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(sessionStatus[1]);
            }
//            else{
//                FacesContext.getCurrentInstance().getExternalContext().redirect("acceso.jsf");
//            }
        } catch (Exception ex) {
            System.out.println("Error en SessionDTO.validaSession: " + ex);
        }
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public UsuarioDTO getUser() {
        return user;
    }

    public void setUser(UsuarioDTO user) {
        this.user = user;
    }

}
