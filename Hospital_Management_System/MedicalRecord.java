public class MedicalRecord {
    private Patient patient;
    // Would need it own textfile to store the data
    public MedicalRecord(Patient patient){
        this.patient = patient;
    }

    public void skeletonMedicalRecord(){
        System.out.println("Skeleton For Medical Record");
    }
}
