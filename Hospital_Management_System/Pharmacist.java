import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Pharmacist extends Staff{
    private PharmacistUI pharmacistUI;

    public Pharmacist(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
        this.pharmacistUI = new PharmacistUI(this);
    }

    @Override
    public String getRole() {
        return "Pharmacist";
    } 
    public void displayUI(){
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do{
            pharmacistUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            pharmacistUI.navigateMenu(option);
        }while(option != 5);
        sc.close();
    }

    public void viewAppointmentOutcomeRecord() {
        String filePath = "AppointmentOutcome_List.txt";
        List<AppointmentOutcome> appointmentOutcomes;
        try {
            appointmentOutcomes = TextFileReader.loadAppointmentOutcomes(filePath);
             for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
                appointmentOutcome.outcomeRecord();
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePrescriptionStatus(int appointmentId, String medicine) {
        System.out.println("Skeleton for Prescription");
    }
    
    public void viewMedicationInventory() {
        String filePath = "Medicine_List.txt";
        List<Medicine> medicineList;
        try {
             medicineList = TextFileReader.loadMedicineList(filePath);
             for (Medicine medicine : medicineList){
                System.out.println(String.format("Medicine: %s", medicine.getMedicineName()));
                System.out.println(medicine.getQuantity() > medicine.getLowQAlert() ? String.format("Quanity: %d", medicine.getQuantity()) : String.format("Quanity: %d (Low Quantity. Please Top Up)", medicine.getQuantity()));
                System.out.println(String.format("Low Quantity Alert: %d \n", medicine.getLowQAlert()));
             }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void skeletonMedicationInventory() {
        System.out.println("Skeleton for Medication Inventory");
    }
    
}

/*
P001
password
 */