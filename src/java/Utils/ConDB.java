
package Utils;

import java.sql.*;

public class ConDB {
    
    public static Connection getConnection() {
        Connection cn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ferreteria", "root", "");
            System.out.println("Conexion Satisfactoria");

        } catch (Exception e) {
            System.out.println("no se conecto");
        }
        return cn;
    }
    
  
    public static void main(String[] args) {
        getConnection();
    }
    
}
