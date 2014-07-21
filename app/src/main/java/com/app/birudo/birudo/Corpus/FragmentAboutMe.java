package com.app.birudo.birudo.Corpus;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.birudo.birudo.Model.User;
import com.app.birudo.birudo.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FragmentAboutMe extends Fragment {

    public static final String ITEM_NAME = "itemName";
    private User currentUser = null;
    private Button validate  = null;

    private EditText nom     = null,
            prenom  = null,
            pseudo  = null,
            email   = null,
            inscriptionDate = null,
            dateNaissance   = null,
            lieuNaissance   = null,
            addresse        = null,
            codePostale     = null,
            description     = null;

    private CheckBox ck_mr = null,
            ck_me = null,
            ck_in = null;

    private enum Civilite {
        Mr, mr, monsieur, Monsieur,
        mme, Mme, Madame, madame,
        mlle, Mlle, Mademoiselle, mademoiselle,
        Inc, inc, Inconnu, inconnu
    }


    private  View.OnClickListener thisListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkMr :
                    if ( ((CheckBox) v).isChecked()) {
                        ck_me.setChecked(false);
                        ck_in.setChecked(false);
                    }
                    break;
                case R.id.checkMme :
                    if ( ((CheckBox) v).isChecked()) {
                        ck_mr.setChecked(false);
                        ck_in.setChecked(false);
                    }
                    break;
                case R.id.checkUnknown :
                    if ( ((CheckBox) v).isChecked()) {
                        ck_me.setChecked(false);
                        ck_mr.setChecked(false);
                    }
                    break;
                case R.id.validate :
                    if (FragmentAboutMe.this.currentUser.isNewUser()) {
                        FragmentAboutMe.this.currentUser.setNom(nom.getText().toString());
                        FragmentAboutMe.this.currentUser.setNom(prenom.getText().toString());
                        String s_date = dateNaissance.getText().toString();
                        if (FragmentAboutMe.this.dateCheck(s_date)) {
                            FragmentAboutMe.this.currentUser.setDateNaissance(s_date);
                        }
                        else {
                            FragmentAboutMe.this.dateInvalidFormat();
                            break;
                        }
                        FragmentAboutMe.this.currentUser.setVilleNaissance(lieuNaissance.getText().toString());
                        FragmentAboutMe.this.currentUser.setAddresse(addresse.getText().toString());
                        FragmentAboutMe.this.currentUser.setAddressVille(codePostale.getText().toString());
                        FragmentAboutMe.this.currentUser.setDescription(description.getText().toString());
                        FragmentAboutMe.this.currentUser.setCivilite(FragmentAboutMe.this.checkCivilite());
                        FragmentAboutMe.this.currentUser.setNewUser(false);
                    }
            }
        }
    };

    private String msg = "All the informations we show you here, is all the informations you gave to us. " +
            "You can modify those informations directly on birudo web page.\n" +
            "If you are a new User, you can update your information for once here.";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment_about_me, container,
                false);

        TextView message      = (TextView) view.findViewById(R.id.Info);
        message.setText(msg);

        this.currentUser = Connection.getUser();

        nom     = (EditText) view.findViewById(R.id.NomEdit);
        prenom  = (EditText) view.findViewById(R.id.PrenomEdit);
        pseudo  = (EditText) view.findViewById(R.id.pseudoEdit);
        email   = (EditText) view.findViewById(R.id.emailEdit);
        inscriptionDate = (EditText) view.findViewById(R.id.inscriptionEdit);
        dateNaissance   = (EditText) view.findViewById(R.id.dateEdit);
        lieuNaissance   = (EditText) view.findViewById(R.id.birthCityEdit);
        addresse        = (EditText) view.findViewById(R.id.addressEdit);
        codePostale     = (EditText) view.findViewById(R.id.cpEdit);
        description     = (EditText) view.findViewById(R.id.descriptionEdit);

        ck_mr = (CheckBox) view.findViewById(R.id.checkMr);
        ck_me = (CheckBox) view.findViewById(R.id.checkMme);
        ck_in = (CheckBox) view.findViewById(R.id.checkUnknown);

        validate = (Button) view.findViewById(R.id.validate);
        validate.setEnabled(false);

        if (this.currentUser.isNewUser()) {
            validate.setOnClickListener(thisListener);
            this.validate.setEnabled(true);
            nom.setText("");
            prenom.setText("");
            pseudo.setText(this.currentUser.getPseudo());
            pseudo.setEnabled(false);
            inscriptionDate.setText(this.currentUser.getDateInscription());
            dateNaissance.setText("");
            lieuNaissance.setText("");
            email.setText(this.currentUser.getEmail());
            email.setEnabled(false);
            addresse.setText("");
            codePostale.setText("");
            description.setText("");
            ck_mr.setChecked(false);
            ck_me.setChecked(false);
            ck_in.setChecked(false);
        }
        else {
            nom.setText(this.currentUser.getNom());
            nom.setEnabled(false);
            prenom.setText(this.currentUser.getPrenom());
            prenom.setEnabled(false);
            pseudo.setText(this.currentUser.getPseudo());
            pseudo.setEnabled(false);
            inscriptionDate.setText(this.currentUser.getDateInscription());
            inscriptionDate.setEnabled(false);
            dateNaissance.setText(this.currentUser.getDateNaissance());
            dateNaissance.setEnabled(false);
            lieuNaissance.setText(this.currentUser.getVilleNaissance());
            lieuNaissance.setEnabled(false);
            email.setText(this.currentUser.getEmail());
            email.setEnabled(false);
            addresse.setText(this.currentUser.getAddresse());
            addresse.setEnabled(false);
            codePostale.setText(this.currentUser.getAddressVille());
            codePostale.setEnabled(false);
            description.setText(this.currentUser.getDescription());
            description.setEnabled(false);
            String civi = this.currentUser.getCivilite();
            Civilite civilite = Civilite.valueOf(civi);
            switch (civilite) {
                case Mr :case mr :case monsieur :case Monsieur :
                    ck_mr.setChecked(true);
                    ck_mr.setEnabled(false);
                    ck_me.setChecked(false);
                    ck_me.setEnabled(false);
                    ck_in.setChecked(false);
                    ck_in.setEnabled(false);
                    break;
                case Mlle : case mlle :case Mme  :case mme :case Mademoiselle :case mademoiselle : case Madame :case madame :
                    ck_mr.setChecked(false);
                    ck_mr.setEnabled(false);
                    ck_me.setChecked(true);
                    ck_in.setChecked(false);
                    ck_in.setEnabled(false);
                    break;
                case Inc :case inc :case Inconnu :case inconnu :
                    ck_mr.setChecked(false);
                    ck_mr.setEnabled(false);
                    ck_me.setChecked(false);
                    ck_me.setEnabled(false);
                    ck_in.setChecked(true);
                    break;
            }
        }

        ck_in.setOnClickListener(thisListener);
        ck_me.setOnClickListener(thisListener);
        ck_mr.setOnClickListener(thisListener);

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

    protected String dateInvalidFormat() {
        Toast.makeText(getActivity().getApplicationContext(), "Given birthday is in invalid format or invalid value", Toast.LENGTH_LONG)
                .show();
        return "NULL";
    }

    protected boolean dateCheck(String s ) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        try {
            d = sdf.parse(s);
            String t = sdf.format(d);
            if(t.compareTo(s) !=  0)
                return false;
            else
                return true;
        } catch (Exception e) {

        }
        return false;
    }

    protected String checkCivilite() {
        if (ck_mr.isChecked())
            return ck_mr.getText().toString();
        else if (ck_me.isChecked())
            return ck_me.getText().toString();
        else return ck_in.getText().toString();
    }


}

