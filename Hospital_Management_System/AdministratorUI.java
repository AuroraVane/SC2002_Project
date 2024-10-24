public class AdministratorUI implements UserUI{
    private Administrator administrator;
    private Register register;

    public AdministratorUI(Administrator administrator){
        this.administrator = administrator;
        this.register = new Register();
    }
    public void printMenu(){
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointment Details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Register New Staff");
        System.out.println("6. Log Out");
    }
    public void navigateMenu(int option){
        switch(option){
            case 1:
                skeletonManageStaff();//viewAndManageStaff();
                break;
            case 2:
                skeletonAppointment();//viewAppointmentDetails();
                break;
            case 3:
                skeletonMedicationInventory();//viewAndManageMedicationInventory();
                break;
            case 4:
                skeletonMedicationInventory();//approveReplenishmentRequests();
                break;
            case 5:
                register.showRegistrationForm(administrator);
                break;
            case 6:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
    public void skeletonManageStaff(){
        System.out.println("Skeleton for Manage Staff");
    }
    public void skeletonAppointment(){
        System.out.println("Skeleton for Appointment");
    }
    public void skeletonMedicationInventory(){
        System.out.println("Skeleton for Medication Inventory");
    }
}
