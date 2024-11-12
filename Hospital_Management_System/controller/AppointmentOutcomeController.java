package controller;

import entity.AppointmentOutcome;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import utils.TextFileReader;
import utils.TextFileWriter;

public class AppointmentOutcomeController {

    
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

    public AppointmentOutcomeController() {
    }
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
    
}
