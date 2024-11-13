package entity;
import java.io.*;
import java.util.List;

import utils.TextFileReader;

public class Medicine {
    /**
     *
     */
    private String medicineName;
    private int quantity;
    private int lowQAlert;

    /**
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
     * @return
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @return
     */
    public int getLowQAlert() {
        return lowQAlert;
    }

    /**
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
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
