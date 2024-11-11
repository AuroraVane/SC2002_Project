import java.io.IOException;
import java.util.Scanner;

import controller.Login;
import controller.PatientUI;
import entity.User;

public class Hospital {
    public static void main(String[] args) throws IOException {
        String patientFilePath = "./TextFiles/Patient_List.txt";
        String stafffilePath = "./TextFiles/Staff_List.txt";
        Boolean isRunning = true;
        Login login = new Login(patientFilePath, stafffilePath);
        while (isRunning) {
            
            @SuppressWarnings("resource") // Ignore warning for scanner VSCode things
            Scanner scanner = new Scanner(System.in);
            printMenu();   
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Please enter ID:");
                    String id = scanner.nextLine();
                    System.out.println("Please enter password:");
                    String password = scanner.nextLine();

                    User authenticatedUser = login.authenticate(id, password);

                    if (authenticatedUser != null) {
                        System.out.println("\n=========================================");
                        System.out.println("\nWelcome " + authenticatedUser.getName());
                        System.out.println("Role: " + authenticatedUser.getRole());
                        System.out.println("\n=========================================");
                        authenticatedUser.displayUI();
                    } else {
                        System.out.println("Invalid ID or password");
                    }
                    break;
                case 2:
                    PatientUI.createPatient();
                    break;
                case 3:
                    Login.forgetPassword();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input. Please enter a valid option.");
                    break;
            }

        }

    }
    public static void printMenu(){
        String menu = """
            ===================================================
                    WELCOME TO HOSPITAL MANAGEMENT SYSTEM
            ===================================================

                         _    _ __  __ _______  
                        | |  | |  \\/  |  ____| 
                        | |__| | |\\/| | |_____ 
                        |  __  |_|  | |_____ |
                        | |  | | |  | |____| |
                        |_|  |_|_|  |_|______|
            ===================================================
                           PLEASE CHOOSE AN OPTION
            ===================================================
                      1. Login
                      2. Register as a New Patient
                      3. Forget Password
                      4. Exit
            ===================================================

            Enter an option:
            """;

        System.out.println(menu);
    }
}