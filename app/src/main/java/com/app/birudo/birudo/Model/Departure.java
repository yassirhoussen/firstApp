package com.app.birudo.birudo.Model;

/**
 * Created by lyos2210 on 09/07/14.
 */
public class Departure {

    private String indice = null;
    private String aide = null;
    private double latitude;
    private double longitude;

    public Departure() {

    }

    public Departure(String indice, String aide, double latitude, double longitude) {
        this.indice = indice;
        this.aide = aide;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Departure getSample() {
        return new Departure("Le point de départ est fixé à la place du Chatelet","",48.8589053803,2.34865278029);
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }

    public String getAide() {
        return aide;
    }

    public void setAide(String aide) {
        this.aide = aide;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Departure : \n").
                append("indice : ").append(this.getIndice()).append("\n").
                append("aide : ").append(this.getAide()).append("\n").
                append("latitude : ").append(this.getLatitude()).append("\n").
                append("longitude : ").append(this.getLongitude()).append("\n");
        return sb.toString();
    }
}
