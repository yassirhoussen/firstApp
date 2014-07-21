package com.app.birudo.birudo.Model;

import java.util.ArrayList;

public class EtapeJeux {

    private String indice = null;
    private String aide = null;
    private int nbReponse;
    private String reponse = "";
    private  double latitude;
    private double longitude;

    public EtapeJeux() {

    }

    public EtapeJeux(String indice, String aide,int nbReponse, String reponse, double latitude, double longitude){
        this.indice      = indice;
        this.aide        = aide;
        this.nbReponse   = nbReponse;
        this.reponse = reponse;
        this.latitude    = latitude;
        this.longitude   = longitude;
    }

    public static ArrayList<EtapeJeux> getSampleList() {
        listEtapes list = new listEtapes();
        return list.getListEtape();
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

    public int getNbReponse() {
        return nbReponse;
    }

    public void setNbReponse(int nbReponse) {
        this.nbReponse = nbReponse;
    }

    public String getReponse() {
        return this.reponse;
    }

    public void setReponse(String listReponse) {
        this.reponse = listReponse;
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
        sb.append("Etape Jeux : \n").
                append("indice : ").append(this.getIndice()).append("\n").
                append("aide : ").append(this.getAide()).append("\n").
                append("nbReponse : ").append(this.getNbReponse()).append("\n").
                append("Reponse : ").append(this.getReponse()).append("\n").
                append("latitude : ").append(this.getLatitude()).append("\n").
                append("longitude : ").append(this.getLongitude()).append("\n");
        return sb.toString();
    }
}

class listEtapes {

    private ArrayList<EtapeJeux> listEtape= null;

    public listEtapes() {
        this.listEtape = new ArrayList<EtapeJeux>();
        this.getSampleList();
    }

