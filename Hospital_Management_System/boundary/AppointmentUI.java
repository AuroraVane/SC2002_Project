package boundary;
import entity.Appointment;
import java.util.List;

/**
 * UI class for AppointmentUI to be inherited by other AppointmentUI subclasses
 */
public abstract  class AppointmentUI {

    /**
     * @param appointmentList
     */
    public abstract void printAllAppointments(List<Appointment> appointmentList);

    /**
     * @param appointmentList
     */
    public void printAllAppointmentsWithIndex(List<Appointment> appointmentList) {
        int i=1;
        for (Appointment appmt: appointmentList){
            System.out.println(i + ". "+appmt.getDate()+ " "+appmt.getTime()+"\n");
            i++;
        }
    }

}
