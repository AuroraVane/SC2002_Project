package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entity.AppointmentOutcome;
import utils.TextFileReader;

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
            }
        }
        updateAppointmentOutcomeFile(appointmentOutcomes);
    }

    public static void updateAppointmentOutcomeFile(List<AppointmentOutcome> appointmentOutcomes) {
        String filePath = "./TextFiles/AppointmentOutcome_List.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (AppointmentOutcome appointmentOutcome : appointmentOutcomes) {
                writer.write(appointmentOutcome.toString());
                writer.newLine();
            }
            System.out.println("File updated successfully.");
        } catch (IOException e) {
            System.err.println("Error updating the file: " + e.getMessage());
        }
    }
    
}