/**
public class FragmentAboutMe extends Fragment {

    public static final String ITEM_NAME = "itemName";
    private EditText nomEdit, prenomEdit, pseudoEdit, birthCity, emailEdit,
            addressEdit, cityEdit, descriptionEdit, dateEdit, inscriptionDate;
    private CheckBox checkMr, checkMme, checkInc;
    private Button validate = null;
    private ImageButton imgButton = null;
    private User currentUser = null;
    private Camera camera;
    private int cameraId = 0;

    private enum Civilite {
        Mr, mr, monsieur, Monsieur,
        mme, Mme, Madame, madame,
        mlle, Mlle, Mademoiselle, mademoiselle,
        Inc, inc, Inconnu, inconnu
    }


    private  View.OnClickListener thisListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.imageButton :
                    if (!getActivity().getPackageManager()
                            .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                        Toast.makeText(getActivity().getApplicationContext(), "No camera on this device", Toast.LENGTH_LONG)
                                .show();
                    } else {
                        cameraId = findFrontFacingCamera();
                        if (cameraId < 0) {
                            Toast.makeText(getActivity().getApplicationContext(), "No front facing camera found.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            camera = Camera.open(cameraId);
                            camera.takePicture(null, null,
                                    new PhotoHandler(getActivity().getApplicationContext()));
                        }
                    }
                    break;
                case R.id.checkMr :
                    if ( ((CheckBox) v).isChecked()) {
                        checkMme.setChecked(false);
                        checkInc.setChecked(false);
                    }
                    break;
                case R.id.checkMme :
                    if ( ((CheckBox) v).isChecked()) {
                        checkMr.setChecked(false);
                        checkInc.setChecked(false);
                    }
                    break;
                case R.id.checkUnknown :
                    if ( ((CheckBox) v).isChecked()) {
                        checkMr.setChecked(false);
                        checkMme.setChecked(false);
                    }
                    break;
                case R.id.validate :
                    if (Connection.getUser().isNewUser()) {
                        Connection.getUser().setNom(nomEdit.getText().toString());
                        Connection.getUser().setNom(prenomEdit.getText().toString());
                        Connection.getUser().setDateNaissance(dateEdit.getText().toString());
                        Connection.getUser().setVilleNaissance(birthCity.getText().toString());
                        Connection.getUser().setAddresse(addressEdit.getText().toString());
                        Connection.getUser().setAddressVille(cityEdit.getText().toString());
                        Connection.getUser().setDescription(descriptionEdit.getText().toString());
                        Connection.getUser().setCivilite(FragmentAboutMe.this.checkCivilite());
                    }
            }
        }
    };

    public FragmentAboutMe() {
        this.currentUser = Connection.getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_about_me, container,
                false);

        //init layout value
        nomEdit         = (EditText) view.findViewById(R.id.NomEdit);
        prenomEdit      = (EditText) view.findViewById(R.id.PrenomEdit);
        pseudoEdit      = (EditText) view.findViewById(R.id.pseudoEdit);
        inscriptionDate = (EditText) view.findViewById(R.id.inscriptionEdit);
        dateEdit        = (EditText) view.findViewById(R.id.dateEdit);
        birthCity       = (EditText) view.findViewById(R.id.birthCityEdit);
        emailEdit       = (EditText) view.findViewById(R.id.emailEdit);
        addressEdit     = (EditText) view.findViewById(R.id.addressEdit);
        cityEdit        = (EditText) view.findViewById(R.id.cpEdit);
        descriptionEdit = (EditText) view.findViewById(R.id.descriptionEdit);
        checkMr         = (CheckBox) view.findViewById(R.id.checkMr);
        checkMme        = (CheckBox) view.findViewById(R.id.checkMme);
        checkInc        = (CheckBox) view.findViewById(R.id.checkUnknown);
        imgButton       = (ImageButton) view.findViewById(R.id.imageButton);
        validate        = (Button) view.findViewById(R.id.validate);

        if (Connection.getUser().isNewUser()) {
            nomEdit.setText("");
            prenomEdit.setText("");
            pseudoEdit.setText(Connection.getUser().getPseudo());
            inscriptionDate.setText(Connection.getUser().getDateInscription());
            pseudoEdit.setEnabled(false);
            dateEdit.setText("");
            birthCity.setText("");
            emailEdit.setText(Connection.getUser().getEmail());
            emailEdit.setEnabled(false);
            addressEdit.setText("");
            cityEdit.setText("");
            descriptionEdit.setText("");
            checkMr.setChecked(false);
            checkMme.setChecked(false);
            checkInc.setChecked(false);
        }
        else {
            nomEdit.setText(Connection.getUser().getNom());
            nomEdit.setEnabled(false);
            prenomEdit.setText(Connection.getUser().getPrenom());
            prenomEdit.setEnabled(false);
            pseudoEdit.setText(Connection.getUser().getPseudo());
            pseudoEdit.setEnabled(false);
            inscriptionDate.setText(Connection.getUser().getDateInscription());
            inscriptionDate.setEnabled(false);
            dateEdit.setText(Connection.getUser().getDateNaissance());
            dateEdit.setEnabled(false);
            birthCity.setText(Connection.getUser().getVilleNaissance());
            birthCity.setEnabled(false);
            emailEdit.setText(Connection.getUser().getEmail());
            emailEdit.setEnabled(false);
            addressEdit.setText(Connection.getUser().getAddresse());
            addressEdit.setEnabled(false);
            cityEdit.setText(Connection.getUser().getAddressVille());
            cityEdit.setEnabled(false);
            descriptionEdit.setText(Connection.getUser().getDescription());
            descriptionEdit.setEnabled(false);
            String civi = Connection.getUser().getCivilite();
            Civilite civilite = Civilite.valueOf(civi);
            switch (civilite)
            {
                case Mr :case mr :case monsieur :case Monsieur :  checkMr.setChecked(true);
                                   checkMr.setEnabled(false);
                                   checkMme.setChecked(false);
                                   checkMme.setEnabled(false);
                                   checkInc.setChecked(false);
                                   checkInc.setEnabled(false);
                    break;
                case Mlle : case mlle :case Mme  :case mme :case Mademoiselle :case mademoiselle : case Madame :case madame :
                    checkMr.setChecked(false);
                    checkMr.setEnabled(false);
                    checkMme.setChecked(true);
                    checkInc.setChecked(false);
                    checkInc.setEnabled(false);
                    break;
                case Inc :case inc :case Inconnu :case inconnu :
                    checkMr.setChecked(false);
                    checkMr.setEnabled(false);
                    checkMme.setChecked(false);
                    checkMme.setEnabled(false);
                    checkInc.setChecked(true);
                    break;
            }
        }

        checkMr.setOnClickListener(thisListener);
        checkMme.setOnClickListener(thisListener);
        checkInc.setOnClickListener(thisListener);
        imgButton.setOnClickListener(thisListener);
        validate.setOnClickListener(thisListener);

        //
        return view;
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private String dateInvalidFormat() {
        Toast.makeText(getActivity().getApplicationContext(), "Given birthday is in invalid format or invalid value", Toast.LENGTH_LONG)
                .show();
        return "NULL";
    }

    private boolean dateCheck(String s ) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        try {
            d = sdf.parse(s);
            String t = sdf.format(d);
            if(t.compareTo(s) !=  0)
                return false;
            else
                return true;
        } catch (Exception e) {

        }
        return false;
    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private String emailInvalidFormat() {
        Toast.makeText(getActivity().getApplicationContext(), "Given email is in invalid format", Toast.LENGTH_LONG)
                .show();
        return "NULL";
    }

    private String checkCivilite() {
        if (checkMr.isChecked())
            return checkMr.getText().toString();
        else if (checkMme.isChecked())
            return checkMme.getText().toString();
        else return checkInc.getText().toString();
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

class PhotoHandler implements PictureCallback {

    private final Context context;
    private static String filename = null;

    public PhotoHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

        File pictureFileDir = getDir();
        if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
            Toast.makeText(context, "Can't create directory to save image.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        filename = pictureFileDir.getPath() + File.separator + photoFile;

        File pictureFile = new File(filename);

        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            fos.write(data);
            fos.close();
            Toast.makeText(context, "New Image saved:" + photoFile,
                    Toast.LENGTH_LONG).show();
        } catch (Exception error) {
            Toast.makeText(context, "Image could not be saved.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private File getDir() {
        File sdDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(sdDir, "BirudoImage");
    }

    public static String getFilename() {
        return filename;
    }

}

 **/
