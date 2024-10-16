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
        //System.out.println("User ID: " + id);
        return id;
    }
    public String getPassword(){
        //System.out.println("User Password: " + password);
        return password;
    }
}