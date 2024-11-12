package boundary;
import java.util.List;

import entity.Medicine;

public class MedicationInventoryUI {
    public void printMainMenu() {
        System.out.println("=========================================");
        System.out.println("\n      1. View Medication Inventory");
        System.out.println("      2. Update Medication Inventory");
        System.out.println("      3. Back");
        System.out.println("\n=========================================");
    }
    public void printMedicationInventory() {
        List <Medicine> medicineList = Medicine.getAllMedicines();
        System.out.println("\nMedication Inventory:");
        System.out.println("=========================================");
        for (Medicine medicine : medicineList){
            System.out.println(String.format("Medicine: %s", medicine.getMedicineName()));
            System.out.println(medicine.getQuantity() > medicine.getLowQAlert() ? String.format("Quanity: %d", medicine.getQuantity()) : String.format("Quanity: %d (Low Quantity. Please Top Up)", medicine.getQuantity()));
            System.out.println(String.format("Low Quantity Alert: %d \n", medicine.getLowQAlert()));
        }
    }
}
