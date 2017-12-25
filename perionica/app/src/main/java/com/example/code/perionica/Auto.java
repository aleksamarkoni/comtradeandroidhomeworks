package com.example.code.perionica;

/**
 * Created by CODE on 25-Dec-17.
 */

public class Auto {
    private String Marka;
    private String Registracija;
    private int Boja;
    private int Cena;

    public Auto(String marka, String registracija, int boja, int cena) {
        Marka = marka;
        Registracija = registracija;
        Boja = boja;
        Cena = cena;
    }

    public String getMarka() {
        return Marka;
    }



    public String getRegistracija() {
        return Registracija;
    }



    public int getBoja() {
        return Boja;
    }



    public int getCena() {
        return Cena;
    }


}
