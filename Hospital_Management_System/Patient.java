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
        System.out.println("patientUI initialized: " + (this.patientUI != null));
    }
    @Override
    public String getRole() {
        return "Patient";
    }
    public void displayUI(){
        this.patientUI.printMenu();
    }
}
