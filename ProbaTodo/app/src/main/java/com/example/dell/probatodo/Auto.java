package com.example.dell.probatodo;


import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

public class Auto implements Parcelable {
    public String model;
    public String registracija;
    public boolean voskiranje;
    public ImageView slika;
    public boolean pranje;

    public Auto(String model) {
        this.model = model;
    }

    public Auto(String model, String registracija, boolean voskiranje, boolean pranje, ImageView slika) {
        this.model = model;
        this.registracija = registracija;
        this.voskiranje = voskiranje;
        this.slika = slika;
        this.pranje = pranje;
    }

    public Auto(String model, String registracija, boolean voskiranje, boolean pranje) {
        this.model = model;
        this.registracija = registracija;
        this.voskiranje = voskiranje;
        this.pranje = pranje;
    }

    public boolean isPranje() {
        return pranje;
    }

    public void setPranje(boolean pranje) {
        this.pranje = pranje;
    }

    public boolean isVoskiranje() {
        return voskiranje;
    }

    public void setVoskiranje(boolean voskiranje) {
        this.voskiranje = voskiranje;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public ImageView getSlika() {
        return slika;
    }

    public void setSlika(ImageView slika) {
        this.slika = slika;
    }


        protected Auto(Parcel in) {
            model = in.readString();
            registracija = in.readString();
            voskiranje = in.readByte() != 0x00;
            slika = (ImageView) in.readValue(ImageView.class.getClassLoader());
            pranje = in.readByte() != 0x00;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(model);
            dest.writeString(registracija);
            dest.writeByte((byte) (voskiranje ? 0x01 : 0x00));
            dest.writeValue(slika);
            dest.writeByte((byte) (pranje ? 0x01 : 0x00));
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<Auto> CREATOR = new Parcelable.Creator<Auto>() {
            @Override
            public Auto createFromParcel(Parcel in) {
                return new Auto(in);
            }

            @Override
            public Auto[] newArray(int size) {
                return new Auto[size];
            }
        };
    }

