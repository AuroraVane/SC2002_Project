public class Pharmacist extends Staff{
    public Pharmacist(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
    }

    @Override
    public String getRole() {
        return "Pharmacist";
    } 
    
}
