/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views_pkg;

import controller_pkg.Controller;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model_pkg.Conexion;
import model_pkg.ReportClass;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ydhurtado
 */
public class DialogPets extends java.awt.Dialog {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    private final Controller controller;

    /**
     * Creates new form DialogPets
     */
    public DialogPets(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        show_pets();
        controller = new Controller(this);
        btn_editPet.setEnabled(false);
        btn_deletePet.setEnabled(false);
        this.RefreshReport();
    }
    
    void show_pets(){
        String sql = "SELECT * FROM tb_pet";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            Object[]pet = new Object[4];
            modelo = (DefaultTableModel)tb_pets.getModel();
            while(rs.next()){
                pet[0] = rs.getInt("id");
                pet[1] = rs.getString("name");
                pet[2] = rs.getString("pet_type");
                pet[3] = rs.getInt("id_owner_pet");
                modelo.addRow(pet);
            }
            tb_pets.setModel(modelo);
        }catch(SQLException e){
            
        }
    }
    
    void add_pet(){
        String name = txt_petName.getText();
        int owner = cb_owner.getSelectedIndex()+1;
        String pet_type = cb_typePet.getSelectedItem().toString();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del departamento");
        }else{
            System.out.println(name + " - " + pet_type + " - "+ 1);
            String query = "INSERT INTO `tb_pet`(name, `pet_type`,id_owner_pet) VALUES('" + name + "', '" + pet_type + "',"+ owner+")";
           
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La mascota ha sido creada con éxito");
                clear_rows_table();
                show_pets();
            }catch(HeadlessException | SQLException e){
              JOptionPane.showMessageDialog(this, "No se pudo crear la mascota");
            }
        }   
    }
    
    void edit_pet(){
        int id = Integer.parseInt(txt_idPet.getText());
        String name = txt_petName.getText();
        int owner = cb_owner.getSelectedIndex()+1;
        String pet_type = cb_typePet.getSelectedItem().toString();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre de la mascota");
        }else{
            String query = "UPDATE tb_pet SET name = '" + name + "', pet_type= '"+pet_type + "', id_owner_pet ="+owner+" WHERE id = " + id;
            //UPDATE tb_persons SET dni =dni, nombre= 'name' WHERE id = id
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La información de la mascota ha sido modificada con éxito");
                clear_rows_table();
                show_pets();
            }catch(HeadlessException | SQLException e){
              JOptionPane.showMessageDialog(this, "No se pudo modificar la información de la mascota");
            }
        }   
    }
    
    void delete_pet(){
        int fila = tb_pets.getSelectedRow();
        int id = Integer.parseInt(txt_idPet.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado una mascotica");
        }else{
            
            System.out.println("ID: " + id);
            String query = "DELETE FROM tb_pet WHERE id = " + id;
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La mascota ha sido eliminado con exito");
                clear_rows_table();
                show_pets();
            }catch(HeadlessException | SQLException e){
            }
        }
    }

    void clear_rows_table(){
        for (int i = 0; i < tb_pets.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
        txt_idPet.setText("");
        txt_petName.setText("");
        cb_owner.setSelectedIndex(0);
        cb_typePet.setSelectedIndex(0);
    }
    
    private void RefreshReport() {
        LinkedList<ReportClass> report = controller.ListPetType();
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (ReportClass record: report) {
            dataset.setValue(record.getPet_type(), record.getAmount_pet_type());
        }
        JFreeChart chart = ChartFactory.createPieChart("Tipos de mascotas", dataset, true, true, true);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true);
        panelGrafica.setLayout(new java.awt.BorderLayout());
        panelGrafica.add(panel, BorderLayout.CENTER);
        panelGrafica.validate();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_petName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cb_owner = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cb_typePet = new javax.swing.JComboBox<>();
        txt_idPet = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pets = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btn_addPet = new javax.swing.JButton();
        btn_editPet = new javax.swing.JButton();
        btn_deletePet = new javax.swing.JButton();
        panelGrafica = new javax.swing.JPanel();

        setBackground(new java.awt.Color(204, 255, 204));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("REGISTRO DE MASCOTAS");

        jLabel2.setText("Nombre mascota");

        jLabel3.setText("Dueño");

        jLabel4.setText("Tipo de mascota");

        cb_typePet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Perro", "Gato" }));

        txt_idPet.setEditable(false);

        jLabel7.setText("ID");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/public_pkg/mascotas.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(7, 7, 7))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cb_typePet, 0, 266, Short.MAX_VALUE)
                            .addComponent(txt_petName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_owner, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_idPet, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_petName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cb_owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_typePet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_idPet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("HISTORIAL DE MASCOTAS");

        tb_pets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mascota", "Tipo", "Dueño"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_pets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_petsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pets);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(272, 272, 272)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_addPet.setText("Agregar mascota");
        btn_addPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addPetActionPerformed(evt);
            }
        });

        btn_editPet.setText("Editar información");
        btn_editPet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editPetActionPerformed(evt);
            }
        });

        btn_deletePet.setText("Eliminar mascota");
        btn_deletePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deletePetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(btn_addPet)
                .addGap(47, 47, 47)
                .addComponent(btn_editPet)
                .addGap(44, 44, 44)
                .addComponent(btn_deletePet)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_addPet)
                    .addComponent(btn_editPet)
                    .addComponent(btn_deletePet))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelGrafica, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tb_petsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_petsMouseClicked
        // TODO add your handling code here:
        int row = tb_pets.getSelectedRow();
        System.out.println(row);
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar una mascota");
        }else{
            int id  = Integer.parseInt(tb_pets.getValueAt(row, 0).toString());
            String name  = (String)tb_pets.getValueAt(row, 1);
            String pet_type = (String)tb_pets.getValueAt(row, 2);
            int id_owner  = Integer.parseInt(tb_pets.getValueAt(row, 3).toString());
          
            System.out.println(id + " - " + name + " - " + pet_type + " - " + id_owner);
            txt_idPet.setText("" + id);
            txt_petName.setText(name);
            cb_owner.setSelectedIndex(id_owner-1);
            cb_typePet.setSelectedItem(pet_type);
            
            btn_addPet.setEnabled(false);
            btn_editPet.setEnabled(true);
            btn_deletePet.setEnabled(true);
        }
    }//GEN-LAST:event_tb_petsMouseClicked

    private void btn_addPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addPetActionPerformed
        add_pet();
        this.RefreshReport();
    }//GEN-LAST:event_btn_addPetActionPerformed

    private void btn_editPetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editPetActionPerformed
        edit_pet();
        btn_addPet.setEnabled(true);
        btn_editPet.setEnabled(false);
        btn_deletePet.setEnabled(false);
        this.RefreshReport();
    }//GEN-LAST:event_btn_editPetActionPerformed

    private void btn_deletePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deletePetActionPerformed
        delete_pet();
        btn_addPet.setEnabled(true);
        btn_editPet.setEnabled(false);
        btn_deletePet.setEnabled(false);
        this.RefreshReport();
    }//GEN-LAST:event_btn_deletePetActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogPets dialog = new DialogPets(new java.awt.Frame(), true);
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
    private javax.swing.JButton btn_addPet;
    private javax.swing.JButton btn_deletePet;
    private javax.swing.JButton btn_editPet;
    public javax.swing.JComboBox<Object> cb_owner;
    private javax.swing.JComboBox<String> cb_typePet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JTable tb_pets;
    private javax.swing.JTextField txt_idPet;
    private javax.swing.JTextField txt_petName;
    // End of variables declaration//GEN-END:variables
}
