package com.app.birudo.birudo.Corpus;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.birudo.birudo.GPS.GPSTracker;
import com.app.birudo.birudo.Model.User;
import com.app.birudo.birudo.Network.RequestBirudo;
import com.app.birudo.birudo.R;

import java.util.ArrayList;
import java.util.List;

public class Corpus extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;
    private User currentUser = null;
    private RequestBirudo network = null;
    private static GPSTracker gps = null;
    List<DrawerItem> dataList;

//    private static Corpus _this = new Corpus();
//    private String[] drawerListViewItems;
//    private DrawerLayout drawerLayout;
//    private ListView drawerListView;
//    private ActionBarDrawerToggle actionBarDrawerToggle;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corpus);
        // Initializing
        this.currentUser = Connection.getUser();
        Corpus.gps         = new GPSTracker(this.getApplicationContext());
        Corpus.gps.getLocation();
        dataList = new ArrayList<DrawerItem>();

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        // Add Drawer Item to dataList
        String [] t_value = getResources().getStringArray(R.array.items);
        for (String value : t_value)
            dataList.add(new DrawerItem(value));

        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }
    }


    public void SelectItem(int position) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (position) {
            case 0: //about me
                fragment = new FragmentAboutMe();
                args.putString(FragmentAboutMe.ITEM_NAME, dataList.get(position)
                        .getItemName());
//                args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
//                        .getImgResID());
                break;
//            case 1: // Statistics
//                fragment = new FragmentStatistics(this.currentUser);
//                args.putString(FragmentStatistics.ITEM_NAME, dataList.get(position)
//                        .getItemName());
            case 1: // Facebook
                fragment = new FragmentFacebook();
                args.putString(FragmentStartGame.ITEM_NAME, dataList.get(position)
                        .getItemName());
                break;
            case 2: //Start game
                fragment = new FragmentStartGame();
                args.putString(FragmentStartGame.ITEM_NAME, dataList.get(position)
                        .getItemName());
                break;
            case 3:
                fragment = new FragmentQuit();
                args.putString(FragmentQuit.ITEM_NAME, dataList.get(position)
                        .getItemName());
                break;
            default:
                break;
        }
        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(dataList.get(position).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public RequestBirudo getNetwork() {
        return this.network;
    }

    public void setNetwork(RequestBirudo network) {
        this.network = network;
    }

    public static GPSTracker getGpsTracker() {
        return Corpus.gps;
    }

}


class CustomDrawerAdapter extends ArrayAdapter<DrawerItem> {

    Context context;
    List<DrawerItem> drawerItemList;
    int layoutResID;

    public CustomDrawerAdapter(Context context, int layoutResourceID,
                               List<DrawerItem> listItems) {
        super(context, layoutResourceID, listItems);
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DrawerItemHolder drawerHolder;
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            drawerHolder = new DrawerItemHolder();

            view = inflater.inflate(layoutResID, parent, false);
            drawerHolder.ItemName = (TextView) view
                    .findViewById(R.id.drawer_itemName);
//            drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

            view.setTag(drawerHolder);

        } else {
            drawerHolder = (DrawerItemHolder) view.getTag();

        }

        DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);

//        drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
//                dItem.getImgResID()));
        drawerHolder.ItemName.setText(dItem.getItemName());

        return view;
    }

    private static class DrawerItemHolder {
        TextView ItemName;
        ImageView icon;
    }
}


class DrawerItem {

    String ItemName;
//    int imgResID;

    public DrawerItem(String itemName /*, int imgResID*/) {
        super();
        ItemName = itemName;
//        this.imgResID = imgResID;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
//    public int getImgResID() {
//        return imgResID;
//    }
//    public void setImgResID(int imgResID) {
//        this.imgResID = imgResID;
//    }



}
