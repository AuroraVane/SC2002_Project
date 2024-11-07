package boundary;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controller.DoctorAppointmentController;
import entity.Appointment;
import entity.Doctor;
import utils.TextFileWriter;

public class DoctorAppointmentUI extends AppointmentUI{
    private TextFileWriter writer;
    DoctorAppointmentController doctorAppmtController; 
    int index, choice;
    public DoctorAppointmentUI(Doctor doctor, String appointmentfilepath)throws IOException{
        this.writer=new TextFileWriter();
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
                case 0: doctorAppmtController.DeclinePendingAppointment(appmt);
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
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("CONFIRMED");
        if (!appointments.isEmpty()){
            printAllAppointmentsWithIndex(appointments);
            System.out.println("Select which Confirmed Appointment you want to conclude");
            @SuppressWarnings("resource")
            Scanner sc=new Scanner(System.in);
            index=sc.nextInt();
            if (index>=appointments.size()){
                System.out.println("Invalid Option. Returning to menu...");
                return;
            }
            sc.nextLine();
            Appointment appmt= appointments.get(index);
            doctorAppmtController.RemoveAppointment(appmt.getAppointmentID());
            System.out.println("Enter service type:");
            String service=sc.nextLine();
            
            //Remove Appointment from appointment list and add appointment outcome
            System.out.println("Enter medcine prescribed:");
            String med=sc.nextLine();
            
            System.out.println("Write Consultation Notes:");
            String notes=sc.nextLine();
            writer.addAppointmentOutcome(appmt.getAppointmentID(), appmt.getDate(), service, med, false, notes);
            doctorAppmtController.ResolveAppointment(appmt);
        }else{
            System.out.println("You currently have 0 Completed appointments waiting");
        }
        
    }//7


    public void ViewUpcomingAppointments(){
        doctorAppmtController.ViewDoctorUpcoming();
    }//6
    
}
