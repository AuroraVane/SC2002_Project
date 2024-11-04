import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DoctorAppointmentController extends AppointmentUI{
    private List<Appointment> DoctorAppointmentList;
    private TextFileWriter writer;
    
    
    public DoctorAppointmentController(String fileName, Doctor doctor) throws IOException {
        this.DoctorAppointmentList = TextFileReader.loadDoctorAppointments(fileName, doctor.getId());
        writer= new TextFileWriter();
    }
    public void RemoveAppointment(int appmtID){
        int i=0;
        for (Appointment appmt:this.DoctorAppointmentList){
            if (appmt.getAppointmentID()==appmtID){
                this.DoctorAppointmentList.remove(i);
                break;
            }
            i++;
        }
    }
    
    
    public List<Appointment> GetStatusAppointments(String status){
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appmt:this.DoctorAppointmentList){
            if (appmt.getStatus().equals(status)){
                appointments.add(appmt);
            }
        }
        return appointments;
    }




    public void SetAvailableSlot(String doctorID, String date, String time){
        Appointment appmt=new Appointment(doctorID, "EMPTY", date, time);
        writer.addAppointment(appmt);
        DoctorAppointmentList.add(appmt);
    }

    public void AcceptPendingAppointment(Appointment appmt){
        appmt.setStatus("CONFIRMED");
        writer.updateAppointment(appmt);
    }
    public void DeclinePendingAppointment(Appointment appmt){
        appmt.setStatus("CANCELLED");
        writer.updateAppointment(appmt);
    }
    public void ViewDoctorUpcoming(){
        List<Appointment> appointments=GetStatusAppointments("CONFIRMED");
        if (!appointments.isEmpty()){
            Collections.sort(appointments, Comparator.comparing(Appointment::getDate));
        }else{
            System.out.println("You have no upcoming appointments.");
            return;
        }
        String firstdate=appointments.get(0).getDate();
        for (Appointment appmt: appointments){
            if (appmt.getDate().equals(firstdate)){
                System.out.println(appmt.getDate()+ " "+appmt.getTime());
            }
        }
    }
    public void ResolveAppointment(Appointment appmt){
        appmt.setStatus("COMPLETED");
        writer.updateAppointment(appmt);
    }
    
    
}
