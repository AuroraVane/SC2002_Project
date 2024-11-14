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
     * Replenish Request constructor
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
     * getter for medicine name
     * @return
     */
    public String getMedicationName() {
        return medicationName;
    }

    /**
     * getter for request status
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     * setter for request status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * getter for requested quantity
     * @return
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * getter for request id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * setter for request id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
}
