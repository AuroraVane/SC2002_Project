public class Administrator extends Staff{
    private AdministratorUI administratorUI;
    public Administrator(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
        this.administratorUI = new AdministratorUI(this);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
    public void displayUI(){
        administratorUI.printMenu();
    }
}
