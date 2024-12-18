package utils;

import entity.Appointment;
import entity.AppointmentOutcome;
import entity.Patient;
import entity.Staff;
import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *Class responsible for file writing related methods
 */
public class TextFileWriter {

    private static final String FILE_PATH = "./TextFiles/Staff_List.txt"; // Path to the staff list file
    private static final String APPOINTMENT_FILE_PATH = "./TextFiles/Appointment_List.txt";
    private static final String OUTCOME_FILE_PATH = "./TextFiles/AppointmentOutcome_List.txt";
    private static final String PATIENT_FILE_PATH = "./TextFiles/Patient_List.txt";
    private static final String OVERSEEING_PATIENTS_FILE_PATH = "./TextFiles/OverseeingPatients.txt";
    private static final String RESET_PASSWORD_FILE_PATH = "./TextFiles/ResetPassword.txt";
    private static final String BILLING_FILE_PATH = "./TextFiles/Billing.txt";

    /**
     * @param password
     * @return
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error hashing password: " + e.getMessage());
            return null;
        }
    }

    /**
     * @param id
     * @param name
     * @param role
     * @param gender
     * @param age
     */
    // Method to add a new staff member
    public void addStaff(String id, String name, String role, String gender, int age) {
        String newStaff = String.format("%s|%s|%s|%s|%d|%s", id, name, role, gender, age, hashPassword("password"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (Files.size(Paths.get(FILE_PATH)) > 0) {
                writer.newLine(); // Add a newline only if the file is not empty
            }
            writer.write(newStaff);
            System.out.println("Staff member added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param id
     * @param name
     * @param dob
     * @param gender
     * @param bloodtype
     * @param email
     */
    public static void addPatient(String id, String name, String dob, String gender, String bloodtype, String email) {
        String newPatient = String.format("%s|%s|%s|%s|%s|%s|%s", id, name, dob, gender, bloodtype, email,
                hashPassword("password"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE_PATH, true))) {
            if (Files.size(Paths.get(PATIENT_FILE_PATH)) > 0) {
                writer.newLine(); // Add a newline only if the file is not empty
            }
            writer.write(newPatient);
            System.out.println("Staff member added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param id
     */
    // Method to delete a staff member by ID
    public void deleteStaff(String id) {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("tempStaffList.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean firstLine = true; // To manage writing newlines only after the first line is written

            // Loop through the file and write all lines except the one with the matching ID
            while ((currentLine = reader.readLine()) != null) {
                // Skip any empty lines
                if (currentLine.trim().isEmpty()) {
                    continue;
                }

                // Extract the ID from the current line
                String[] staffDetails = currentLine.split("\\|");

                // Skip the line that matches the staff ID to be deleted
                if (staffDetails[0].equals(id)) {
                    continue;
                }

                // For the first valid line, avoid writing a newline beforehand
                if (!firstLine) {
                    writer.newLine(); // Add a newline only after the first valid line
                } else {
                    firstLine = false; // Mark that the first line has been written
                }

                // Write the current valid staff details
                writer.write(currentLine);
            }

            System.out.println("Staff member deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }

    /**
     * @param id
     * @param name
     * @param role
     * @param gender
     * @param age
     * @param password
     */
    // Method to update an existing staff member's details by ID
    public void updateStaff(String id, String name, String role, String gender, int age, String password) {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("tempStaffList.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            Boolean firstLine = true;
            // Loop through the file and update the line with the matching ID
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                String[] staffDetails = currentLine.split("\\|");
                if (staffDetails[0].equals(id)) {
                    // Write the updated staff details
                    currentLine = String.format("%s|%s|%s|%s|%d|%s", id, name, role, gender, age, password);
                }
                if (!firstLine) {
                    writer.newLine();
                } else {
                    firstLine = false;
                }
                // Write the original staff details
                writer.write(currentLine);
            }
            System.out.println("Staff member updated successfully.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }

    /**
     * @param name
     * @param stock
     */
    public void updateMedicationInventory(String name, String stock) {
        File inputFile = new File("./TextFiles/Medicine_List.txt");
        File tempFile = new File("tempMedicineList.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean firstLine = true;

            // Loop through the file and update the line with the matching ID
            while ((currentLine = reader.readLine()) != null) {

                if (currentLine.trim().isEmpty()) {
                    continue;
                }

                String[] staffDetails = currentLine.split("\\|");
                if (staffDetails[0].equals(name)) {
                    // Write the updated staff details
                    currentLine = String.format("%s|%s|%s", name, stock, staffDetails[2]);
                }
                if (!firstLine) {
                    writer.newLine();
                } else {
                    firstLine = false;
                }

                writer.write(currentLine);
            }
            System.out.println("Medicine updated successfully.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }

    /**
     * @param id
     * @param name
     * @param status
     * @param value
     */
    public void addReplenishmentRequest(String id, String name, String status, String value) {
        String newReplenishment = String.format("%s|%s|%s|%s", id, name, status, value);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./TextFiles/Replenishment_List.txt", true))) {
            if (Files.size(Paths.get("./TextFiles/Replenishment_List.txt")) > 0) {
                writer.newLine(); // Add a newline only if the file is not empty
            }
            writer.write(newReplenishment);
            System.out.println("Replenishment Request added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param name
     * @return
     */
    public String[] updateReplenishmentRequest(String name) {
        File inputFile = new File("./TextFiles/Replenishment_List.txt");
        File tempFile = new File("tempReplenishmentList.txt");
        String medicinename = " ";
        String quantity = " ";
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean firstLine = true;
            // Loop through the file and update the line with the matching ID
            while ((currentLine = reader.readLine()) != null) {

                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                String[] staffDetails = currentLine.split("\\|");
                if (staffDetails[0].equals(name)) {
                    // Write the updated staff details
                    currentLine = String.format("%s|%s|%s|%s", name, staffDetails[1], "Approved", staffDetails[3]);
                    medicinename = staffDetails[1];
                    quantity = staffDetails[3];
                }
                if (!firstLine) {
                    writer.newLine();
                } else {
                    firstLine = false;
                }

                writer.write(currentLine);
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
        return new String[] { medicinename, quantity };
    }

    /**
     * @param appmt
     */
    public static void updateAppointment(Appointment appmt) {
        File inputFile = new File(APPOINTMENT_FILE_PATH);
        File tempFile = new File("tempAppointment.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            Boolean firstLine = true;
            // Loop through the file and update the line with the matching ID
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                String[] appmtDetails = currentLine.split("\\|");
                if (appmtDetails[0].equals(appmt.getAppointmentID() + "")) {
                    // Write the updated staff details
                    currentLine = String.format("%d|%s|%s|%s|%s|%s", appmt.getAppointmentID(), appmt.getPatientID(),
                            appmt.getStaffID(), appmt.getStatus(), appmt.getDate(), appmt.getTime());
                }
                if (!firstLine) {
                    writer.newLine();
                } else {
                    firstLine = false;
                }
                // Write the original staff details
                writer.write(currentLine);
            }
            System.out.println("Appointment updated successfully.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }

    /**
     * @param appmt
     */
    public void addAppointment(Appointment appmt) {
        String newAppointment = String.format("%d|%s|%s|%s|%s|%s", appmt.getAppointmentID(), appmt.getPatientID(),
                appmt.getStaffID(), appmt.getStatus(), appmt.getDate(), appmt.getTime());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE_PATH, true))) {
            if (Files.size(Paths.get(FILE_PATH)) > 0) {
                writer.newLine(); // Add a newline only if the file is not empty
            }
            writer.write(newAppointment);
            System.out.println("Appointment added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param appointmentId
     * @param date
     * @param service
     * @param medicine
     * @param status
     * @param consulationNotes
     */
    public void addAppointmentOutcome(int appointmentId, String date, String service, String medicine, boolean status,
            String consulationNotes) {
        String newAppointment = String.format("%d|%s|%s|%s|%b|%s", appointmentId, date, service, medicine, status,
                consulationNotes);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTCOME_FILE_PATH, true))) {
            if (Files.size(Paths.get(FILE_PATH)) > 0) {
                writer.newLine(); // Add a newline only if the file is not empty
            }
            writer.write(newAppointment);
            System.out.println("Appointment Outcome added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param FILE_PATH
     */
    public void WriteFile(String FILE_PATH) {
        try (BufferedWriter Writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            System.out.println("Enter 0 to exit");
            @SuppressWarnings("resource")
            Scanner w = new Scanner(System.in);
            while (true) {
                String x = w.nextLine();
                if (x.equals("0")) {
                    break;
                }
                Writer.write(x);
                Writer.newLine();
            }
            Writer.close();
            System.out.println("Written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param patientId
     * @param newEmail
     */
    public static void updatePatientEmail(String patientId, String newEmail) {
        List<String> fileContent = new ArrayList<>();

        try {
            // Read all lines into a list
            fileContent = Files.readAllLines(Paths.get(PATIENT_FILE_PATH));

            // Loop through each line to find the patient
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i);
                String[] parts = line.split("\\|");

                // Check if the ID matches
                if (parts[0].equals(patientId)) {
                    // Update the email field
                    parts[5] = newEmail;
                    // Join the parts back into a line
                    fileContent.set(i, String.join("|", parts));
                    break;
                }
            }

            // Write updated content back to the file
            Files.write(Paths.get(PATIENT_FILE_PATH), fileContent);

        } catch (IOException e) {
            System.out.println("An error occurred while updating the email: " + e.getMessage());
        }
    }

    /**
     * @param doctorID
     * @param newpatientID
     */
    public static void updateOverseeingPatient(String doctorID, String newpatientID) {
        File inputFile = new File(OVERSEEING_PATIENTS_FILE_PATH);
        File tempFile = new File("tempfile.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            Boolean firstLine = true;

            // Loop through the file and update the line with the matching ID
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                String[] Details = currentLine.split("\\|");

                if (Details[0].equals(doctorID)) {
                    // Write the updated staff details

                    currentLine += "|" + newpatientID;
                }
                if (!firstLine) {
                    writer.newLine();
                } else {
                    firstLine = false;
                }
                // Write the original staff details
                writer.write(currentLine);
            }
            System.out.println("updated successfully.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }

    /**
     * @param patient
     * @param newPassword
     * @return
     */
    public static boolean changePassword(Patient patient, String newPassword) {
        List<String> fileContent = new ArrayList<>();

        try {
            // Read all lines into a list
            fileContent = Files.readAllLines(Paths.get(PATIENT_FILE_PATH));

            // Loop through each line to find the patient
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i);
                String[] parts = line.split("\\|");

                // Check if the ID matches
                if (parts[0].equals(patient.getId())) {
                    // Update the email field
                    parts[6] = hashPassword(newPassword);
                    // Join the parts back into a line
                    fileContent.set(i, String.join("|", parts));
                    break;
                }
            }

            // Write updated content back to the file
            Files.write(Paths.get(PATIENT_FILE_PATH), fileContent);

        } catch (IOException e) {
            System.out.println("An error occurred while updating the password: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @param staff
     * @param newPassword
     * @return
     */
    public static boolean changePassword(Staff staff, String newPassword) {
        List<String> fileContent = new ArrayList<>();

        try {
            // Read all lines into a list
            fileContent = Files.readAllLines(Paths.get(FILE_PATH));

            // Loop through each line to find the patient
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i);
                String[] parts = line.split("\\|");

                // Check if the ID matches
                if (parts[0].equals(staff.getId())) {
                    // Update the email field
                    parts[5] = hashPassword(newPassword);
                    // Join the parts back into a line
                    fileContent.set(i, String.join("|", parts));
                    break;
                }
            }

            // Write updated content back to the file
            Files.write(Paths.get(FILE_PATH), fileContent);

        } catch (IOException e) {
            System.out.println("An error occurred while updating the password: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @param id
     */
    public static void sendResetPassword(String id) {
        String newResetRequest = String.format("%s", id);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESET_PASSWORD_FILE_PATH, true))) {
            if (Files.size(Paths.get(RESET_PASSWORD_FILE_PATH)) > 0) {
                writer.newLine(); // Add a newline only if the file is not empty
            }
            writer.write(newResetRequest);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param id
     * @return
     */
    public static boolean completeResetPassword(String id) {
        List<String> fileContent = new ArrayList<>();
        try {
            // Read all lines into a list
            if (id.length() == 4) {
                fileContent = Files.readAllLines(Paths.get(FILE_PATH));
                for (int i = 0; i < fileContent.size(); i++) {
                    String line = fileContent.get(i);
                    String[] parts = line.split("\\|");

                    // Check if the ID matches
                    if (parts[0].equals(id)) {
                        // Update the email field
                        parts[5] = hashPassword("password");
                        // Join the parts back into a line
                        fileContent.set(i, String.join("|", parts));
                        break;
                    }
                }
                Files.write(Paths.get(FILE_PATH), fileContent);
            } else {
                fileContent = Files.readAllLines(Paths.get(PATIENT_FILE_PATH));
                for (int i = 0; i < fileContent.size(); i++) {
                    String line = fileContent.get(i);
                    String[] parts = line.split("\\|");

                    // Check if the ID matches
                    if (parts[0].equals(id)) {
                        // Update the email field
                        parts[6] = hashPassword("password");
                        // Join the parts back into a line
                        fileContent.set(i, String.join("|", parts));
                        break;
                    }
                }
                Files.write(Paths.get(PATIENT_FILE_PATH), fileContent);
            }
            File inputFile = new File(RESET_PASSWORD_FILE_PATH);
            File tempFile = new File("tempResetList.txt");

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String currentLine;
                boolean firstLine = true;
                while ((currentLine = reader.readLine()) != null) {
                    // Skip any empty lines
                    if (currentLine.trim().isEmpty()) {
                        continue;
                    }
                    String[] staffDetails = currentLine.split("\\|");

                    if (staffDetails[0].equals(id)) {
                        continue;
                    }
                    if (!firstLine) {
                        writer.newLine();
                    } else {
                        firstLine = false;
                    }
                    writer.write(currentLine);
                }
            } catch (IOException e) {
                System.out.println("Error processing file: " + e.getMessage());
            }
            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temp file.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred while updating the password: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * @param billid xx
     * @param appointmentID
     * @param PatientId
     * @param price
     * @param status
     */
    public static void addBill(String billid, String appointmentID, String PatientId, String price, String status) {
        String newBill = String.format("%s|%s|%s|%s|%s", billid, appointmentID, PatientId, price, status);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BILLING_FILE_PATH, true))) {
            if (Files.size(Paths.get(BILLING_FILE_PATH)) > 0) {
                writer.newLine(); // Add a newline only if the file is not empty
            }
            writer.write(newBill);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * @param appmtoutcome
     */
    public static void updateAppointmentOutcome(AppointmentOutcome appmtoutcome) {
        List<String> fileContent = new ArrayList<>();

        try {
            // Read all lines into a list
            fileContent = Files.readAllLines(Paths.get(OUTCOME_FILE_PATH));

            // Loop through each line to find the patient
            for (int i = 0; i < fileContent.size(); i++) {
                String line = fileContent.get(i);
                String[] parts = line.split("\\|");

                // Check if the ID matches
                if (parts[0].equals(appmtoutcome.getAppointmentId() + "")) {
                    // Update the email field
                    parts[4] = "true";
                    // Join the parts back into a line
                    fileContent.set(i, String.join("|", parts));
                    break;
                }
            }

            // Write updated content back to the file
            Files.write(Paths.get(OUTCOME_FILE_PATH), fileContent);

        } catch (IOException e) {
            System.out.println("An error occurred while updating appointment outcome: " + e.getMessage());
        }
    }

    /**
     * @param billId
     */
    public static void updateBillStatus(String billId) {
        File inputFile = new File(BILLING_FILE_PATH);
        File tempFile = new File("tempBillingList.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean firstLine = true;

            // Loop through the file and update the line with the matching ID
            while ((currentLine = reader.readLine()) != null) {

                if (currentLine.trim().isEmpty()) {
                    continue;
                }

                String[] billDetails = currentLine.split("\\|");
                if (billDetails[0].equals(billId)) {
                    // Write the updated staff details
                    currentLine = String.format("%s|%s|%s|%s|%s", billDetails[0], billDetails[1], billDetails[2],
                            billDetails[3], "Paid");
                }
                if (!firstLine) {
                    writer.newLine();
                } else {
                    firstLine = false;
                }

                writer.write(currentLine);
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }

    public static void deleteAppointment(int id) {
        String strID=id+"";
        File inputFile = new File(APPOINTMENT_FILE_PATH);
        File tempFile = new File("tempAppointmentList.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;
            boolean firstLine = true;
            while ((currentLine = reader.readLine()) != null) {
                // Skip any empty lines
                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                String[] appointmentDetails = currentLine.split("\\|");
                if (appointmentDetails[0].equals(strID)) {
                    continue;
                }
                if (!firstLine) {
                    writer.newLine();
                } else {
                    firstLine = false;
                }
                writer.write(currentLine);
            }
            System.out.println("Appointment deleted successfully.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }
}
