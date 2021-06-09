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

  public ManageData(
    java.awt.Frame parent,
    boolean modal,
    String act,
    String nis
  ) {
    super(parent, modal);
    initComponents();
    koneksi =
      DatabaseConfiguration.getConnection(
        "localhost",
        "3306",
        "root",
        "",
        "db_sekolah"
      );

    action = act;
    if (action.equals("Edit")) {
      txtNIS.setEnabled(false);
      showData(nis);
    }
  }

  public void SimpanData() {
    String nis = txtNIS.getText();
    String nama = txtNama.getText();
    String kelas = cmbKelas.getSelectedItem().toString();
    String jurusan = cmbJurusan.getSelectedItem().toString();

    try {
      Statement stmt = koneksi.createStatement();
      String query =
        "INSERT INTO t_siswa(nis,nama,kelas,jurusan) " +
        "VALUES('" +
        nis +
        "','" +
        nama +
        "','" +
        kelas +
        "','" +
        jurusan +
        "')";
      System.out.println(query);
      int berhasil = stmt.executeUpdate(query);
      if (berhasil == 1) {
        JOptionPane.showMessageDialog(null, " Insert Success");
      } else {
        JOptionPane.showMessageDialog(null, " Insert Failer");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Internal server error");
    }
  }

  // Read
  void showData(String nis) {
    try {
      Statement stmt = koneksi.createStatement();
      String query = "SELECT * FROM t_siswa WHERE nis = '" + nis + "'";
      ResultSet rs = stmt.executeQuery(query);
      rs.first();
      txtNIS.setText(rs.getString("nis"));
      txtNama.setText(rs.getString("nama"));
      cmbKelas.setSelectedItem(rs.getString("kelas"));
      cmbJurusan.setSelectedItem(rs.getString("jurusan"));
    } catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Bad credentials");
    }
  }

  // Edit
  public void EditData() {
    String nis = txtNIS.getText();
    String nama = txtNama.getText();
    String kelas = cmbKelas.getSelectedItem().toString();
    String jurusan = cmbJurusan.getSelectedItem().toString();

    try {
      Statement stmt = koneksi.createStatement();
      String query =
        "UPDATE t_siswa SET nama = '" +
        nama +
        "'," +
        "kelas= '" +
        kelas +
        "'," +
        "jurusan= '" +
        jurusan +
        "' WHERE nis = '" +
        nis +
        "'";
      System.out.println(query);
      int berhasil = stmt.executeUpdate(query);
      if (berhasil == 1) {
        JOptionPane.showMessageDialog(null, " Data has been updated");
      } else {
        JOptionPane.showMessageDialog(null, " Something wrong");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(null, "Bad credentials");
    }
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    lblTitle = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jLabel2 = new javax.swing.JLabel();
    txtNama = new javax.swing.JTextField();
    cmbKelas = new javax.swing.JComboBox<>();
    cmbJurusan = new javax.swing.JComboBox<>();
    btnSimpan = new javax.swing.JButton();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    txtNIS = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

    lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
    lblTitle.setText("Tambah Data");

    jLabel2.setText("NIS");

    txtNama.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          txtNamaActionPerformed(evt);
        }
      }
    );

    cmbKelas.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] { "XI-RPL1", "XI-RPL2", "XI-RPL3", "XI-RPL4", "XI-RPL5" }
      )
    );

    cmbJurusan.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] {
          "Rekayasa Perangkat Lunak",
          "Teknik Komputer dan Jaringan",
          "Multimedia",
          "Teknik Audio Vidio",
          "Teknik Otomasi Industri",
        }
      )
    );
    cmbJurusan.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          cmbJurusanActionPerformed(evt);
        }
      }
    );

    btnSimpan.setText("Simpan");
    btnSimpan.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          btnSimpanActionPerformed(evt);
        }
      }
    );

    jLabel3.setText("Nama Lengkap");

    jLabel4.setText("Kelas");

    jLabel5.setText("Jurusan");

    txtNIS.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          txtNISActionPerformed(evt);
        }
      }
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
      getContentPane()
    );
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator1)
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(lblTitle)
                        .addGroup(
                          layout
                            .createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addGroup(
                              layout
                                .createParallelGroup(
                                  javax.swing.GroupLayout.Alignment.TRAILING
                                )
                                .addComponent(btnSimpan)
                                .addGroup(
                                  layout
                                    .createSequentialGroup()
                                    .addGroup(
                                      layout
                                        .createParallelGroup(
                                          javax.swing.GroupLayout.Alignment.LEADING
                                        )
                                        .addComponent(
                                          jLabel2,
                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                          26,
                                          javax.swing.GroupLayout.PREFERRED_SIZE
                                        )
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel5)
                                    )
                                    .addGap(40, 40, 40)
                                    .addGroup(
                                      layout
                                        .createParallelGroup(
                                          javax.swing.GroupLayout.Alignment.LEADING,
                                          false
                                        )
                                        .addComponent(
                                          cmbJurusan,
                                          0,
                                          223,
                                          Short.MAX_VALUE
                                        )
                                        .addComponent(
                                          cmbKelas,
                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                          129,
                                          javax.swing.GroupLayout.PREFERRED_SIZE
                                        )
                                        .addComponent(txtNama)
                                        .addComponent(
                                          txtNIS,
                                          javax.swing.GroupLayout.PREFERRED_SIZE,
                                          177,
                                          javax.swing.GroupLayout.PREFERRED_SIZE
                                        )
                                    )
                                )
                            )
                        )
                    )
                    .addGap(0, 29, Short.MAX_VALUE)
                )
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGap(23, 23, 23)
            .addComponent(lblTitle)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              jSeparator1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              10,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  jLabel2,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
                .addComponent(
                  txtNIS,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  txtNama,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(jLabel3)
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  cmbKelas,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(jLabel4)
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  cmbJurusan,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(jLabel5)
            )
            .addGap(18, 18, 18)
            .addComponent(btnSimpan)
            .addGap(30, 30, 30)
        )
    );

    pack();
  } // </editor-fold>//GEN-END:initComponents

  private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_txtNamaActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_txtNamaActionPerformed

  private void cmbJurusanActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_cmbJurusanActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_cmbJurusanActionPerformed

  private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_btnSimpanActionPerformed
    // TODO add your handling code here:
    if (action.equals("Edit")) EditData(); else SimpanData();
  } //GEN-LAST:event_btnSimpanActionPerformed

  private void txtNISActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_txtNISActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_txtNISActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {

    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger
        .getLogger(ManageData.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger
        .getLogger(ManageData.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger
        .getLogger(ManageData.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger
        .getLogger(ManageData.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

  }

  private javax.swing.JButton btnSimpan;
  private javax.swing.JComboBox<String> cmbJurusan;
  private javax.swing.JComboBox<String> cmbKelas;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JLabel lblTitle;
  private javax.swing.JTextField txtNIS;
  private javax.swing.JTextField txtNama;
}
