package com.app.birudo.birudo.Corpus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.birudo.birudo.Model.User;
import com.app.birudo.birudo.R;

public class Connection extends ActionBarActivity {

    private EditText password = null;
    private EditText login = null;
    private Button connection =  null;
    private Button lostPassword = null;
    private Button inscription = null;

    private Intent intentInscription = null;
    private Intent intentCorpus = null;
    private Intent intentLostPassword = null;

    private static User user = null;

    private static Connection _this = null;

    private View.OnClickListener thisListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.connection:
                    String s_login = login.getText().toString();
                    String s_pwd = password.getText().toString();
                    if(!s_login.isEmpty() && !s_pwd.isEmpty() &&
                            s_login.equals(_this.user.getLogin()) && s_pwd.equals(_this.user.getPassword())) {
                        Toast.makeText(getApplicationContext(), "Connection reussit !!!", Toast.LENGTH_SHORT).show();
                        startActivity(intentCorpus);
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Désolé, le login ou le mot de passe est vide ou ne correspond pas. Merci de recommencer.", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.lostPassword:
                    startActivity(intentLostPassword);
                    break;
                case R.id.inscription:
                    startActivity(intentInscription);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        login = (EditText)findViewById(R.id.login);
        password = (EditText)findViewById(R.id.password);
        connection = (Button)findViewById(R.id.connection);
        lostPassword = (Button) findViewById(R.id.lostPassword);
        inscription = (Button)findViewById(R.id.inscription);

        //initialisation du model USER
        user    = User.getUser();
        _this = this;

        //set button onclickListener Event
        connection.setOnClickListener(thisListener);
        lostPassword.setOnClickListener(thisListener);
        inscription.setOnClickListener(thisListener);

        //create Intent for navigation
        this.intentInscription = new Intent(this, Inscription.class);
        this.intentCorpus = new Intent(this, Corpus.class);
        this.intentLostPassword = new Intent(this, LostPassword.class);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.connection, menu);
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

    public static User getUser() {
        return _this.user;
    }
    public static void setUser(User user) {
        _this.user = user;
    }
}
