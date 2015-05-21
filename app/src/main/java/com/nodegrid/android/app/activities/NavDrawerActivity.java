package com.nodegrid.android.app.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.nodegrid.android.app.R;
import com.nodegrid.android.app.adapters.DrawerSystemApiListAdapter;
import com.nodegrid.android.app.fragments.methodGETFragment;
import com.nodegrid.android.app.views.NavItem;

import java.util.ArrayList;

/**
 * Created by kwijewardana on 5/18/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class NavDrawerActivity extends Activity {

    private ListView mDrawerList;
    private LinearLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    //private View listFinalTouchedView = null;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        setUpViews();
    }

    private void setUpViews() {
        mNavItems.add(new NavItem("System API Calls", "REST calls for NodeGrid systematic scenarios", true));
        mNavItems.add(new NavItem("Check NodeGrid Status", null, false));
        mNavItems.add(new NavItem("Create new user", null, false));
        mNavItems.add(new NavItem("Retrieve user", null, false));
        mNavItems.add(new NavItem("Delete user", null, false));
        mNavItems.add(new NavItem("", null, true));
        mNavItems.add(new NavItem("Oauth API Calls", "REST calls for NodeGrid authentication", true));
        mNavItems.add(new NavItem("Generate token", null, false));
        mNavItems.add(new NavItem("", null, true));
        mNavItems.add(new NavItem("App API Calls", "REST calls for NodeGrid App", true));
        mNavItems.add(new NavItem("Store object", null, false));
        mNavItems.add(new NavItem("Retrieve objects", null, false));
        mNavItems.add(new NavItem("Retrieve object from ID", null, false));
        mNavItems.add(new NavItem("Advance querying", null, false));
        mNavItems.add(new NavItem("Delete object from ID", null, false));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigtion Drawer with options
        mDrawerPane = (LinearLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerSystemApiListAdapter adapter = new DrawerSystemApiListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position, view);
            }
        });

        // enabling action bar app icon and behaving it as toggle button
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /**
     * Called when a particular item from the navigation drawer
     * @param position
     */
    private void selectItemFromDrawer(int position, View view) {

        if (!mNavItems.get(position).isTitle()) {

            /*if (listFinalTouchedView != null) {
                listFinalTouchedView.setBackgroundColor(getResources().getColor(R.color.white));
            }

            listFinalTouchedView = view;
            view.setBackgroundColor(getResources().getColor(R.color.light_blue));*/

            Fragment fragment = new methodGETFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.mainContent, fragment)
                    .commit();

            mDrawerList.setItemChecked(position, true);
            setTitle(mNavItems.get(position).getMTitle());

            // Close the drawer
            mDrawerLayout.closeDrawer(mDrawerPane);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(NavDrawerActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
