import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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
        int option = 0;
        Scanner sc = new Scanner(System.in);
        do{
            pharmacistUI.printMenu();
            System.out.println("Select an option: ");
            option = sc.nextInt();
            pharmacistUI.navigateMenu(option);
        }while(option != 5);
        sc.close();
    }

    public List<AppointmentOutcome> getAllAppointmentOutcomes(){
        String filePath = "AppointmentOutcome_List.txt";
        List<AppointmentOutcome> appointmentOutcomes;
        try {
            appointmentOutcomes = TextFileReader.loadAppointmentOutcomes(filePath);
            return appointmentOutcomes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Medicine> getAllMedicines(){
        
        String filePath = "Medicine_List.txt";
        List<Medicine> medicineList;
        try {
             medicineList = TextFileReader.loadMedicineList(filePath);
             return medicineList;
             
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

/*
P001
password
 */