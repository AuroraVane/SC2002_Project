import java.util.List;

public class MedicalRecordUI {
    private List<MedicalRecord> medicalRecordList;
    public void viewPatientList(List<String> overseeingPatientList, List<MedicalRecord> medicalRecordList) {
        this.medicalRecordList = medicalRecordList;
        for (String patientID : overseeingPatientList) {
            for (MedicalRecord medicalRecord : medicalRecordList) {
                if (medicalRecord.getPatientID().equals(patientID)) {
                    viewMedicalRecord(patientID);
                }
            }
        }
    }
    public void viewMedicalRecord(String patientID) {
        for (MedicalRecord medicalRecord : medicalRecordList) {
            if (medicalRecord.getPatientID().equals(patientID)) {
                System.out.println();
                System.out.println("Appointment ID:"+ medicalRecord.getAppointmentID());
                System.out.println("Patient ID:"+ medicalRecord.getPatientID());
                System.out.println("Name:"+ medicalRecord.getName());
                System.out.println("DOB:"+ medicalRecord.getDOB());
                System.out.println("Gender:"+ medicalRecord.getGender());
                System.out.println("Bloodtype:"+ medicalRecord.getBloodtype());
                System.out.println("Contact:"+ medicalRecord.getContact());
                System.out.println("Diagnosis:"+ medicalRecord.getDiagnosis());
                System.out.println("Treatment:"+ medicalRecord.getTreatment());
                System.out.println();
            }
        }
    }
}
