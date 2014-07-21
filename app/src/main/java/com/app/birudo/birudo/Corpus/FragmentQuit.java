package com.app.birudo.birudo.Corpus;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.app.birudo.birudo.R;

public class FragmentQuit extends Fragment {

    public static final String ITEM_NAME = "itemName";
    Switch switchButton = null;
    TextView message = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_quit, container,
                false);
        switchButton = (Switch) view.findViewById(R.id.switchQuit);
        switchButton.setTextOff("Non");
        switchButton.setTextColor(getResources().getColor(R.color.BirudoGreen2));
        switchButton.setTextOn("Oui");
        switchButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getActivity(), "Merci et à bientôt! ", Toast.LENGTH_SHORT).show();
//                    android.os.Process.killProcess(android.os.Process.myPid());
//                    moveTaskToBack(true);
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                }

            }
        });
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

}
