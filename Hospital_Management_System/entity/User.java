package entity;
public abstract class User {
    private String id;
    private String name;
    private String password;
    private String gender;

    public User(String id, String name, String password, String gender) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
    }

    public abstract String getRole();

    public String getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPassword(){
        return password;
    }
    public String getGender(){
        return gender;
    }
    public void displayUI(){};
    public void navigateMenu(int option){};
}