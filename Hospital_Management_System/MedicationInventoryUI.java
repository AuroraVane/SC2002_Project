import java.util.List;

public class MedicationInventoryUI {
    public void printMainMenu() {
        System.out.println("\n1. View Medication Inventory");
        System.out.println("2. Update Medication Inventory");
        System.out.println("3. Back");
    }
    public void printMedicationInventory(List<MedicationInventory> medicationInventoryList) {
        System.out.println("\nMedication Inventory:");
        for (MedicationInventory medicationInventory : medicationInventoryList) {
            System.out.println(medicationInventory.getName() + ", " + medicationInventory.getStock());
        }
    }
}
