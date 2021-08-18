/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_pkg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model_pkg.Conexion;
import model_pkg.DataModelHospital;
import model_pkg.DataModelPet;

/**
 *
 * @author ydhurtado
 */
public class ControllerPet {
    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    
    private DataModelPet pet;
    private ArrayList<DataModelPet> collection_pets = new ArrayList<>();
    private DefaultComboBoxModel hospital_cb = new DefaultComboBoxModel();
    private DefaultListModel list_pet;
    private DefaultTableModel table_model = new DefaultTableModel();

    public DataModelPet getPet() {
        return pet;
    }

    public void setPet() {
        String query = "SELECT * FROM tb_pet";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //Los datos que devuelve la consulta se muestran en la tabla
            
            while(rs.next()){
                DataModelPet pet_query = new DataModelPet();
                pet_query.setIdPet(rs.getInt("id"));
                pet_query.setPetName(rs.getString("name"));
                pet_query.setIdOwner(rs.getInt("id_owner"));
                pet_query.setPetType(rs.getString("pet_type"));
                this.pet = pet_query;
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public ArrayList<DataModelPet> getPetCollection() {
        return this.collection_pets;
    }
    
    public void setLefPet(){
        String query = "SELECT tb_pet.* "
                + "FROM tb_pet_hospital "
                + "RIGHT JOIN tb_pet "
                + "ON tb_pet_hospital.id_pet = tb_pet.id "
                + "INNER JOIN tb_pet_owners "
                + "ON tb_pet.id_owner_pet = tb_pet_owners.id "
                + "WHERE tb_pet_hospital.id_pet IS NULL;";
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //Los datos que devuelve la consulta se muestran en la tabla
            while(rs.next()){
                DataModelPet pet_query = new DataModelPet();
                pet_query.setIdPet(rs.getInt("tb_pet.id"));
                pet_query.setPetName(rs.getString("tb_pet.name"));
                pet_query.setIdOwner(rs.getInt("tb_pet.id_owner_pet"));
                pet_query.setPetType(rs.getString("tb_pet.pet_type"));
                
                this.collection_pets.add(pet_query);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public void setRightPet(int idHospital){
        String query = "SELECT tb_pet.* "
                + "FROM tb_pet_hospital "
                + "INNER JOIN tb_pet ON tb_pet_hospital.id_pet = tb_pet.id "
                + "INNER JOIN tb_hospital ON tb_pet_hospital.id_hospital = tb_hospital.id "
                + "WHERE tb_hospital.id = " + idHospital;
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //Los datos que devuelve la consulta se muestran en la tabla
            while(rs.next()){
                DataModelPet pet_query = new DataModelPet();
                pet_query.setIdPet(rs.getInt("tb_pet.id"));
                pet_query.setPetName(rs.getString("tb_pet.name"));
                pet_query.setIdOwner(rs.getInt("tb_pet.id_owner_pet"));
                pet_query.setPetType(rs.getString("tb_pet.pet_type"));
                
                this.collection_pets.add(pet_query);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    } 

    public DefaultListModel getList_pet() {
        return list_pet;
    }

    public void setList_pet(DefaultListModel list_pet) {
        this.list_pet = list_pet;
    }

    public DefaultComboBoxModel getHospital_cb() {
        return hospital_cb;
    }

    public void setHospital_cb() {        
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT id, name FROM `tb_hospital`";
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (cn != null) {
                while (rs.next()) {
                    DataModelHospital hospital = new DataModelHospital();
                    hospital.setId_hospital(rs.getInt("id"));
                    hospital.setHospital(rs.getString("name"));
                    this.hospital_cb.addElement(hospital);
                }
            } else {
                JOptionPane.showMessageDialog(null, ".::Error al realizar la consulta::.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ".::Error en la conexión::.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultTableModel getTable_model() {
        return table_model;
    }

    public void setTable_model(DefaultTableModel model, int idHospital) {
        this.table_model = model;
        String  query = "SELECT tb_pet.id, tb_pet.name, tb_pet_owners.owner "
                        + "FROM tb_pet_hospital "
                        + "INNER JOIN tb_pet "
                        + "ON tb_pet_hospital.id_pet = tb_pet.id "
                        + "INNER JOIN tb_hospital "
                        + "ON tb_pet_hospital.id_hospital = tb_hospital.id "
                        + "INNER JOIN tb_pet_owners "
                        + "ON tb_pet.id_owner_pet = tb_pet_owners.id "
                        + "WHERE tb_hospital.id = " + idHospital;
        try{
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(query);
            //Los datos que devuelve la consulta se muestran en la tabla
            while(rs.next()){
                DataModelPet pet_query = new DataModelPet();
                pet_query.setIdPet(rs.getInt("tb_pet.id"));
                pet_query.setPetName(rs.getString("tb_pet.name"));
                String owner = rs.getString("tb_pet_owners.owner");
                
                Object[] petObject = new Object[3];
                petObject[0] = pet_query.getIdPet();
                petObject[1] = pet_query.getPetName();
                petObject[2] = owner;
                this.table_model.addRow(petObject);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public boolean savePetHospital(int idHospital, int idPet){
        boolean saved = false;
        String query = "INSERT INTO tb_pet_hospital (id_hospital, id_pet) VALUES "
                + "("+idHospital+", "
                + ""+idPet+")";
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            int affectRow = st.executeUpdate(query);
            if (affectRow > 0) {
                saved = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return saved;
    }
    
    public boolean deletePetHospital(int idHospital, int idPet){
        boolean deleted = false;
        String query = "DELETE FROM tb_pet_hospital WHERE id_hospital = " + idHospital + " AND id_pet = " + idPet;
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            int affectRow = st.executeUpdate(query);
            if (affectRow > 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return deleted;
    }
}
