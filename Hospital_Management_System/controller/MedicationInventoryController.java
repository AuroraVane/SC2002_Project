package controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import boundary.MedicationInventoryUI;
import entity.MedicationInventory;
import entity.Medicine;
import utils.TextFileReader;
import utils.TextFileWriter;


public class MedicationInventoryController {
    private List<MedicationInventory> medicationInventoryList;
    private MedicationInventoryUI medicationInventoryUI;
    private TextFileWriter writer;

    public MedicationInventoryController(String fileName) throws IOException {
        medicationInventoryList = TextFileReader.loadMedicationInventory(fileName);
        this.medicationInventoryUI = new MedicationInventoryUI();
    }

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
    public void viewMedicationInventory(){
        medicationInventoryUI.printMedicationInventory();
    }
    public void updateMedicationInventory(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the medication: ");
        String name = scanner.nextLine();
        System.out.println("Enter the new stock level: ");
        String stock = scanner.nextLine();

        this.writer = new TextFileWriter();
        writer.updateMedicationInventory(name, stock);
        try{
            medicationInventoryList = TextFileReader.loadMedicationInventory("./TextFiles/Medicine_List.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to load medication inventory list from file.");
            e.printStackTrace(); // Optional: To print the stack trace for debugging
        }
    }
    
    public void updateMedicationInventory(String name,String stock){
        int newstock = -1;
        for (MedicationInventory medicationInventory : medicationInventoryList) {
            if (medicationInventory.getName().equals(name)) {
                newstock = Integer.parseInt(medicationInventory.getStock()) + Integer.parseInt(stock);
            }
        }
        this.writer = new TextFileWriter();
        if(newstock != -1){
            writer.updateMedicationInventory(name, String.valueOf(newstock));
        }
    }
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