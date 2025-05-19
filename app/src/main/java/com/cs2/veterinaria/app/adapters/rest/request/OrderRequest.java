package com.cs2.veterinaria.app.adapters.rest.request;

public class OrderRequest {
    private String drugName;
    private String dateOrder;
    private long idPet;
    private long idOwner;
    private long userId;

    // Getters y setters
    public String getDrugName() { 
        return drugName; 
    }
    public void setDrugName(String drugName) { 
        this.drugName = drugName; 
    }
    public String getDateOrder() { 
        return dateOrder; 
    }
    public void setDateOrder(String dateOrder) { 
        this.dateOrder = dateOrder; 
    }
    public long getIdPet() { return idPet; 
    }
    public void setIdPet(long idPet) {
        this.idPet = idPet; 
    }
    public long getIdOwner() { 
        return idOwner; 
    }
    public void setIdOwner(long idOwner) { 
        this.idOwner = idOwner; 
    }
    public long getUserId() { 
        return userId; 
    }
    public void setUserId(long userId) { 
        this.userId = userId; 
    }
}