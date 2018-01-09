package com.example.dell.autoperionica;


public class Auto {
    private String marka;
    private String registracija;
    private int cena;
    private int boja;

    public Auto(String marka, String registracija, int cena, int boja) {
        this.marka = marka;
        this.registracija = registracija;
        this.cena = cena;
        this.boja = boja;
    }

    public String getMarka() {
        return marka;
    }

    public String getRegistracija() {
        return registracija;
    }

    public int getCena() {
        return cena;
    }

    public int getBoja() {
        return boja;
    }
}
