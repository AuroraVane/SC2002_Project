package boundary;
import java.util.Scanner;

import entity.Patient;
import utils.TextFileWriter;

/**
 * UI class for PatientNonMedicalRecordUI
 */
public class PatientNonMedicalRecordUI {
    Patient patient;

    /**
     * Patient Non Medical Record UI constructor
     * @param patient
     */
    public PatientNonMedicalRecordUI(Patient patient) {
        this.patient=patient;
    }
    public void setContactInfo(String newContactInfo) {
        this.patient.setContactinfo(newContactInfo);
        System.out.println("Contact information updated successfully to " + newContactInfo + ".");
    }

    /**
     *updates contact information of patient via medical record
     */
    public void updateContactInfo(){
        System.out.println("Enter new contact information:");
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        String newContactInfo = sc.nextLine();
        TextFileWriter.updatePatientEmail(this.patient.getId(), newContactInfo);
        this.patient.setContactinfo(newContactInfo);
    }
    
}
