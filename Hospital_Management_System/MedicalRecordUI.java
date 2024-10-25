import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MedicalRecordUI {
    MedicalRecordController MRController;
    PatientManager patientmanager;

    public MedicalRecordUI(String MRfilepath, String PatientfilePath, String OverseeingPatientsFilePath, String id)throws IOException{
        MRController=new MedicalRecordController(MRfilepath, PatientfilePath);
        patientmanager=new PatientManager(id, OverseeingPatientsFilePath, PatientfilePath);
    }

    public void ViewOverseeingPatients(){
        List<MedicalRecord> MRList=MRController.getOverseeingPatientsMR(patientmanager.getOverseeingPatientsID());
        MRController.printAllMedicalRecordOfOverseeing(MRList);
    }

    public void UpdatePatientMedicalRecords(){
        System.out.println("Select which Patient's Medical Record to view");
        Scanner sc=new Scanner(System.in);
        int index=sc.nextInt();
        patientmanager.printAlloverseeingPatients();
        Patient p=patientmanager.getPatientByIndex(index);
        MedicalRecord m=MRController.findPatientMedicalRecord(p.getId());
        if (m==null){
            System.out.println("Error. Could not find Patients medical record in database.");
        }

        //still gotta add prescription?? im not sure what class isit at
        System.out.println("Select what you want to edit:");
        System.out.println("1. Diagnosis");
        System.out.println("2. Treatment Plans");
        int choice=sc.nextInt();
        switch(choice){
            case 1:MRController.EditFile(m.getDiagnosis_FILEPATH());
            case 2: MRController.EditFile(m.getTreatment_plans_FILEPATH());
            default:System.out.println("Set filename for consultation notes");
        }
    }

}
