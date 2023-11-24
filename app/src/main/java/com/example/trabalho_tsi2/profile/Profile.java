package com.example.trabalho_tsi2.profile;

public class Profile {
    private String fullName;
    private String rga;
    private Double balance;

    public Profile() {}

    public Profile(String fullName, String rga, Double balance) {
        this.fullName = fullName;
        this.rga = rga;
        this.balance = balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRga() {
        return rga;
    }

    public void setRga(String rga) {
        this.rga = rga;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
