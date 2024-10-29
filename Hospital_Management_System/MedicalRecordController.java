import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getGender());
            System.out.println(p.getDOB());
            System.out.println(p.getContactinfo());
            System.out.println(p.getBloodtype());
            TextFileReader.NormalRead(x.getDiagnosis_FILEPATH());
            TextFileReader.NormalRead(x.getTreatment_plans_FILEPATH());
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
    }
    public void EditFile(String filepath){
        
    }
    
}
