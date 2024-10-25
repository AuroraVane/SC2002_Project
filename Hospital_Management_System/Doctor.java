import java.io.IOException;

public class Doctor extends Staff{

    private DoctorUI doctorUI;
    public Doctor(String id, String name, String password, String gender, String role, String age) throws IOException{
        super(id,name,password,gender,role,age);
        this.doctorUI = new DoctorUI(this);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }
    public void displayUI(){
        doctorUI.printMenu();
    }
}
