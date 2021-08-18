/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model_pkg;

/**
 *
 * @author ydhurtado
 */
public class ReportClass {
    String pet_type;
    int amount_pet_type;
    String hospital;

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    

    public String getPet_type() {
        return pet_type;
    }

    public void setPet_type(String pet_type) {
        this.pet_type = pet_type;
    }

    public int getAmount_pet_type() {
        return amount_pet_type;
    }

    public void setAmount_pet_type(int amount_pet_type) {
        this.amount_pet_type = amount_pet_type;
    }
}
