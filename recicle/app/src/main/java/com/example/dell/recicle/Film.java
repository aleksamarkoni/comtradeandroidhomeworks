package com.example.dell.recicle;

public class Film {
    private String ime;
    private String zanr;
    private String godina;

    public Film(String ime, String zanr, String godina) {
        this.ime = ime;
        this.zanr = zanr;
        this.godina = godina;
    }

    public String getIme() {
        return ime;
    }

    public String getZanr() {
        return zanr;
    }

    public String getGodina() {
        return godina;
    }
}
