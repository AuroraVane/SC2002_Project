public class PatientAppointmentManager {
    
    public void PatientSchedule(Slot newPending){
        //Pop from doctor empty hashtable
        //add to doctor pending hashtable 
    }
    public void PatientCancel(int index){
        //Pop slot from doctor's pending hashtable
        //Convert slot status to EMPTY
        //add back to the doctor's emptyslots hashtable        
    }
    public void PatientReschedule(Slot newPending, Slot oldPending){
        //Run cancel and then schedule
    }

}
