import java.io.IOException;
import java.util.List;

public class Login {
    private List<Patient> patientList;
    private List<Staff> staffList;

    public Login(String patientFilePath, String staffFilePath) throws IOException{
        patientList = TextFileReader.loadPatients(patientFilePath);
        staffList = TextFileReader.loadStaff(staffFilePath);
    }

    public User authenticate(String id, String password){
        for (Patient patient : patientList) {
            if (patient.getId().equals(id) && patient.getPassword().equals(password)) {
                return patient;
            }
        }
        for (Staff staff : staffList) {
            if (staff.getId().equals(id) && staff.getPassword().equals(password)) {
                return staff;
            }
        }
        
        System.out.println("Invalid ID or password");
        return null;
    }
}