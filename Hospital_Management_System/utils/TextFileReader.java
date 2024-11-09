package utils;
import boundary.MedicalRecord;
import entity.Appointment;
import entity.AppointmentOutcome;
import entity.MedicationInventory;
import entity.Medicine;
import entity.Patient;
import entity.ReplenishmentRequest;
import entity.Staff;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            String[] details = line.split("\\|");
            String appointmentID = details[0];
            String patientID = details[1];
            String staffID = details[2];
            String status = details[3];
            String date = details[4];
            String time = details[5];
            appointments.add(new Appointment(Integer.parseInt(appointmentID), patientID, staffID, status, date, time));
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

    public static List<MedicalRecord> loadMedicalRecord(String filePath, String PatientfilePath)throws IOException{
        List<MedicalRecord> MRList=new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            String id = details[0];
            String diagnosis_FILEPATH = details[1];
            String treatment_plans_FILEPATH = details[2];
            MedicalRecord mr=new MedicalRecord(loadSelectivePatient(PatientfilePath, id), diagnosis_FILEPATH, treatment_plans_FILEPATH);
            MRList.add(mr);
        }
        reader.close();
        
        return MRList;
    }

    public static List<String> loadDiagnosis(String diagnosisFilePath) throws IOException{
        List<String> diagnoses=new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(diagnosisFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            diagnoses.add(line);
        }
        reader.close();
        
        return diagnoses;
    }

    public static String loadTreatmentPlan(String treatmentFilePath) throws IOException{
        String treatment= "";
        BufferedReader reader = new BufferedReader(new FileReader(treatmentFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            treatment += " " + line;
        }
        reader.close();
        
        return treatment;
    }

    public static Patient loadSelectivePatient(String filePath, String patientID) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        Patient p=null;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            if (patientID.equals(details[0])){
                String id = details[0];
                String name = details[1];
                String DOB = details[2];
                String gender = details[3];
                String bloodtype = details[4];
                String contactinfo = details[5];
                String password = details[6];

                p=new Patient(id, name, password, gender, DOB, bloodtype, contactinfo);
            }   
        }
        reader.close();
        return p;
    }

    public static List<String> loadOverseeingPatients(String doctorId, String filePath)throws IOException{
        List<String> PatientIDs=new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            if (details[0].equals(doctorId)){
                for  (int i = 1; i < details.length; i++) {
                    PatientIDs.add(details[i]);
                }
            }
        }
        reader.close();
        return PatientIDs;
    }
    public static List<Patient> loadSelectivePatients(String filePath, List<String> patientIDs) throws IOException {
        List<Patient> patients = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            if (patientIDs.contains(details[0])){
                String id = details[0];
                String name = details[1];
                String DOB = details[2];
                String gender = details[3];
                String bloodtype = details[4];
                String contactinfo = details[5];
                String password = details[6];

                patients.add(new Patient(id, name, password, gender, DOB, bloodtype, contactinfo));
            }   
        }
        reader.close();
        return patients;
    }
    public static void NormalRead(String filePath)throws IOException {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static List<Appointment> loadDoctorAppointments(String filePath, String doctorid) throws IOException {
        List<Appointment> appointments = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\\|"); // Special Character | requires \\
            if (details[2].equals(doctorid)){
                String appointmentID = details[0];
                String patientID = details[1];
                String staffID = details[2];
                String status = details[3];
                String date = details[4];
                String time = details[5];
                appointments.add(new Appointment(Integer.parseInt(appointmentID), patientID, staffID, status, date, time));
            }
        }
        reader.close();
        return appointments;
    }

    public static List<String> loadResetPasswordRequests(){
        List<String> resetPasswordRequests = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./TextFiles/ResetPassword.txt")))
         {
            String line;
            while ((line = reader.readLine()) != null) {
                resetPasswordRequests.add(line);
            }
            reader.close();
            return resetPasswordRequests;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resetPasswordRequests;
    }
    public static String findUserName(String id, String type) {
        String filename;
        if (type.equals("Doctor")){
            filename="./TextFiles/Staff_List.txt";
        }else{
            filename="./TextFiles/Patient_List.txt";
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split("\\|"); // Special Character | requires \\
                if (id.equals(details[0])){
                    reader.close();
                    return details[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

}