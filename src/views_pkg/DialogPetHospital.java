/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views_pkg;

import controller_pkg.ControllerPet;
import controller_pkg.ItemHospital;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model_pkg.Conexion;
import model_pkg.DataDB;
import model_pkg.DataModelDB;
import model_pkg.DataModelHospital;
import model_pkg.DataModelPet;

/**
 *
 * @author ydhurtado
 */
public class DialogPetHospital extends java.awt.Dialog {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultComboBoxModel cb_model = new DefaultComboBoxModel();
    DefaultListModel list_left, list_right;
    
    private int idHospital;
    
    /**
     * Creates new form DialogPetHospital
     */
    public DialogPetHospital(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        loadComboHospital();
        getIdHospital();
        setLeftPets();
        setRightPets();
        
        jList_Registered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        jList_Unregistered.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
    }
    
    void setLeftPets(){
        ControllerPet ctl_pet = new ControllerPet();
        ctl_pet.setLefPet();
        list_left = new DefaultListModel();
        for (int i = 0; i < ctl_pet.getPetCollection().size(); i++) {
            DataModelPet pet = (DataModelPet) ctl_pet.getPetCollection().get(i);
            list_left.addElement(pet);
        }
        jList_Unregistered.setModel(list_left);
        setEnabledButtons(1);
    }
    
    void setRightPets(){
        ControllerPet ctl_pet = new ControllerPet();
        ctl_pet.setRightPet(this.idHospital);
        list_right = new DefaultListModel();
        for (int i = 0; i < ctl_pet.getPetCollection().size(); i++) {
            DataModelPet pet = (DataModelPet) ctl_pet.getPetCollection().get(i);
            list_right.addElement(pet);
        }
        jList_Registered.setModel(list_right);
        setEnabledButtons(2);
    }
    
    void setEnabledButtons(int button){
        if (button == 1) {
            int index = jList_Unregistered.getSelectedIndex();
            if (index < 0) {
                btn_inPut.setEnabled(false);
            } else {
                btn_inPut.setEnabled(true);
            }
        } else if(button == 2){
            int index = jList_Registered.getSelectedIndex();
            if (index < 0) {
                btn_outPut.setEnabled(false);
            } else {
                btn_outPut.setEnabled(true);
            }
        }
    }
    
    void loadComboHospital(){
        ControllerPet ctl_pet = new ControllerPet();
        ctl_pet.setHospital_cb();
        cb_model = ctl_pet.getHospital_cb();
        cb_hospital.setModel(cb_model);
    }
    
    void getIdHospital(){
        DataModelHospital hospital = (DataModelHospital) cb_hospital.getSelectedItem();
        this.idHospital = hospital.getId_hospital();
        setTable();
    }
    
    void setTable(){
        clearTable();
        ControllerPet ctl_pet = new ControllerPet();
        ctl_pet.setTable_model((DefaultTableModel)tb_petsHospital.getModel(), this.idHospital);
        modelo = ctl_pet.getTable_model();
        tb_petsHospital.setModel(modelo);
    }
    
    void clearTable(){
        for (int i = 0; i < tb_petsHospital.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;
        }
    }
    
    void inputPet(){
        int index = jList_Unregistered.getSelectedIndex();
        DataModelPet pet = (DataModelPet) list_left.getElementAt(index);
        ControllerPet ctl_pet = new ControllerPet();
        boolean save = ctl_pet.savePetHospital(this.idHospital, pet.getIdPet());
        if (save) {
            JOptionPane.showMessageDialog(this, "Mascota añadida con éxito.");
            loadComboHospital();
            getIdHospital();
            setTable();
            setRightPets();
            setLeftPets();
        }
    }
    
    void outPut(){
        int index = jList_Registered.getSelectedIndex();
        DataModelPet pet = (DataModelPet) list_right.getElementAt(index);
        ControllerPet ctl_pet = new ControllerPet();
        boolean delete = ctl_pet.deletePetHospital(this.idHospital, pet.getIdPet());
        if (delete) {
            JOptionPane.showMessageDialog(this, "La mascota ha sido retirada del hospital.");
            loadComboHospital();
            getIdHospital();
            setTable();
            setRightPets();
            setLeftPets();
        }
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
        cb_hospital = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList_Unregistered = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_Registered = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_inPut = new javax.swing.JButton();
        btn_outPut = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tb_petsHospital = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setText("Clínica");

        cb_hospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_hospitalActionPerformed(evt);
            }
        });

        jList_Unregistered.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_UnregisteredMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList_Unregistered);

        jList_Registered.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList_RegisteredMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList_Registered);

        jLabel2.setText("No registradas");

        jLabel3.setText("Registradas");

        btn_inPut.setText(">");
        btn_inPut.setEnabled(false);
        btn_inPut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_inPutActionPerformed(evt);
            }
        });

        btn_outPut.setText("<");
        btn_outPut.setEnabled(false);
        btn_outPut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_outPutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_inPut, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_outPut, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btn_inPut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_outPut)))
                .addGap(40, 40, 40))
        );

        tb_petsHospital.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mascota", "Dueño"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tb_petsHospital);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
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

    private void cb_hospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_hospitalActionPerformed
        getIdHospital();
        setRightPets();
        setTable();
    }//GEN-LAST:event_cb_hospitalActionPerformed

    private void jList_UnregisteredMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_UnregisteredMouseClicked
        setEnabledButtons(1);
    }//GEN-LAST:event_jList_UnregisteredMouseClicked

    private void jList_RegisteredMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList_RegisteredMouseClicked
        setEnabledButtons(2);
    }//GEN-LAST:event_jList_RegisteredMouseClicked

    private void btn_inPutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_inPutActionPerformed
        inputPet();
    }//GEN-LAST:event_btn_inPutActionPerformed

    private void btn_outPutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_outPutActionPerformed
        outPut();
    }//GEN-LAST:event_btn_outPutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogPetHospital dialog = new DialogPetHospital(new java.awt.Frame(), true);
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
    private javax.swing.JButton btn_inPut;
    private javax.swing.JButton btn_outPut;
    public javax.swing.JComboBox<Object> cb_hospital;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList_Registered;
    private javax.swing.JList<String> jList_Unregistered;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tb_petsHospital;
    // End of variables declaration//GEN-END:variables
}
