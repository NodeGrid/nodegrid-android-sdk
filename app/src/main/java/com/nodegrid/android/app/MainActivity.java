package com.nodegrid.android.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nodegrid.android.sdk.services.connections.OauthApiCalls;
import com.nodegrid.android.sdk.services.connections.SystemApiCalls;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends ActionBarActivity {

    private Button checkSystemStatusBtn;
    private Button createSystemUserBtn;
    private Button getSystemUserBtn;
    private Button deleteSystemUserBtn;

    private Button generateTokenBtn;

    private SystemApiCalls systemApiCalls = new SystemApiCalls();
    private OauthApiCalls oauthApiCalls = new OauthApiCalls();

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

        checkSystemStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response =  systemApiCalls.checkSystemStatus();
                if (response != null)
                    Log.d("TAG/System Status: ", response);
                else
                    Log.d("TAG/System Status: ", "NULL");
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
                String response = systemApiCalls.searchUserFromUsername("john");
                if (response != null)
                    Log.d("TAG/User: ", response);
                else
                    Log.d("TAG/User: ", "NULL");
            }
        });

        deleteSystemUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = systemApiCalls.deleteUserFromUserId("54dd8bd5592867fe084e5af4");
                if (response != null)
                    Log.d("TAG/User delete: ", response);
                else
                    Log.d("TAG/User delete: ", "NULL");
            }
        });

        generateTokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = "NULL";
                JSONObject authJsonParams = new JSONObject();
                try {
                    authJsonParams.put("username", "john");
                    authJsonParams.put("password", "john123");
                    response = oauthApiCalls.generateOauthToken(authJsonParams);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (response != null)
                    Log.d("TAG/Generate token: ", response);
                else
                    Log.d("TAG/Generate token: ", "NULL");
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
