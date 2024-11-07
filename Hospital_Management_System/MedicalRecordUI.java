import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordUI {
    private MedicalRecordController MRController;
    private PatientManager patientmanager;
    private TextFileWriter writer;
    private String id;

    public MedicalRecordUI(String MRfilepath, String PatientfilePath, String OverseeingPatientsFilePath, String id)throws IOException{
        this.MRController=new MedicalRecordController(MRfilepath, PatientfilePath);
        this.patientmanager=new PatientManager(id, OverseeingPatientsFilePath, PatientfilePath);
        this.writer=new TextFileWriter();
        this.id=id;
    }

    public void ViewOverseeingPatients(){
        List<MedicalRecord> MRList=MRController.getOverseeingPatientsMR(patientmanager.getOverseeingPatientsID());
        try {
            MRController.printAllMedicalRecordOfOverseeing(MRList);
        } catch (IOException e) {
            System.out.println("Error: Unable to load staff list from file.");
            e.printStackTrace(); // Optional: To print the stack trace for debugging
        }
    }
    
    public void UpdatePatientMedicalRecords(){
        System.out.println("Select which Patient's Medical Record to view");
        patientmanager.printAlloverseeingPatients();
        @SuppressWarnings("resource")
        Scanner sc=new Scanner(System.in);
        int index=sc.nextInt();
        Patient p=patientmanager.getPatientByIndex(index);
        MedicalRecord m=MRController.findPatientMedicalRecord(p.getId());
        if (m==null){
            System.out.println("Error. Could not find Patients medical record in database.");
            return;
        }

        //still gotta add prescription?? im not sure what class isit at
        System.out.println("Select what you want to edit:");
        System.out.println("1. Diagnosis");
        System.out.println("2. Treatment Plans");
        int choice=sc.nextInt();
        
        switch(choice){
            case 1: 
                writer.WriteFile("./TextFiles/"+m.getDiagnosis_FILEPATH());
                break;
            case 2: 
                writer.WriteFile("./TextFiles/"+m.getTreatment_plans_FILEPATH());
                break;
            default:
                System.out.println("Error, File could not be found.");
                break;
        }
        
    }//2

}
