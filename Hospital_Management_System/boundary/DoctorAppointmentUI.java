package boundary;
import controller.DoctorAppointmentController;
import entity.Appointment;
import entity.Doctor;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import utils.TextFileReader;
import utils.TextFileWriter;

public class DoctorAppointmentUI extends AppointmentUI{
    private TextFileWriter writer;
    DoctorAppointmentController doctorAppmtController; 
    int index, choice;
    public DoctorAppointmentUI(Doctor doctor, String appointmentfilepath)throws IOException{
        this.writer=new TextFileWriter();
        this.doctorAppmtController=new DoctorAppointmentController(appointmentfilepath, doctor);
    }
    @Override
    public void printAllAppointments(List<Appointment> appointmentList){
        System.out.println("Appointment ID | Patient | Date       | Time");
        for (Appointment appointment : appointmentList) {
            System.out.printf("%-14s | %-9s | %s | %s%n",
                            appointment.getAppointmentID(),
                            TextFileReader.findUserName(appointment.getPatientID(), "Patient"),
                            appointment.getDate(),
                            appointment.getTime());
        }
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
        while (true) {
            @SuppressWarnings("resource")
            Scanner sc = new Scanner(System.in);
    
            String dateStr=""; // Declare outside the loop
            String time="";   // Declare outside the loop
    
            boolean validDate = false;
            while (!validDate) {
                System.out.println("Enter date in format YYYYMMDD (e.g., 20241110): \nEnter 'b' to return to menu");
                dateStr = sc.nextLine();
    
                // Validate date format (YYYYMMDD)
                if (dateStr.equals("b")) {
                    return;
                }
                if (dateStr.matches("\\d{8}")) {
                    validDate = true;
                } else {
                    System.out.println("Invalid date format. Please try again.");
                }
            }
    
            boolean validTime = false;
            while (!validTime) {
                System.out.println("Give Time in format HHMM (e.g., 1400): ");
                time = sc.nextLine();
    
                // Validate time format (HHMM)
                if (time.matches("\\d{4}")) {
                    validTime = true;
                } else {
                    System.out.println("Invalid time format. Please try again.");
                }
            }
    
            doctorAppmtController.SetAvailableSlot(doctorID, dateStr, time);
        }
    }//4

    public void ManagePendingAppointments(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("PENDING");
        
        while (!appointments.isEmpty()){
            System.out.println("Select which Pending Slot you want to review. \nEnter 0 to go back");
            printAllAppointmentsWithIndex(appointments);
            @SuppressWarnings("resource")
            Scanner sc=new Scanner(System.in);
            index=sc.nextInt()-1;
            if (index==-1){
                break;
            }
            
            
            System.out.println("Enter 1 to accept and 2 to decline \nEnter 0 to go Back");
            choice=sc.nextInt();
            Appointment appmt=appointments.get(index);
            switch(choice){
                case 1: doctorAppmtController.AcceptPendingAppointment(appmt);
                        break;
                case 2: doctorAppmtController.DeclinePendingAppointment(appmt);
                        break;
                case 0: break;
                default: System.out.println("Error, invalid option");
                        break;

            }
        }
        if(appointments.isEmpty()){
            System.out.println("You currently have 0 Pending appointments");
        }
    }//5
    public void RecordAppointmentOutcome(){
        while(true){
            List<Appointment> appointments=doctorAppmtController.GetStatusAppointments("CONFIRMED");
            if (!appointments.isEmpty()){
                printAllAppointmentsWithIndex(appointments);
                System.out.println("Select which Confirmed Appointment you want to conclude \nEnter 0 to go back");
                @SuppressWarnings("resource")
                Scanner sc=new Scanner(System.in);
                index=sc.nextInt()-1;
                if(index==-1){
                    return;
                }
                if (index>=appointments.size()){
                    System.out.println("Invalid Option.");
                    break;
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
        }
        
    }//7


    public void ViewUpcomingAppointments(){
        List<Appointment> list=doctorAppmtController.GetDoctorUpcoming();
        if (list!=null){
            printAllAppointments(list);
        }
    }//6
    
}
