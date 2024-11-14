package entity;

import utils.Color.ColorRole;

/**
 * Abstract superclass For all user types
 */
public abstract class User {
    private String id;
    private String name;
    private String password;
    private String gender;

    /**
     * User constructor
     * @param id
     * @param name
     * @param password
     * @param gender
     */
    public User(String id, String name, String password, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    /**
     * abstract getter for user role
     * @return
     */
    public abstract String getRole();

    /**
     * abstract getter for colour role
     * @return
     */
    public abstract ColorRole getColorRole();

    /**
     * getter for id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * getter for name
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * getter for password
     * @return
     */
    public String getPassword(){
        return password;
    }

    /**
     * setter for password
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * getter for gender
     * @return
     */
    public String getGender(){
        return gender;
    }

    /**
     * Pseudo method to display the UI of the user
     */
    public void displayUI(){};
}