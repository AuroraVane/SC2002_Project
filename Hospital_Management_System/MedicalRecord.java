public class MedicalRecord {
    private String appointmentID;
    private String patientID;
    private String name;
    private String DOB;
    private String gender;
    private String bloodtype;
    private String contact;
    private String diagnosis;
    private String treatment;

    // Would need it own textfile to store the data
    public MedicalRecord(String appointmentID, String patientID, String name,String DOB,String gender,String bloodtype,String contact,String diagnosis,String treatment){
        this.appointmentID = appointmentID;
        this.patientID = patientID;
        this.name = name;
        this.DOB = DOB;
        this.gender = gender;
        this.bloodtype = bloodtype;
        this.contact = contact;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }
    public void skeletonMedicalRecord(){
        System.out.println("Skeleton For Medical Record");
    }
    public String getAppointmentID() {
        return appointmentID;
    }
    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }
    public String getPatientID() {
        return patientID;
    }
    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDOB() {
        return DOB;
    }
    public void setDOB(String dOB) {
        DOB = dOB;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getBloodtype() {
        return bloodtype;
    }
    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getDiagnosis() {
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    public String getTreatment() {
        return treatment;
    }
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
