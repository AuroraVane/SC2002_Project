import java.io.IOException;
import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) throws IOException{
        String patientFilePath = "Patient_List.txt";
        String stafffilePath = "Staff_List.txt";
        String AppointmentFilePath="Appointment.txt";
        String MedicalRecordFilePath="MeicalRecord.txt";
        
        Login login = new Login(patientFilePath, stafffilePath);
        System.err.println("Welcome to the XYZ Hospital");
        @SuppressWarnings("resource") //Ignore warning for scanner VSCode things
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter ID:");
        String id = scanner.nextLine();
        System.out.println("Please enter password:");
        String password = scanner.nextLine();

        User authenticatedUser = login.authenticate(id, password);

        if (authenticatedUser != null) {
            System.out.println("\nWelcome " + authenticatedUser.getName());
            System.out.println("Role: " + authenticatedUser.getRole());
            System.out.println("Navigating to main menu...\n");
            authenticatedUser.displayUI();
            String option = scanner.nextLine();
            //authenticatedUser.navigateMenu(option);
        }
        else{
            System.out.println("Invalid ID or password");
        }
    }
}