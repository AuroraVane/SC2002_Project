package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import boundary.ManageStaff;
import entity.Staff;
import utils.TextFileReader;
import utils.TextFileWriter;

/**
 * Controller class for ManageStaffUI used by Administrator to manage staff controls the flow of the ManageStaffUI and the staff entity
 */
public class ManageStaffController {
    private static List<Staff> staffList;
    private ManageStaff manageStaff;
    private TextFileWriter writer;

    /**
     * @param fileName
     * @throws IOException
     */
    public ManageStaffController(String fileName) throws IOException {
        staffList = TextFileReader.loadStaff(fileName);
        this.manageStaff = new ManageStaff();
    }

    /**
     * Controller for the menu to manage staff
     */
    public void MenuController() {
        int option = -1;
        while (option != 5) {
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            manageStaff.printMainMenu();
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
            navigateMenu(option);
        }
    }

    /**
     * @param option
     */
    public void navigateMenu(int option) {
        switch (option) {
            case 1:
                viewStaff();
                break;
            case 2:
                addStaff();
                break;
            case 3:
                removeStaff();
                break;
            case 4:
                updateStaff();
                break;
            case 5:
                System.out.println("Returning to main menu...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * @param staffList 
     */
    public void viewStaff() {
        manageStaff.printViewStaffMenu();
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 1 || choice > 4) {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }

        switch (choice) {
            case 1:
                staffList.sort(Comparator.comparing(Staff::getName));
                manageStaff.printViewStaff(staffList, 1);
                break;
            case 2:
                staffList.sort(Comparator.comparing(Staff::getRole));
                manageStaff.printViewStaff(staffList, 2);
                break;
            case 3:
                staffList.sort(Comparator.comparing(Staff::getGender));
                manageStaff.printViewStaff(staffList, 3);
                break;
            case 4:
                staffList.sort(Comparator.comparing(Staff::getAge));
                manageStaff.printViewStaff(staffList, 4);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    /**
     * Add a staff member to the staff list
     */
    public void addStaff() {
        String[] staffDetails = manageStaff.getStaffDetails();
        String id = "";
        if(staffDetails[1].equals("Doctor")){
            id = "D00" + String.valueOf(getStaffCount().get(0) + 1);
            System.out.println(id);
        } else if(staffDetails[1].equals("Pharmacist")){
            id = "P00" + String.valueOf(getStaffCount().get(1) + 1);
            System.out.println(id);
        } else if(staffDetails[1].equals("Administrator")){
            id = "A00" + String.valueOf(getStaffCount().get(2) + 1);
            System.out.println(id);
        }
        this.writer = new TextFileWriter();
        writer.addStaff(id, staffDetails[0], staffDetails[1], staffDetails[2], Integer.parseInt(staffDetails[3]));
        try {
            staffList = TextFileReader.loadStaff("./TextFiles/Staff_List.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to reset staff list from file.");
            e.printStackTrace();
        }

    }

    /**
     * Remove a staff member from the staff list
     */
    public void removeStaff() {
        String id = manageStaff.getStaffId();
        this.writer = new TextFileWriter();
        writer.deleteStaff(id);
        try {
            staffList = TextFileReader.loadStaff("./TextFiles/Staff_List.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to reset staff list from file.");
            e.printStackTrace();
        }
    }

    /**
     * Update a staff member's details
     */
    public void updateStaff() {
        String id = manageStaff.getStaffId();
        String[] staffDetails = manageStaff.getStaffDetails();
        this.writer = new TextFileWriter();
        writer.updateStaff(id, staffDetails[0], staffDetails[1], staffDetails[2], Integer.parseInt(staffDetails[3]),
                "password");
        try {
            staffList = TextFileReader.loadStaff("./TextFiles/Staff_List.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to reset staff list from file.");
            e.printStackTrace();
        }
    }

    /**
     * Load the staff list from the file
     */
    public static void loadStaffList() {
        try {
            staffList = TextFileReader.loadStaff("./TextFiles/Staff_List.txt");
        } catch (IOException e) {
            System.out.println("Error: Unable to reset staff list from file.");
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @return
     */
    public static boolean checkStaffIDExist(String id) {
        // Check if staff ID exists
        loadStaffList();
        for (Staff staff : staffList) {
            if (staff.getId().equals(id)) {
                return true;
            }  
        }
        return false;
    }

    /**
     * @return
     */
    public static List<Integer> getStaffCount(){
        List<Integer> count = new ArrayList<>(Arrays.asList(0, 0, 0)); 
        for (Staff staff : staffList) {
            if (staff.getRole().equals("Doctor")) {
                count.set(0, count.get(0) + 1);
            } else if (staff.getRole().equals("Pharmacist")) {
                count.set(1, count.get(1) + 1);
            } else if (staff.getRole().equals("Administrator")) {
                count.set(2, count.get(2) + 1);
            }
        }
        return count;
    }
}