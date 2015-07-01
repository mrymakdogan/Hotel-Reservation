
package connectiondb;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Baglan {

    public Connection baglan() {
         Connection c=null;
        try {
            Class.forName("org.postgresql.Driver");
           
            c=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Database Hata Var "+ex.getMessage());
        }

        return c;
    }
}