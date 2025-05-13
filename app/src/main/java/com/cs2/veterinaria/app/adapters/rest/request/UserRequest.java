package com.cs2.veterinaria.app.adapters.rest.request;

public class UserRequest {
    private long personId;
    private long document;
    private String name;
    private int age;
    private long userId;
    private String userName;
    private String password;
    private String role;

    
    @Override
    public String toString() {
        return "UserRequest [personId=" + personId + ", document=" + document + ", name=" + name + ", age=" + age
                + ", userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
    }
    public long getPersonId() {
        return personId;
    }
    public void setPersonId(long personId) {
        this.personId = personId;
    }
    public long getDocument() {
        return document;
    }
    public void setDocument(long document) {
        this.document = document;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    
}
