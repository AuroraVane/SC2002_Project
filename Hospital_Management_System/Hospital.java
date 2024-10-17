import java.util.Scanner;
import java.io.IOException;

public class Hospital {
    public static void main(String[] args) throws IOException{
        String patientFilePath = "Patient_List.txt";
        String stafffilePath = "Staff_List.txt";
        Login login = new Login(patientFilePath, stafffilePath);
        System.err.println("Welcome to the XYZ Hospital");
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
        }
        else{
            System.out.println("Invalid ID or password");
        }
    }
}