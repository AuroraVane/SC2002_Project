package controller;
import entity.Appointment;
import entity.Appointment.Status;
import entity.Doctor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.TextFileReader;
import utils.TextFileWriter;

/**
 * Controller class for DoctorAppointmentUI 
 */
public class DoctorAppointmentController{
    private List<Appointment> DoctorAppointmentList;
    private TextFileWriter writer;


    /**
     * Doctor Appointment Controller constructor
     * @param fileName
     * @param doctor
     * @throws IOException
     */
    public DoctorAppointmentController(String fileName, Doctor doctor) throws IOException {
        this.DoctorAppointmentList = TextFileReader.loadDoctorAppointments(fileName, doctor.getId());
        writer= new TextFileWriter();
    }

    /**
     * Remove Appointment from controller appointment list
     * @param appmtID
     */
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

    /**
     * get the list of appointments with the given status
     * @param status
     * @return
     */
    public List<Appointment> GetStatusAppointments(Status status){
        List<Appointment> appointments = new ArrayList<>();
        for (Appointment appmt:this.DoctorAppointmentList){
            if (appmt.getStatus().equals(status)){
                appointments.add(appmt);
            }
        }
        return appointments;
    }

    /**
     * set new available slot
     * @param doctorID
     * @param date
     * @param time
     */
    public Appointment SetAvailableSlot(String doctorID, String date, String time){
        Appointment appmt=new Appointment(doctorID, Status.EMPTY, date, time);
        writer.addAppointment(appmt);
        DoctorAppointmentList.add(appmt);
        return appmt;
    }

    /**
     * Accept a pending appointment
     * @param appmt
     */
    public void AcceptPendingAppointment(Appointment appmt){
        appmt.setStatus(Status.CONFIRMED);
        TextFileWriter.updateAppointment(appmt);
    }

    /**
     * Decline a pending appointment
     * @param appmt
     */
    public void DeclinePendingAppointment(Appointment appmt){
        appmt.setStatus(Status.CANCELLED);
        TextFileWriter.updateAppointment(appmt);
    }

    /**
     * Get the doctor's upcoming confirmed appointments
     * @return
     */
    public List<Appointment> GetDoctorUpcoming(){
        List<Appointment> appointments=GetStatusAppointments(Status.CONFIRMED);
        if (!appointments.isEmpty()){
            Collections.sort(appointments, Comparator.comparing(Appointment::getDate));
        }else{
            System.out.println("You have no upcoming appointments.");
            return null;
        }
        return appointments;
    }

    /**
     * resolve appointment as archived by changing status to completed
     * @param appmt
     */
    public void ResolveAppointment(Appointment appmt){
        appmt.setStatus(Status.COMPLETED);
        TextFileWriter.updateAppointment(appmt);
    }
    
    
}
