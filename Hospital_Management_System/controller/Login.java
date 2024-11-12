package controller;
import java.io.IOException;
import java.util.List;

import entity.Administrator;
import entity.Doctor;
import entity.Patient;
import entity.Pharmacist;
import entity.Staff;
import entity.User;
import utils.TextFileReader;
import utils.TextFileWriter;

import java.util.Scanner;

public class Login {
    private List<Patient> patientList;
    private List<Staff> staffList;
    private static List<String> resetPasswordRequests;

    public Login(String patientFilePath, String staffFilePath) throws IOException{
        patientList = TextFileReader.loadPatients(patientFilePath);
        staffList = TextFileReader.loadStaff(staffFilePath);
    }

    public User authenticate(String id, String password){
        try{
            patientList = TextFileReader.loadPatients("./TextFiles/Patient_List.txt");
            staffList = TextFileReader.loadStaff("./TextFiles/Staff_List.txt");
        } catch (IOException e){
            e.printStackTrace();
        }
        String passwordHash = TextFileWriter.hashPassword(password);
        for (Patient patient : patientList) {
            if (patient.getId().equals(id) && patient.getPassword().equals(passwordHash)) {
                return patient;
            }
        }
        for (Staff staff : staffList) {
            if (staff.getId().equals(id) && staff.getPassword().equals(passwordHash)) {
                if(staff.getRole().equals("Doctor")){
                    return new Doctor(staff.getId(), staff.getName(), staff.getPassword(), staff.getGender(), staff.getRole(), staff.getAge());
                }
                else if(staff.getRole().equals("Pharmacist")){
                    return new Pharmacist(staff.getId(), staff.getName(), staff.getPassword(), staff.getGender(), staff.getRole(), staff.getAge());
                }
                else if(staff.getRole().equals("Administrator")){
                    return new Administrator(staff.getId(), staff.getName(), staff.getPassword(), staff.getGender(), staff.getRole(), staff.getAge());
                }
            }
        }
        
        System.out.println("Invalid ID or password");
        return null;
    }
    public static void forgetPassword() {
        System.out.println("===================================================");
        System.out.println("                Forget Password");
        System.out.println("---------------------------------------------------");
        System.out.println("                Enter your ID ");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        switch(id.length()){
            case 4:
                if(ManageStaffController.checkStaffIDExist(id) == true){
                    TextFileWriter.sendResetPassword(id);
                    System.out.println("Reset password request has been submitted");
                }
                else{
                    System.out.println("Invalid ID");
                }
                break;
            case 5:
                if(PatientManager.checkPatientIDExist(id) == true){
                    TextFileWriter.sendResetPassword(id);
                    System.out.println("Reset password request has been submitted");
                }
                else{
                    System.out.println("Invalid ID");
                };
                break;
            default:
                System.out.println("Invalid ID");
                break;
        }
    }
    public static void approvePasswordResetRequests(){
        resetPasswordRequests = TextFileReader.loadResetPasswordRequests();
        for(String id : resetPasswordRequests){
            System.out.println("Approve password reset request for " + id + "? (Y/N)");
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
            String choice = sc.nextLine();
            if(choice.equals("Y")){
                Boolean success = TextFileWriter.completeResetPassword(id);
                if(success){
                    System.out.println("Password changed successfully.");
                }
                else{
                    System.out.println("Error: Unable to change password.");
                }
            }
        }
    }
}