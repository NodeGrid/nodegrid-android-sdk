package com.nodegrid.android.app.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nodegrid.android.app.AppCommonUtils;
import com.nodegrid.android.app.R;
import com.nodegrid.android.sdk.data.NodeGridResponse;
import com.nodegrid.android.sdk.services.connections.AppApiCalls;
import com.nodegrid.android.sdk.services.connections.OauthApiCalls;
import com.nodegrid.android.sdk.services.connections.SystemApiCalls;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kwijewardana on 4/22/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class MainActivity extends ActionBarActivity {

    private Button checkSystemStatusBtn;
    private Button createSystemUserBtn;
    private Button getSystemUserBtn;
    private Button deleteSystemUserBtn;

    private Button generateTokenBtn;

    private Button getAllObjectBtn;
    private Button getObjectFromIdBtn;

    private Context context = this;

    private SystemApiCalls systemApiCalls = new SystemApiCalls();
    private OauthApiCalls oauthApiCalls = new OauthApiCalls();
    private AppApiCalls appApiCalls = new AppApiCalls();
    private AppCommonUtils appCommonUtils = new AppCommonUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }

    private void setUpView() {
        checkSystemStatusBtn = (Button) findViewById(R.id.systemCheckBtn);
        createSystemUserBtn = (Button) findViewById(R.id.systemUserCreateBtn);
        getSystemUserBtn = (Button) findViewById(R.id.systemUserGetBtn);
        deleteSystemUserBtn = (Button) findViewById(R.id.systemUserDeleteBtn);

        generateTokenBtn = (Button) findViewById(R.id.generateTokenBtn);

        getAllObjectBtn = (Button) findViewById(R.id.getAllObjectsBtn);
        getObjectFromIdBtn = (Button) findViewById(R.id.getObjectFromIdBtn);

        checkSystemStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NodeGridResponse response =  systemApiCalls.checkSystemStatus();
                if (response != null) {
                    Log.d("TAG/System Status: ", response.getStatus());
                    Map<String, String> requestObject = new HashMap<>();
                    requestObject.put("method", "GET");
                    requestObject.put("endPoint", "[/system/status]");
                    appCommonUtils.createViewDialog(context, requestObject, response).show();
                } else {
                    Log.d("TAG/System Status: ", "NULL");
                }
            }
        });

        createSystemUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = "Null";
            }
        });

        getSystemUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO REMOVE - Username is hardcoded for retrieve system user
                NodeGridResponse response = systemApiCalls.searchUserFromUsername("john");
                if (response != null) {
                    Log.d("TAG/User: ", response.getStatus());
                } else {
                    Log.d("TAG/User: ", "NULL");
                }
            }
        });

        deleteSystemUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO REMOVE - UserId is hardcoded for delete
                NodeGridResponse response = systemApiCalls.deleteUserFromUserId("54dd8bd5592867fe084e5af4");
                if (response != null) {
                    Log.d("TAG/User delete: ", response.getStatus());
                    Log.d("TAG/User delete: ", String.valueOf(response.getResponseObj()));
                } else {
                    Log.d("TAG/User delete: ", "NULL");
                }
            }
        });

        generateTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NodeGridResponse response = null;
                JSONObject authJsonParams = new JSONObject();
                //TODO REMOVE - Username & Password is hardcoded for generateToken
                try {
                    authJsonParams.put("username", "john");
                    authJsonParams.put("password", "john123");
                    response = oauthApiCalls.generateOauthToken(authJsonParams);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (response != null) {
                    Log.d("TAG/Generate token: ", response.getMessage());
                } else {
                    Log.d("TAG/Generate token: ", "NULL");
                }
            }
        });

        getAllObjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NodeGridResponse response;
                Map<String, String> headerParams = new HashMap<>();
                //TODO REMOVE - Generated token is hardcoded for retrieve data testing
                headerParams.put("Authorization", "eb88047ab090c558a1149b3df4b08a92fa951d14");

                response = appApiCalls.readAllCollectionObjects("cars", headerParams);

                if (response != null) {
                    Log.d("TAG/Objects: ", response.getStatus());
                } else {
                    Log.d("TAG/Objects: ", "NULL");
                }
            }
        });

        getObjectFromIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NodeGridResponse response;
                Map<String, String> headerParams = new HashMap<>();
                //TODO REMOVE - Generated token is hardcoded for retrieve data testing
                headerParams.put("Authorization", "eb88047ab090c558a1149b3df4b08a92fa951d14");

                response = appApiCalls.readCollectionObjectFromId("cars", "54dd8c8a592867fe084e5af5", headerParams);

                if (response != null) {
                    Log.d("TAG/Objects: ", response.getStatus());
                } else {
                    Log.d("TAG/Objects: ", "NULL");
                }
            }
        });
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
