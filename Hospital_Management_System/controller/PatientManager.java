package controller;
import java.io.IOException;
import java.util.List;

import entity.Patient;
import utils.TextFileReader;

public class PatientManager {
    private List<Patient> OverseeingPatients;
    private List<String> OverseeingPatientsID;
    private static List<Patient> patientList;

    public PatientManager(String doctorId, String OverseeingPatientsFilePath, String PatientFilePath) throws IOException{
        this.OverseeingPatientsID=TextFileReader.loadOverseeingPatients(doctorId, OverseeingPatientsFilePath);
        this.OverseeingPatients=TextFileReader.loadSelectivePatients(PatientFilePath, OverseeingPatientsID);
        
    }

    public void printAlloverseeingPatients() {
        int i=0;
        System.out.println("Overseeing Patients:");
        for (Patient patient : this.OverseeingPatients) {
            System.out.println(i+". "+patient.getName());
            i++;
        }
    }
    public Patient getPatientByIndex(int index){
        return this.OverseeingPatients.get(index);
    }
    public List<String> getOverseeingPatientsID(){
        return this.OverseeingPatientsID;
    }

    public static void loadPatientList() {
        try {
            patientList = TextFileReader.loadPatients("./TextFiles/Patient_List.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkPatientIDExist(String id) {
        // Check if staff ID exists
        loadPatientList();
        for (Patient patient : patientList) {
            if (patient.getId().equals(id)) {
                return true;
            }  
        }
        return false;
    }
}
// D0458|P009|P8293