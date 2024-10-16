import java.util.Scanner;
import java.io.IOException;

public class Hospital {
    public static void main(String[] args) throws IOException{
        String patientFilePath = "Patient_List.txt";
        String stafffilePath = "Staff_List.txt";
        Login login = new Login(patientFilePath, stafffilePath);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID:");
        String id = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User authenticatedUser = login.authenticate(id, password);

        if (authenticatedUser != null) {
            System.out.println("Role: " + authenticatedUser.getRole());
        }
        else{
            System.out.println("Invalid ID or password");
        }
    }
}