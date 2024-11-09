import java.io.IOException;
import java.util.Scanner;

import controller.PatientUI;
import entity.User;

public class Hospital {
    public static void main(String[] args) throws IOException {
        String patientFilePath = "./TextFiles/Patient_List.txt";
        String stafffilePath = "./TextFiles/Staff_List.txt";
        Boolean isRunning = true;
        Login login = new Login(patientFilePath, stafffilePath);
        while (isRunning) {
            System.err.println("Welcome to the XYZ Hospital");
            @SuppressWarnings("resource") // Ignore warning for scanner VSCode things
            Scanner scanner = new Scanner(System.in);
            System.out.println("1. Login");
            System.out.println("2. Register as a new patient");
            System.out.println("3. Forget Password");
            System.out.println("4. Exit");
            System.out.println("Enter an option: ");
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
                        System.out.println("\nWelcome " + authenticatedUser.getName());
                        System.out.println("Role: " + authenticatedUser.getRole());
                        System.out.println("Navigating to main menu...\n");
                        authenticatedUser.displayUI();

                        int option = -1;
                        boolean validInput = false;
                        while (!validInput) {
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
}