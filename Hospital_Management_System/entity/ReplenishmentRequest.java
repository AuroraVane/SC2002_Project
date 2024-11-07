package entity;
public class ReplenishmentRequest {
    private String id;
    private String medicationName;
    private String status;
    private String quantity;

    public ReplenishmentRequest(String id,String medicationName, String status, String quantity) {
        this.id = id;
        this.medicationName = medicationName;
        this.status = status;
        this.quantity = quantity;
    }
    public String getMedicationName() {
        return medicationName;
    }
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
