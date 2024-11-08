package controller;
import java.io.IOException;
import java.util.Scanner;

import boundary.PatientAppointmentUI;
import boundary.PatientMedicalRecordUI;
import boundary.PatientNonMedicalRecordUI;
import entity.Patient;
import entity.Staff;
import utils.TextFileWriter;

public class PatientUI implements UserUI {
    private Patient patient;
    private PatientMedicalRecordUI medicalrecordUI;
    private PatientAppointmentUI patientappointmentUI;
    private PatientNonMedicalRecordUI nonmedicalrecordUI;

    public PatientUI(Patient patient) {
        this.patient = patient;
        try {
            this.medicalrecordUI = new PatientMedicalRecordUI("./TextFiles/MedicalRecord.txt", "./TextFiles/Patient_List.txt", patient.getId()); 
        } catch (IOException e) {
            System.out.println("Error: Unable to load list from file.");
            e.printStackTrace();
        }
        this.patientappointmentUI = new PatientAppointmentUI(); 
        this.nonmedicalrecordUI = new PatientNonMedicalRecordUI(patient); 
        
    }
    public void changePassword(){
        System.out.println("Enter new password: ");
        // Code to change password
    }
    public void printMenu() {
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointments Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. Change Password");
        System.out.println("0. Log Out");
    }

    public void navigateMenu(int option) {
        switch (option) {
            case 1:
                medicalrecordUI.viewMedicalRecord(this.patient);
                break;
            case 2:
                nonmedicalrecordUI.updateContactInfo();
                break;
            case 3:
                patientappointmentUI.viewAvailableAppointmentSlots();
                break;
            case 4:
                patientappointmentUI.scheduleAppointment(this.patient);
                break;
            case 5:
                patientappointmentUI.rescheduleAppointment(this.patient.getId());
                break;
            case 6:
                patientappointmentUI.cancelAppointment(this.patient.getId());
                break;
            case 7:
                patientappointmentUI.viewScheduledAppointments(this.patient.getId());
                break;
            case 8:
                patientappointmentUI.viewAppointmentOutcomeRecords(this.patient.getId());
                break;
            case 9:
                changePassword(patient);
                break;
            case 0:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    
    
    public void skeletonAppointment() {
        System.out.println("Skeleton for Appointment");
    }
    @Override
    public void changePassword(Staff staff) {
        
    }
    @Override
    public void changePassword(Patient patient) {
        System.out.println("Enter new password: ");
        // Code to change password
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.nextLine();
        Boolean success = TextFileWriter.changePassword(patient, newPassword);
        if (success){
            System.out.println("Password changed successfully.");
        }
        else{
            System.out.println("Error: Unable to change password.");
        }
    }

    public static void createPatient(){
        System.out.println("Registering a new patient...");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String id = "P100" + String.valueOf(Patient.getPatientCount()+1);
        System.out.println("Enter your Name: ");
        String name = sc.nextLine();
        System.out.println("Enter your Date Of Birth: ");
        String dob = sc.nextLine();
        System.out.println("Enter your gender: ");
        String gender = sc.nextLine();
        System.out.println("Enter your blood type: ");
        String bloodtype = sc.nextLine();
        System.out.println("Enter your contact information (email): ");
        String contactinfo = sc.nextLine();
        TextFileWriter.addPatient(id,name,dob,gender,bloodtype,contactinfo);
        System.out.println("Patient registered successfully.");
        System.out.println("Your ID is: " + id);
    }
}
