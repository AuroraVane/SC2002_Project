import java.util.List;

public class ManageStaff {
    private List<Staff> staffList;
    public void printMainMenu(){
        System.out.println("\nStaff Management Menu");
        System.out.println("1. View Staff");
        System.out.println("2. Add Staff");
        System.out.println("3. Remove Staff");
        System.out.println("4. Update Staff");
        System.out.println("5. Back");
    }
    public void printViewStaffMenu(){
        System.out.println("\nView Staff By:");
        System.out.println("1. Name\n2. Role\n3. Gender\n4. Age\n");
        System.out.println("Enter an option: ");
    }
    public void printViewStaff(List<Staff> staffList,int option){
        this.staffList = staffList;
        System.out.println("\nStaff List:");
        for (Staff staff : staffList) {
            switch(option){
                case 1:
                    System.out.println(staff.getName());
                    break;
                case 2:
                    System.out.println(staff.getName()+", Role: "+staff.getRole());
                    break;
                case 3:
                    System.out.println(staff.getName()+", Gender: "+staff.getGender());
                    break;
                case 4:
                    System.out.println(staff.getName()+", Age: "+staff.getAge());
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
