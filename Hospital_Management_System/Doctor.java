import java.util.Scanner;

public class Doctor extends Staff{
    private DoctorUI doctorUI;
    public Doctor(String id, String name, String password, String gender, String role, String age){
        super(id,name,password,gender,role,age);
        this.doctorUI = new DoctorUI(this);
    }

    @Override
    public String getRole() {
        return "Doctor";
    }
    public void displayUI(){
        int option = 0;
        
        Scanner sc = new Scanner(System.in);
        do{
            doctorUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            doctorUI.navigateMenu(option);
        }while(option != 8);
        sc.close();
    }
}
