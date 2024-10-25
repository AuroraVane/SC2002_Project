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
                if(staff.getRole().equals("Doctor")){
                    return new Doctor(staff.getId(), staff.getName(), staff.getPassword(), staff.getGender(), staff.getRole(), staff.getAge());
                }
                else if(staff.getRole().equals("Pharmacist")){
                    return new Pharmacist(staff.getId(), staff.getName(), staff.getPassword(), staff.getGender(), staff.getRole(), staff.getAge());
                }
                else if(staff.getRole().equals("Administrator")){
                    return new Administrator(staff.getId(), staff.getName(), staff.getPassword(), staff.getGender(), staff.getRole(), staff.getAge());
                }
            }
        }
        
        System.out.println("Invalid ID or password");
        return null;
    }
}