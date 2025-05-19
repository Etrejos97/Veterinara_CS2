package com.cs2.veterinaria.app.adapters.rest.response;

public class HistoryClinicResponse {
    private Long idHistory;
    private Long idPet;
    private String details;

    public HistoryClinicResponse() {}

    public HistoryClinicResponse(Long idHistory, Long idPet, String details) {
        this.idHistory = idHistory;
        this.idPet = idPet;
        this.details = details;
    }

    public Long getIdHistory() { return idHistory; }
    public void setIdHistory(Long idHistory) { this.idHistory = idHistory; }
    public Long getIdPet() { return idPet; }
    public void setIdPet(Long idPet) { this.idPet = idPet; }
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
