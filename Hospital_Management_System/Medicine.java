import java.io.*;
import java.util.List;

public class Medicine {
    private String medicineName;
    private int quantity;
    private int lowQAlert;

    public Medicine(String medicineName, int quantity, int lowQAlert) {
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.lowQAlert = lowQAlert;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLowQAlert() {
        return lowQAlert;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

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
