
public class Patient extends User {
    private String DOB;
    private String bloodType;
    private String contactInfo;
    private MedicalRecord medicalRecord;
    private PatientUI patientUI;

    public Patient(String id, String name, String password, String gender, 
                   String DOB, String bloodType, String contactInfo) {
        super(id, name, password, gender);
        this.DOB = DOB;
        this.bloodType = bloodType;
        this.contactInfo = contactInfo;
        this.medicalRecord = new MedicalRecord();
        this.patientUI = new PatientUI(this);
    }

    @Override
    public String getRole() {
        return "Patient";
    }

    public void displayUI() {
        this.patientUI.printMenu();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }
}
