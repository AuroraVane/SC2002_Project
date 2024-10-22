import java.io.IOException;

public class AdministratorUI implements UserUI{
    private Administrator administrator;

    private ManageStaffController manageStaffController;
    private AppointmentController appointmentController;
    private MedicationInventoryController medicationInventoryController;

    public AdministratorUI(Administrator administrator){
        this.administrator = administrator;
        try {
            this.manageStaffController = new ManageStaffController("Staff_List.txt");
            this.appointmentController = new AppointmentController("Appointment_List.txt");
            this.medicationInventoryController = new MedicationInventoryController("Medicine_List.txt");
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
        System.out.println("5. Register New Staff");
        System.out.println("6. Log Out");
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
                skeletonMedicationInventory();//approveReplenishmentRequests();
                break;
            case 5:
                break;
            case 6:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    public void skeletonAppointment(){
        System.out.println("Skeleton for Appointment");
    }
    public void skeletonMedicationInventory(){
        System.out.println("Skeleton for Medication Inventory");
    }
}
