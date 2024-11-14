package boundary;
import entity.Appointment;
import java.util.List;

/**
 * UI class for AppointmentUI to be inherited by other AppointmentUI subclasses
 */
public interface AppointmentUI {


    /**
     * Abstract class for printing a single appointment
     * @param appmt
     */
    public abstract void printAppointment(Appointment appmt);

    /**
     * Abstract class for printing all appointments from a list of appointments
     * @param appointmentList
     */
    public abstract void printAllAppointments(List<Appointment> appointmentList);

    

}
