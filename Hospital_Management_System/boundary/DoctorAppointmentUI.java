package boundary;
import controller.BillController;
import controller.DoctorAppointmentController;
import entity.Appointment;
import entity.Appointment.Status;
import entity.Doctor;
import entity.Medicine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.TextFileReader;
import utils.TextFileWriter;

/**
 * UI class for DoctorAppointmentUI used by DoctorAppointmentController
 */
public class DoctorAppointmentUI implements AppointmentUI{
    private TextFileWriter writer;
    DoctorAppointmentController doctorAppmtController; 
    int index, choice;

    /**
     * Doctor appointment related UI
     * @param doctor
     * @param appointmentfilepath
     * @throws IOException
     */
    public DoctorAppointmentUI(Doctor doctor, String appointmentfilepath)throws IOException{
        this.writer=new TextFileWriter();
        this.doctorAppmtController=new DoctorAppointmentController(appointmentfilepath, doctor);
    }

    /**
     * Prints an appointment while omitting doctorID
     * @param appointment
     */
    public void printAppointment(Appointment appointment){
        System.out.printf("%-14s | %-14s | %s | %s | %s%n",
                            appointment.getAppointmentID(),
                            TextFileReader.findUserName(appointment.getPatientID(), "Patient"),
                            appointment.getDate(),
                            appointment.getTime(),
                            appointment.getStatus().toString());
    }

    /**
     * Prints all appointments from a list of appointments while omitting doctorID
     * @param appointmentList
     */
    @Override
    public void printAllAppointments(List<Appointment> appointmentList){
        System.out.println("Appointment ID |     Patient    | Date     | Time | Status");
        for (Appointment appointment : appointmentList) {
            printAppointment(appointment);
            
        }
    }
    /**
     * Abstract class for printing all appointments from a list of appointments with index
     * @param appointmentList
     */
    public void printAllAppointmentsWithIndex(List<Appointment> appointmentList) {
        int i=1;
        for (Appointment appmt: appointmentList){
            System.out.println(i + ". "+appmt.getDate()+ " "+appmt.getTime()+"\n");
            i++;
        }
    }


    /**
     * View Doctor's Personal Schedule
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

    /**
     * Allows index based inputting to retrieve desired appointment
     * @param appmt
     * @return
     */
    public int Selection(List<Appointment> appmt){
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        int index=-2;

        while (true){
            try {
                index=sc.nextInt()-1;
            } catch (java.util.InputMismatchException e) {
                
            }
            
            sc.nextLine();
            if (index==-1){
                return -1;
            }else if(index<-1 || index>appmt.size()){
                System.out.println("Invalid Slot Chosen. ");
            }else{
                System.out.println("Slot selected.");
                return index;
            }
            
        }

    }

    /**
     * Set, update and remove any available slots under the doctor
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
     *Manage by Accepting or Declining Pending appointments
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
     * prints array of string, used for Medicine names
     * @param arr
     */
    public void PrintMedicine(List<String> arr){
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }


    /**
     * checks input to Medicine stock
     * @return
     */
    public String checkMedicineStock(){
        @SuppressWarnings("resource")
        Scanner sc=new Scanner(System.in);

        List<Medicine> medicineList=Medicine.getAllMedicines();
        List<String> medicineNames=new ArrayList<>();
        for (Medicine medicine : medicineList) {
            medicineNames.add(medicine.getMedicineName());
        }
        while (true) {
            System.out.println("Enter medicine prescribed (or 'b' to return):");
            String med = sc.nextLine();
        
            if (med.equals("b")) {
                return "b";
            }
            boolean found = false;
            for (String m : medicineNames) {
                if (m.equalsIgnoreCase(med)) {
                    found = true;
                    break; // Exit the loop if a match is found
                }
            }
            if (!found) {
                System.out.println("No stock of this medicine. Try again.");
                System.out.println("Medicine Available: ");
                PrintMedicine(medicineNames);

            } else {
                return med;
            }
        }

    }

    /**
     * Select any confirmed appointments and resolve it into a appointment outcome using it
     */
    public void RecordAppointmentOutcome(){
        while(true){
            List<Appointment> appointments=doctorAppmtController.GetStatusAppointments(Status.CONFIRMED);
            if (!appointments.isEmpty()){
                printAllAppointmentsWithIndex(appointments);
                System.out.println("Select which Confirmed Appointment you want to conclude \nEnter 0 to go back");
                int index=Selection(appointments);
                if (index==-1) {
                    return;
                }
                @SuppressWarnings("resource")
                Scanner sc=new Scanner(System.in);
                
                System.out.println("Enter service type:");
                String service=sc.nextLine();
                
                //Remove Appointment from appointment list and add appointment outcome
                String med=checkMedicineStock();
                if (med=="b"){
                    return;
                }
                Appointment appmt= appointments.get(index);
                int appmtID = appmt.getAppointmentID();
                doctorAppmtController.RemoveAppointment(appmtID);
                
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
     *
     *
     */
    public void ViewUpcomingAppointments(){
        List<Appointment> list=doctorAppmtController.GetDoctorUpcoming();
        if (list!=null){
            printAllAppointments(list);
        }
    }//6
    
}
