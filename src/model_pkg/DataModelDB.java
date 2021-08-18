/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_pkg;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author ydhurtado
 */
public class DataModelDB {
    public ArrayList<DataDB> getOwners(int idOwner) {
        Conexion con = new Conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
        DataDB datosDB;
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT id, owner FROM `tb_pet_owners`";
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (cn != null) {
                System.out.println("Conexión éxitosa DataModelDB");
                while (rs.next()) {
                    datosDB = new DataDB();
                    datosDB.setIdOwner(rs.getInt("id"));
                    datosDB.setOwner(rs.getString("owner"));
                    list.add(datosDB);
                }
            } else {
                JOptionPane.showMessageDialog(null, ".::Error al realizar la consulta::.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ".::Error en la conexión::.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
     
    public ArrayList<DataDB>getHospitals(int idHospital) {
        Conexion con = new Conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
        DataDB datosDB;
        ArrayList list = new ArrayList();
        try {
            String sql = "SELECT id, name FROM `tb_hospital` WHERE 1";
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (cn != null) {
                System.out.println("Conexión éxitosa DataModelDB");
                while (rs.next()) {
                    datosDB = new DataDB();
                    datosDB.setId_hospital(rs.getInt("id"));
                    datosDB.setHospital(rs.getString("name"));
                    list.add(datosDB);
                }
            } else {
                JOptionPane.showMessageDialog(null, ".::Error al realizar la consulta::.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ".::Error en la conexión::.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return list;
    }
    
    public LinkedList<ReportClass> ListPetType() {
        Conexion con = new Conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
        DataDB datosDB;
        
        LinkedList<ReportClass> report = new LinkedList<>();
        try {
                String sql = "SELECT pet_type,count(pet_type) FROM `tb_pet` GROUP BY pet_type";
                cn = con.getConnection();
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if (cn != null) {
                    System.out.println("Conexión éxitosa DataModelDB");
                    while (rs.next()) {
                        ReportClass record = new ReportClass();
                        String pet_type = rs.getString(1);
                        int amount = rs.getInt(2);
                        record.setPet_type(pet_type);
                        record.setAmount_pet_type(amount);
                        report.add(record);
                }
            } else {
                JOptionPane.showMessageDialog(null, ".::Error al realizar la consulta::.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ".::Error en la conexión::.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return report;
    }
    
    public LinkedList<ReportClass> ListHospitalPetType(int idHospital) {
        Conexion con = new Conexion();
        Connection cn;
        Statement st;
        ResultSet rs;
        DataDB datosDB;
        
        LinkedList<ReportClass> report = new LinkedList<>();
        try {
                String sql = "SELECT tb_pet.pet_type, count(tb_pet.pet_type), tb_hospital.name "
                        + "FROM tb_pet_hospital "
                        + "INNER JOIN tb_pet "
                        + "ON tb_pet_hospital.id_pet = tb_pet.id "
                        + "INNER JOIN tb_hospital "
                        + "ON tb_pet_hospital.id_hospital = tb_hospital.id "
                        + "WHERE tb_hospital.id = " + idHospital + " "
                        + "GROUP BY tb_pet.pet_type;";
                cn = con.getConnection();
                st = cn.createStatement();
                rs = st.executeQuery(sql);
                if (cn != null) {
                    System.out.println("Conexión éxitosa DataModelDB");
                    while (rs.next()) {
                        ReportClass record = new ReportClass();
                        String pet_type = rs.getString(1);
                        int amount = rs.getInt(2);
                        String hospital = rs.getString(3);
                        record.setPet_type(pet_type);
                        record.setAmount_pet_type(amount);
                        record.setHospital(hospital);
                        report.add(record);
                }
            } else {
                JOptionPane.showMessageDialog(null, ".::Error al realizar la consulta::.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, ".::Error en la conexión::.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return report;
    }
}
