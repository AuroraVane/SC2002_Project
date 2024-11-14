package entity;
import controller.AppointmentOutcomeController;
import controller.MedicationInventoryController;
import controller.PharmacistUI;
import java.util.List;
import java.util.Scanner;

/**
 * Subclass of Staff, Pharmacist entity class to be used to get and set attributes for pharmacist functions
 */
public class Pharmacist extends Staff{
    private PharmacistUI pharmacistUI;

    /**
     * Pharmacist constructor
     * @param id
     * @param name
     * @param password
     * @param gender
     * @param role
     * @param age
     */
    public Pharmacist(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
        this.pharmacistUI = new PharmacistUI(this);
    }

    /**
     * getter for Pharmacist role
     * @return
     */
    @Override
    public String getRole() {
        return "Pharmacist";
    }

    /**
     * Display the UI for the pharmacist
     */
    public void displayUI(){
        int option = 0;
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
        while(option != 6){
            pharmacistUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            pharmacistUI.navigateMenu(option);
        }
    }



    
}

/*
P001
password
 */