package com.cs2.veterinaria.app.adapters.rest.request;

public class OwnerRequest {
    private String name;
    private Long document;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getDocument() { return document; }
    public void setDocument(Long document) { this.document = document; }
}