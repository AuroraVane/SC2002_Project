package entity;
import java.util.Scanner;

import controller.AdministratorUI;

public class Administrator extends Staff{
    private AdministratorUI administratorUI;
    public Administrator(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
        this.administratorUI = new AdministratorUI(this);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
    public void displayUI(){
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do{
            administratorUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            administratorUI.navigateMenu(option);
        }while(option != 6);
    }
}
