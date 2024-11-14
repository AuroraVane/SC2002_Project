package controller;
import java.io.IOException;
import java.util.Scanner;

import boundary.PatientAppointmentUI;
import boundary.PatientMedicalRecordUI;
import boundary.PatientNonMedicalRecordUI;
import entity.Patient;
import entity.Staff;
import utils.TextFileWriter;

/**
 * Controller class for PatientUI implemented from UserUI interface 
 */
public class PatientUI implements UserUI {
    private Patient patient;
    private PatientMedicalRecordUI medicalrecordUI;
    private PatientAppointmentUI patientappointmentUI;
    private PatientNonMedicalRecordUI nonmedicalrecordUI;

    /**
     * Patient UI constructor
     * @param patient
     */
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

    /**
     *print display UI for Patient
     */
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
        System.out.println("10. View Bill");
        System.out.println("0. Log Out");
    }

    /**naviagte main menu of Patient
     * @param option
     */
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
            case 10:
                BillController.getBillbyPatientID(patient.getId());
                break;
            case 0:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }


    /**
     *Patient Skeleton for Appointment
     */
    public void skeletonAppointment() {
        System.out.println("Skeleton for Appointment");
    }
    @Override
    public void changePassword(Staff staff) {
        
    }

    /**
     * Change password for patient
     * @param patient
     */
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

    /**
     *register new Patient
     */
    public static void createPatient(){
        System.out.println("===================================================");
        System.out.println("Registering a new patient...");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String id = "P100" + String.valueOf(Patient.getPatientCount()+1);
        System.out.println("Enter your Name: ");
        String name = sc.nextLine();
        System.out.println("Enter your Date Of Birth (YYYY-MM-DD): ");
        String dob = "";
        while(true){
            dob = sc.nextLine();
            if(dob.matches("\\d{4}-\\d{2}-\\d{2}")){
                break;
            }
            else{
                System.out.println("Invalid date format. Please enter in the format YYYY-MM-DD");
            }
        }
        System.out.println("Enter your gender: ");
        String gender = "";
        while(!gender.equals("Male") || !gender.equals("Female") || gender.equals("Others") || gender.equals("Prefer not to say") || gender.equals("Transformer")){
            System.out.println("Enter staff gender");
            gender = sc.nextLine();
            if(gender.equals("Male") || gender.equals("Female") || gender.equals("Others") || gender.equals("Prefer not to say") || gender.equals("Transformer")){
                break;
            }
            else{
                System.out.println("Invalid Gender (Male/Female/Others/Prefer not to say). Please try again.");
            }
        }
        System.out.println("Enter your blood type (A/B/AB/O/A+/B+/AB+/O+): ");
        String bloodtype = "";
        while(!bloodtype.equals("A") || !bloodtype.equals("B") || !bloodtype.equals("AB") || !bloodtype.equals("O") || !bloodtype.equals("A+") || !bloodtype.equals("B+") || !bloodtype.equals("AB+") || !bloodtype.equals("O+")){
            bloodtype = sc.nextLine();
            if(bloodtype.equals("A") || bloodtype.equals("B") || bloodtype.equals("AB") || bloodtype.equals("O") || !bloodtype.equals("A+") || !bloodtype.equals("B+") || !bloodtype.equals("AB+") || !bloodtype.equals("O+")){
                break;
            }
            else{
                System.out.println("Invalid blood type. Please try again.");
            }
        }
        System.out.println("Enter your contact information (email): ");
        String contactinfo = "";
        while(true){
            contactinfo = sc.nextLine();
            if(contactinfo.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
                break;
            }
            else{
                System.out.println("Invalid email format. Please try again.");
            }
        }
        TextFileWriter.addPatient(id,name,dob,gender,bloodtype,contactinfo);
        System.out.println("Patient registered successfully.");
        System.out.println("Your ID is: " + id);
        System.out.println("Returning to Main Menu");
        System.out.println("===================================================");
    }
}
