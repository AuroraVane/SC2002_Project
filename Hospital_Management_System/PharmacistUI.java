import java.util.Scanner;

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
                pharmacist.viewAppointmentOutcomeRecord();
                break;
            case 2:
                System.out.println("Enter Appointment ID: ");
                int appointmentId = sc.nextInt();
                System.out.println("Enter Medicine to prescribe: ");
                String medicine = sc.next();
                pharmacist.updatePrescriptionStatus(appointmentId, medicine);
                break;
            case 3:
                pharmacist.viewMedicationInventory();//viewMedicationInventory();
                break;
            case 4:
                pharmacist.skeletonMedicationInventory();//submitReplenishmentRequest();
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}
