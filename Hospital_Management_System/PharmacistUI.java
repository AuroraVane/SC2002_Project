import java.util.Scanner;
import java.util.List;

public class PharmacistUI implements UserUI{
    private Pharmacist pharmacist;

    public PharmacistUI(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
    }
    public void printMenu(){
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Presccription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Log Out");
    }
    public void navigateMenu(int option){
        Scanner sc = new Scanner(System.in);
        switch(option){
            case 1:
                viewAppointmentOutcomeRecord();
                break;
            case 2:
                System.out.println("Enter Appointment ID: ");
                int appointmentId = sc.nextInt();
                System.out.println("Enter Medicine to prescribe: ");
                String medicine = sc.next();
                updatePrescriptionStatus(appointmentId, medicine);
                break;
            case 3:
                viewMedicationInventory();//viewMedicationInventory();
                break;
            case 4:
                skeletonMedicationInventory();//submitReplenishmentRequest();
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    public void viewAppointmentOutcomeRecord() {
        List<AppointmentOutcome> appointmentOutcomes = pharmacist.getAllAppointmentOutcomes();
        
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            appointmentOutcome.outcomeRecord();
        }
    }

    public void updatePrescriptionStatus(int appointmentId, String medicine) {
        
    }
    
    public void viewMedicationInventory() {
        List<Medicine> medicineList = pharmacist.getAllMedicines();
        for (Medicine medicine : medicineList){
            System.out.println(String.format("Medicine: %s", medicine.getMedicineName()));
            System.out.println(medicine.getQuantity() > medicine.getLowQAlert() ? String.format("Quanity: %d", medicine.getQuantity()) : String.format("Quanity: %d (Low Quantity. Please Top Up)", medicine.getQuantity()));
            System.out.println(String.format("Low Quantity Alert: %d \n", medicine.getLowQAlert()));
        }
    }

    public void skeletonMedicationInventory() {
        System.out.println("Skeleton for Medication Inventory");
    }
}
