package entity;
import java.util.List;
import java.util.Scanner;

import controller.AppointmentOutcome;
import controller.MedicationInventoryController;
import controller.PharmacistUI;

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
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while(option != 6){
            pharmacistUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            pharmacistUI.navigateMenu(option);
        }
    }


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
            MedicationInventoryController.updateMedicineQuantity(record.getMedicine());
        }
    }
    
}

/*
P001
password
 */