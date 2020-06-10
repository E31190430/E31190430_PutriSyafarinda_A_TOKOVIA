
package config;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDialog;

/**
 *
 * @author ASUS
 */
public class conn {

     private static Connection mysqlConfig;
     
    public static Connection configDB() throws SQLException{
        try {
            String url = "jdbc:mysql://localhost:3306/db_minimarket"; //url database
            String user = "root"; //user database
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlConfig = DriverManager.getConnection(url,user,pass);
            
        } catch (Exception e) {
            System.err.println("Koneksi Gagal " + e.getMessage());//untuk menampilkan jika ada eror
        }
        return mysqlConfig;
    }
}
  

