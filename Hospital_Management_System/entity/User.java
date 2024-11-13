package entity;

import utils.Color.ColorRole;

/**
 *
 */
public abstract class User {
    private String id;
    private String name;
    private String password;
    private String gender;

    /**
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
     * @return
     */
    public abstract String getRole();

    /**
     * @return
     */
    public abstract ColorRole getColorRole();

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * @return
     */
    public String getPassword(){
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * @return
     */
    public String getGender(){
        return gender;
    }

    /**
     *
     */
    public void displayUI(){};
}