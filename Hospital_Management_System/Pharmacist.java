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


    //TODO: Check if medicine quantity is enough, afterwards update AppointmentOutcome status=false-> true, decrease the medicine quantity
    public void updatePrescriptionStatus(int appointmentId) {
        List<AppointmentOutcome> appointmentOutcomes = AppointmentOutcome.getAllAppointmentOutcomes();
        AppointmentOutcome record = null;
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            if (appointmentId == appointmentOutcome.getAppointmentId()){
                record = appointmentOutcome;
                System.out.println("AppointmentOutcome Record found!");
                appointmentOutcome.printAppointmentOutcome();
            }
        }
        if (record == null){
            return;
        }

        AppointmentOutcome.updateAppointmentOutcomeStatus(appointmentId);
        if (!record.isStatus()){
            Medicine.updateMedicineQuantity(record.getMedicine());
        }
    }
    
}

/*
P001
password
 */