    public void getSampleList() {

        this.listEtape.add(new EtapeJeux(
                "Dieu me garde de quatre maisons,\nDe la taverne,\ndu Lombard,\nDe l'hospital et de la prison.\"\n" +
                        "Gabriel Meurier, Trésor des Sentences, xvie siècle.\n Le lieu est un clin d'oeil, au pays des volcans.",
                "Nous cherchons un restaurant representant une region dans une rue.",
                1,
                "auvergne",
                48.859102,
                2.349755
        ));

        this.listEtape.add(new EtapeJeux(
                "Étienne Marcel fait l'acquisition de la « Maison aux Piliers » au nom de la municipalité en juillet 1357. " +
                        "Ancienne place de Grève, elle est rebaptisée en 1982 en un espace réservé aux piétons.",
                "La maison de la ville",
                1,
                "hotel de ville",
                48.856540,
                2.351778
        ));

        this.listEtape.add(new EtapeJeux(
                "Anciennement appelé passerelle de Grève pendant ses deux premières années, son nom actuelle viens d'une bataille " +
                        "remportée par Napoléon Bonaparte sur les Autrichiens en 1796. ",
                "Nous rechercons un pont",
                1,
                "pont d'arcole",
                48.855833,
                2.350833
        ));

        this.listEtape.add(new EtapeJeux(
                " Situé à une cinquantaine de mètres devant l'entrée de Notre-Dame. Elle est matérialisée dans les pavés du parvis de la cathédrale " +
                        "sous la forme d'une rose des vents gravée au centre d'un médaillon octogonal en bronze. ",
                "Point Zéro",
                1,
                "point zero",
                48.853402,
                2.348785
        ));

        this.listEtape.add(new EtapeJeux(
                "À cet emplacement se trouvait, au Moyen Âge, un terrain vague que l'on appelait : Motte aux Papelards ou simplement le Terrein, " +
                        "où s'égayait tout le personnel de l'église. En 2011, Woody Allen y tourna une partie d'une film Minuit à Paris. ",
                "un square",
                1,
                "square jean 23",
                48.8525,
                2.35139
        ));

        this.listEtape.add(new EtapeJeux(
                "Inauguré le 12 avril 1962 par le général de Gaulle. Œuvre de l'architecte Georges-Henri Pingusson. " +
                        "Annuellement, le dernier dimanche d'avril, le lieu accueille la Journée du souvenir des martyrs et des héros de la déportation. ",
                "un lieu de mémoire",
                1,
                "mémorial des martyrs de la deportation",
                48.851750,
                2.352378
        ));

        this.listEtape.add(new EtapeJeux(
                "Il portait en l'an XII le nom de quai Catinat et quelque temps après, il fut renommé en raison du voisinage avec l'ancien Archevêché de Paris. " +
                        "Ce lieu sépare le square Jean-XXIII (à l’ouest) du square de l’Île-de-France (à l’est).",
                "un quai",
                1,
                "Quai de l'Archevêché",
                48.852193,
                2.351976
        ));

        this.listEtape.add(new EtapeJeux(
                " Construit par Christophe Marie et achevé en 1634,Il franchissait la Seine en oblique, dans l’axe du quai d’Orléans à la rue des Ursins. " +
                        "Endommagé par la débâcle de 1709, il fut détruit en 1710. Apres plusieurs constructions et destructions, en 1968,il voit le jour, et est inauguré en 1970.",
                "un pont enjambant la seine",
                1,
                "Pont Saint-Louis",
                48.852778,
                2.352222
        ));

        this.listEtape.add(new EtapeJeux(
                "Ouverte vers 1635-1636 par Claude Le Ragois de Bretonvilliers, propriétaire des terrains attenants, qui y fait construire son hôtel particulier sur les plans de Jean Androuet du Cerceau. " +
                        "Il sera détruit en 1874 pour la construction du pont de Sully. Cette rue présente la particularité d'avoir un porche à une extrémité lui permettant ainsi de communiquer avec la rue Saint-Louis-en-l'Île. " +
                        "Ce porche est le pavillon, seul bâtiment ayant été conservé de l'Hôtel de Bretonvilliers. ",
                "un element a l'extremité de la rue",
                1,
                "le porche",
                48.850888,
                2.358984
        ));

        this.listEtape.add(new EtapeJeux(
                "Situé sur la pointe amont de l'île Saint-Louis. D'une forme triangulaire, il est délimité sur ses côtés sud et nord-est par " +
                        "les bras de la Seine, et sur son côté nord-ouest par le boulevard Henri-IV. " +
                        "En son milieu un momument de Laurent Marqueste, est inauguré le 18 juin 1894",
                "un monument a la gloire du sculpteur",
                1,
                "Square Barye",
                48.849972,
                2.359778
        ));

        this.listEtape.add(new EtapeJeux(
                "L'edifice construit de 1614 à 1635, par l'ingénieur-entrepreneur Christophe Marie, en fait l'un des plus anciens Paris.",
                "un pont",
                1,
                "Pont Marie",
                48.852778,
                2.3575
        ));

        this.listEtape.add(new EtapeJeux(
                "édifice religieux construit au xviie siècle par les architectes jésuites Étienne Martellange et François Derand, sur ordre de Louis XIII. " +
                        "La première pierre de est posée par le Cardinal de Richelieu en 1627 pour la Maison Professe que les Jésuites occupent à proximité.",
                "un edifice religieux",
                1,
                "Église Saint-Paul",
                48.8546,
                2.361417
        ));

        this.listEtape.add(new EtapeJeux(
                "Cette rue existe déjà au xiiie siècle sous le nom de « rue de Percée », nom encore gravé sur les murs à " +
                        "chacune de ses extrémités. Son nom actuel lui est donné en 1877 en souvenir du bâtisseur de la Bastille.",
                "le nom de cette rue",
                1,
                "rue du prevot",
                48.855199 ,
                2.360046
        ));

        this.listEtape.add(new EtapeJeux(
                "Hôtel particulier, édifié à partir de 1655 dans le quartier du Marais. Il est classé au titre des monuments historiques en 1966. " +
                        "Restauré, sous la direction de Bernard Fonquernie architecte en chef des monuments historiques, il accueille depuis 2004 la Cour administrative d’appel de Paris.",
                "Le nom de l\'edifice",
                1,
                "Hôtel de Beauvais, hotel de Beauvais",
                48.855194,
                2.3583
        ));

        this.listEtape.add(new EtapeJeux(
                " Bâtie sur les fondations du premier bâtiment connu rive droite à Paris, à savoir une basilique dont on trouve " +
                        "l'existence dès la fin du IVe siècle, elle constitue de ce fait la plus ancienne paroisse " +
                        "sur la rive droite de la Seine. Commencée en 1494, s'est déroulée sur une période de 150 ans environ.",
                "un edifice religieux",
                1,
                "Eglise Saint-Gervais",
                48.855556,
                2.354444
        ));
    }

    public ArrayList<EtapeJeux> getListEtape() {
        return listEtape;
    }

    public void setListEtape(ArrayList<EtapeJeux> listEtape) {
        this.listEtape = listEtape;
    }
}