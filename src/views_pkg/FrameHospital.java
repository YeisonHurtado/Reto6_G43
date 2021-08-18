/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views_pkg;

import java.awt.BorderLayout;
import model_pkg.Conexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model_pkg.Conexion;
import model_pkg.DataModelDB;
import model_pkg.ReportClass;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import views_pkg.DialogClients;

/**
 *
 * @author ydhurtado
 */
public class FrameHospital extends javax.swing.JFrame {
    
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    
    DataModelDB dataModel = new DataModelDB();

    /**
     * Creates new form FrameHospital
     */
    public FrameHospital() {
        initComponents();
        setLocationRelativeTo(null);
        show_hospitals();
        
        btn_update_hospital.setEnabled(false);
        btn_delete_hospital.setEnabled(false);
    }
    
    void show_hospitals(){
        String sql = "SELECT * FROM tb_hospital";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            //Los datos que devuelve la consulta se muestran en la tabla
            Object[]hospital = new Object[3];
            modelo = (DefaultTableModel)tb_hospitals.getModel();
            while(rs.next()){
                hospital[0] = rs.getInt("id");
                hospital[1] = rs.getString("name");
                hospital[2] = rs.getString("address");
                modelo.addRow(hospital);
                System.out.println(rs.getInt("id"));
            }
            tb_hospitals.setModel(modelo);
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    void add_hospital(){
        String name = txt_hospital.getText();
        String address = txt_address.getText();
        
        if (name.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del departamento");
        }else{
            String query = "INSERT INTO `tb_hospital`(name, `address`) VALUES('" + name + "', '" + address + "')";
           
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El hospital ha sido creado");
                clear_rows_table();
                show_hospitals();
            }catch(HeadlessException | SQLException e){
              JOptionPane.showMessageDialog(this, "No se pudo crear el hospital");
            }
        }   
    }
   
