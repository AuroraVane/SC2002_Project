import java.util.List;
import java.util.Scanner;

public class DoctorAppointmentUI extends AppointmentUI{
    private TextFileWriter writer;
    DoctorAppointmentController doctorAppmtController; 
    int index, choice;
    public DoctorAppointmentUI(Doctor doctor){
    }
    

    public void ViewDoctorPersonalSchedule(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("CONFIRMED");
        printAllAppointments(appointments);
    }//3

    public void SetAvailability(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter date in format YYYY-MM-DD");
        String dateStr=sc.nextLine();
        System.out.println("Give Time in format HH:MM");
        String time=sc.nextLine();
        System.out.println("Set filename for consultation notes");
        String filepath=sc.nextLine();
        sc.close();
        doctorAppmtController.SetAvailableSlot(dateStr, time, filepath);
    }//4

    public void ManagePendingAppointments(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("CONFIRMED");
        System.out.println("Select which Pending Slot you want to review");
        printAllAppointmentsWithIndex(appointments);
        Scanner sc=new Scanner(System.in);
        index=sc.nextInt();
        System.out.println("Enter 1 to accept and 0 to decline");
        choice=sc.nextInt();
        Appointment appmt=appointments.get(index);
        switch(choice){
            case 1: doctorAppmtController.AcceptPendingAppointment(appmt);
            case 2: doctorAppmtController.DeclinePendingAppointment(appmt);
            default: System.out.println("Error");
        }
    }//5
    public void RecordAppointmentOutcome(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("COMPLETED");
        printAllAppointments(appointments);
        System.out.println("Select which Confirmed Appointment you want to conclude");
        Scanner sc=new Scanner(System.in);
        index=sc.nextInt();
        Appointment appmt= appointments.get(index);
        System.out.println("Enter service type");
        doctorAppmtController.RemoveAppointment(appmt.getAppointmentID());
        String service=sc.nextLine();
        
        //Remove Appointment from appointment list and add appointment outcome
        System.out.println("Enter medcine prescribed:");
        String med=sc.nextLine();
        System.out.println("Write Consultation Notes:");
        String notes=sc.nextLine();
        writer.addAppointmentOutcome(appmt.getAppointmentID(), notes, service, med, false, notes);
    }//7


    public void ViewUpcomingAppointments(){
        doctorAppmtController.ViewDoctorUpcoming();
    }//6
    
}
