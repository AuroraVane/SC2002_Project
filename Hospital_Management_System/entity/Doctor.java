package entity;
import java.util.Scanner;

import controller.DoctorUI;

/**
 *
 */
public class Doctor extends Staff{

    private DoctorUI doctorUI;

    /**
     * @param id
     * @param name
     * @param password
     * @param gender
     * @param role
     * @param age
     */
    public Doctor(String id, String name, String password, String gender, String role, String age){
        super(id,name,password,gender,role,age);
        this.doctorUI = new DoctorUI(this);
    }

    /**
     * @return
     */
    @Override
    public String getRole() {
        return "Doctor";
    }

    /**
     *
     */
    public void displayUI(){
        int option = 0;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while(option != 9){
            doctorUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            doctorUI.navigateMenu(option);
        }
    }
}
