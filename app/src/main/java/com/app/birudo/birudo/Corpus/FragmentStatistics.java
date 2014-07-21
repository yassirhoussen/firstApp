package com.app.birudo.birudo.Corpus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.app.birudo.birudo.Model.User;
import com.app.birudo.birudo.R;

public class FragmentStatistics extends Fragment {

    public static final String ITEM_NAME = "itemName";
    private User currentUser = null;

    public FragmentStatistics() {

    }

    @SuppressLint("ValidFragment")
    public FragmentStatistics(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_statistics, container,
                false);

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
