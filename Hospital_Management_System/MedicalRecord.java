import java.io.IOException;
import java.util.List;

public class MedicalRecord {
    private Patient patient;
    private String diagnosis_FILEPATH;
    //private Prescription prescriptions[];
    private String treatment_plans_FILEPATH;
    //private TextFileCreator creator;
    // Would need it own textfile to store the data

    public MedicalRecord(Patient patient, String diagnosis_FILEPATH, String treatment_plans_FILEPATH) {
        this.patient = patient;
        this.diagnosis_FILEPATH = diagnosis_FILEPATH;
        TextFileCreator.createFile("./TextFiles/"+diagnosis_FILEPATH);
        this.treatment_plans_FILEPATH = treatment_plans_FILEPATH;
        TextFileCreator.createFile("./TextFiles/"+treatment_plans_FILEPATH);
        //create file @ filepath upon creation of Medical Record object
    }

    public static void viewMedicalRecord(Patient p) {
        System.out.println("Medical Record for " + p.getName() + ":");
        System.out.println("DOB: " + p.getDOB());
        System.out.println("Blood Type: " + p.getBloodtype());
        try {
            List<MedicalRecord> mRecords = TextFileReader.loadMedicalRecord("./TextFiles/MedicalRecord.txt", "./TextFiles/Patient_List.txt");
            for (MedicalRecord medicalRecord : mRecords){
                if(p.getId().equals(medicalRecord.patient.getId())){
                    List<String> patientDiagnosis = TextFileReader.loadDiagnosis("./TextFiles/" + medicalRecord.getDiagnosis_FILEPATH());
                    for(int i = 0; i < patientDiagnosis.size(); i+=2){
                        System.out.println(String.format("Date of Diagnosis: %s\nDiagnosis: %s",patientDiagnosis.get(i),patientDiagnosis.get(i+1)));
                    }
                    String treatment = TextFileReader.loadTreatmentPlan("./TextFiles/" + medicalRecord.getTreatment_plans_FILEPATH());
                    System.out.println("Treatment Plans:");
                    System.out.println(treatment);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Patient getPatient() {
        return this.patient;
    }

    public String getDiagnosis_FILEPATH() {
        return this.diagnosis_FILEPATH;
    }

    public void setDiagnosis_FILEPATH(String diagnosis_FILEPATH) {
        this.diagnosis_FILEPATH = diagnosis_FILEPATH;
    }

    public String getTreatment_plans_FILEPATH() {
        return this.treatment_plans_FILEPATH;
    }

    public void setTreatment_plans_FILEPATH(String treatment_plans_FILEPATH) {
        this.treatment_plans_FILEPATH = treatment_plans_FILEPATH;
    }
    
    public void skeletonMedicalRecord(){
        System.out.println("Skeleton For Medical Record");
    }
}

