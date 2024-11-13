package entity;

/**
 * An entity class to represent a replenishment request
 */

public class ReplenishmentRequest {
    private String id;
    private String medicationName;
    private String status;
    private String quantity;

    /**
     * @param id
     * @param medicationName
     * @param status
     * @param quantity
     */
    public ReplenishmentRequest(String id,String medicationName, String status, String quantity) {
        this.id = id;
        this.medicationName = medicationName;
        this.status = status;
        this.quantity = quantity;
    }

    /**
     * @return
     */
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * @param medicationName
     */
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    /**
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
