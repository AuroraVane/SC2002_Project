import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Comparator;

public class ManageStaffController {
    private List<Staff> staffList;
    private ManageStaff manageStaff;

    public ManageStaffController(String fileName) throws IOException {
        staffList = TextFileReader.loadStaff(fileName);
        this.manageStaff = new ManageStaff();
    }

    public void MenuController() {
        int option = -1;
        while(option != 5) {
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
                manageStaff.printViewStaff(staffList,1);
                break;
            case 2:
                staffList.sort(Comparator.comparing(Staff::getRole));
                manageStaff.printViewStaff(staffList,2);
                break;
            case 3:
                staffList.sort(Comparator.comparing(Staff::getGender));
                manageStaff.printViewStaff(staffList,3);
                break;
            case 4:
                staffList.sort(Comparator.comparing(Staff::getAge));
                manageStaff.printViewStaff(staffList,4);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    public void addStaff() {
        System.out.println("Adding Staff");
    }

    public void removeStaff() {
        System.out.println("Removing Staff");
    }

    public void updateStaff() {
        System.out.println("Updating Staff");
    }
}