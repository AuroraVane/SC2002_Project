import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class TextFileWriter {

    private static final String FILE_PATH = "Staff_List.txt";  // Path to the staff list file
    private static final String APPOINTMENT_FILE_PATH = "Appointment_List.txt";
    private static final String OUTCOME_FILE_PATH="AppointmentOutcome_List.txt";

    // Method to add a new staff member
    public void addStaff(String id, String name, String role, String gender, int age, String password) {
        String newStaff = String.format("%s|%s|%s|%s|%d|%s", id, name, role, gender, age, password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            if (Files.size(Paths.get(FILE_PATH)) > 0) {
                writer.newLine();  // Add a newline only if the file is not empty
            } 
            writer.write(newStaff);
            System.out.println("Staff member added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

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
                    writer.newLine();  // Add a newline only after the first valid line
                } else {
                    firstLine = false;  // Mark that the first line has been written
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
                if(!firstLine){
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

    public void updateMedicationInventory(String name,String stock){
        File inputFile = new File("Medicine_List.txt");
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
                    currentLine = String.format("%s|%s|%s", name, stock,staffDetails[2]);
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
    public String[] updateReplenishmentRequest(String name){
        File inputFile = new File("Replenishment_List.txt");
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
                    currentLine = String.format("%s|%s|%s|%s", name, staffDetails[1],"Approved",staffDetails[3]);
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
        return new String[]{medicinename, quantity};
    }
    //writes in text file until user prompts to stop
    
    public void updateAppointment(Appointment appmt) {
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
                if (appmtDetails[0].equals(appmt.getAppointmentID()+"")) {
                    // Write the updated staff details
                    currentLine = String.format("%d|%s|%s|%s|%s|%s", appmt.getAppointmentID(), appmt.getPatientID(), appmt.getStaffID(), appmt.getStatus(), appmt.getDate(), appmt.getTime());
                } 
                if(!firstLine){
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
    public void addAppointment(Appointment appmt) {
        String newAppointment = String.format("%d|%s|%s|%s|%s|%s", appmt.getAppointmentID(), appmt.getPatientID(), appmt.getStaffID(), appmt.getStatus(), appmt.getDate(), appmt.getTime());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(APPOINTMENT_FILE_PATH, true))) {
            if (Files.size(Paths.get(FILE_PATH)) > 0) {
                writer.newLine();  // Add a newline only if the file is not empty
            } 
            writer.write(newAppointment);
            System.out.println("Appointment added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void deleteAppointment(String id) {
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
                if (appointmentDetails[0].equals(id)) {
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

        // Replace the original file with the updated file
        if (!inputFile.delete()) {
            System.out.println("Could not delete the original file.");
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename the temp file.");
        }
    }

    public void addAppointmentOutcome(int appointmentId, String date, String service, String medicine, boolean status, String consulationNotes) {
        String newAppointment = String.format("%d|%s|%s|%s|%b|%s", appointmentId, date, service, medicine, status, consulationNotes);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTCOME_FILE_PATH, true))) {
            if (Files.size(Paths.get(FILE_PATH)) > 0) {
                writer.newLine();  // Add a newline only if the file is not empty
            } 
            writer.write(newAppointment);
            System.out.println("Appointment Outcome added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    public void WriteFile(String FILE_PATH) {
        try {
            FileWriter Writer = new FileWriter(FILE_PATH);
            System.out.println("Enter 0 to exit");
            @SuppressWarnings("resource")
            Scanner w=new Scanner(System.in);
            while (true) {
                String x=w.nextLine();
                if (x.equals("0")){
                    break;
                }
                Writer.write(x);
                
            }
            Writer.close();
            //w.close();
            //prevent resource leaks
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}