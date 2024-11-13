package controller;

import entity.Patient;
import entity.Staff;
import java.util.Scanner;
import utils.TextFileWriter;

/**
 *
 */
public abstract class StaffUI implements UserUI{
    /**
     * @param staff
     */
    @Override
    public void changePassword(Staff staff){
        System.out.println("Enter new password: ");
        // Code to change password
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String newPassword = scanner.nextLine();
        Boolean success = TextFileWriter.changePassword(staff, newPassword);
        if (success){
            System.out.println("Password changed successfully.");
        }
        else{
            System.out.println("Error: Unable to change password.");
        }
    }

    /**
     * @param patient
     */
    @Override
    public void changePassword(Patient patient){
        // Code to change password
    }
}
