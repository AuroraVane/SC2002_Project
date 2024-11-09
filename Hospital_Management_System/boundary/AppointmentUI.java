package boundary;
import entity.Appointment;
import java.util.List;

public abstract  class AppointmentUI {
    
    public abstract void printAllAppointments(List<Appointment> appointmentList);

    public void printAllAppointmentsWithIndex(List<Appointment> appointmentList) {
        int i=0;
        for (Appointment appmt: appointmentList){
            System.out.println(i + ". "+appmt.getDate()+ " "+appmt.getTime()+"\n");
            i++;
        }
    }

}
