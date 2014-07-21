package com.app.birudo.birudo.Corpus;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.birudo.birudo.Model.Departure;
import com.app.birudo.birudo.Model.EtapeJeux;
import com.app.birudo.birudo.Model.SampleGame;
import com.app.birudo.birudo.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MapsActivity extends Activity implements
        View.OnClickListener, /*OnMarkerClickListener,*/
        OnMyLocationChangeListener {

    //    private final int RQS_GooglePlayServices = 1;
    private GoogleMap myMap;
    private Circle myCircle;
    private LatLng userLocation = null;

    private boolean isGameStarted = false;

    private TextView indice = null;
    private Button help = null, valider = null;
    private EditText response = null;
    private Dialog customDialog = null;
    private SampleGame sample = null;

    private int countMarker = 0;

    private HashMap<Integer, MarkerOptions> userCheckpoint = new HashMap<Integer, MarkerOptions>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        this.sample = FragmentStartGame.getSampleGame();
        this.populateUserCheckPoint();

        this.indice   = (TextView) findViewById(R.id.indiceText);
        this.valider  = (Button)   findViewById(R.id.validateButton);
        this.help     = (Button)   findViewById(R.id.helpButton);
        this.response = (EditText) findViewById(R.id.reponseEdit);

        //remove focus from editText
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        this.valider.setOnClickListener(this);
        this.help.setOnClickListener(this);
        // Get a handle to the Map Fragment
        this.setUpMapIfNeeded();

//        FragmentManager myFragmentManager = getFragmentManager();
//        GoogleMap map = ((MapFragment) getFragmentManager()
//                .findFragmentById(R.id.map)).getMap();
//        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);


//        LatLng sydney = new LatLng(-33.867, 151.206);
//
//        map.setMyLocationEnabled(true);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//
//        map.addMarker(new MarkerOptions()
//                .title("Sydney")
//                .snippet("The most populous city in Australia.")
//                .position(sydney));
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

    @Override
    protected void onResume() {
        super.onResume();
        this.setUpMapIfNeeded();
    }

    // init the map and check if not null
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (myMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            FragmentManager myFragmentManager = getFragmentManager();
            myMap = ((MapFragment) myFragmentManager
                    .findFragmentById(R.id.map)).getMap();
            // add Map stuff
            myMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            myMap.getUiSettings().setZoomControlsEnabled(true);
            myMap.getUiSettings().setCompassEnabled(false);
            myMap.getUiSettings().setMyLocationButtonEnabled(true);
            myMap.getUiSettings().setRotateGesturesEnabled(true);
            myMap.getUiSettings().setScrollGesturesEnabled(true);
            myMap.getUiSettings().setTiltGesturesEnabled(true);
            myMap.getUiSettings().setZoomGesturesEnabled(true);
            myMap.setTrafficEnabled(false);
//            myMap.setOnMarkerClickListener(this);
            myMap.setOnMyLocationChangeListener(this);
            // add the first postion as departure
            this.markerBoucing(this.addDepartureMarker());

            // Check if we were successful in obtaining the map.
            if (myMap != null) {
                this.changeGameStartedStatus(true);
                setUpMap();
            }
        }
    }

    private void setUpMap() {
            this.help.setEnabled(true);
            this.response.setEnabled(true);
            this.help.setVisibility(View.VISIBLE);
            this.valider.setText("Valider");
            this.valider.setOnClickListener(this);
            this.help.setOnClickListener(this);
            this.indice.setText(this.sample.getEtapes().get(this.countMarker).getIndice());
//        myMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    // show the user's validate Checkpoint
    private void populateUserCheckPoint() {
        int i = 0;
        for(EtapeJeux etape : this.sample.getEtapes()){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("etapes" + i).
                        position(new LatLng(etape.getLatitude(), etape.getLongitude())).
                        draggable(false);
            userCheckpoint.put(Integer.valueOf(i), markerOptions);
            i++;
        }
        userCheckpoint.put(Integer.valueOf(i), new MarkerOptions()
                        .title("Arrival")
                        .position(new LatLng(
                                this.sample.getArrivee().getLatitude(),
                                this.sample.getArrivee().getLongitude()
                        ))
                        .draggable(false)
        );
    }

    private void showUserValidateCheckPoint(int currentPosition) {
        MarkerOptions current = this.userCheckpoint.get(currentPosition);
        myMap.addMarker(current);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpButton :
                String aide = this.sample.getSample().getEtapes().get(this.countMarker).getAide();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                // Add the buttons
                builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        dialog.dismiss();
                        dialog.cancel();
                    }
                });
                builder.setTitle("Aide...");
                builder.setCancelable(false);
                builder.setIcon(getResources().getDrawable(R.drawable.birudotransparent));
                builder.setMessage(aide);

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

                break;
            case R.id.validateButton :
                String t_reponse = this.response.getText().toString();
                EtapeJeux currentEtape = this.sample.getEtapes().get(this.countMarker);
                if (t_reponse.equalsIgnoreCase("jdhalf")) {
                    showResponses();
                }
                if (!t_reponse.equalsIgnoreCase(currentEtape.getReponse()))
                    Toast.makeText(getApplicationContext(), "Mauvaise réponse. ", Toast.LENGTH_LONG).show();
                else {
                    this.showUserValidateCheckPoint(this.countMarker);
                    this.countMarker++;
                    Toast.makeText(getApplicationContext(), "Bonne réponse. ", Toast.LENGTH_LONG).show();
                    this.indice.clearComposingText();
                    this.indice.setText(this.sample.getEtapes().get(countMarker).getIndice());
                }
                break;
        }
    }

    //get User Location change
    @Override
    public void onMyLocationChange(Location location) {
        userLocation = new LatLng(location.getLatitude(), location.getLongitude());
        double accuracy = location.getAccuracy();
        if (myCircle == null) {
            CircleOptions circleOptions = new CircleOptions()
                    .center(userLocation)   //set center
                    .radius(accuracy)   //set radius in meters
                    .fillColor(0x40ff0000)
                    .strokeColor(Color.BLUE)
                    .strokeWidth(5);

            myCircle = myMap.addCircle(circleOptions);
        } else {
            myCircle.setCenter(userLocation);
            myCircle.setRadius(accuracy);
        }
        myMap.animateCamera(CameraUpdateFactory.newLatLng(userLocation));
    }



    // make the marker boucing
    public boolean markerBoucing(final Marker marker) {
        final Handler handler = new Handler();
        final long startTime = SystemClock.uptimeMillis();
        final long duration = 2000;

        Projection proj = myMap.getProjection();
        final LatLng markerLatLng = marker.getPosition();
        Point startPoint = proj.toScreenLocation(markerLatLng);
        startPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - startTime;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * markerLatLng.longitude + (1 - t) * startLatLng.longitude;
                double lat = t * markerLatLng.latitude + (1 - t) * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
            }
        });
        return true; //have consumed the event
    }


    public Marker addDepartureMarker() {
        Departure dep = sample.getDepart();
        MarkerOptions depOption = new MarkerOptions();
        depOption.title("Departure Position").
                snippet(dep.getIndice()).
                position(new LatLng(dep.getLatitude(), dep.getLongitude())).
                draggable(false);
        return this.myMap.addMarker(depOption);
    }


    public void showResponses() {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i< this.sample.getEtapes().size(); i++){
            EtapeJeux jeux = this.sample.getEtapes().get(i);
            value.append("etape ").append(i).append(": ")
                 .append(jeux.getIndice())
                 .append("\n   =>  ")
                 .append(jeux.getReponse())
                    .append("\n\n");
        }
        value.append("Arrivee : ")
                .append(this.sample.getArrivee().getIndice())
                .append("\n\n");
        customDialog = new Dialog(this);
        customDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        customDialog.setContentView(R.layout.custom_dialog);
        customDialog.setCancelable(false);
        customDialog.setTitle("Birudo show all Responses..");

        //set up text
        TextView text = (TextView) customDialog.findViewById(R.id.TextView);
        text.setText("");
        text.setText(value.toString());

      //set up button
        Button button = (Button) customDialog.findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.cancel();
                customDialog.dismiss();
            }
        });
        //now that the dialog is set up, it's time to show it
        customDialog.show();
        customDialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.birudorosetransparent);
    }


    public void changeGameStartedStatus(boolean status) {
        this.isGameStarted = status;
    }
}
