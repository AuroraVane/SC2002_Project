import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReplenishmentRequestController {
    private List<ReplenishmentRequest> replenishmentRequestList;
    private ReplenishmentRequestUI replenishmentRequestUI;
    private TextFileWriter writer;
    private MedicationInventoryController medicationInventoryController;

    public ReplenishmentRequestController(String fileName) throws IOException {
        this.replenishmentRequestList = TextFileReader.loadReplenishmentRequest(fileName);
        this.replenishmentRequestUI = new ReplenishmentRequestUI();
        this.medicationInventoryController = new MedicationInventoryController("Medicine_List.txt");
    }

    public void MenuController() {
        int option = -1;
        while(option != 3){
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            replenishmentRequestUI.printMainMenu();
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
    public void viewReplenishmentRequests(){
        replenishmentRequestUI.printReplenishmentRequests(replenishmentRequestList);
    }
    public void approveReplenishmentRequests(){
        String input = replenishmentRequestUI.approveReplenishmentRequests(replenishmentRequestList);
        this.writer = new TextFileWriter();
        String[] output = writer.updateReplenishmentRequest(input);
        medicationInventoryController.updateMedicationInventory(output[0],output[1]);
        try{
            this.replenishmentRequestList = TextFileReader.loadReplenishmentRequest("Replenishment_List.txt");
        }catch(IOException e){
            System.out.println("Error: Unable to load replenishment request list from file.");
            e.printStackTrace(); // Optional: To print the stack trace for debugging
        }
        
    }
}
