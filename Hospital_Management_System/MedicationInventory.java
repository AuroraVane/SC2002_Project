public class MedicationInventory {
    private String name;
    private String stock;
    private String alert;

    public MedicationInventory(String name, String stock, String alert) {
        this.name = name;
        this.stock = stock;
        this.alert = alert;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStock() {
        return stock;
    }
    public void setStock(String stock) {
        this.stock = stock;
    }
    public String getAlert() {
        return alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }
}
