import java.io.IOException;
import java.util.List;

import org.w3c.dom.Text;

import entity.Administrator;
import entity.Doctor;
import entity.Patient;
import entity.Pharmacist;
import entity.Staff;
import entity.User;
import utils.TextFileReader;
import utils.TextFileWriter;

public class Login {
    private List<Patient> patientList;
    private List<Staff> staffList;

    public Login(String patientFilePath, String staffFilePath) throws IOException{
        patientList = TextFileReader.loadPatients(patientFilePath);
        staffList = TextFileReader.loadStaff(staffFilePath);
    }

    public User authenticate(String id, String password){
        String passwordHash = TextFileWriter.hashPassword(password);
        for (Patient patient : patientList) {
            if (patient.getId().equals(id) && patient.getPassword().equals(passwordHash)) {
                return patient;
            }
        }
        for (Staff staff : staffList) {
            if (staff.getId().equals(id) && staff.getPassword().equals(passwordHash)) {
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