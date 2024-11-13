package boundary;

import entity.Patient;
import utils.TextFileCreator;

/**
 *
 */
public class MedicalRecord {
    private Patient patient;
    private String diagnosis_FILEPATH;
    //private Prescription prescriptions[];
    private String treatment_plans_FILEPATH;
    //private TextFileCreator creator;
    // Would need it own textfile to store the data

    /**
     * @param patient
     * @param diagnosis_FILEPATH
     * @param treatment_plans_FILEPATH
     */
    public MedicalRecord(Patient patient, String diagnosis_FILEPATH, String treatment_plans_FILEPATH) {
        this.patient = patient;
        this.diagnosis_FILEPATH = diagnosis_FILEPATH;
        TextFileCreator.createFile("./TextFiles/"+diagnosis_FILEPATH);
        this.treatment_plans_FILEPATH = treatment_plans_FILEPATH;
        TextFileCreator.createFile("./TextFiles/"+treatment_plans_FILEPATH);
        //create file @ filepath upon creation of Medical Record object
    }


    /**
     * @return
     */
    public Patient getPatient() {
        return this.patient;
    }

    /**
     * @return
     */
    public String getDiagnosis_FILEPATH() {
        return this.diagnosis_FILEPATH;
    }

    /**
     * @param diagnosis_FILEPATH
     */
    public void setDiagnosis_FILEPATH(String diagnosis_FILEPATH) {
        this.diagnosis_FILEPATH = diagnosis_FILEPATH;
    }

    /**
     * @return
     */
    public String getTreatment_plans_FILEPATH() {
        return this.treatment_plans_FILEPATH;
    }

    /**
     * @param treatment_plans_FILEPATH
     */
    public void setTreatment_plans_FILEPATH(String treatment_plans_FILEPATH) {
        this.treatment_plans_FILEPATH = treatment_plans_FILEPATH;
    }
}

