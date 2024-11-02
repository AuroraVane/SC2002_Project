public class Doctor extends Staff{
    private DoctorUI doctorUI;
    public Doctor(String id, String name, String password, String gender, String role, String age) {
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
    public void navigateMenu(int option){
        doctorUI.navigateMenu(option);
    }
}
