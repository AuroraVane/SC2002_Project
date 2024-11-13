package boundary;
import java.util.List;
import java.util.Scanner;

import entity.Administrator;
import entity.Medicine;
import entity.Pharmacist;
import entity.ReplenishmentRequest;
import entity.Staff;

/**
 * UI class for ReplenishmentRequestUI used by ReplenishmentRequestController
 */
public class ReplenishmentRequestUI {
    /**
     * @param staff
     */
    public void printMainMenu(Staff staff) {
        System.out.println("=========================================");
        System.out.println("\n    1. View Replenishment Requests");
        if(staff instanceof Administrator){
            System.out.println("    2. Approve Replenishment Requests");
        }
        else if(staff instanceof Pharmacist){
            System.out.println("    2. Submit Replenishment Requests");
        }
        System.out.println("    3. Back");
        System.out.println("\n=========================================");
    }

    /**
     * @param replenishmentRequestList
     */
    public void printReplenishmentRequests(List<ReplenishmentRequest> replenishmentRequestList) {
        System.out.println("=========================================");
        System.out.println("\n      Replenishment Requests:");
        for (ReplenishmentRequest replenishmentRequest : replenishmentRequestList) {
            System.out.printf("%-4s | %-14s | %-4s | %-8s \n",
                                        replenishmentRequest.getId(),
                                        replenishmentRequest.getMedicationName(),
                                        replenishmentRequest.getQuantity(),
                                        replenishmentRequest.getStatus()
                                          );
        }
    }

    /**
     * @param replenishmentRequestList
     * @return
     */
    public String approveReplenishmentRequests(List<ReplenishmentRequest> replenishmentRequestList) {
        printReplenishmentRequests(replenishmentRequestList);
        System.out.println("=========================================");
        System.out.println("\nSelect a replenishment request to approve:");
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * @return
     */
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
    
