
public class MedicalRecordManager {
    private MedicalRecordDatabase database;

    public MedicalRecordManager() {
        database = new MedicalRecordDatabase();

    }
    public void viewOverseeingPatients(String doctorId){
        database.viewOverseeingPatientsList(doctorId);
    }
    public void WriteDiagnosis(String patientId){
        MedicalRecord MR=database.getPatientMR(patientId);
        TextFileWriter.WriteFile(MR.getDiagnosis_FILEPATH());
    }
    public void WriteTreatmentPlans(String patientId){
        MedicalRecord MR=database.getPatientMR(patientId);
        TextFileWriter.WriteFile(MR.getTreatment_plans_FILEPATH());
    }

    public void ViewMedical(String patientId){
        MedicalRecord MR=database.getPatientMR(patientId);
        TextFileReader.NormalRead(MR.getDiagnosis_FILEPATH());
    }

    //only doctors can edit medical part of the MR
    public void EditMedicalTxt(String patientId, String type){
        MedicalRecord MR=database.getPatientMR(patientId);
        if (type.equals("DIAGNOSIS")){
            TextFileWriter.WriteFile(MR.getDiagnosis_FILEPATH());
        }else if (type.equals("TREATMENT_PLANS")){
            TextFileWriter.WriteFile(MR.getTreatment_plans_FILEPATH());
        }else{
            System.out.println("Error. No such medical record type.");
        }
    }

    public void EditNonMedical(Patient patient){
        //consist of patient getters and setters + additional guiding print statments 
    } 
}
