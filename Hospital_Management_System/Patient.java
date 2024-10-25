public class Patient extends User{
    private String DOB;
    private String bloodtype;
    private String contactinfo;

    private PatientUI patientUI;
    
    public Patient(String id, String name, String password, String gender,String DOB, String bloodtype, String contactinfo) {
        super(id, name, password,gender);
        this.DOB = DOB;
        this.bloodtype = bloodtype;
        this.contactinfo = contactinfo;
        this.patientUI = new PatientUI(this);
    }
    @Override
    public String getRole() {
        return "Patient";
    }
    public String getDOB() {
        return DOB;
    }
    public String getBloodtype() {
        return bloodtype;
    }
    public String getContactinfo() {
        return contactinfo;
    }
    public void displayUI(){
        this.patientUI.printMenu();
    }
}
