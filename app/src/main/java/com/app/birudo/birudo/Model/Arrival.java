package com.app.birudo.birudo.Model;

public class Arrival {

    private String indice = null;
    private String aide = null;
    private double latitude;
    private double longitude;

    public Arrival () {

    }

    public Arrival (String indice, String aide, double latitude, double longitude) {
        this.indice = indice;
        this.aide = aide;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Arrival getSample() {
        return new Arrival( "Inauguré en 1977, il accueil plus de 5,3 millions visiteurs en 2012. Au sein du musée national d'Art moderne, centre de création industrielle (MNAM/CCI)," +
                " il conserve l'une des trois plus importantes collections d'art moderne et contemporain au monde.", "Centre national d’art et de culture", 48.860653, 2.352411);
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
        sb.append("Arrival : \n").
                append("indice : ").append(this.getIndice()).append("\n").
                append("aide : ").append(this.getAide()).append("\n").
                append("latitude : ").append(this.getLatitude()).append("\n").
                append("longitude : ").append(this.getLongitude()).append("\n");
        return sb.toString();
    }
}
