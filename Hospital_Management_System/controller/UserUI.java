package controller;

import entity.Staff;
import entity.Patient;

/**
 * Interface for UserUI to be used to set controller functions for user interface
 */
public interface UserUI {
    public void printMenu();
    public void navigateMenu(int option);
    public void changePassword(Staff staff);
    public void changePassword(Patient patient);
}
