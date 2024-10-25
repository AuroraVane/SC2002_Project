
public class OutcomeRecordManager {
    
    public void WriteNotes(Slot slot){
        TextFileWriter.WriteFile(slot.getRecord().getConsultationNotes_FILE_PATH());
    }
    public void giveType(String type, Slot slot){
        slot.getRecord().setTreatment_type(type);
    }
}
