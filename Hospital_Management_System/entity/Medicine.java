package entity;
import java.io.*;
import java.util.List;

import utils.TextFileReader;

public class Medicine {
    /**
     * An entity class to represent a medicine that holds the medicine name, quantity and low quantity alert
     */
    private String medicineName;
    private int quantity;
    private int lowQAlert;

    /**
     * medcine constructor
     * @param medicineName
     * @param quantity
     * @param lowQAlert
     */
    public Medicine(String medicineName, int quantity, int lowQAlert) {
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.lowQAlert = lowQAlert;
    }

    /**
     * getter for medicine name
     * @return
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * getter for medicine quantity
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * getter for low quantity alert
     * @return
     */
    public int getLowQAlert() {
        return lowQAlert;
    }

    /**
     * setter for quantity of medicine
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * get all medicine in a list
     * @return
     */
    public static List<Medicine> getAllMedicines(){
        
        String filePath = "./TextFiles/Medicine_List.txt";
        List<Medicine> medicineList;
        try {
             medicineList = TextFileReader.loadMedicineList(filePath);
             return medicineList;
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
