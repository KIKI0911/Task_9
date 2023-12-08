package com.user.register;

public class RegisterRequest {
    private Integer id;
    private String name;
    private String email;
    private Integer addressId;
    private Integer age;

    public RegisterRequest(Integer id, String name, String email, Integer addressId, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.addressId = addressId;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public Integer getAge() {
        return age;
    }
}
