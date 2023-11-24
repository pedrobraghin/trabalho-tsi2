package com.example.trabalho_tsi2.profile;

public class Profile {
    private String fullName;
    private String rga;
    private Double saldo;

    public Profile() {}

    public Profile(String fullName, String rga, Double saldo) {
        this.fullName = fullName;
        this.rga = rga;
        this.saldo = saldo;
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

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}
