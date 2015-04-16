package com.nodegrid.android.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nodegrid.android.sdk.services.connections.SystemApiCalls;


public class MainActivity extends ActionBarActivity {

    private Button checkSystemStatusBtn;
    private Button createSystemUserBtn;
    private Button getSystemUserBtn;
    private Button deleteSystemUserBtn;

    private SystemApiCalls systemApiCalls = new SystemApiCalls();

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

        checkSystemStatusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = "Null";
                if (systemApiCalls.checkSystemStatus() != null){
                    response = systemApiCalls.checkSystemStatus();
                }
                Log.d("TAG/System Status: ", response);
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
                String response = "Null";
                if (systemApiCalls.searchUserFromUsername("john") != null) {
                    response = systemApiCalls.searchUserFromUsername("john");
                }
                Log.d("TAG/User: ", response);
            }
        });

        deleteSystemUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String response = "Null";
                if (systemApiCalls.deleteUserFromUserId("54dd8bd5592867fe084e5af4") != null) {
                    response = systemApiCalls.deleteUserFromUserId("54dd8bd5592867fe084e5af4");
                }
                Log.d("TAG/User delete: ", response);
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
