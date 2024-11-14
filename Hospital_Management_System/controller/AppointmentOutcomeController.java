package controller;

import entity.AppointmentOutcome;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import utils.TextFileReader;
import utils.TextFileWriter;

/**
 * Controller class for AppointmentOutcome entity and AppointmentOutcomeUI
 */
public class AppointmentOutcomeController {


    /**
     * get all appointment outcomes from file
     * @return
     */
    public static List<AppointmentOutcome> getAllAppointmentOutcomes(){
        String filePath = "./TextFiles/AppointmentOutcome_List.txt";
        List<AppointmentOutcome> appointmentOutcomes;
        try {
            appointmentOutcomes = TextFileReader.loadAppointmentOutcomes(filePath);
            return appointmentOutcomes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * update appointment outcome status
     * @param appointmentId
     */
    public static void updateAppointmentOutcomeStatus(int appointmentId){
        List<AppointmentOutcome> appointmentOutcomes = getAllAppointmentOutcomes();
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            if (appointmentOutcome.getAppointmentId() == appointmentId){
                appointmentOutcome.setPStatus(true);
                TextFileWriter.updateAppointmentOutcome(appointmentOutcome);
                appointmentOutcome.printAppointmentOutcome();
            }
        }
    }

    /**
     * Appointment Outcome Controller constructor
     */
    public AppointmentOutcomeController() {
    }

    /**
     *View all appointment outcomes
     */
    public void viewAppointmentOutcomeRecord() {
        List<AppointmentOutcome> appointmentOutcomes = getAllAppointmentOutcomes();
        System.out.println("=========================================");
        for (AppointmentOutcome appointmentOutcome : appointmentOutcomes){
            appointmentOutcome.printAppointmentOutcome();
        }
        System.out.println("Press any key to continue...");
        System.out.println("=========================================");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
    /**
     * update prescription status
     * @param appointmentId
     */
    public void checkPrescriptionStatus(int appointmentId) {
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
    /**
     *Update Prescription Status
     */
    public void updatePrescriptionStatus() { //Rain Check
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Appointment ID: ");
        int appointmentId = sc.nextInt();
        checkPrescriptionStatus(appointmentId);
    }
    
}
