import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class AppointmentController {
    private List<Appointment> appointments;
    private List<Doctor> doctors;

    public AppointmentManagement() {
        this.appointments = new ArrayList<>();
        this.doctors = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public boolean scheduleAppointment(String appointmentId, String patientId, 
                                       String doctorId, LocalDate date, LocalTime time) {
        Doctor doctor = findDoctorById(doctorId);

        if (doctor != null && doctor.isSlotAvailable(date, time)) {
            Appointment appointment = new Appointment(appointmentId, patientId, doctorId, date, time);
            appointments.add(appointment);
            doctor.removeSlot(date, time);  // Mark the slot as taken
            System.out.println("Appointment scheduled successfully.");
            return true;
        } else {
            System.out.println("Selected slot is not available.");
            return false;
        }
    }

    public boolean rescheduleAppointment(String appointmentId, LocalDate newDate, LocalTime newTime) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                Doctor doctor = findDoctorById(appointment.getDoctorId());

                if (doctor != null && doctor.isSlotAvailable(newDate, newTime)) {
                    doctor.addAvailableSlot(appointment.date, appointment.time);  // Free old slot
                    appointment.date = newDate;
                    appointment.time = newTime;
                    doctor.removeSlot(newDate, newTime);  // Mark new slot as taken
                    System.out.println("Appointment rescheduled successfully.");
                    return true;
                } else {
                    System.out.println("New slot is not available.");
                    return false;
                }
            }
        }
        System.out.println("Appointment not found.");
        return false;
    }

    public void cancelAppointment(String appointmentId) {
        Iterator<Appointment> iterator = appointments.iterator();

        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getAppointmentId().equals(appointmentId)) {
                Doctor doctor = findDoctorById(appointment.getDoctorId());
                doctor.addAvailableSlot(appointment.date, appointment.time);  // Free slot
                iterator.remove();
                System.out.println("Appointment canceled successfully.");
                return;
            }
        }
        System.out.println("Appointment not found.");
    }

    private Doctor findDoctorById(String doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId().equals(doctorId)) return doctor;
        }
        return null;
    }
}

