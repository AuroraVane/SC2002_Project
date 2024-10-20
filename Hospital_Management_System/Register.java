import java.util.Scanner;

public class Register {
    public void showRegistrationForm(User currentUser){
        @SuppressWarnings("resource") //Ignore warning for scanner VSCode things
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose role to register (Administrator, Pharmacist, Doctor): ");
        String role = scanner.nextLine();// input from user
        System.out.println("Enter Name: ");
        String name = scanner.nextLine();// input from user
        System.out.println("Enter Username: ");
        String username = scanner.nextLine();// input from user
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();// input from user

        this.registerUser(currentUser, role, name, username, password);
    }

    public void registerUser(User currentUser, String role, String id, String name, String password){
        if (currentUser instanceof Administrator) {
            if (role.equalsIgnoreCase("Administrator") || 
                role.equalsIgnoreCase("Pharmacist") || 
                role.equalsIgnoreCase("Doctor")) {
                
                createNewUser(role, name, name, password);
            } else {
                System.out.println("Administrators can only register Administrators, Pharmacists, or Doctors.");
            }
        } else if (currentUser instanceof Doctor) {
            if (role.equalsIgnoreCase("Patient")) {
                
                createNewUser(role, name, name, password);
            } else {
                System.out.println("Doctors can only register Patients.");
            }
        } else {
            System.out.println("Only administrators and doctors can register users.");
        }
    }

    private void createNewUser(String role, String name, String username, String password) {
        switch(role) {
            case "Administrator":
                // Skeleton
                break;
            case "Pharmacist":
                // Skeleton
                break;
            case "Doctor":
                // Skeleton
                break;
            case "Patient":
                // Skeleton
                break;
            default:
                System.out.println("Invalid role.");
        }
        System.out.println("User with role " + role + " registered successfully.");
    }
}
