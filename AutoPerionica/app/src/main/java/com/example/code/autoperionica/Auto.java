package com.example.code.autoperionica;

public class Auto {
    private String marka;
    private String registracija;
    private int cenaPranja;
    private int boja;

    public Auto(String marka, String registracija, int cenaPranja, int boja) {
        this.marka = marka;
        this.registracija = registracija;
        this.cenaPranja = cenaPranja;
        this.boja = boja;
    }

    public String getMarka() {
        return marka;
    }

    public String getRegistracija() {
        return registracija;
    }

    public int getCenaPranja() {
        return cenaPranja;
    }

    public int getBoja() {
        return boja;
    }
}
