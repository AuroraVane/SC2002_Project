import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReplenishmentRequestController {
    private List<ReplenishmentRequest> replenishmentRequestList;
    private ReplenishmentRequestUI replenishmentRequestUI;
    private TextFileWriter writer;
    private MedicationInventoryController medicationInventoryController;
    private int nextID;

    public ReplenishmentRequestController(String fileName) throws IOException {
        this.replenishmentRequestList = TextFileReader.loadReplenishmentRequest(fileName);
        this.nextID = replenishmentRequestList.size() + 1;
        this.replenishmentRequestUI = new ReplenishmentRequestUI();
        this.medicationInventoryController = new MedicationInventoryController("./TextFiles/Medicine_List.txt");
    }

    public void MenuController(Staff staff) {
        int option = -1;
        while(option != 3){
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            replenishmentRequestUI.printMainMenu(staff);
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
            navigateMenu(option,staff);
        }
    }

    public void navigateMenu(int option, Staff staff){
        if (staff instanceof Administrator){
            System.out.println("Admin");
            switch(option){
                case 1:
                    viewReplenishmentRequests();
                    break;
                case 2:
                    approveReplenishmentRequests();
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        else if (staff instanceof Pharmacist){
            switch(option){
                case 1:
                    viewReplenishmentRequests();
                    break;
                case 2:
                    submitReplenishmentRequests();
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void viewReplenishmentRequests(){
        replenishmentRequestUI.printReplenishmentRequests(replenishmentRequestList);
    }
    public void approveReplenishmentRequests(){
        String input = replenishmentRequestUI.approveReplenishmentRequests(replenishmentRequestList);
        this.writer = new TextFileWriter();
        String[] output = writer.updateReplenishmentRequest(input);
        medicationInventoryController.updateMedicationInventory(output[0],output[1]);
        try{
            this.replenishmentRequestList = TextFileReader.loadReplenishmentRequest("./TextFiles/Replenishment_List.txt");
        }catch(IOException e){
            System.out.println("Error: Unable to load replenishment request list from file.");
            e.printStackTrace(); // Optional: To print the stack trace for debugging
        }
        
    }
    public void submitReplenishmentRequests(){
        Medicine medicine = replenishmentRequestUI.submitReplenishmentRequests();
        if(medicine != null){
            this.writer = new TextFileWriter(); 
            writer.addReplenishmentRequest(String.valueOf(this.nextID),medicine.getMedicineName(), "Pending", String.valueOf(medicine.getLowQAlert()));
            try{
                this.replenishmentRequestList = TextFileReader.loadReplenishmentRequest("./TextFiles/Replenishment_List.txt");
                nextID++;
            }catch(IOException e){
                System.out.println("Error: Unable to load replenishment request list from file.");
                e.printStackTrace(); // Optional: To print the stack trace for debugging
            }
        }
        else{
            System.out.println("Medicine not found.");
        }
    }
}
