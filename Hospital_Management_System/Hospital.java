import java.io.IOException;
import java.util.Scanner;

import entity.User;

public class Hospital {
    public static void main(String[] args) throws IOException{
        String patientFilePath = "./TextFiles/Patient_List.txt";
        String stafffilePath = "./TextFiles/Staff_List.txt";
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

            int option = -1;
            boolean validInput = false;
            while(!validInput){
                System.out.println("\nEnter an option: ");
                String input = scanner.nextLine();

                try {
                    option = Integer.parseInt(input);
                    validInput = true; 
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                }
            }
            authenticatedUser.navigateMenu(option);
        }
        else{
            System.out.println("Invalid ID or password");
        }
    }
}