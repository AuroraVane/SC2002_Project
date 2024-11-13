package entity;
import java.util.Scanner;

import controller.AdministratorUI;

/**
 * Subclass of Staff, Administrator entity class to be used to get and set attributes for administrator functions
 */
public class Administrator extends Staff{
    private AdministratorUI administratorUI;

    /**
     * @param id
     * @param name
     * @param password
     * @param gender
     * @param role
     * @param age
     */
    public Administrator(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
        this.administratorUI = new AdministratorUI(this);
    }

    /**
     * @return
     */
    @Override
    public String getRole() {
        return "Administrator";
    }

    /**
     * Display the UI for the administrator
     */
    public void displayUI(){
        int option = 0;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while(option != 7){
            administratorUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            administratorUI.navigateMenu(option);
        }
    }
}
