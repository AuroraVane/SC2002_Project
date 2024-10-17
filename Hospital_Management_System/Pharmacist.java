public class Pharmacist extends Staff{
    private PharmacistUI pharmacistUI;

    public Pharmacist(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
        this.pharmacistUI = new PharmacistUI(this);
    }

    @Override
    public String getRole() {
        return "Pharmacist";
    } 
    public void displayUI(){
        pharmacistUI.printMenu();
    }
    
}
