/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views_pkg;

import model_pkg.Conexion;
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
public class DialogClients extends java.awt.Dialog {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    
    /**
     * Creates new form DialogClients
     */
    public DialogClients(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        show_owners();
        
        btn_editClient.setEnabled(false);
        btn_deleteClient.setEnabled(false);
    }
    
    void show_owners() {
        String sql = "SELECT * FROM `tb_pet_owners`";
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[] owner = new Object[6];
            modelo = (DefaultTableModel) tb_owners.getModel();
            while (rs.next()) {
                owner[0] = rs.getInt("id");
                owner[1] = rs.getString("owner");
                owner[2] = rs.getString("document_type");
                owner[3] = rs.getInt("document");
                owner[4] = rs.getInt("contact");
                owner[5] = rs.getString("gender");
                modelo.addRow(owner);
            }
            tb_owners.setModel(modelo);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void add_owner() {
        String gender = "";
        String name = txt_owner.getText();
        String document_type = cb_document_type.getSelectedItem().toString();
        int document = Integer.parseInt(txt_document.getText());
        int contact = Integer.parseInt(txt_contact.getText());

        if (rb_f.isSelected()) {
            gender = "Femenino";
        } else {
            gender = "Masculino";
        }

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del dueño de la mascota");
        } else {
            String query = "INSERT INTO `tb_pet_owners`(`owner`, `document_type`, `document`, `contact`, `gender`) VALUES('" + name + "', '" + document_type + "'," + document + "," + contact + ", '" + gender + "')";

            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El usuario ha sido creado");
                clear_rows_table();
                show_owners();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo crear el usuario");
            }
        }
    }

    void edit_owner() {
        //Hacemos nuevamente lectura de los valores contenidos en los JTextField
        //Para identificar si el usuario modifico algún valor
        int id = Integer.parseInt(txt_id.getText());
        String name = txt_owner.getText();
        String document_type = cb_document_type.getSelectedItem().toString();
        int document = Integer.parseInt(txt_document.getText());
        int contact = Integer.parseInt(txt_contact.getText());
        String gender;
        if (rb_f.isSelected()) {
            gender = "Femenino";
        } else {
            gender = "Masculino";
        }
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del dueño");
        } else {
            String query = "UPDATE tb_pet_owners SET name = '" + name + "', document_type= '" + document_type + "', document = " + document + ", contact=" + contact + ",gender= '" + gender + "' WHERE id = " + id;
            //UPDATE tb_persons SET dni =dni, nombre= 'name' WHERE id = id
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Datos del dueño modificados con éxito");
                clear_rows_table();
                show_owners();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo modificar la información del dueño");
            }
        }
    }

    void delete_owner() {
        int fila = tb_owners.getSelectedRow();
        int id = Integer.parseInt(txt_id.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado un dueño de mascota");
        } else {

            String query = "DELETE FROM tb_pet_owners WHERE id = " + id;
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El dueño ha sido eliminado con exito");
                clear_rows_table();
                show_owners();
            } catch (HeadlessException | SQLException e) {
            }
        }
    }

    void clear_rows_table() {
        for (int i = 0; i < tb_owners.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
        txt_id.setText("");
        txt_document.setText("");
        txt_owner.setText("");
    }
    
    void get_client(){
        int row = tb_owners.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un dueño");
        }else{
            int id  = Integer.parseInt((String)tb_owners.getValueAt(row, 0).toString());
            String name  = (String)tb_owners.getValueAt(row, 1);
            String document_type = (String)tb_owners.getValueAt(row, 2);
            int document  = Integer.parseInt((String)tb_owners.getValueAt(row, 3).toString());
            int contact  = Integer.parseInt((String)tb_owners.getValueAt(row, 4).toString());
            String gender = tb_owners.getValueAt(row, 5).toString();
            txt_id.setText("" + id);
            txt_owner.setText(name);
            txt_document.setText(document_type);
            txt_document.setText("" + document);
            txt_contact.setText("" +contact);
            cb_document_type.setSelectedItem(document_type);
            if(gender.equals("Femenino"))
                rb_f.setSelected(true);
            else if (gender.equals("Masculino"))
                rb_m.setSelected(true);
            btn_saveClient.setEnabled(false);
            btn_editClient.setEnabled(true);
            btn_deleteClient.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btng_gender = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_owner = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cb_document_type = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txt_document = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_contact = new javax.swing.JTextField();
        rb_m = new javax.swing.JRadioButton();
        rb_f = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        btn_saveClient = new javax.swing.JButton();
        btn_editClient = new javax.swing.JButton();
        btn_deleteClient = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_owners = new javax.swing.JTable();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("FORMULARIO REGISTRO DE DUEÑO");

        jLabel2.setText("Nombre");

        jLabel3.setText("Tipo de documento");

        cb_document_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C.C.", "T.I.", "C.E.", "Pasaporte" }));

        jLabel4.setText("Número de documento");

        jLabel5.setText("Contacto");

        btng_gender.add(rb_m);
        rb_m.setText("Masculino");
        rb_m.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_mActionPerformed(evt);
            }
        });

        btng_gender.add(rb_f);
        rb_f.setText("Femenino");

        jLabel6.setText("Género");

        btn_saveClient.setText("Agregar cliente");
        btn_saveClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveClientActionPerformed(evt);
            }
        });

        btn_editClient.setText("Editar información");
        btn_editClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editClientActionPerformed(evt);
            }
        });

        btn_deleteClient.setText("Eliminar cliente");
        btn_deleteClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteClientActionPerformed(evt);
            }
        });

        jLabel7.setText("ID");

        txt_id.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_saveClient)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editClient)
                        .addGap(18, 18, 18)
                        .addComponent(btn_deleteClient))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_document, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_owner, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_document_type, javax.swing.GroupLayout.Alignment.LEADING, 0, 243, Short.MAX_VALUE)
                            .addComponent(txt_contact, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel6))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rb_m)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rb_f))
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cb_document_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_document, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_m)
                    .addComponent(rb_f)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_saveClient)
                    .addComponent(btn_editClient)
                    .addComponent(btn_deleteClient))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        tb_owners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Tipo documento", "Número", "Contacto", "Género"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_owners.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ownersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_owners);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void rb_mActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_mActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_mActionPerformed

    private void btn_saveClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_saveClientActionPerformed

    private void tb_ownersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ownersMouseClicked
        if (evt.getClickCount() == 2) {
            get_client();
        }
    }//GEN-LAST:event_tb_ownersMouseClicked

    private void btn_editClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editClientActionPerformed
        edit_owner();
        btn_saveClient.setEnabled(true);
        btn_editClient.setEnabled(false);
        btn_deleteClient.setEnabled(false);
    }//GEN-LAST:event_btn_editClientActionPerformed

    private void btn_deleteClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteClientActionPerformed
        delete_owner();
        btn_saveClient.setEnabled(true);
        btn_editClient.setEnabled(false);
        btn_deleteClient.setEnabled(false);
    }//GEN-LAST:event_btn_deleteClientActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogClients dialog = new DialogClients(new java.awt.Frame(), true);
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
    private javax.swing.JButton btn_deleteClient;
    private javax.swing.JButton btn_editClient;
    private javax.swing.JButton btn_saveClient;
    private javax.swing.ButtonGroup btng_gender;
    private javax.swing.JComboBox<String> cb_document_type;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rb_f;
    private javax.swing.JRadioButton rb_m;
    private javax.swing.JTable tb_owners;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_document;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_owner;
    // End of variables declaration//GEN-END:variables
}
