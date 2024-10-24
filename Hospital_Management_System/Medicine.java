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
        
        String filePath = "Medicine_List.txt";
        List<Medicine> medicineList;
        try {
             medicineList = TextFileReader.loadMedicineList(filePath);
             return medicineList;
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    @Override
    public String toString() {
        return medicineName + "|" + quantity + "|" + lowQAlert;
    }

    public static void updateMedicineFile(List<Medicine> medicines) {
        String filePath = "Medicine_List.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Medicine med : medicines) {
                writer.write(med.toString());
                writer.newLine();
            }
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.err.println("Error updating the file: " + e.getMessage());
        }
    }
    
}
