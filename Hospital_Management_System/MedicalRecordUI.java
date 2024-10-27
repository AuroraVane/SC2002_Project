import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordUI {
    MedicalRecordController MRController;
    PatientManager patientmanager;
    private TextFileWriter writer;

    public MedicalRecordUI(String MRfilepath, String PatientfilePath, String OverseeingPatientsFilePath, String id)throws IOException{
        MRController=new MedicalRecordController(MRfilepath, PatientfilePath);
        patientmanager=new PatientManager(id, OverseeingPatientsFilePath, PatientfilePath);
        writer=new TextFileWriter();
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
    public void WriteFile(String FILE_PATH, Scanner w) {
        try {
            FileWriter Writer = new FileWriter(FILE_PATH);
            System.out.println("Enter 0 to exit");
            //Scanner w=new Scanner(System.in);
            while (true) {
                String x=w.nextLine();
                if (x.equals("0")){
                    break;
                }
                Writer.write(x);
                
            }
            Writer.close();
            w.close();
            //prevent resource leaks
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void UpdatePatientMedicalRecords(){
        System.out.println("Select which Patient's Medical Record to view");
        patientmanager.printAlloverseeingPatients();
        Scanner scc=new Scanner(System.in);
        int index=scc.nextInt();
        Patient p=patientmanager.getPatientByIndex(index);
        MedicalRecord m=MRController.findPatientMedicalRecord(p.getId());
        if (m==null){
            System.out.println("Error. Could not find Patients medical record in database.");
        }

        //still gotta add prescription?? im not sure what class isit at
        System.out.println("Select what you want to edit:");
        System.out.println("1. Diagnosis");
        System.out.println("2. Treatment Plans");
        int choice=scc.nextInt();
        
        switch(choice){
            case 1: 
                WriteFile(m.getDiagnosis_FILEPATH(), scc);
                break;
            case 2: 
                WriteFile(m.getTreatment_plans_FILEPATH(), scc);
                break;
            default:
                System.out.println("Error, File could not be found.");
                break;
        }
        scc.close();
        
    }
    
    

}
