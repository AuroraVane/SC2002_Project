public class Patient extends User{
    private String DOB;
    private String bloodtype;
    private String contactinfo;

    public Patient(String id, String name, String password, String gender,String DOB, String bloodtype, String contactinfo) {
        super(id, name, password,gender);
        this.DOB = DOB;
        this.bloodtype = bloodtype;
        this.contactinfo = contactinfo;
    }
    @Override
    public String getRole() {
        return "Patient";
    }
}
