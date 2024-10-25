public class OutcomeRecord {
    String treatment_type;
    //Prescription ptn[];
    String ConsultationNotes_FILE_PATH;
    

    public String getTreatment_type() {
        return this.treatment_type;
    }

    public void setTreatment_type(String treatment_type) {
        this.treatment_type = treatment_type;
    }

    public String getConsultationNotes_FILE_PATH() {
        return this.ConsultationNotes_FILE_PATH;
    }

    public void setConsultationNotes_FILE_PATH(String ConsultationNotes_FILE_PATH) {
        this.ConsultationNotes_FILE_PATH = ConsultationNotes_FILE_PATH;
    }

    public OutcomeRecord(String treatment_type, String ConsultationNotes_FILE_PATH) {
        this.treatment_type = treatment_type;
        this.ConsultationNotes_FILE_PATH = ConsultationNotes_FILE_PATH;
    }
}