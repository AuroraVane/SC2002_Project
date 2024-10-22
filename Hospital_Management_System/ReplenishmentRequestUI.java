import java.util.List;
import java.util.Scanner;

public class ReplenishmentRequestUI {
    public void printMainMenu() {
        System.out.println("\n1. View Replenishment Requests");
        System.out.println("2. Approve Replenishment Requests");
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
}
