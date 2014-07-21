package com.app.birudo.birudo.Corpus;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.birudo.birudo.R;

public class LostPassword extends ActionBarActivity {
    private EditText email = null;
    private Button validate = null, returnConnection = null;
    private Intent intentConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_password);

        email = (EditText)findViewById(R.id.dialogEmail);
        validate = (Button)findViewById(R.id.validate);
        returnConnection = (Button)findViewById(R.id.returnConnection);
        intentConnection = new Intent(this, Connection.class);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String dialogtextEmail = email.getText().toString();
                    if (dialogtextEmail.isEmpty() || dialogtextEmail.equals("")) {
                        Toast.makeText(getApplicationContext(), "Aucune adresse e-mail n'a été saisie. Merci de recommencer", Toast.LENGTH_SHORT).show();
                    } else {
                        if (isValidEmail(dialogtextEmail) == false) {
                            Toast.makeText(getApplicationContext(), "Format adresse e-mail saisie incorrect. Merci de recommencer", Toast.LENGTH_SHORT).show();
                        } else {
                            // a rempalcer par le webservice de regeneration de mail
                            if (dialogtextEmail.equals("test@test.fr")) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Un nouveau mot de passe sera envoyé a "+ dialogtextEmail).
                                        append(dialogtextEmail).append(" . Vous allez etre rediriger vers la page de connexion dans quelques instants.");
                                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                                new CountDownTimer(1500, 1000) {
                                    public void onTick(long millisUntilFinished) {
                                        ;
                                    }
                                    public void onFinish() {
                                        startActivity(intentConnection);
                                    }
                                }.start();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Adresse e-mail saisie incorrect. Merci de recommencer", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }

            }
        });

        returnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Retour à la page de connection.", Toast.LENGTH_SHORT).show();
                startActivity(intentConnection);
                }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lost_password, menu);
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
