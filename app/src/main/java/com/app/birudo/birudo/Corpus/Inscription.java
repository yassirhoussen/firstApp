package com.app.birudo.birudo.Corpus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.birudo.birudo.Corpus.Connection;
import com.app.birudo.birudo.Corpus.Corpus;
import com.app.birudo.birudo.Model.User;
import com.app.birudo.birudo.R;

public class Inscription extends ActionBarActivity {
    private EditText pseudo, login, email, password, confirmpassword;
    private Button validate = null;
    private String  s_pseudo, s_login, s_email, s_password,s_confirm;
    private Intent intentCorpus = null;

    private View.OnClickListener thisListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            s_pseudo = pseudo.getText().toString();
            s_login = login.getText().toString();
            s_email = email.getText().toString();
            s_password = password.getText().toString();
            s_confirm = confirmpassword.getText().toString();

            Toast toast =  new Toast(getApplicationContext());
            if (s_pseudo.isEmpty() || s_pseudo.equals("")){
                toast.makeText(getApplicationContext(), "Le champs pseudo est vide. Merci de le saisir.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (s_login.isEmpty() || s_login.equals("")){
                toast.makeText(getApplicationContext(), "Le champs login est vide. Merci de le saisir.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (s_email.isEmpty() || s_email.equals("")){
                toast.makeText(getApplicationContext(), "Le champs email est vide. Merci de le saisir.", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                if (isValidEmail(s_email) == false){
                    toast.makeText(getApplicationContext(), "L'e-mail saisie est incorrect. Merci de le resaisir dans le bon format.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if(s_password.isEmpty() || s_password.equals("")){
                toast.makeText(getApplicationContext(), "Le champs mot de passe est vide . Merci de le saisir.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(s_confirm.isEmpty() || s_confirm.equals("")){
                toast.makeText(getApplicationContext(), "Le champs confirmation mot de passe est vide . Merci de le saisir.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!s_password.equals(s_confirm))
            {
                toast.makeText(getApplicationContext(), "La confirmation du mot de passe et le mot de passe ne sont pas identique. Merci de les resaisir.", Toast.LENGTH_SHORT).show();
                return;
            }
            else {
                    User current = User.createUser(s_login, s_pseudo, s_email, s_password);
                    Connection.setUser(current);
                    toast.makeText(getApplicationContext(), "Votre inscription est faite, bienvenue sur Birudo.", Toast.LENGTH_SHORT).show();
                    startActivity(intentCorpus);
            }

        }
    };
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        pseudo = (EditText)findViewById(R.id.pseudoText);
        login = (EditText)findViewById(R.id.loginText);
        email = (EditText)findViewById(R.id.emailText);
        password = (EditText)findViewById(R.id.passwordText);
        confirmpassword = (EditText)findViewById(R.id.confirmPasswordText);
        validate = (Button)findViewById(R.id.validate);
        validate.setOnClickListener(thisListener);
        //initialisation du passage au corps de l'application apres validation de du formulaire
        intentCorpus = new Intent(this, Corpus.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.inscription, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

}
