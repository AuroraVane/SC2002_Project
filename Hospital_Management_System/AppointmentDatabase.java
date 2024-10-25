
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List; 

/*
    Deisgn Considerations:
        Patient choose available slot they want by date
        I also put doctor's confirmed slots byt date because of convenience when viewing their scheudles
        
        I assume patients dont have as many appointments as the doctor so hashtable isnt as necessary
        
*/
public class AppointmentDatabase extends Database{    
    private Hashtable<String, List<Slot>> doctorEmptySlots; //for patient
    private List<Slot> patientConfirmedSlots; //for patient
    private List<Slot> doctorPendingSlots; //for doctor review
    private Hashtable<String, List<Slot>> doctorConfirmedSlots; //for doctor schedule
    private String filePath;
    //for doctor
    public AppointmentDatabase(String filePath, Doctor doctor) {
        this.filePath=filePath;
        try {
            doctorPendingSlots = ImportDoctorAppointments(Status.PENDING, doctor.getId());
            doctorConfirmedSlots = ImportAppointmentsByDate("DOCTOR/CONFIRMED");
        } catch (IOException e) {
            // Handle the IOException, e.g., log the error, retry the operation, or notify the user.
            System.err.println("Error importing appointments: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions, e.g., database errors, parsing errors.
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    //For patient
    public AppointmentDatabase(String filePath, Patient patient){
        this.filePath=filePath;
        
        try {
            patientConfirmedSlots = ImportDoctorAppointments(Status.CONFIRMED, patient.getId());
            doctorEmptySlots = ImportAppointmentsByDate("DOCTOR/EMPTY");
        } catch (IOException e) {
            // Handle the IOException, e.g., log the error, retry the operation, or notify the user.
            System.err.println("Error importing appointments: " + e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions, e.g., database errors, parsing errors.
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
        doctorEmptySlots = new Hashtable<>();
        patientConfirmedSlots = new ArrayList<>();
        
    }

    public List<Slot> getListSelection(String choice){
        switch (choice) {
            case "PATIENT/CONFIRMED":
                return patientConfirmedSlots;
            case "DOCTOR/PENDING":
                return doctorPendingSlots;
            default:
                throw new IllegalArgumentException("Invalid key");
        }
    }
    public Hashtable<String, List<Slot>> getTableSelection(String choice){
        switch (choice) {
            case "DOCTOR/EMPTY":
                return doctorEmptySlots;
            case "DOCTOR/CONFIRMED":
                return doctorConfirmedSlots;
            default:
                throw new IllegalArgumentException("Invalid key");
        }
    }
    
    public void addListSlot(Slot value, String type){
        List<Slot> list=getListSelection(type);
        list.add(-1, value);
    }

    public Slot popListSlot(String type, int index){
        List<Slot> list=getListSelection(type);
        return list.remove(index);
        
    }
    public Slot getListSlot(String type, int index){
        List<Slot> list=getListSelection(type);
        return list.get(index);
    }

    public void addHTSlot(String key, Slot value, String type){
        addht(key,value,getTableSelection(type));
    }

    public Slot popHTSlot(String key, String type, int index){
        return popht(key,index,getTableSelection(type));
        
    }
    public Slot getHTList(String key, String type, int index){
        return getht(key,index,getTableSelection(type));
    }
    public List<Slot> getList(String type){
        return getListSelection(type);
    }
    

    public Status StringtoStatus(String s){
        return Status.valueOf(s);
    }
    public LocalDate StringToLocalDate(String x){
        return LocalDate.parse(x);
    }
    public LocalTime StringToLocalTime(String x){
        return LocalTime.parse(x);
    }

    public Hashtable<String, List<Slot>> ImportAppointmentsByDate(String type) throws IOException {
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
            addht(date, slot, table);
        }
        reader.close();
        return table;
    }
    public List<Slot> ImportDoctorAppointments(Status s, String ID) throws IOException{
        List<Slot> doctorAP = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String statusStr = details[4];
            Status status =StringtoStatus(statusStr);
            String doctorId = details[5];
            if (status!=s || !doctorId.equals(ID)){
                continue;
            }
            String slotid=details[0];
            String start = details[2];
            String end = details[3];
            String date = details[1];
            String patientId = details[6];
            String treatment_type = details[7];
            String ConsultationNotes_FILEPATH = details[8];
            Slot slot=new Slot(slotid, status, StringToLocalDate(date), StringToLocalTime(start), StringToLocalTime(end), doctorId, patientId, treatment_type, ConsultationNotes_FILEPATH);
            doctorAP.add(slot);
        }
        reader.close();
        return doctorAP;
    }

    public void AddAppointmentInTxt(Slot slot){
        try {
            FileWriter Writer = new FileWriter(this.filePath);
            
            Writer.write(slot.getSlotid()+ '|' );
            Writer.write(slot.getDate().toString()+ '|');
            Writer.write(slot.getStart().toSecondOfDay()+ '|');
            Writer.write(slot.getEnd().toString()+ '|');
            Writer.write(slot.getStatus().toString()+ '|');
            Writer.write(slot.getDoctorId()+ '|');
            Writer.write(slot.getPatientId()+ '|');
            Writer.write(slot.getRecord().getTreatment_type()+ '|');
            Writer.write(slot.getRecord().getConsultationNotes_FILE_PATH());
            //prevent resource leaks
            Writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void changeAPStatusInTxt(String slotId, Status status) {
        try {
            File file = new File(this.filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split("\\|");
                if (details[0].equals(slotId)) {
                    details[3] = status.toString();
                    line = String.join("|", details);
                }
                writer.write(line + System.lineSeparator());
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
            // Handle the exception, e.g., log the error, retry the operation, or notify the user.
        }
    }

}
