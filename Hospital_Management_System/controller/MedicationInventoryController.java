package controller;
import boundary.MedicationInventoryUI;
import entity.Medicine;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import utils.TextFileWriter;


/**
 * Controller class for MedicationInventoryUI 
 */
public class MedicationInventoryController {
    private List<Medicine> medicationInventoryList;
    private MedicationInventoryUI medicationInventoryUI;
    private TextFileWriter writer;

    /**
     * @param fileName
     * @throws IOException
     */
    public MedicationInventoryController(String fileName) throws IOException {
        medicationInventoryList = Medicine.getAllMedicines();
        this.medicationInventoryUI = new MedicationInventoryUI();
    }

    /**
     * Controller for the menu to manage medication inventory
     */
    public void MenuController() {
        int option = -1;
        while(option != 3){
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            medicationInventoryUI.printMainMenu();
            boolean validInput = false;
            while (!validInput) {
                System.out.println("\nEnter an option: ");
                String input = scanner.nextLine();
                try {
                    option = Integer.parseInt(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
            navigateMenu(option);
        }
    }

    /**
     * @param option
     */
    public void navigateMenu(int option){
        switch(option){
            case 1:
                viewMedicationInventory();
                break;
            case 2:
                updateMedicationInventory();
                break;
            case 3:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Display the medication inventory
     */
    public void viewMedicationInventory(){
        medicationInventoryUI.printMedicationInventory();
    }

    /**
     * Update the medication inventory
     */
    public void updateMedicationInventory(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the medication: ");
        String name = scanner.nextLine();
        System.out.println("Enter the new stock level: ");
        String stock = scanner.nextLine();

        this.writer = new TextFileWriter();
        writer.updateMedicationInventory(name, stock);
        this.medicationInventoryList= Medicine.getAllMedicines();
        
    }

    /**
     * @param name
     * @param stock
     */
    public void updateMedicationInventory(String name,String stock){
        int newstock = -1;
        for (Medicine medicationInventory : medicationInventoryList) {
            if (medicationInventory.getMedicineName().equals(name)) {
                newstock = medicationInventory.getQuantity() + Integer.parseInt(stock);
            }
        }
        this.writer = new TextFileWriter();
        if(newstock != -1){
            writer.updateMedicationInventory(name, String.valueOf(newstock));
        }
    }

    /**
     * @param medicineName
     */
    public static void updateMedicineQuantity(String medicineName){
        List<Medicine> medicines = Medicine.getAllMedicines();
        for (Medicine medicine : medicines){
            if (medicine.getMedicineName().equals(medicineName)){
                if (medicine.getQuantity() > 0){
                    medicine.setQuantity(medicine.getQuantity() -1);
                }else{
                    System.out.println("There is not enough medicine!");
                }
            }
        }
        updateMedicineFile(medicines);
    }

    /**
     * @param medicines
     */
    public static void updateMedicineFile(List<Medicine> medicines) {
        String filePath = "./TextFiles/Medicine_List.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Medicine med : medicines) {
                writer.write(med.getMedicineName() + "|" + med.getQuantity() + "|" + med.getLowQAlert());
                writer.newLine();
            }
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.err.println("Error updating the file: " + e.getMessage());
        }
    }
}