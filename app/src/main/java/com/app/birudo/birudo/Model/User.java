package com.app.birudo.birudo.Model;

import java.text.SimpleDateFormat;

/**
 * Created by lyos2210 on 02/07/14.
 */

public class User {

    private String nom;
    private String prenom;
    private String login;
    private String dateNaissance;
    private String villeNaissance;
    private String pseudo;
    private String email;
    private String password;
    private String civilite;
    private String addresse;
    private String zipCode;
    private String addressVille;
    private String Description;
    private String st_lastGameName, st_winGameName, st_winGamedate, st_lastGameDate;
    private int nbJeuxScena,nbJeuxJoueur, nbJeuxInter;
    private int score;
    private String dateInscription;
    private boolean isNewUser;

    public User() {

    }

    private User(String nom, String prenom, String login,  String dateNaissance,String villeNaissance, String pseudo, String email, String password, String civilite, String addresse, String zipCode, String ville, String Description,
            String st_lastGameName, String st_winGameName, String st_winGamedate, String st_lastGameDate,
            int nbJeuxScena, int nbJeuxJoueur, int nbJeuxInter, int score, String dateInscription, boolean isNewUser) {
            this.nom = nom;
            this.prenom = prenom;
            this.login = login;
            this.dateNaissance = dateNaissance;
            this.villeNaissance = villeNaissance;
            this.pseudo = pseudo;
            this.email = email;
            this.password = password;
            this.civilite = civilite;
            this.addresse = addresse;
            this.zipCode = zipCode;
            this.addressVille = ville;
            this.Description = Description;
            this.st_lastGameName = st_lastGameName;
            this.st_winGameName = st_winGameName;
            this.st_winGamedate = st_winGamedate;
            this.st_lastGameDate = st_lastGameDate;
            this.nbJeuxScena = nbJeuxScena;
            this.nbJeuxJoueur = nbJeuxJoueur;
            this.nbJeuxInter = nbJeuxInter;
            this.score = score;
            this.dateInscription = dateInscription;
            this.isNewUser = isNewUser;
    }

    public static User getUser () {
        return new User("Doe", "John","johndoe", "24/07/1988","San Fransisco", "johndoe", "john.doe@mycompany.com", "johndoe", "mr",
                    "235 Montgomery St", "CA 94104", "San Francisco, US", "Cupcake ipsum dolor sit amet pastry chocolate bar I love. Fruitcake sweet roll I love jelly-o lemon drops. Dragée gummi bears pie pudding. Cupcake donut apple pie pastry cookie pie. Tart pudding croissant soufflé croissant. Pudding powder macaroon.",
                    "Chasse au tresor", "None", "None", "23/06/2014", 0, 2, 0, 2000, "20/06/2014", false);
    }

    public static User createUser(String login, String pseudo, String email, String password) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new java.util.Date());
        return new User("", "", login,  "","", pseudo, email, password,
                "", "", "", "", "","", "", "", "",
                0, 0, 0, 0, currentDate ,true);
    }

    // getter and setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddressVille() {
        return addressVille;
    }

    public void setAddressVille(String ville) {
        addressVille = ville;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLastGameName() {
        return st_lastGameName;
    }

    public void setLastGameName(String st_lastGameName) {
        this.st_lastGameName = st_lastGameName;
    }

    public String getWinGameName() {
        return st_winGameName;
    }

    public void setWinGameName(String st_winGameName) {
        this.st_winGameName = st_winGameName;
    }

    public String getWinGameDate() {
        return st_winGamedate;
    }

    public void setWinGameDate(String st_winGamedate) {
        this.st_winGamedate = st_winGamedate;
    }

    public String getLastGameDate() {
        return st_lastGameDate;
    }

    public void setLastGameDate(String getSt_lastGameDate) {
        this.st_lastGameDate = getSt_lastGameDate;
    }

    public int getNbJeuxScenariste() {
        return nbJeuxScena;
    }

    public void setNbJeuxScenarisite(int nbJeuxScena) {
        this.nbJeuxScena = nbJeuxScena;
    }

    public int getNbJeuxJoueur() {
        return nbJeuxJoueur;
    }

    public void setNbJeuxJoueur(int nbJeuxJoueur) {
        this.nbJeuxJoueur = nbJeuxJoueur;
    }

    public int getNbJeuxIntervenant() {
        return nbJeuxInter;
    }

    public void setNbJeuxIntervenant(int nbJeuxInter) {
        this.nbJeuxInter = nbJeuxInter;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(String dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSt_lastGameName() {
        return st_lastGameName;
    }

    public void setSt_lastGameName(String st_lastGameName) {
        this.st_lastGameName = st_lastGameName;
    }

    public String getSt_winGameName() {
        return st_winGameName;
    }

    public void setSt_winGameName(String st_winGameName) {
        this.st_winGameName = st_winGameName;
    }

    public String getSt_winGamedate() {
        return st_winGamedate;
    }

    public void setSt_winGamedate(String st_winGamedate) {
        this.st_winGamedate = st_winGamedate;
    }

    public String getSt_lastGameDate() {
        return st_lastGameDate;
    }

    public void setSt_lastGameDate(String st_lastGameDate) {
        this.st_lastGameDate = st_lastGameDate;
    }

    public int getNbJeuxScena() {
        return nbJeuxScena;
    }

    public void setNbJeuxScena(int nbJeuxScena) {
        this.nbJeuxScena = nbJeuxScena;
    }

    public int getNbJeuxInter() {
        return nbJeuxInter;
    }

    public void setNbJeuxInter(int nbJeuxInter) {
        this.nbJeuxInter = nbJeuxInter;
    }

    public boolean isNewUser() {
        return isNewUser;
    }

    public void setNewUser(boolean isNewUser) {
        this.isNewUser = isNewUser;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }
}



