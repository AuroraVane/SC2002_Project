package controller;

import java.util.List;
import java.util.Scanner;

import utils.TextFileReader;
import utils.TextFileWriter;

public class BillController {
    public static enum PaymentStatus {
        PAID, UNPAID
    }

    public static enum Medicine {
        Paracetamol, Ibuprofen, Amoxicillin, CharcoalPills
    }

    public static void addBill(String appointmentID, String patientID, String medicine, PaymentStatus status) {
        String billID = String.valueOf(getBillCount() + 1);
        int amount = -1;
        switch (medicine) {
            case "Paracetamol":
                amount = 10;
                break;
            case "Ibuprofen":
                amount = 20;
                break;
            case "Amoxicillin":
                amount = 30;
                break;
            case "CharcoalPills":
                amount = 20;
                break;
        }
        TextFileWriter.addBill(billID, appointmentID, patientID, String.valueOf(100 + (1.5 * amount)), "Unpaid");
    }

    public static void getBillbyPatientID(String patientID) {
        List<String> billList = TextFileReader.loadBillList();
        boolean found = false;
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        for (String bill : billList) {
            String[] billDetails = bill.split("\\|");
            if (billDetails[2].equals(patientID)) {
                if (billDetails[4].equals("Unpaid")) {
                    found = true;
                    System.out.println("Bill ID: " + billDetails[0]);
                    System.out.println("Appointment ID: " + billDetails[1]);
                    System.out.println("Amount: " + billDetails[3]);
                    System.out.println("Status: " + billDetails[4]);

                    System.out.println("Do you want to pay bill(Y/N)?");
                    String choice = scanner.nextLine();
                    if (choice.equals("Y")) {
                        TextFileWriter.updateBillStatus(billDetails[0]);
                    }
                }
            }
        }
        if (!found) {
            System.out.println("No bills found");
        }
    }

    public static int getBillCount() {
        List<String> billList = TextFileReader.loadBillList();
        int billCount = billList.size();
        return billCount;
    }
}
