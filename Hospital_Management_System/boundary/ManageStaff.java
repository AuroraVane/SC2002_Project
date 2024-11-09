package boundary;
import java.util.List;
import java.util.Scanner;

import entity.Staff;

public class ManageStaff {
    public void printMainMenu(){
        System.out.println("\nStaff Management Menu");
        System.out.println("1. View Staff");
        System.out.println("2. Add Staff");
        System.out.println("3. Remove Staff");
        System.out.println("4. Update Staff");
        System.out.println("5. Back");
    }
    public void printViewStaffMenu(){
        System.out.println("\nView Staff By:");
        System.out.println("1. Name\n2. Role\n3. Gender\n4. Age\n");
    }
    public void printViewStaff(List<Staff> staffList,int option){
        System.out.println("\nStaff List:");
        for (Staff staff : staffList) {
            switch(option){
                case 1:
                    System.out.println(staff.getName());
                    break;
                case 2:
                    System.out.println(staff.getName()+", Role: "+staff.getRole());
                    break;
                case 3:
                    System.out.println(staff.getName()+", Gender: "+staff.getGender());
                    break;
                case 4:
                    System.out.println(staff.getName()+", Age: "+staff.getAge());
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public String[] getStaffDetails() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter staff name: ");
        String name = scanner.nextLine();

        String role = "";
        while(!role.equals("Doctor") || !role.equals("Pharmacist") || !role.equals("Administrator")){
            System.out.println("Enter staff role (Doctor/Pharmacist/Administrator): ");
            role = scanner.nextLine();
            if(role.equals("Doctor") || role.equals("Pharmacist") || role.equals("Administrator")){
                break;
            }
            else{
                System.out.println("Invalid role. Please try again.");
            }
        }
        String gender = "";
        while(!gender.equals("Male") || !gender.equals("Female") || gender.equals("Others") || gender.equals("Prefer not to say") || gender.equals("Transformer")){
            System.out.println("Enter staff gender");
            gender = scanner.nextLine();
            if(gender.equals("Male") || gender.equals("Female") || gender.equals("Others") || gender.equals("Prefer not to say") || gender.equals("Transformer")){
                break;
            }
            else{
                System.out.println("Invalid Gender (Male/Female/Others/Prefer not to say). Please try again.");
            }
        }

        System.out.println("Enter staff age: ");
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

        // Returning details as a String array (converting age to a String)
        return new String[] {name, role, gender, String.valueOf(age)};
    }

    public String getStaffId() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter staff ID: ");
        return scanner.nextLine();
    }
}
