package com.app.birudo.birudo.Corpus;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.birudo.birudo.Model.SampleGame;
import com.app.birudo.birudo.Model.User;
import com.app.birudo.birudo.R;


public class FragmentStartGame extends Fragment {

    public static final String ITEM_NAME = "itemName";
    private Button connection = null;
    private Button connection2 = null;
    private TextView gameName = null, gamePresentation = null, gameEnd = null;

    private static SampleGame sampleGame = null;

    private User currentUser = null;
    private Intent intentMaps = null;

    public FragmentStartGame() {
        this.currentUser = Connection.getUser();
        FragmentStartGame.sampleGame = new SampleGame().getSample();
    }

    private View.OnClickListener currentListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.connection) {
                getActivity().startActivity(new Intent(getActivity(), MapsActivity.class));
//                Fragment Maps = new Maps();
//                FragmentTransaction transaction= getActivity().getFragmentManager().beginTransaction();
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack
//                transaction.replace(R.id.content_frame, Maps);
//                transaction.addToBackStack(null);
//                // Commit the transaction
//                transaction.commit();
            }
            else if (v.getId() == R.id.connection2) {
                Toast.makeText(getActivity().getApplicationContext(), "Not yet implemented but just a dummy exemple", Toast.LENGTH_LONG)
                        .show();
//                getActivity().startActivity(new Intent(getActivity(), Test.class));
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_start_game, container,
                false);
        //init view element
        gameName           = (TextView) view.findViewById(R.id.Game1Name);
        gamePresentation   = (TextView) view.findViewById(R.id.Game1DescContent);
        gameEnd            = (TextView) view.findViewById(R.id.Game1StartDate);
        connection         = (Button) view.findViewById(R.id.connection);
        connection2        = (Button) view.findViewById(R.id.connection2);

        // set value
        this.gameName.setText("");
        this.gameName.setText(sampleGame.getInfo().getNomGame());
        this.gamePresentation.setText("");
        this.gamePresentation.setText(sampleGame.getInfo().getDescription());
        Toast.makeText(getActivity().getApplicationContext(), this.gamePresentation.getText(),Toast.LENGTH_LONG);
        this.gameEnd.setText("");
        this.gameEnd.setText("Date de fin : " + sampleGame.getInfo().getDateFin());

        connection.setOnClickListener(currentListener);
        connection2.setOnClickListener(currentListener);
        return view;
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

    public static SampleGame getSampleGame() {
        return sampleGame;
    }

    public static void setSampleGame(SampleGame sampleGame) {
        FragmentStartGame.sampleGame = sampleGame;
    }

}

