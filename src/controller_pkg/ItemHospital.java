/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_pkg;

/**
 *
 * @author ydhurtado
 */
public class ItemHospital {
    private int id_hospital;
    private String hospital;

    public ItemHospital(int id_hospital, String hospital) {
        this.id_hospital = id_hospital;
        this.hospital = hospital;
    }

    public int getId_hospital() {
        return id_hospital;
    }

    public void setId_hospital(int id_hospital) {
        this.id_hospital = id_hospital;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    @Override
    public String toString(){
        return getHospital();
    }
}
