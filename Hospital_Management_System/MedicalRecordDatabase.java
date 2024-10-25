
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List; 

public class MedicalRecordDatabase extends Database{
    private List<Patient> overseeingPatients;
    private Hashtable<String, MedicalRecord> recordList;
    
    
    
    public MedicalRecordDatabase() {
        overseeingPatients = new ArrayList<>();
        recordList =new Hashtable<>();
    }
    public void viewOverseeingPatientsList(String doctorId) {
        printList(overseeingPatients);
    }
    public Patient getOverseeingPatient(int index){
        return overseeingPatients.get(index);
    }
    /*im still cosnidering whether:
    1. to make 1 txt file for each doctor to record overseeing patients
    OR 2. make doctor overseeing an attribute in every patient object
    will come bak ltr to this, for now overseeingPatients will not be deployed
    */

    /* 

    public void addOverseeingPatient(String doctorId, Patient patient) {
        List<Patient> patients = overseeingPatients.getOrDefault(doctorId, new ArrayList<>());
        patients.add(patient);
        overseeingPatients.put(doctorId, patients);
    }

    public void removeOverseeingPatient(String doctorId, Patient patient) {
        List<Patient> patients = overseeingPatients.get(doctorId);
        if (patients != null) {
            patients.remove(patient);
            if (patients.isEmpty()) {
                overseeingPatients.remove(doctorId);
            }
        }
    }
    public void ExportSavedDatabase(){

    }

    public Hashtable<String, List<Slot>> ImportRecordList(String type) throws IOException {
        Hashtable<String, List<Slot>> table=new Hashtable<>();
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String slotid = details[0];
            String start = details[1];
            String end = details[2];
            String date = details[3];
            String status = details[4];
            String doctorId = details[5];
            String patientId = details[6];
            String treatment_type = details[7];
            String ConsultationNotes_FILEPATH = details[8];
            Slot slot=new Slot(slotid, StringtoStatus(status), StringToLocalDate(date), StringToLocalTime(start), StringToLocalTime(end), doctorId, patientId, treatment_type, ConsultationNotes_FILEPATH);
            addht(patientId, slot, table);
        }
        reader.close();
        return table;
    }
    
    */
    public void viewAllPatientMR(){
        //iterate recordList for patient and print all MR
        printItemTable(recordList);
    }
    public MedicalRecord getPatientMR(String patientId){
        return recordList.get(patientId);
    }
    

}
