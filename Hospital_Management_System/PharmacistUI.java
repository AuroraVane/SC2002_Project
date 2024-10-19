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
                skeletonAppointment();//viewAppointmentOutcomeRecord();
                break;
            case 2:
                skeletonPrescription();//updatePrescriptionStatus();
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
    }
    public void skeletonAppointment() {
        System.out.println("Skeleton for Appointment");
    }
    public void skeletonPrescription() {
        System.out.println("Skeleton for Prescription");
    }
    public void skeletonMedicationInventory() {
        System.out.println("Skeleton for Medication Inventory");
    }

}
