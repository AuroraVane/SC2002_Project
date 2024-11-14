package controller;
import entity.Medicine;
import entity.Pharmacist;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class for PharmacistUI to be used to navigate the pharmacist functions
 */
public class PharmacistUI extends StaffUI{
    private Pharmacist pharmacist;
    private AppointmentOutcomeController appointmentController;
    private MedicationInventoryController medicationInventoryController;
    private ReplenishmentRequestController replenishmentRequestController;
    private AppointmentOutcomeController appointmentOutcomeController;
    /**
     * Pharmacist UI constructor
     * @param pharmacist
     */
    public PharmacistUI(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
        try{
            this.appointmentController = new AppointmentOutcomeController();
            this.medicationInventoryController = new MedicationInventoryController("./TextFiles/Medicine_List.txt");
            this.replenishmentRequestController = new ReplenishmentRequestController("./TextFiles/Replenishment_List.txt");
            this.appointmentOutcomeController=new AppointmentOutcomeController();
        } catch (Exception e){
            System.out.println("Error: Unable to load appointment list from file.");
            e.printStackTrace();
        }
    }

    /**
     *print display UI for Pharmacist
     */
    public void printMenu(){
        System.out.println("=========================================");
        System.out.println("           PHARMACIST MAIN MENU          ");
        System.out.println("=========================================");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Change Password");
        System.out.println("6. Log Out");
        System.out.println("=========================================");
    }

    /**
     * navigate main menu for Pharmacist
     * @param option
     */
    public void navigateMenu(int option){
        switch(option){
            case 1:
                appointmentController.viewAppointmentOutcomeRecord();
                break;
            case 2:
                appointmentOutcomeController.updatePrescriptionStatus();
                break;
            case 3:
                medicationInventoryController.viewMedicationInventory();
                break;
            case 4:
                replenishmentRequestController.MenuController(pharmacist);
                break;
            case 5:
                changePassword(pharmacist);
                break;
            case 6:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
