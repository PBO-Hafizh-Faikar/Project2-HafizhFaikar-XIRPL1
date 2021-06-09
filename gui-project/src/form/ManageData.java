package form;

import config.DatabaseConfiguration;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class ManageData extends javax.swing.JDialog {

     Connection koneksi;
    String action;
    public ManageData(java.awt.Frame parent, boolean modal, String act, String nis) {
        super(parent, modal);
        initComponents();
        koneksi = DatabaseConfiguration.getConnection("localhost", "3306", "root", "", "db_sekolah");
    
        action = act;
        if(action.equals("Edit")){
            txtNIS.setEnabled(false);
            showData(nis);
        }
    }
    
    public void SimpanData(){
        String nis      = txtNIS.getText();
        String nama     = txtNama.getText();
        String kelas    = cmbKelas.getSelectedItem().toString();
        String jurusan  = cmbJurusan.getSelectedItem().toString();
    
        try{
            Statement stmt = koneksi.createStatement();
            String query = "INSERT INTO t_siswa(nis,nama,kelas,jurusan) " + "VALUES('"+nis+"','"+nama+"','"+kelas+"','"+jurusan+"')";
            System.out.println(query);
            int berhasil = stmt.executeUpdate(query);
            if(berhasil == 1){
                JOptionPane.showMessageDialog(null, " Data Berhasil Dimasukkan");
            }else{
                JOptionPane.showMessageDialog(null, " Data Gagal Dimasukkan");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Pada Database");
        }
    }
}
