import java.io.IOException;
import java.util.List;

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
    public void displayUI(){
        this.patientUI.printMenu();
    }

    public static List<Patient> getAllPatients(){
        
        String filePath = "Patient_List.txt";
        List<Patient> patientList;
        try {
             patientList = TextFileReader.loadPatients(filePath);
             return patientList;
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
}
