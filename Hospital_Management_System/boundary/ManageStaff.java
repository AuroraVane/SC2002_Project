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

        System.out.println("Enter staff role: ");
        String role = scanner.nextLine();

        System.out.println("Enter staff gender: ");
        String gender = scanner.nextLine();

        System.out.println("Enter staff age: ");
        int age = Integer.parseInt(scanner.nextLine());


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
