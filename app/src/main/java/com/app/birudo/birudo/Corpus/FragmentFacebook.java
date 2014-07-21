package com.app.birudo.birudo.Corpus;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.birudo.birudo.R;

import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;

public class FragmentFacebook extends Fragment {

    public static final String ITEM_NAME = "itemName";
    private SocialAuthAdapter adapter;
    boolean status;

    // Android Components
    private Button update;
    private EditText edit;
    private View view = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_facebook, container,
                false);
        TextView textMessage = (TextView) view.findViewById(R.id.message);
        textMessage.setText("Welcome to this Birudo Social page. You can share here message on Facebook and Twitter");

        LinearLayout bar = (LinearLayout) view.findViewById(R.id.linearbar);
        bar.setBackgroundResource(R.drawable.bar_gradient);

        adapter = new SocialAuthAdapter(new ResponseListener());

        // Please note : Update status functionality is only supported by
        // Facebook, Twitter, Linkedin, MySpace, Yahoo and Yammer.

        // Add providers
        adapter.addProvider(SocialAuthAdapter.Provider.FACEBOOK, R.drawable.facebook);
//        adapter.addProvider(SocialAuthAdapter.Provider.TWITTER, R.drawable.twitter);

        // For twitter use add callback method. Put your own callback url here.
//        adapter.addCallBack(SocialAuthAdapter.Provider.TWITTER, "http://socialauth.in/socialauthdemo/socialAuthSuccessAction.do");

        // Add keys and Secrets
        try {
            adapter.addConfig(SocialAuthAdapter.Provider.FACEBOOK, "324813061005144", "7d86017b4d8a45d8cbee337bb792a2aa", null);
//            adapter.addConfig(SocialAuthAdapter.Provider.TWITTER, "RRkXv4F3JWxPePGg6LEsw0c4Y", "prI9sqDdoJMmtrTEnuWediTFfWlXCVs0ACYeILuAfl7GOfcEjI",
//                    null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter.enable(bar);

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

    private final class ResponseListener implements DialogListener {
        @Override
        public void onComplete(Bundle values) {

            // Variable to receive message status
            Log.d("FragmentFacebook", "Authentication Successful");

            // Get name of provider after authentication
            final String providerName = values.getString(SocialAuthAdapter.PROVIDER);
            Log.d("FragmentFacebook", "Provider Name = " + providerName);
            Toast.makeText(getActivity().getApplicationContext(), providerName + " connected", Toast.LENGTH_SHORT).show();

            update = (Button) view.findViewById(R.id.update);
            edit = (EditText) view.findViewById(R.id.editTxt);

            // Please avoid sending duplicate message. Social Media Providers
            // block duplicate messages.

            update.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // Call updateStatus to share message via oAuth providers
                    // adapter.updateStatus(edit.getText().toString(), new
                    // MessageListener(), false);

                    // call to update on all connected providers at once
                    adapter.updateStatus(edit.getText().toString(), new MessageListener(), true);
                }
            });
            }

        @Override
        public void onError(SocialAuthError socialAuthError) {
            socialAuthError.printStackTrace();
            Log.d("Share-Bar", socialAuthError.getMessage());
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onBack() {

        }
    }

    // To get status of message after authentication
    private final class MessageListener implements SocialAuthListener<Integer> {
        @Override
        public void onExecute(String provider, Integer t) {
            Integer status = t;
            if (status.intValue() == 200 || status.intValue() == 201 || status.intValue() == 204)
                Toast.makeText(getActivity().getApplicationContext(), "Message posted on " + provider, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity().getApplicationContext(), "Message not posted on" + provider, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SocialAuthError e) {

        }
    }

}