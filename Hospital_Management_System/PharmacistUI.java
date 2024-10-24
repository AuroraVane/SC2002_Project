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
                skeletonMedicationInventory();//viewMedicationInventory();
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
        sc.close();
    }
    public void viewAppointmentOutcomeRecord() {
        AppointmentOutcome ao1 = new AppointmentOutcome(1, "2024-02-01", "Consultation", "Ibuprofen", "NA");
        AppointmentOutcome ao2 = new AppointmentOutcome(2, "2024-07-05", "X-Ray", "Amoxicillin", "NA");
        AppointmentOutcome[] dummyAOList = {ao1, ao2};
        for (int i = 0; i < dummyAOList.length; i++){
            System.out.println(String.format("Appointment Outcome Record %d:", i+1));
            dummyAOList[i].outcomeRecord();
        }
    }
    public void updatePrescriptionStatus(int appointmentId, String medicine) {
        System.out.println("Skeleton for Prescription");
    }
    public void skeletonMedicationInventory() {
        System.out.println("Skeleton for Medication Inventory");
    }

}
