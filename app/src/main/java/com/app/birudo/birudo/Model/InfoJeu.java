package com.app.birudo.birudo.Model;

/**
 * Created by lyos2210 on 09/07/14.
 */
public class InfoJeu {

    private String nomGame = null;
    private String dateDebut = null;
    private String dateFin = null;
    private String description = null;

    public InfoJeu() {

    }

    public InfoJeu(String nomGame, String dateDebut, String dateFin, String description) {
        this.nomGame     = nomGame;
        this.dateDebut   = dateDebut;
        this.dateFin     = dateFin;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("nom : ").append(this.getNomGame()).append("\n");
        builder.append("debut : ").append(this.getDateDebut()).append("\n");
        builder.append("fin : ").append(this.getDateFin()).append("\n");
        builder.append("desc : ").append(this.getDescription()).append("\n");
        return builder.toString();
    }

    // getter and setters
    public String getNomGame() {
        return nomGame;
    }

    public void setNomGame(String nomGame) {
        this.nomGame = nomGame;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //sample test game
    public static InfoJeu getSample() {
        return new InfoJeu("Une visite a Paris",
                "02/07/2014",
                "30/07/2014",
                "Un petite balade insolite a Paris sous forme de jeu, qui vous permettra de decouvrir des lieux insolites.\n Cette visite se deroulera dans les environs de Chatelet, l\'Ile de Saint-Louis et L'ile de la cité."
        );
    }
}
