/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preconceptual;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author L455-SP5017
 */
public class ConexionSQL {

    //Statement stm;
    public static Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection co = DriverManager.getConnection("jdbc:mysql://localhost/preconceptual","root","GearPass2015-");
            return co;
            //Statement stm1 = co.createStatement();
        } catch (ClassNotFoundException exc) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, exc);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void cerrar(Connection co) {
        if (co != null) {
            try {
                co.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void statement(String Datos) {
        Connection co;
        co=conectar();
        try {
            Statement sentencia = co.createStatement();
            // Se elimina la tabla en caso de que ya existiese
            sentencia.execute(Datos);
        }catch(MySQLIntegrityConstraintViolationException ex){
            JOptionPane.showMessageDialog(null,"No puedes borrar esta triada porque tiene una dependencia");
        } catch (SQLException e) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, e);
        }
        cerrar(co);
    }
    
    public static ResultSet querys(String consulta) {
        Connection co;
        co=conectar();
        ResultSet result=null;
        try {
            PreparedStatement statement = co.prepareStatement(consulta);
            result = statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, e);
        }
        return result;
    }

}
