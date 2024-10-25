import java.util.List;

public class AppointmentUI {
    public void printMainMenu() {
        System.out.println("View Appointments");
    }
    public void printAllAppointments(List<Appointment> appointmentList) {
        System.out.println("Appointments:");
        for (Appointment appointment : appointmentList) {
            System.out.println(appointment.getPatientID() + ", " + appointment.getStaffID() + ", " + appointment.getStatus() + ", " + appointment.getDate() + ", " + appointment.getTime());
        }
    }
    public void printAllAppointmentsWithIndex(List<Appointment> appointmentList) {
        int i=0;
        for (Appointment appmt: appointmentList){
            System.out.println(i + ". "+appmt.getDate()+ " "+appmt.getTime()+"\n");
            i++;
        }
    }




}
