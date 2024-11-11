package controller;

import java.util.List;

import utils.TextFileReader;
import utils.TextFileWriter;

public class BillController {
    private static List<String> billList;
    public static enum PaymentStatus {
        PAID, UNPAID
    }

    public static enum Medicine{
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
        TextFileWriter.addBill(billID, appointmentID, patientID,String.valueOf(100+(1.5*amount)),"Unpaid");
    }

    public static void loadBillList(){
        billList = TextFileReader.loadBillList();
    }

    public static int getBillCount(){
        loadBillList();
        int billCount = billList.size();
        return billCount;
    }
}
