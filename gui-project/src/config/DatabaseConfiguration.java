package config;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
 public class DatabaseConfiguration {
    public static Connection getKoneksi(String host, String port, String username, String password, String dataBaseName) {
        String configJdbc = "jdbc:mysql://" + host + ":" + port + "/" + dataBaseName;
        Connection koneksi = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection(configJdbc,
            username, password);
            System.out.println("Koneksi Berhasil");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi Database Error");
            connect = null;
        }
        return connect;
    }
}