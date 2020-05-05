package com.example.intercareapp;

public class Organization {
    private String name;
    private String address;
    private String email;
    private int rating;
    private String[] treatments;

    public Organization(String name, String address, String email, int rating, String[] treatments) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.rating = rating;
        this.treatments = treatments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String[] getTreatments() {
        return treatments;
    }

    public void setTreatments(String[] treatments) {
        this.treatments = treatments;
    }
}
