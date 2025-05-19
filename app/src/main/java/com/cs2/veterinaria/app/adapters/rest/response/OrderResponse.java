package com.cs2.veterinaria.app.adapters.rest.response;

public class OrderResponse {
    private Long idOrder;
    private String drugName;
    private String dateOrder;

    public OrderResponse() {}

    public OrderResponse(Long idOrder, String drugName, String dateOrder) {
        this.idOrder = idOrder;
        this.drugName = drugName;
        this.dateOrder = dateOrder;
    }

    public Long getIdOrder() { return idOrder; }
    public void setIdOrder(Long idOrder) { this.idOrder = idOrder; }
    public String getDrugName() { return drugName; }
    public void setDrugName(String drugName) { this.drugName = drugName; }
    public String getDateOrder() { return dateOrder; }
    public void setDateOrder(String dateOrder) { this.dateOrder = dateOrder; }
}
