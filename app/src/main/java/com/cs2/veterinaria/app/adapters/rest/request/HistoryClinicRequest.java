package com.cs2.veterinaria.app.adapters.rest.request;

public class HistoryClinicRequest {
    private long idPet;
    private String details;

    public long getIdPet() { 
        return idPet; 
    }
    public void setIdPet(long idPet) { 
        this.idPet = idPet; 
    }
    public String getDetails() { 
        
        return details; }
    public void setDetails(String details) { 
        this.details = details;
    }
}