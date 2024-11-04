import java.io.IOException;
import java.util.List;

public class PatientManager {
    private List<Patient> OverseeingPatients;
    private List<String> OverseeingPatientsID;
    
    public PatientManager(String doctorId, String OverseeingPatientsFilePath, String PatientFilePath) throws IOException{
        this.OverseeingPatientsID=TextFileReader.loadOverseeingPatients(doctorId, OverseeingPatientsFilePath);
        this.OverseeingPatients=TextFileReader.loadSelectivePatients(PatientFilePath, OverseeingPatientsID);
        
    }

    public void printAlloverseeingPatients() {
        int i=0;
        System.out.println("Overseeing Patients:");
        for (Patient patient : this.OverseeingPatients) {
            System.out.println(i+". "+patient.getName());
            i++;
        }
    }
    public Patient getPatientByIndex(int index){
        return this.OverseeingPatients.get(index);
    }
    public List<String> getOverseeingPatientsID(){
        return this.OverseeingPatientsID;
    }
}
// D0458|P009|P8293