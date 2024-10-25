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
        switch(option){
            case 1:
                viewAppointmentOutcomeRecord();
                break;
            case 2:
                updatePrescriptionStatus();
                break;
            case 3:
                viewMedicationInventory();//viewMedicationInventory();
                break;
            case 4:
                submitReplenishmentRequest();//submitReplenishmentRequest();
                break;
            case 5:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    
    public void viewAppointmentOutcomeRecord() {
        List<AppointmentOutcome> appointmentOutcomes = AppointmentOutcome.getAllAppointmentOutcomes();
        
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            appointmentOutcome.printAppointmentOutcome();
        }
    }

    public void updatePrescriptionStatus() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        pharmacist.updatePrescriptionStatus(appointmentId);
        
    }
    
    public void viewMedicationInventory() {
        List<Medicine> medicineList = Medicine.getAllMedicines();
        for (Medicine medicine : medicineList){
            System.out.println(String.format("Medicine: %s", medicine.getMedicineName()));
            System.out.println(medicine.getQuantity() > medicine.getLowQAlert() ? String.format("Quanity: %d", medicine.getQuantity()) : String.format("Quanity: %d (Low Quantity. Please Top Up)", medicine.getQuantity()));
            System.out.println(String.format("Low Quantity Alert: %d \n", medicine.getLowQAlert()));
        }
    }

    public void submitReplenishmentRequest() {
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
