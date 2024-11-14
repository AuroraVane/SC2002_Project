package entity;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controller.PatientUI;
import utils.Color.ColorRole;
import utils.TextFileReader;

/**
 * Subclass of User, Patient entity class to be used to get and set attributes for patient functions
 */
public class Patient extends User{
    private String DOB;
    private String bloodtype;
    private String contactinfo;

    private PatientUI patientUI;

    /**
     * Patient constructor
     * @param id
     * @param name
     * @param password
     * @param gender
     * @param DOB
     * @param bloodtype
     * @param contactinfo
     */
    public Patient(String id, String name, String password, String gender,String DOB, String bloodtype, String contactinfo) {
        super(id, name, password,gender);
        this.DOB = DOB;
        this.bloodtype = bloodtype;
        this.contactinfo = contactinfo;
        this.patientUI = new PatientUI(this);
    }

    /**
     * getter for role Patient
     * @return
     */
    @Override
    public String getRole() {
        return "Patient";
    }

    /**
     * Display the UI for the patient
     */
    public void displayUI(){
        int option = -1;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while(option != 0){
            patientUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            patientUI.navigateMenu(option);
        }
    }

    /**
     * gets all Patients from file
     * @return
     */
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

    /**
     * gets number of Pateints
     * @return
     */
    public static int getPatientCount(){
        List<Patient> patientList = getAllPatients();
        return patientList.size();
    }

    /**
     * getter for date of birth
     * @return
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * getter for blood type
     * @return
     */
    public String getBloodtype() {
        return bloodtype;
    }

    /**
     * getter for contact information
     * @return
     */
    public String getContactinfo() {
        return contactinfo;
    }

    /**
     * setter for contact information
     * @param contactinfo
     */
    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

    /**
     * getter for colour role
     * @return
     */
    @Override
    public ColorRole getColorRole() {
        return ColorRole.PATIENT;
    }

}