/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.qubez.utilierias.conexion;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author carlos.juarez
 */

public class Factory {
    
    public static Connection getConnection(String nameDbContext){
        Connection con = null;
        try {
            Context initCtx = new InitialContext();
            Context contexto = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) contexto.lookup("jdbc/" + nameDbContext);
            con = ds.getConnection();
        } catch (Exception e) {
            System.out.println("Error en Factory.getConnection: " + e.getMessage());
        } finally {
            return con;
        }

    }
    
    public static void close(Connection con){
        if(con != null){
            con = null;
        }
    }
}
