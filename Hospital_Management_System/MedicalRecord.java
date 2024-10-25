import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private List<String> diagnoses;
    private List<String> treatments;

    public MedicalRecord() {
        this.diagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
    }

    public void addDiagnosis(String diagnosis) {
        diagnoses.add(diagnosis);
    }

    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    @Override
    public String toString() {
        return "Diagnoses: " + diagnoses + "\nTreatments: " + treatments;
    }
}
