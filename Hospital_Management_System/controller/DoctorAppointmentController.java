package controller;
import entity.Appointment;
import entity.Doctor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.TextFileReader;
import utils.TextFileWriter;

public class DoctorAppointmentController{
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
        TextFileWriter.updateAppointment(appmt);
    }
    public void DeclinePendingAppointment(Appointment appmt){
        appmt.setStatus("CANCELLED");
        TextFileWriter.updateAppointment(appmt);
    }
    public List<Appointment> GetDoctorUpcoming(){
        List<Appointment> appointments=GetStatusAppointments("CONFIRMED");
        if (!appointments.isEmpty()){
            Collections.sort(appointments, Comparator.comparing(Appointment::getDate));
        }else{
            System.out.println("You have no upcoming appointments.");
            return null;
        }
        int s=appointments.size();
        int i=0;
        String firstdate=appointments.get(0).getDate();
        for (int j=0;j<s;j++){
            if (!appointments.get(j).getDate().equals(firstdate)){
                appointments.remove(i);
            }else i++;
        }
        return appointments;
    }
    public void ResolveAppointment(Appointment appmt){
        appmt.setStatus("COMPLETED");
        TextFileWriter.updateAppointment(appmt);
    }
    
    
}
