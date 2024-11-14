package boundary;
import java.util.List;
import java.util.Scanner;

import entity.Staff;

/**
 * UI class for ManageStaff used by ManageStaffController
 */
public class ManageStaff {
    /**
     * Print the staff main menu
     */
    public void printMainMenu(){
        System.out.println("\n=========================================");
        System.out.println("\n          STAFF MANAGEMENT MENU");
        System.out.println("\n=========================================");
        System.out.println("            1. View Staff");
        System.out.println("            2. Add Staff");
        System.out.println("            3. Remove Staff");
        System.out.println("            4. Update Staff");
        System.out.println("            5. Back");
        System.out.println("=========================================");
    }

    /**
     * Print the view staff menu
     */
    public void printViewStaffMenu(){
        System.out.println("\n=========================================");
        System.out.println("\n            View Staff By:");
        System.out.println("            1. Name\n            2. Role\n            3. Gender\n            4. Age\n");
        System.out.println("\n=========================================");
    }

    /**
     * Print for admin when viewing staff
     * @param staffList
     * @param option
     */
    public void printViewStaff(List<Staff> staffList,int option){
        System.out.println("\n=========================================");
        System.out.println("\n              Staff List");
        System.out.println("\n=========================================");
        for (Staff staff : staffList) {
            switch(option){
                case 1:
                    System.out.println("            "+staff.getName());
                    break;
                case 2:
                    System.out.println("    "+staff.getName()+", Role: "+staff.getRole());
                    break;
                case 3:
                    System.out.println("    "+staff.getName()+", Gender: "+staff.getGender());
                    break;
                case 4:
                    System.out.println("        "+staff.getName()+", Age: "+staff.getAge());
                    break;
                default:
                    System.out.println("\"            \"+Invalid option. Please try again.");
            }
        }
        System.out.println("\n=========================================");
        System.out.println("    Press Enter to continue...");
        System.out.println("=========================================");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * get details for staff, given their name
     * @return
     */
    public String[] getStaffDetails() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("            Enter staff name: ");
        String name = scanner.nextLine();

        String role = "";
        System.out.println("=========================================");
        while(!role.equals("Doctor") || !role.equals("Pharmacist") || !role.equals("Administrator")){
            System.out.println("   Enter staff role (Doctor/Pharmacist/Administrator): ");
            role = scanner.nextLine();
            if(role.equals("Doctor") || role.equals("Pharmacist") || role.equals("Administrator")){
                break;
            }
            else{
                System.out.println("Invalid role. Please try again.");
            }
        }
        System.out.println("=========================================");
        String gender = "";
        while(!gender.equals("Male") || !gender.equals("Female") || gender.equals("Others") || gender.equals("Prefer not to say") || gender.equals("Transformer")){
            System.out.println("        Enter staff gender");
            gender = scanner.nextLine();
            if(gender.equals("Male") || gender.equals("Female") || gender.equals("Others") || gender.equals("Prefer not to say") || gender.equals("Transformer")){
                break;
            }
            else{
                System.out.println("Invalid Gender (Male/Female/Others/Prefer not to say). Please try again.");
            }
        }
        System.out.println("=========================================");
        System.out.println("            Enter staff age: ");
        int age = -1;
        while(age < 0 || age > 150){
            try {
                age = Integer.parseInt(scanner.nextLine());
                if(age < 0 || age > 150){
                    System.out.println("Invalid age. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 150.");
            }
        }
        System.out.println("=========================================");
        // Returning details as a String array (converting age to a String)
        return new String[] {name, role, gender, String.valueOf(age)};
    }

    /**
     * get id of staff by input
     * @return
     */
    public String getStaffId() {
        System.out.println("=========================================");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("            Enter staff ID: ");
        return scanner.nextLine();
    }
}
