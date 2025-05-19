package com.cs2.veterinaria.app.adapters.rest.response;

public class OwnerResponse {
    private Long document;
    private String name;

    public OwnerResponse() {}

    public OwnerResponse(Long document, String name) {
        this.document = document;
        this.name = name;
    }

    public Long getDocument() { 
        return document; 
    }
    public void setDocument(Long document) { 
        this.document = document; 
    }
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }
}