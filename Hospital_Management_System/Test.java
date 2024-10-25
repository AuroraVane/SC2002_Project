public class Test {
    public static void main(String[] args) {
        //D001|John Smith|Doctor|Male|45|password
        //Doctor doctor=new Doctor("D001", "John Smith", "password", "Male", "Doctor", "45");
        //doctor.displayUI();
        //P003|Annette Birkins|Pharmacist|Female|41|password
        Pharmacist pharm=new Pharmacist("P003", "Annette Birkins", "password", "", "Pharmacist", "41");
        pharm.displayUI();
    }
}
