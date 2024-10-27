import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class DoctorAppointmentUI extends AppointmentUI{
    private TextFileWriter writer;
    DoctorAppointmentController doctorAppmtController; 
    int index, choice;
    public DoctorAppointmentUI(Doctor doctor, String appointmentfilepath)throws IOException{
        this.doctorAppmtController=new DoctorAppointmentController(appointmentfilepath, doctor);
    }
    

    public void ViewDoctorPersonalSchedule(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("CONFIRMED");
        if (!appointments.isEmpty()){
            printAllAppointments(appointments);
        }else{
            System.out.println("No current appointments!");
        }
        
    }//3

    public void SetAvailability(String doctorID){
        @SuppressWarnings("resource")
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter date in format YYYYMMDD");
        String dateStr=sc.nextLine();
        System.out.println("Give Time in format HHMM");
        String time=sc.nextLine();
        doctorAppmtController.SetAvailableSlot(doctorID, dateStr, time);
    }//4

    public void ManagePendingAppointments(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("PENDING");
        
        if (!appointments.isEmpty()){
            System.out.println("Select which Pending Slot you want to review");
            printAllAppointmentsWithIndex(appointments);
            @SuppressWarnings("resource")
            Scanner sc=new Scanner(System.in);
            index=sc.nextInt();
            System.out.println("Enter 1 to accept and 0 to decline");
            choice=sc.nextInt();
            Appointment appmt=appointments.get(index);
            switch(choice){
                case 1: doctorAppmtController.AcceptPendingAppointment(appmt);
                        break;
                case 2: doctorAppmtController.DeclinePendingAppointment(appmt);
                        break;
                default: System.out.println("Error");
                        break;

            }
        }
        else{
            System.out.println("You currently have 0 Pending appointments");
        }
    }//5
    public void RecordAppointmentOutcome(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("COMPLETED");
        if (!appointments.isEmpty()){
            printAllAppointments(appointments);
            System.out.println("Select which Confirmed Appointment you want to conclude");
            @SuppressWarnings("resource")
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
        }else{
            System.out.println("You currently have 0 Completed appointments waiting");
        }
    }//7


    public void ViewUpcomingAppointments(){
        doctorAppmtController.ViewDoctorUpcoming();
    }//6
    
}
