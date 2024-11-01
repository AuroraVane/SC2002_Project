import java.util.Scanner;
import java.util.List;

public class PharmacistUI implements UserUI{
    private Pharmacist pharmacist;
    private AppointmentController appointmentController;
    private MedicationInventoryController medicationInventoryController;
    private ReplenishmentRequestController replenishmentRequestController;

    public PharmacistUI(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
        try{
            this.appointmentController = new AppointmentController("Appointment_List.txt");
            this.medicationInventoryController = new MedicationInventoryController("Medicine_List.txt");
            this.replenishmentRequestController = new ReplenishmentRequestController("Replenishment_List.txt");
        } catch (Exception e){
            System.out.println("Error: Unable to load appointment list from file.");
            e.printStackTrace();
        }
    }
    public void printMenu(){
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Log Out");
    }
    public void navigateMenu(int option){
        switch(option){
            case 1:
                appointmentController.viewAppointmentOutcomeRecord();
                break;
            case 2:
                updatePrescriptionStatus();
                break;
            case 3:
                medicationInventoryController.viewMedicationInventory();
                break;
            case 4:
                replenishmentRequestController.MenuController(pharmacist);
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void updatePrescriptionStatus() { //Rain Check
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        pharmacist.updatePrescriptionStatus(appointmentId);
    }

    public void submitReplenishmentRequest() {
        System.out.println("Select medicine for replenishment");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        String medicineName = sc.nextLine();
        List<Medicine> medicineList = Medicine.getAllMedicines();
        Medicine medicine = null;
        for (Medicine med : medicineList){
            if (med.equals(medicineName)) {
                medicine = med;
            }
        }

        if (medicine != null){
            if(medicine.getQuantity() <= medicine.getLowQAlert()){
                //Submit a request
            }
        }
    }
}
