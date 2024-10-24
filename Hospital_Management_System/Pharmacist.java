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

    public void updatePrescriptionStatus(int appointmentId, String medicine) {
        List<Medicine> medicines = Medicine.getAllMedicines();
        List<AppointmentOutcome> appointmentOutcomes = AppointmentOutcome.getAllAppointmentOutcomes();
        AppointmentOutcome.updateAppointmentOutcomeStatus(appointmentId);
    }
    
}

/*
P001
password
 */