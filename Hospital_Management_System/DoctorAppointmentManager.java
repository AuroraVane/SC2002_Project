import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoctorAppointmentManager extends OutcomeRecordManager{
    private AppointmentDatabase database;
    public DoctorAppointmentManager(String filePath, Doctor doctor){
        database= new AppointmentDatabase(filePath, doctor);

    }

    //once appointment marked as completed, will move and convert it into a ap/medical outocme record
    
    public void DoctorSet(LocalDate date, LocalTime start, LocalTime end, String doctorId, String ConsultationNotes_FILE_PATH){
        //Doctor adds an empty appointment
        //directly access txt to add this
        Slot newSlot=new Slot(Status.EMPTY, date, start, end, doctorId, ConsultationNotes_FILE_PATH);
        database.AddAppointmentInTxt(newSlot);
    }
    public void DoctorAccept(String doctorID, int index){
        //Doctor accepts appointment thats previously pending
        //1. Removes and retrieve from doctor pending list
        //2. Change status to confirmed in TXT file
        Slot slot=database.popHTSlot(doctorID, "DOCTOR/PENDING", index);
        database.changeAPStatusInTxt(slot.getSlotid(), Status.CONFIRMED);
    }
    public void ViewPendingList(){
        database.printList(database.getList("DOCTOR/PENDING"));
    }
    /*  
    Design Assumption: doctor declines appointment pending request if they're busy
    during that slot, so we do not add back to the doctor hashtable of available
    appointment times
    */
    public void DoctorDecline(String doctorID, int index){
        //Doctor accepts appointment thats previously pending
        //1. Removes and retrieve from doctor pending hashtable
        //2. Change status to cancelled 
        Slot slot=database.popHTSlot(doctorID, "DOCTOR/PENDING", index);
        database.changeAPStatusInTxt(slot.getSlotid(), Status.CANCELED);
    }

    public void viewDoctorUpcoming(){
        String type="DOCTOR/CONFIRMED";
        LocalDate today=LocalDate.now();
        List<Slot> arr = database.getList(type);
        List<LocalTime> times = new ArrayList<>();
        for (Slot s :arr){
            if (s.getDate().equals(today)) times.add(s.getStart());
        }
        if (!times.isEmpty()){
            Collections.sort(times, new Comparator<LocalTime>() {
                @Override
                public int compare(LocalTime time1, LocalTime time2) {
                    return time1.compareTo(time2);
                }
            });

            // Or using lambda expression (Java 8 and above)
            Collections.sort(times, (time1, time2) -> time1.compareTo(time2));

            // Print the sorted list
            for (LocalTime time : times) {
                System.out.println(time);
            }
        }
    }
    public void FinishAppointment(int index, String treatment_type){
        String type="DOCTOR/CONFIRMED";
        Slot removingSlot=database.popListSlot(type, index);
        database.changeAPStatusInTxt(removingSlot.getSlotid(), Status.CONFIRMED);
        giveType(treatment_type, removingSlot);
        WriteNotes(removingSlot);
        
    }
    public void viewConfirmedAppointments(){
        String type="DOCTOR/CONFIRMED";
        List<Slot> arr = database.getList(type);
        for (Slot slot : arr) {
            System.out.println("Date: " + slot.getDate().toString());
            System.out.println("Start Time: " + slot.getStart().toString());
        }

    }

    //VIEW UPCOMING APPOINTMENTS - VIEW APPOINTMENTS FOR TODAY SORT BY TIME?
    public void viewDoctorPersonalSchedule(){
        String type="DOCTOR/CONFIRMED";
        List<Slot> arr = database.getList(type);
        for (Slot slot : arr) {
            System.out.println("Date: " + slot.getDate().toString());
            System.out.println("Start Time: " + slot.getStart().toString());
            System.out.println("End Time: " + slot.getEnd().toString());
            System.out.println();
        }
    }
    /* 
    !! Design Assumption: Patient and Doctors can only cancel pending appointments, 
    once confirmed by doctor,the appointment can no longer be cancelled!!
    */

    


}

