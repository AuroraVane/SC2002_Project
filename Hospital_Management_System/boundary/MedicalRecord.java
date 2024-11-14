package boundary;

import entity.Patient;
import utils.TextFileCreator;

/**
 * Class for Medical Record used by MedicalRecordController
 */
public class MedicalRecord {
    private Patient patient;
    private String diagnosis_FILEPATH;
    //private Prescription prescriptions[];
    private String treatment_plans_FILEPATH;
    //private TextFileCreator creator;
    // Would need it own textfile to store the data

    /**
     * Medical Record constructor
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
     * getter for patient attribute
     * @return
     */
    public Patient getPatient() {
        return this.patient;
    }

    /**
     * getter for filepath of patient's diagnosis relative to ./TextFiles
     * @return
     */
    public String getDiagnosis_FILEPATH() {
        return this.diagnosis_FILEPATH;
    }

    /**
     * setter for filepath of patient's diagnosis relative to ./TextFiles
     * @param diagnosis_FILEPATH
     */
    public void setDiagnosis_FILEPATH(String diagnosis_FILEPATH) {
        this.diagnosis_FILEPATH = diagnosis_FILEPATH;
    }

    /**
     * getter for filepath of patient's treatment plans relative to ./TextFiles
     * @return
     */
    public String getTreatment_plans_FILEPATH() {
        return this.treatment_plans_FILEPATH;
    }

    /**
     * setter for filepath of patient's treatment plans relative to ./TextFiles
     * @param treatment_plans_FILEPATH
     */
    public void setTreatment_plans_FILEPATH(String treatment_plans_FILEPATH) {
        this.treatment_plans_FILEPATH = treatment_plans_FILEPATH;
    }
}

