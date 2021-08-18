/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_pkg;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import model_pkg.DataDB;
import model_pkg.DataModelDB;
import model_pkg.ReportClass;
import views_pkg.DialogPets;

/**
 *
 * @author ydhurtado
 */
public class Controller implements ActionListener{
    private final DialogPets view;
    DataModelDB model = new DataModelDB();

    public final void events() {
        view.cb_owner.addActionListener(this);
    }
    
    public Controller(DialogPets view) {
        this.view = view;
        getOwners();
        Item item = (Item) view.cb_owner.getSelectedItem();
        events();
    }

    public final void getOwners() {
        ArrayList<DataDB> list;
        DataModelDB model = new DataModelDB();
        list = model.getOwners(0);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                int idOwner = list.get(i).getIdOwner();
                String owner = list.get(i).getOwner();
                view.cb_owner.addItem(new Item(idOwner, owner));
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron registro de clientes", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public LinkedList<ReportClass> ListPetType() {
        LinkedList<ReportClass> report = this.model.ListPetType();
        System.out.println(report);
        return report;
    }
    
    public LinkedList<ReportClass> ListHospitalPetType(int idHospital) {
        LinkedList<ReportClass> report = this.model.ListHospitalPetType(idHospital);
        System.out.println(report);
        return report;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
