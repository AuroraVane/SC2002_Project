package entity;

import utils.Color.ColorRole;

/**
 *  Subclass of User, Superclass of Doctor, Administrator, Pharmacist to be used for general staff functions
 */
public class Staff extends User{
    private String age;
    private String role;

    /**
     * Staff constructor
     * @param id
     * @param name
     * @param password
     * @param gender
     * @param role
     * @param age
     */
    public Staff(String id, String name, String password, String gender, String role, String age) {
        super(id, name, password,gender);
        this.age = age;
        this.role = role;
    }

    /**
     * getter for role staff
     * @return
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * getter for age
     * @return
     */
    public String getAge(){
        return age;
    }

    /**
     * Colour role for Users
     * @return
     */
    @Override
    public ColorRole getColorRole() {
        switch (role) {
            case "Doctor":
                return ColorRole.DOCTOR;
            case "Administrator":
                return ColorRole.ADMINISTRATOR;
            case "Pharmacist":
                return ColorRole.PHARMACIST;
            default:
                return ColorRole.PATIENT;
        }
    }
}