    void edit_hospital(){
        //Hacemos nuevamente lectura de los valores contenidos en los JTextField
        //Para identificar si el usuario modifico algún valor
        int id = Integer.parseInt(txt_idHospital.getText());
        String name = txt_hospital.getText();
        String address = txt_address.getText();
        if (name.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del hospital o la dirección");
        }else{
            String query = "UPDATE tb_hospital SET name = '" + name + "', address= '"+address + "' WHERE id = " + id;
            //UPDATE tb_persons SET dni =dni, nombre= 'name' WHERE id = id
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El hospital ha sido modificado con éxito");
                clear_rows_table();
                show_hospitals();
            }catch(HeadlessException | SQLException e){
              JOptionPane.showMessageDialog(this, "No se pudo modificar el hospital");
            }
        }   
    }
   
    void delete_hospital(){
        int fila = tb_hospitals.getSelectedRow();
        int id = Integer.parseInt(txt_idHospital.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado un hospital");
        }else{
            
            System.out.println("ID: " + id);
            String query = "DELETE FROM tb_hospital WHERE id = " + id;
            try{
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El hospital ha sido eliminado con exito");
                clear_rows_table();
                show_hospitals();
            }catch(HeadlessException | SQLException e){
            }
        }
    }
 
    void clear_rows_table(){
        for (int i = 0; i < tb_hospitals.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i-1;
        }
        txt_idHospital.setText("");
        txt_hospital.setText("");
        txt_address.setText("");
    }
    
    private void RefreshReport(int idHospital){
        LinkedList<ReportClass> report = dataModel.ListHospitalPetType(idHospital);
        DefaultPieDataset dataset = new DefaultPieDataset();
        String hospital = "";
        for (ReportClass record: report) {
            hospital = record.getHospital();
            dataset.setValue(record.getPet_type(), record.getAmount_pet_type());
        }
        JFreeChart chart = ChartFactory.createPieChart("Mascotas en la clínica " + hospital, dataset, true, true, true);
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_hospital = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_address = new javax.swing.JTextField();
        txt_idHospital = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_add_hospital = new javax.swing.JButton();
        btn_update_hospital = new javax.swing.JButton();
        btn_delete_hospital = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_openPetHospital = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_hospitals = new javax.swing.JTable();
        btn_consult_clients = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btn_consultPets = new javax.swing.JButton();
        btn_consultDoctors = new javax.swing.JButton();
        panelGrafica = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setText("Nombre");

        jLabel2.setText("Dirección");

        txt_idHospital.setEditable(false);

        jLabel3.setText("ID");

        btn_add_hospital.setText("Agregar hospital");
        btn_add_hospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_add_hospitalActionPerformed(evt);
            }
        });

        btn_update_hospital.setText("Editar información");
        btn_update_hospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_update_hospitalActionPerformed(evt);
            }
        });

        btn_delete_hospital.setText("Eliminar hospital");
        btn_delete_hospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delete_hospitalActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("FORMULARIO DE REGISTRO DE HOSPITALES");

        btn_openPetHospital.setText("Gesitonar Registro de mascotas");
        btn_openPetHospital.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_openPetHospitalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txt_idHospital, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txt_hospital)
                                .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(88, 88, 88))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_openPetHospital)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_add_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_update_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_delete_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_hospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_idHospital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add_hospital)
                    .addComponent(btn_update_hospital)
                    .addComponent(btn_delete_hospital))
                .addGap(18, 18, 18)
                .addComponent(btn_openPetHospital)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_hospitals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Hospital", "Dirección"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_hospitals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_hospitalsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_hospitals);

        btn_consult_clients.setText("Consultar clientes");
        btn_consult_clients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consult_clientsActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("HISTORIAL DE HOSPITALES");

        btn_consultPets.setText("Consultar mascotas");
        btn_consultPets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultPetsActionPerformed(evt);
            }
        });

        btn_consultDoctors.setText("Consultar doctores");
        btn_consultDoctors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultDoctorsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btn_consult_clients)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_consultPets)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_consultDoctors)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_consult_clients)
                    .addComponent(btn_consultPets)
                    .addComponent(btn_consultDoctors))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 304, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 406, Short.MAX_VALUE)
        );

        jLabel6.setText("Selecciona una clínica de la tabla...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(8, 8, 8)
                        .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_consult_clientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consult_clientsActionPerformed
        new DialogClients(this, true).setVisible(true);
    }//GEN-LAST:event_btn_consult_clientsActionPerformed

    private void btn_add_hospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_add_hospitalActionPerformed
        add_hospital();
    }//GEN-LAST:event_btn_add_hospitalActionPerformed

    private void btn_update_hospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_update_hospitalActionPerformed
        edit_hospital();
        btn_add_hospital.setEnabled(true);
        btn_update_hospital.setEnabled(false);
        btn_delete_hospital.setEnabled(false);
    }//GEN-LAST:event_btn_update_hospitalActionPerformed

    private void btn_delete_hospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delete_hospitalActionPerformed
        delete_hospital();
        btn_add_hospital.setEnabled(true);
        btn_update_hospital.setEnabled(false);
        btn_delete_hospital.setEnabled(false);
    }//GEN-LAST:event_btn_delete_hospitalActionPerformed

    private void tb_hospitalsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_hospitalsMouseClicked
        int row = tb_hospitals.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un hospital");
        }else{
            int id  = Integer.parseInt((String)tb_hospitals.getValueAt(row, 0).toString());
            String name  = (String)tb_hospitals.getValueAt(row, 1);
            String address = (String)tb_hospitals.getValueAt(row, 2);
            System.out.println(id + " - " + name + " - " + address);
            txt_idHospital.setText("" + id);
            txt_hospital.setText(name);
            txt_address.setText(address);
            this.RefreshReport(id);
            
            btn_add_hospital.setEnabled(false);
            btn_update_hospital.setEnabled(true);
            btn_delete_hospital.setEnabled(true);
        }
    }//GEN-LAST:event_tb_hospitalsMouseClicked

    private void btn_consultPetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultPetsActionPerformed
        new DialogPets(this, true).setVisible(true);
    }//GEN-LAST:event_btn_consultPetsActionPerformed

    private void btn_consultDoctorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultDoctorsActionPerformed
        new DialogDoctors(this, true).setVisible(true);
    }//GEN-LAST:event_btn_consultDoctorsActionPerformed

    private void btn_openPetHospitalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openPetHospitalActionPerformed
        new DialogPetHospital(this, true).setVisible(true);
    }//GEN-LAST:event_btn_openPetHospitalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrameHospital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameHospital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameHospital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameHospital.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameHospital().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add_hospital;
    private javax.swing.JButton btn_consultDoctors;
    private javax.swing.JButton btn_consultPets;
    private javax.swing.JButton btn_consult_clients;
    private javax.swing.JButton btn_delete_hospital;
    private javax.swing.JButton btn_openPetHospital;
    private javax.swing.JButton btn_update_hospital;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JTable tb_hospitals;
    private javax.swing.JTextField txt_address;
    private javax.swing.JTextField txt_hospital;
    private javax.swing.JTextField txt_idHospital;
    // End of variables declaration//GEN-END:variables
}
