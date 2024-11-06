
import java.io.IOException;
//some file exception error here idk wut is it exactly

public class PatientMedicalRecordUI {
    private MedicalRecordController MRController;
    private String patientID;

    public PatientMedicalRecordUI(String MRfilepath, String PatientfilePath, String patientID) throws IOException{
        MRController=new MedicalRecordController(MRfilepath, PatientfilePath); 
        
        this.patientID=patientID;
    }
    public void viewMedicalRecord() {
        
        MedicalRecord m=MRController.findPatientMedicalRecord(this.patientID);
        MRController.singleprint_medicalrecord(m); 
        
    }
    
}
