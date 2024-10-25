
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
enum Status{
    EMPTY,
    PENDING,
    CONFIRMED,
    COMPLETED,
    CANCELED
};
public class Slot {
    private String slotid;
    private LocalTime start;
    private LocalTime end;
    private LocalDate date;
    private Status status;
    private String doctorId;

    public String getSlotid() {
        return this.slotid;
    }

    public void setSlotid(String slotid) {
        this.slotid = slotid;
    }

    public LocalTime getStart() {
        return this.start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return this.end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDoctorId() {
        return this.doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    public void setRecord(OutcomeRecord record) {
        this.record = record;
    }
    private String patientId;
    private OutcomeRecord record;
    public Slot(Status status, LocalDate date, LocalTime start, LocalTime end, String doctorId, String ConsultationNotes_FILE_PATH){
        this.slotid=UUID.randomUUID().toString();
        this.start=start;
        this.end=end;
        this.date=date;
        this.doctorId=doctorId;
        this.status=Status.EMPTY;
        this.record= new OutcomeRecord("", ConsultationNotes_FILE_PATH);
    }
    public Slot(String slotid, Status status, LocalDate date, LocalTime start, LocalTime end, String doctorId, String PatientId, String treatment_type, String ConsultationNotes_FILE_PATH){
        this.slotid=slotid;
        this.start=start;
        this.end=end;
        this.date=date;
        this.doctorId=doctorId;
        this.status=Status.EMPTY;
        this.record= new OutcomeRecord(treatment_type, ConsultationNotes_FILE_PATH);
    }
    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status=status;
    }

    public OutcomeRecord getRecord(){
        return this.record;
    }

 
}
