package boundary;
import java.util.List;
import java.util.Scanner;

import entity.Administrator;
import entity.Medicine;
import entity.Pharmacist;
import entity.ReplenishmentRequest;
import entity.Staff;

public class ReplenishmentRequestUI {
    public void printMainMenu(Staff staff) {

        System.out.println("\n1. View Replenishment Requests");
        if(staff instanceof Administrator){
            System.out.println("2. Approve Replenishment Requests");
        }
        else if(staff instanceof Pharmacist){
            System.out.println("2. Submit Replenishment Requests");
        }
        System.out.println("3. Back");
    }
    public void printReplenishmentRequests(List<ReplenishmentRequest> replenishmentRequestList) {
        System.out.println("\nReplenishment Requests:");
        for (ReplenishmentRequest replenishmentRequest : replenishmentRequestList) {
            System.out.println(replenishmentRequest.getMedicationName() + ", " + replenishmentRequest.getQuantity() +", " + replenishmentRequest.getStatus());
        }
    }
    public String approveReplenishmentRequests(List<ReplenishmentRequest> replenishmentRequestList) {
        printReplenishmentRequests(replenishmentRequestList);
        System.out.println("\nSelect a replenishment request to approve:");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public Medicine submitReplenishmentRequests() {
        System.out.println("Select medicine for replenishment");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        String medicineName = sc.nextLine();
        List<Medicine> medicineList = Medicine.getAllMedicines();
        Medicine medicine = null;
        for (Medicine med : medicineList){
            if (med.getMedicineName().equals(medicineName)) {
                medicine = med;
            }
        }
        return medicine;
    }

}
    
