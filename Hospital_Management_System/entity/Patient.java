package entity;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controller.PatientUI;
import utils.TextFileReader;

public class Patient extends User{
    private String DOB;
    private String bloodtype;
    private String contactinfo;

    private PatientUI patientUI;
    
    public Patient(String id, String name, String password, String gender,String DOB, String bloodtype, String contactinfo) {
        super(id, name, password,gender);
        this.DOB = DOB;
        this.bloodtype = bloodtype;
        this.contactinfo = contactinfo;
        this.patientUI = new PatientUI(this);
    }
    @Override
    public String getRole() {
        return "Patient";
    }
    public void displayUI(){
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do{
            patientUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            patientUI.navigateMenu(option);
        }while(option != 9);
        sc.close();
    }

    public static List<Patient> getAllPatients(){
        String filePath = "./TextFiles/Patient_List.txt";
        List<Patient> patientList;
        try {
             patientList = TextFileReader.loadPatients(filePath);
             return patientList;
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDOB() {
        return DOB;
    }
    public String getBloodtype() {
        return bloodtype;
    }
    public String getContactinfo() {
        return contactinfo;
    }
    
    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

}