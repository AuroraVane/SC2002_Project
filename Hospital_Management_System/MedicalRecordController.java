import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordController {
    private List<MedicalRecord> medicalRecordList;
    private List<String> overseeingPatientList;
    private MedicalRecordUI medicalRecordUI;
    private TextFileWriter textFileWriter;
    public MedicalRecordController(String docID, String MRfilepath) throws IOException {
        this.medicalRecordList = TextFileReader.loadMedicalRecords(MRfilepath);
        this.overseeingPatientList = TextFileReader.loadOverseeingPatients(docID, "./TextFiles/Overseeingpatients.txt");
        this.medicalRecordUI = new MedicalRecordUI();
    }
    public void viewPatientList(){
        medicalRecordUI.viewPatientList(overseeingPatientList,medicalRecordList);
    }
    public void UpdatePersonalMedicalRecord(){
        System.out.println("Select which Patient's Medical Record to update");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        List<Patient> patientList = Patient.getAllPatients();
        for(String patientID : overseeingPatientList){
            if(patientID.equals(id)){
                for (Patient patient : patientList){
                    if(patient.getId().equals(patientID)){
                        System.out.println("Enter new Diagnosis: ");
                        String diagnosis = sc.nextLine();
                        System.out.println("Enter new Treatment: ");
                        String treatment = sc.nextLine();
                        String insid = String.valueOf(medicalRecordList.size() + 1);
                        this.textFileWriter = new TextFileWriter();
                        textFileWriter.updatePersonalMedicalRecord(insid,patient.getId(),patient.getName(),patient.getDOB(),patient.getGender(),patient.getBloodtype(),patient.getContactinfo(),diagnosis,treatment);                    }
                }
            }
        }
    }

    
}
