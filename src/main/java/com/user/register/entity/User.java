package com.user.register.entity;

public class User {

    private Integer id;
    private String name;
    private String email;
    private Integer addressId;
    private Integer age;

    public User(Integer id, String name, String email, Integer addressId, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.addressId = addressId;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
