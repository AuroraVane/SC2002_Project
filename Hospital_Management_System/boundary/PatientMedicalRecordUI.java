package boundary;

import entity.Patient;
import java.io.IOException;
import java.util.List;
import utils.TextFileReader;

/**
 *
 */
public class PatientMedicalRecordUI {
    //private MedicalRecordController MRController;
    private String patientID;

    /**
     * @param MRfilepath
     * @param PatientfilePath
     * @param patientID
     * @throws IOException
     */
    public PatientMedicalRecordUI(String MRfilepath, String PatientfilePath, String patientID) throws IOException{
        //MRController=new MedicalRecordController(MRfilepath, PatientfilePath);
        this.patientID=patientID;
    }

    /**
     * @param p
     */
    public void viewMedicalRecord(Patient p) {
        System.out.println("Medical Record for " + p.getName() + ":");
        System.out.println("DOB: " + p.getDOB());
        System.out.println("Blood Type: " + p.getBloodtype()+"\n");
        try {
            List<MedicalRecord> mRecords = TextFileReader.loadMedicalRecord("./TextFiles/MedicalRecord.txt", "./TextFiles/Patient_List.txt");
            for (MedicalRecord x : mRecords){
                if(p.getId().equals(x.getPatient().getId())){
                
                    System.out.println("Diagnosis History:");
                    TextFileReader.NormalRead("./TextFiles/"+x.getDiagnosis_FILEPATH());
                    System.out.println("\n");
                    System.out.println("Treatment Plans:");
                    TextFileReader.NormalRead("./TextFiles/"+x.getTreatment_plans_FILEPATH());
                    System.out.println("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
