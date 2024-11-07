package controller;

import entity.Staff;
import entity.Patient;

public interface UserUI {
    public void printMenu();
    public void navigateMenu(int option);
    public void changePassword(Staff staff);
    public void changePassword(Patient patient);
}
