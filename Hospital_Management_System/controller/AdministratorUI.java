package controller;
import java.io.IOException;

import entity.Administrator;

public class AdministratorUI extends StaffUI{
    private Administrator administrator;

    private ManageStaffController manageStaffController;
    private AppointmentController appointmentController;
    private MedicationInventoryController medicationInventoryController;
    private ReplenishmentRequestController replenishmentRequestController;

    public AdministratorUI(Administrator administrator){
        this.administrator = administrator;
        try {
            this.manageStaffController = new ManageStaffController("./TextFiles/Staff_List.txt");
            this.appointmentController = new AppointmentController("./TextFiles/Appointment_List.txt");
            this.medicationInventoryController = new MedicationInventoryController("./TextFiles/Medicine_List.txt");
            this.replenishmentRequestController = new ReplenishmentRequestController("./TextFiles/Replenishment_List.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to load staff list from file.");
            e.printStackTrace(); // Optional: To print the stack trace for debugging
        }
    }
    public void printMenu(){
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointment Details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Approve Password Reset Requests");
        System.out.println("6. Change Password");
        System.out.println("7. Log Out");
    }
    public void navigateMenu(int option){
        switch(option){
            case 1:
                manageStaffController.MenuController();
                break;
            case 2:
                appointmentController.MenuController();
                break;
            case 3:
                medicationInventoryController.MenuController();
                break;
            case 4:
                replenishmentRequestController.MenuController(administrator);
                break;
            case 5:
                Login.approvePasswordResetRequests();
                break;
            case 6:
                changePassword(administrator);
                break;
            case 7:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
