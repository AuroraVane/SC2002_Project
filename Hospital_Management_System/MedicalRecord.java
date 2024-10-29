import java.util.ArrayList;

public class MedicalRecord {
    private Patient patient;
    private ArrayList<String> diagnoses;
    private ArrayList<String> treatments;

    public MedicalRecord(Patient patient) {
        this.patient = patient;
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
    }

    public void viewMedicalRecord() {
        System.out.println("Medical Record for " + patient.getName() + ":");
        System.out.println("DOB: " + patient.getDOB());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Diagnoses: ");
        for (String diagnosis : diagnoses) {
            System.out.println("- " + diagnosis);
        }
        System.out.println("Treatments: ");
        for (String treatment : treatments) {
            System.out.println("- " + treatment);
        }
    }

    // Adding a new diagnosis to the medical record
    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
        System.out.println("New diagnosis added: " + diagnosis);
    }

    // Adding a new treatment to the medical record
    public void addTreatment(String treatment) {
        treatments.add(treatment);
        System.out.println("New treatment added: " + treatment);
    }

    // Getters
    public ArrayList<String> getDiagnoses() {
        return diagnoses;
    }

    public ArrayList<String> getTreatments() {
        return treatments;
    }
}
