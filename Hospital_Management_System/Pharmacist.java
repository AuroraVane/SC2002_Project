public class Pharmacist extends Staff{
    private PharmacistUI pharmacistUI = new PharmacistUI(this);

    public Pharmacist(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
    }

    @Override
    public String getRole() {
        return "Pharmacist";
    } 
    public void displayUI(){
        pharmacistUI.printMenu();
    }
    
}
