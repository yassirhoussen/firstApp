package com.app.birudo.birudo.Network;

import android.util.Base64;

import com.app.birudo.birudo.Model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RequestBirudo {

    private final static String head_url = "http://ios:ios@birudo.cluster1.easy-hebergement.net/CI/index.php/restful/";
    private User currentUser = null;
    private final String USER_AGENT = "Mozilla/5.0";

    public RequestBirudo(User user){
        this.currentUser = user;
    }

    /**
     *  Encrypt in MD5
     * @param value String
     * @return String  encrypted value
     */
    public static final String md5Encrypt(String value) {
        try {
            MessageDigest digest = MessageDigest
                    .getInstance("MD5");
            digest.update(value.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * make a call to the url given in parameter
     * @param url String
     * @return String result of request
     * @throws Exception
     */
    private String requestBirudo(String url) throws Exception {
        RequestTask task = (RequestTask) new RequestTask().execute(url);
        return task.get();
    }

    /**
     * call the connection webservice. the return value contains :
     * if ok :
     *  - result 1
     *  - id
     *  - email
     *  - pseudo
     *  else
     *  - result 0
     *
     * @param login String
     * @param password String
     * @return String result
     * @throws Exception
     */
    public boolean callConnection(String login, String password) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(head_url).
                append("connexion/login/").
                append(new String(Base64.encode(login.getBytes("UTF-8"),Base64.DEFAULT)).replaceAll("[\r\n]+", "")).
                append("/password/").
                append(new String(Base64.encode(md5Encrypt(password).getBytes("UTF-8"),Base64.DEFAULT)).replaceAll("[\r\n]+", "")).
                append("/format/json");
        String result = this.sendGet(url.toString());
        if (!result.equals("{\"result\":0}")) {
//            currentUser.updateUserValue(result);
            return true;
        }
        return false;
    }

    /**
     * call the inscription webservice. the return value contains :
     *  if Ok
     *   - result 1
     *  else
     *   - result 0
     * @param pseudo String
     * @param login String
     * @param email String
     * @param password String
     * @return String result of request
     * @throws Exception
     */
    public boolean callInscription(String pseudo, String login, String email, String password) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(head_url).
                append("inscription/login/").append(new String(Base64.encode(login.getBytes("UTF-8"), Base64.DEFAULT)).replaceAll("[\r\n]+", ""))
                .append("/password/").append(new String(Base64.encode(md5Encrypt(password).getBytes("UTF-8"), Base64.DEFAULT)).replaceAll("[\r\n]+", ""))
                .append("/pseudo/").append(new String(Base64.encode(pseudo.getBytes("UTF-8"), Base64.DEFAULT)).replaceAll("[\r\n]+", ""))
                .append("/email/").append(new String(Base64.encode(email.getBytes("UTF-8"), Base64.DEFAULT)).replaceAll("[\r\n]+", ""))
                .append("/format/json");
        String result = requestBirudo(url.toString());
        if (!result.equals("{\"result\":0,\"error\":\"login\"}")) {
//            currentUser.updateUserValue(result);
            return true;
        }
        return false;
    }

    /**
     * get user account value webservice. the return value contains :
     *  if Ok
     *   - result 1
     *  else
     *   - result 0
     * @param id String
     * @return String result of request
     * @throws Exception
     */
    public void getUserAccount(String id) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(head_url).
                append("compte_user/id/").
                append(Base64.encode(id.getBytes("UTF-8"),Base64.DEFAULT))
                .append("/format/json");
        String result = requestBirudo(url.toString());
//        currentUser.updateUserValue(result);
    }

    /**
     * get user historical by calling webservice. the result contains :
     * if OK :
     *  - user_id : l'id d'in joueur
     *  - team_id : l'id de son équipe (si defini, si non initialisé à: - 1)
     *  - name_team : le nom de son équipe (si défini, sinon initialisé à: NULL)
     *  - game_id : l'id d'un jeu
     *  - game_name : le nm d'un jeu
     *  - scenarist_id : l'id d'n scénariste
     *  - date_crea_jeu : la date de création d'un jeu
     *  - date_debut_jeu : la date de début d'un jeu
     *  - date_fin_jeu : la date de fin d'un jeu
     *  - score : le nombre de point gagné sur le jeu. (varchar 45, initialisé à "jeu en cours" ou "en attente de lancement"
     *... suivant la date de début du jeu.)
     * else
     *   - id
     *   - result
     *   - error
     * @param id String
     * @return String result of request
     * @throws Exception
     */
    public void getUserHistoric(String id) throws Exception {
        StringBuilder url = new StringBuilder();
        url.append(head_url).
                append("historicplayer/id/").
                append(Base64.encode(id.getBytes("UTF-8"),Base64.DEFAULT)).
                append("/format/json");
        String result = requestBirudo(url.toString());
        if (!result.equals("{\"id\":null,\"result\":0,\"error\":\"Aucun jeu disponible\""))
            //
            ;
//            currentUser.updateUserValue(result);
    }


    // HTTP GET request
    private String sendGet(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");
        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        //print result
        return response.toString();

    }
}
