import java.util.ArrayList;

public class AppointmentController {
    private ArrayList<Appointment> appointments;

    public AppointmentController() {
        this.appointments = new ArrayList<>();
    }

    // View available appointment slots for a specific doctor
    public void viewAvailableSlots(Doctor doctor) {
        System.out.println("Available slots for Dr. " + doctor.getName() + ":");
        // Placeholder logic to display available slots (This can be extended based on implementation)
    }

    // Schedule a new appointment
    public void scheduleAppointment(Patient patient, Doctor doctor, String date, String time) {
        Appointment newAppointment = new Appointment(patient, doctor, date, time);
        appointments.add(newAppointment);
        System.out.println("Appointment scheduled successfully with Dr. " + doctor.getName());
    }

    // Reschedule an existing appointment
    public void rescheduleAppointment(Appointment appointment, String newDate, String newTime) {
        System.out.println("Rescheduling appointment...");
        appointment.setAppointmentDate(newDate);
        appointment.setAppointmentTime(newTime);
        appointment.updateStatus("rescheduled");
        System.out.println("Appointment rescheduled to " + newDate + " at " + newTime);
    }

    // Cancel an existing appointment
    public void cancelAppointment(Appointment appointment) {
        appointment.updateStatus("canceled");
        System.out.println("Appointment canceled.");
    }

    // Find an appointment by doctor, date, and time
    public Appointment findAppointment(Patient patient, Doctor doctor, String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().equals(patient) &&
                appointment.getDoctor().equals(doctor) &&
                appointment.getAppointmentDate().equals(date) &&
                appointment.getAppointmentTime().equals(time)) {
                return appointment;
            }
        }
        return null; // No appointment found
    }

    // View all scheduled appointments for a specific patient
    public void viewScheduledAppointments(Patient patient) {
        System.out.println("Scheduled Appointments for Patient " + patient.getName() + ":");
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().equals(patient) && appointment.getStatus().equals("confirmed")) {
                appointment.viewAppointmentDetails();
            }
        }
    }

    // View past appointments for a specific patient
    public void viewPastAppointments(Patient patient) {
        System.out.println("Past Appointments for Patient " + patient.getName() + ":");
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().equals(patient) && appointment.getStatus().equals("completed")) {
                appointment.viewAppointmentDetails();
            }
        }
    }

    // View details of a specific appointment
    public void viewAppointmentDetails(Appointment appointment) {
        System.out.println("Displaying appointment details:");
        appointment.viewAppointmentDetails();
    }

    // Get the list of appointments (getter for testing purposes)
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}
