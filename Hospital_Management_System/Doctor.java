public class Doctor extends Staff{
    private DoctorUI doctorUI = new DoctorUI(this);
    public Doctor(String id, String name, String password, String gender, String role, String age) {
        super(id,name,password,gender,role,age);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }
    public void displayUI(){
        doctorUI.printMenu();
    }
}
