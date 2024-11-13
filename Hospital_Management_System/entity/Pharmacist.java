package entity;
import controller.AppointmentOutcomeController;
import controller.MedicationInventoryController;
import controller.PharmacistUI;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class Pharmacist extends Staff{
    private PharmacistUI pharmacistUI;

    /**
     * @param id
     * @param name
     * @param password
     * @param gender
     * @param role
     * @param age
     */
    public Pharmacist(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
        this.pharmacistUI = new PharmacistUI(this);
    }

    /**
     * @return
     */
    @Override
    public String getRole() {
        return "Pharmacist";
    }

    /**
     *
     */
    public void displayUI(){
        int option = 0;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while(option != 6){
            pharmacistUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            pharmacistUI.navigateMenu(option);
        }
    }


    /**
     * @param appointmentId
     */
    public void updatePrescriptionStatus(int appointmentId) {
        List<AppointmentOutcome> appointmentOutcomes = AppointmentOutcomeController.getAllAppointmentOutcomes();
        AppointmentOutcome record = null;
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            if (appointmentId == appointmentOutcome.getAppointmentId()){
                record = appointmentOutcome;
                System.out.println("AppointmentOutcome Record found!");
                AppointmentOutcomeController.updateAppointmentOutcomeStatus(appointmentId);   
            }
        }
        if (record == null){
            return;
        }

        
        if (!record.isPStatus()){
            MedicationInventoryController.updateMedicineQuantity(record.getMedicine());
        }
    }
    
}

/*
P001
password
 */