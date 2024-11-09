package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import boundary.MedicalRecord;
import entity.Patient;
import utils.TextFileReader;

public class MedicalRecordController {
    private List<MedicalRecord> MedicalRecordList;
    

    public MedicalRecordController(String filepath, String PatientfilePath)throws IOException{
        this.MedicalRecordList=TextFileReader.loadMedicalRecord(filepath, PatientfilePath);
    }
    public List<MedicalRecord> getOverseeingPatientsMR(List<String> OverseeingPatientsID){
        List<MedicalRecord> MRList=new ArrayList<>();
        for (MedicalRecord x: this.MedicalRecordList) {
            if (OverseeingPatientsID.contains(x.getPatient().getId())){
                MRList.add(x);
            }
        }
        return MRList;
    }
    
    public void printAllMedicalRecordOfOverseeing(List<MedicalRecord> MRList) throws IOException{
        System.out.println("Overseeing Patients' Medical Records:");
        Patient p;
        for (MedicalRecord x: MRList) {
            p=x.getPatient();
            System.out.println("Patient ID: "+ p.getId());
            System.out.println("Name: "+p.getName());
            System.out.println("Gender: "+p.getGender());
            System.out.println("Date of Birth: "+p.getDOB());
            System.out.println("Contact information: "+p.getContactinfo());
            System.out.println("Blood Type: "+p.getBloodtype()+"\n");
            System.out.println("Diagnosis History:");
            TextFileReader.NormalRead("./TextFiles/"+x.getDiagnosis_FILEPATH());
            System.out.println("\n");
            System.out.println("Treatment Plans History:");
            TextFileReader.NormalRead("./TextFiles/"+x.getTreatment_plans_FILEPATH());
            System.out.println("\n");
              
        }
    }
    public MedicalRecord findPatientMedicalRecord(String patientID){
        for (MedicalRecord x: this.MedicalRecordList) {
            if (patientID.equals(x.getPatient().getId())){
                return x;
            }
        }
        return null;
    }//1
    
}