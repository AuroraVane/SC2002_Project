public class AdministratorUI implements UserUI{
    private Administrator administrator;
    public AdministratorUI(Administrator administrator){
        this.administrator = administrator;
    }
    public void printMenu(){
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointment Details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Log Out");
    }
}
