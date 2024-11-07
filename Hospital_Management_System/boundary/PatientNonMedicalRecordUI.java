package boundary;
import java.util.Scanner;

import entity.Patient;
import utils.TextFileWriter;

public class PatientNonMedicalRecordUI {
    Patient patient;
    public PatientNonMedicalRecordUI(Patient patient) {
        this.patient=patient;
    }
    public void setContactInfo(String newContactInfo) {
        this.patient.setContactinfo(newContactInfo);
        System.out.println("Contact information updated successfully to " + newContactInfo + ".");
    }
    public void updateContactInfo(){
        System.out.println("Enter new contact information:");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String newContactInfo = sc.nextLine();
        TextFileWriter.updatePatientEmail(this.patient.getId(), newContactInfo);
        this.patient.setContactinfo(newContactInfo);
    }
    
}
