public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private Administrator admin;
    private Pharmacist pharmacist;

    public Appointment(Patient patient){
        this.patient = patient;
    }
    public Appointment(Doctor doctor){
        this.doctor = doctor;
    }
    public Appointment(Administrator admin){
        this.admin = admin;
    }
    public Appointment(Pharmacist pharmacist){
        this.pharmacist = pharmacist;
    }
    public void viewAppointmentSlots(Doctor doctor){
        System.out.println("Viewing Appointment Slots for ze Doctor");
    }
    public void scheduleAppointment(Patient patient, Doctor doctor){
        System.out.println("Scheduling Appointment for ze Patient and Doctor");
    }
    public void rescheduleAppointment(Patient patient, Doctor doctor){
        System.out.println("Rescheduling Appointment for ze Patient and Doctor");
    }
    public void cancelAppointment(Patient patient, Doctor doctor){
        System.out.println("Cancelling Appointment for ze Patient and Doctor");
    }
    public void viewScheduledAppointments(Patient patient){
        System.out.println("Viewing Scheduled Appointments for ze Patient");
    }
    public void viewPastAppointmentOutcomeRecords(Patient patient){
        System.out.println("Viewing Past Appointment Outcome Records for ze Patient");
    }

    public void viewPersonalSchedule(Doctor doctor){
        System.out.println("Viewing Personal Schedule for ze Doctor");
    }
    public void setAvailabilityForAppointments(Doctor doctor){
        System.out.println("Setting Availability for Appointments for ze Doctor");
    }
    public void acceptOrDeclineAppointment(Doctor doctor){
        System.out.println("Accepting or Declining Appointment for ze Doctor");
    }
    public void viewUpcomingAppointments(Doctor doctor){
        System.out.println("Viewing Upcoming Appointments for ze Doctor");
    }
    public void recordAppointmentOutcome(Doctor doctor){
        System.out.println("Recording Appointment Outcome for ze Doctor");
    }
    
    public void viewAppointmentDetails(Doctor doctor,Patient patient){
        System.out.println("Viewing Appointment Details for ze Administrator");
    }

}
