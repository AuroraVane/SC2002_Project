import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader {
    public static List<Patient> loadPatients(String filePath) throws IOException {
        List<Patient> patients = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String id = details[0];
            String name = details[1];
            String DOB = details[2];
            String gender = details[3];
            String bloodtype = details[4];
            String contactinfo = details[5];
            String password = details[6];

            patients.add(new Patient(id, name, password, gender, DOB, bloodtype, contactinfo));
        }

        reader.close();
        return patients;
    }

    public static List<Staff> loadStaff(String filePath) throws IOException {
        List<Staff> staff = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String id = details[0];
            String name = details[1];
            String role = details[2];
            String gender = details[3];
            String age = details[4];
            String password = details[5];

            staff.add(new Staff(id, name, password, gender, role, age));
        }

        reader.close();
        return staff;
    }

    public static List<Appointment> loadAppointments(String filePath) throws IOException {
        List<Appointment> appointments = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String patientID = details[0];
            String staffID = details[1];
            String status = details[2];
            String date = details[3];
            String time = details[4];

            appointments.add(new Appointment(patientID, staffID, status, date, time));
        }

        reader.close();
        return appointments;
    }
    public static List<MedicationInventory> loadMedicationInventory(String filePath) throws IOException {
        List<MedicationInventory> medicationInventory = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String medicationName = details[0];
            String quantity = details[1];
            String threshold = details[2];

            medicationInventory.add(new MedicationInventory(medicationName, quantity, threshold));
        }

        reader.close();
        return medicationInventory;
    }
    public static List<ReplenishmentRequest> loadReplenishmentRequest(String filePath) throws IOException {
        List<ReplenishmentRequest> replenishmentRequests = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String id = details[0];
            String medicationName = details[1];
            String status = details[2];
            String quantity = details[3];

            replenishmentRequests.add(new ReplenishmentRequest(id,medicationName, status, quantity));
        }

        reader.close();
        return replenishmentRequests;
    }

    public static List<Medicine> loadMedicineList(String filePath) throws IOException {
        List<Medicine> medicineList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String name = details[0];
            int quantity = Integer.parseInt(details[1]);
            int lowQAlert = Integer.parseInt(details[2]);

            medicineList.add(new Medicine(name, quantity, lowQAlert));
        }

        reader.close();
        return medicineList;
    }

    public static List<AppointmentOutcome> loadAppointmentOutcomes(String filePath) throws IOException {
        List<AppointmentOutcome> aoList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            int id = Integer.parseInt(details[0]);
            String date = details[1];
            String service = details[2];
            String medicine = details[3];
            boolean status = details[4].equals("false") ? false : true;
            String notes = details[5];

            aoList.add(new AppointmentOutcome(id, date, service, medicine, status, notes));
        }

        reader.close();
        return aoList;
    }
}
