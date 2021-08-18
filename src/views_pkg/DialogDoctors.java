/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views_pkg;

import controller_pkg.ControllerDoctor;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model_pkg.Conexion;

/**
 *
 * @author ydhurtado
 */
public class DialogDoctors extends java.awt.Dialog {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    private final ControllerDoctor controller;
    /**
     * Creates new form DialogDoctors
     */
    public DialogDoctors(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        show_doctors();
        btn_editDoctor.setEnabled(false);
        btn_deleteDoctor.setEnabled(false);
        controller = new ControllerDoctor(this);
    }
    
    void show_doctors() {
        String sql = "SELECT * FROM tb_doctor";
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[] doctor = new Object[5];
            modelo = (DefaultTableModel) tb_doctors.getModel();
            while (rs.next()) {
                doctor[0] = rs.getInt("id");
                doctor[1] = rs.getString("doctor");
                doctor[2] = rs.getString("document_type");
                doctor[3] = rs.getInt("document");
                doctor[4] = rs.getInt("id_hospital");
                modelo.addRow(doctor);
                System.out.println(rs.getInt("id"));
            }
            tb_doctors.setModel(modelo);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void add_doctor() {
        String name = txt_nameDoctor.getText();
        String document_type= cb_documentType.getSelectedItem().toString();
        int document = Integer.parseInt(txt_document.getText());
        int id_hospital = cb_hospital.getSelectedIndex() + 1;
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del Dr.");
        } else {
            System.out.println(name + " - " + id_hospital);
            String query = "INSERT INTO `tb_doctor`(`doctor`, `document_type`, `document`, `id_hospital`) VALUES('" + name + "', '" + document_type + "'," + document + ","+id_hospital + ")";

            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El doctor ha sido creado con éxito");
                clear_rows_table();
                show_doctors();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo crear el doctor");
            }
        }
    }

    void edit_doctor() {
        int id = Integer.parseInt(txt_idDoctor.getText());
        String name = txt_nameDoctor.getText();
        String document_type = cb_documentType.getSelectedItem().toString();
        int document = Integer.parseInt(txt_document.getText());
        int id_hospital = cb_hospital.getSelectedIndex() + 1;
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del doctor");
        } else {
            String query = "UPDATE tb_doctor SET doctor = '" + name + "', document_type= '" + document_type + "', document =" + document + ", id_hospital =" +id_hospital +" WHERE id = " + id;
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La información del doctor ha sido modificada con éxito");
                clear_rows_table();
                show_doctors();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo modificar la información del doctor");
            }
        }
    }

    void delete_doctor() {
        int fila = tb_doctors.getSelectedRow();
        int id = Integer.parseInt(txt_idDoctor.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado un doctor");
        } else {
            String query = "DELETE FROM tb_doctor WHERE id = " + id;
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El doctor ha sido eliminado con exito");
                clear_rows_table();
                show_doctors();
            } catch (HeadlessException | SQLException e) {
            }
        }
    }

    void clear_rows_table() {
        for (int i = 0; i < tb_doctors.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
        txt_document.setText("");
        txt_idDoctor.setText("");
        txt_nameDoctor.setText("");
        cb_documentType.setSelectedIndex(0);
        cb_hospital.setSelectedIndex(0);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_nameDoctor = new javax.swing.JTextField();
        txt_document = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_documentType = new javax.swing.JComboBox<>();
        cb_hospital = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_idDoctor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_saveDoctor = new javax.swing.JButton();
        btn_editDoctor = new javax.swing.JButton();
        btn_deleteDoctor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_doctors = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("REGISTRO DE DOCTORES");

        jLabel2.setText("Nombre");

        jLabel3.setText("Documento");

        jLabel4.setText("Tipo de documento");

        cb_documentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C.C.", "C. E.", "Pasaporte" }));

        jLabel5.setText("Clínica");

        jLabel6.setText("ID");

        txt_idDoctor.setEditable(false);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/public_pkg/veterinaria.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(jLabel1)
                .addContainerGap(300, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_document)
                            .addComponent(cb_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_idDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_documentType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_nameDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_nameDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cb_documentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_document, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cb_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_idDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btn_saveDoctor.setText("Agregar cliente");
        btn_saveDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveDoctorActionPerformed(evt);
            }
        });

        btn_editDoctor.setText("Editar información");
        btn_editDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editDoctorActionPerformed(evt);
            }
        });

        btn_deleteDoctor.setText("Eliminar cliente");
        btn_deleteDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteDoctorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(btn_saveDoctor)
                .addGap(18, 18, 18)
                .addComponent(btn_editDoctor)
                .addGap(18, 18, 18)
                .addComponent(btn_deleteDoctor)
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_saveDoctor)
                    .addComponent(btn_editDoctor)
                    .addComponent(btn_deleteDoctor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("HISTORIAL DE DOCTORES");

        tb_doctors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo documento", "No. documento", "Clínica"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_doctors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_doctorsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_doctors);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btn_saveDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveDoctorActionPerformed
        add_doctor();
    }//GEN-LAST:event_btn_saveDoctorActionPerformed

    private void btn_editDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editDoctorActionPerformed
        edit_doctor();
        btn_saveDoctor.setEnabled(true);
        btn_editDoctor.setEnabled(false);
        btn_deleteDoctor.setEnabled(false);
    }//GEN-LAST:event_btn_editDoctorActionPerformed

    private void btn_deleteDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteDoctorActionPerformed
        delete_doctor();
        btn_saveDoctor.setEnabled(true);
        btn_editDoctor.setEnabled(false);
        btn_deleteDoctor.setEnabled(false);
    }//GEN-LAST:event_btn_deleteDoctorActionPerformed

    private void tb_doctorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_doctorsMouseClicked
        int row = tb_doctors.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un doctor");
        }else{
            int id  = Integer.parseInt(tb_doctors.getValueAt(row, 0).toString());
            String name  =tb_doctors.getValueAt(row, 1).toString();
            String document_type = tb_doctors.getValueAt(row, 2).toString();
            int document  = Integer.parseInt(tb_doctors.getValueAt(row, 3).toString());
            int hospital  =Integer.parseInt(tb_doctors.getValueAt(row, 4).toString());
            txt_idDoctor.setText("" + id);
            txt_nameDoctor.setText(name);
            txt_document.setText("" + document);
            cb_documentType.setSelectedItem(document_type);
            cb_hospital.setSelectedIndex(hospital-1);
            
            btn_saveDoctor.setEnabled(false);
            btn_editDoctor.setEnabled(true);
            btn_deleteDoctor.setEnabled(true);
        }
    }//GEN-LAST:event_tb_doctorsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogDoctors dialog = new DialogDoctors(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_deleteDoctor;
    private javax.swing.JButton btn_editDoctor;
    private javax.swing.JButton btn_saveDoctor;
    private javax.swing.JComboBox<String> cb_documentType;
    public javax.swing.JComboBox<Object> cb_hospital;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_doctors;
    private javax.swing.JTextField txt_document;
    private javax.swing.JTextField txt_idDoctor;
    private javax.swing.JTextField txt_nameDoctor;
    // End of variables declaration//GEN-END:variables
}
