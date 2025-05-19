package com.cs2.veterinaria.app.adapters.rest.request;

public class CancelOrderRequest {
    private long idOrder;
    private long idPet;
    private String reason;

    public long getIdOrder() { return idOrder; }
    public void setIdOrder(long idOrder) { this.idOrder = idOrder; }
    public long getIdPet() { return idPet; }
    public void setIdPet(long idPet) { this.idPet = idPet; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
