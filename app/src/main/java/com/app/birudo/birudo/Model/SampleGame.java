package com.app.birudo.birudo.Model;

import java.util.ArrayList;

/**
 * Created by lyos2210 on 09/07/14.
 */
public class SampleGame {

    private InfoJeu info = null;
    private Departure depart = null;
    private Arrival arrivee = null;
    private ArrayList<EtapeJeux> etapes = null;

    public SampleGame() {
        this.info = null;
        this.depart = null;
        this.arrivee = null;
        this.etapes = new ArrayList<EtapeJeux>();
    }

    private SampleGame(InfoJeu info, Departure depart, Arrival arrivee, ArrayList<EtapeJeux> etapes) {
        this.info = info;
        this.depart = depart;
        this.arrivee = arrivee;
        this.etapes = etapes;
    }

    public SampleGame getSample() {
        return new SampleGame(InfoJeu.getSample(), Departure.getSample(), Arrival.getSample(), EtapeJeux.getSampleList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(info.toString()).append("\n");
        sb.append(depart.toString()).append("\n");
        sb.append(arrivee.toString()).append("\n");
        for(int i  = 0; i< this.etapes.size(); i++)
                sb.append(this.etapes.get(i).toString()).append("\n");
        return sb.toString();
    }

    public InfoJeu getInfo() {
        return info;
    }

    public void setInfo(InfoJeu info) {
        this.info = info;
    }

    public Departure getDepart() {
        return depart;
    }

    public void setDepart(Departure depart) {
        this.depart = depart;
    }

    public Arrival getArrivee() {
        return arrivee;
    }

    public void setArrivee(Arrival arrivee) {
        this.arrivee = arrivee;
    }

    public ArrayList<EtapeJeux> getEtapes() {
        return etapes;
    }

    public void setEtapes(ArrayList<EtapeJeux> etapes) {
        this.etapes = etapes;
    }
}
