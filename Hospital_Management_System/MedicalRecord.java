public class MedicalRecord {
    private Patient patient;
    private String diagnosis_FILEPATH;
    //private Prescription prescriptions[];
    private String treatment_plans_FILEPATH;
    private TextFileCreator creator;
    // Would need it own textfile to store the data

    public MedicalRecord(Patient patient, String diagnosis_FILEPATH, String treatment_plans_FILEPATH) {
        this.patient = patient;
        this.diagnosis_FILEPATH = diagnosis_FILEPATH;
        creator.createFile(diagnosis_FILEPATH);
        this.treatment_plans_FILEPATH = treatment_plans_FILEPATH;
        creator.createFile(treatment_plans_FILEPATH);
        //create file @ filepath upon creation of Medical Record object
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
