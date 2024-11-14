package controller;
import entity.Patient;
import java.io.IOException;
import java.util.List;
import utils.TextFileReader;

/**
 * Controller class for PatientManager 
 */
public class PatientManager {
    private List<Patient> OverseeingPatients;
    private List<String> OverseeingPatientsID;
    private static List<Patient> patientList;

    /**
     * Patient Manager constructor
     * @param doctorId
     * @param OverseeingPatientsFilePath
     * @param PatientFilePath
     * @throws IOException
     */
    public PatientManager(String doctorId, String OverseeingPatientsFilePath, String PatientFilePath) throws IOException{
        this.OverseeingPatientsID=TextFileReader.loadOverseeingPatients(doctorId, OverseeingPatientsFilePath);
        this.OverseeingPatients=TextFileReader.loadSelectivePatients(PatientFilePath, OverseeingPatientsID);
        
    }

    /**
     * Print all overseing patients of a doctor
     */
    public void printAlloverseeingPatients() {
        int i=1;
        System.out.println("Overseeing Patients:");
        for (Patient patient : this.OverseeingPatients) {
            System.out.println(i+". "+patient.getName());
            i++;
        }
    }

    /**
     * get patient by index
     * @param index
     * @return
     */
    public Patient getPatientByIndex(int index){
        if (index>=0 && index<this.OverseeingPatients.size()){
            return this.OverseeingPatients.get(index);
        }
        System.out.println("Invalid input. ");
        return null;
        
    }

    /**
     * return ID of overseeing patient
     * @return
     */
    public List<String> getOverseeingPatientsID(){
        return this.OverseeingPatientsID;
    }

    /**
     * load patient list
     */
    public static void loadPatientList() {
        try {
            patientList = TextFileReader.loadPatients("./TextFiles/Patient_List.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * check if patient ID exists in patient list
     * @param id
     * @return
     */
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