package boundary;
import controller.BillController;
import controller.DoctorAppointmentController;
import entity.Appointment;
import entity.Appointment.Status;
import entity.Doctor;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import utils.TextFileReader;
import utils.TextFileWriter;

/**
 * UI class for DoctorAppointmentUI used by DoctorAppointmentController
 */
public class DoctorAppointmentUI extends AppointmentUI{
    private TextFileWriter writer;
    DoctorAppointmentController doctorAppmtController; 
    int index, choice;

    /**
     * @param doctor
     * @param appointmentfilepath
     * @throws IOException
     */
    public DoctorAppointmentUI(Doctor doctor, String appointmentfilepath)throws IOException{
        this.writer=new TextFileWriter();
        this.doctorAppmtController=new DoctorAppointmentController(appointmentfilepath, doctor);
    }

    /**
     * @param appointmentList
     */
    @Override
    public void printAllAppointments(List<Appointment> appointmentList){
        System.out.println("Appointment ID |     Patient    | Date     | Time | Status");
        for (Appointment appointment : appointmentList) {
            System.out.printf("%-14s | %-14s | %s | %s | %s%n",
                            appointment.getAppointmentID(),
                            TextFileReader.findUserName(appointment.getPatientID(), "Patient"),
                            appointment.getDate(),
                            appointment.getTime(),
                            appointment.getStatus().toString());
        }
    }


    /**
     * @param appointmentList
     */
    public void ViewDoctorPersonalSchedule(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments(Status.CONFIRMED);
        appointments.addAll(doctorAppmtController.GetStatusAppointments(Status.EMPTY));
        if (!appointments.isEmpty()){
            printAllAppointments(appointments);
        }else{
            System.out.println("No current appointments!");
        }
        
    }//3

    public int Selection(List<Appointment> appmt){
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int index;
        index=sc.nextInt()-1;
        sc.nextLine();
        if (index==-1){
            return -1;
        }else if(index<-1 || index>appmt.size()){
            System.out.println("Invalid Slot Chosen. ");
        }else{
            System.out.println("Slot selected.");
            return index;
        }
        return -1;
    }

    /**
     * @param doctorID
     */
    public void SetAvailability(String doctorID){
        
        while (true) {
            System.out.println("Current avilability slots:");
            List<Appointment> emptyappointments=doctorAppmtController.GetStatusAppointments(Status.EMPTY);
            printAllAppointmentsWithIndex(emptyappointments);
            System.out.println("1. Set new avilability slot");
            System.out.println("2. Update existing avilability slot");
            System.out.println("3. Remove an avilability slot");
            System.out.println("0. Back");
            @SuppressWarnings("resource")
            int index=0, index2=0;
            Scanner sc = new Scanner(System.in);
            int choice=sc.nextInt();
            sc.nextLine();
            
            if (choice==0){
                return;
            }
            switch (choice) {
                case 1:
                    break;
                case 2:
                    System.out.println("Select which Slot you want to update:");
                    index=Selection(emptyappointments);
                    if (index!=-1){
                        break;
                    }
                case 3:
                    System.out.println("Select which Slot you want to remove:");
                    index2=Selection(emptyappointments);
                    if (index2!=-1){
                        System.out.println("Slot Removed.");
                        int id=emptyappointments.get(index2).getAppointmentID();
                        doctorAppmtController.RemoveAppointment(id);
                        TextFileWriter.deleteAppointment(id);
                        break;
                    }
                default:
                    System.out.println("Invalid option.");
                    
            }
            if (choice==1 || choice==2){
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
                while (!validTime ) {
                    System.out.println("Give Time in format HHMM (e.g., 1400): ");
                    time = sc.nextLine();
        
                    // Validate time format (HHMM)
                    if (time.matches("\\d{4}")) {
                        validTime = true;
                    } else {
                        System.out.println("Invalid time format. Please try again.");
                    }
                }
                if (choice==1){
                    Appointment appmt=doctorAppmtController.SetAvailableSlot(doctorID, dateStr, time);
                    emptyappointments.add(appmt);
                }else if (choice==2){
                    Appointment newappmt=emptyappointments.get(index);
                    newappmt.setDate(dateStr);
                    newappmt.setTime(time);
                    TextFileWriter.updateAppointment(newappmt);
                }
            }
        }
    }//4

    /**
     * @param appointments
     */
    public void ManagePendingAppointments(){
        List<Appointment> appointments=doctorAppmtController.GetStatusAppointments(Status.PENDING);
        
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
            appointments=doctorAppmtController.GetStatusAppointments(Status.PENDING);
        }
        if(appointments.isEmpty()){
            System.out.println("You currently have 0 Pending appointments");
        }
    }//5

    /**
     * @param appointments
     */
    public void RecordAppointmentOutcome(){
        while(true){
            List<Appointment> appointments=doctorAppmtController.GetStatusAppointments(Status.CONFIRMED);
            if (!appointments.isEmpty()){
                printAllAppointmentsWithIndex(appointments);
                System.out.println("Select which Confirmed Appointment you want to conclude \nEnter 0 to go back");
                @SuppressWarnings("resource")
                int index=-1;
                Scanner sc=new Scanner(System.in);
                while(index==-1){
                    index=Selection(appointments);
                };
                sc.nextLine();
                Appointment appmt= appointments.get(index);
                int appmtID = appmt.getAppointmentID();
                doctorAppmtController.RemoveAppointment(appmtID);
                System.out.println("Enter service type:");
                String service=sc.nextLine();
                
                //Remove Appointment from appointment list and add appointment outcome
                System.out.println("Enter medcine prescribed:");
                String med=sc.nextLine();

                System.out.println("Write Consultation Notes:");
                String notes=sc.nextLine();
                BillController.addBill(String.valueOf(appmtID), appmt.getPatientID(), med, BillController.PaymentStatus.UNPAID);
                writer.addAppointmentOutcome(appmtID, appmt.getDate(), service, med, false, notes);
                doctorAppmtController.ResolveAppointment(appmt);
            }else{
                System.out.println("You currently have 0 Completed appointments waiting");
                break;
            }
        }
        
    }//7


    /**
     * @param appointments
     */
    public void ViewUpcomingAppointments(){
        List<Appointment> list=doctorAppmtController.GetDoctorUpcoming();
        if (list!=null){
            printAllAppointments(list);
        }
    }//6
    
}
