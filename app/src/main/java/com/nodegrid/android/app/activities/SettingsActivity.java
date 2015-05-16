package com.nodegrid.android.app.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nodegrid.android.app.AppCommonUtils;
import com.nodegrid.android.app.R;
import com.nodegrid.android.sdk.CommonUtils;

/**
 * Created by kwijewardana on 5/15/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class SettingsActivity extends Activity {

    private EditText nodeGridUrlEditText;
    private Button cancelBtn;
    private Button saveBtn;

    private Context context = this;
    private AppCommonUtils appCommonUtils = new AppCommonUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setUpViews();
    }

    private void setUpViews() {
        cancelBtn = (Button) findViewById(R.id.configCancelBtn);
        saveBtn = (Button) findViewById(R.id.configSaveBtn);
        nodeGridUrlEditText = (EditText) findViewById(R.id.nodegridUrlEditText);

        CommonUtils.NODEGRID_SERVER_URL = appCommonUtils.getSharePref(context, "nodegrid_server_url");

        if (CommonUtils.NODEGRID_SERVER_URL == null || CommonUtils.NODEGRID_SERVER_URL.equals("")) {
            nodeGridUrlEditText.setHint("http://192.168.1.2:3000");
        } else {
            nodeGridUrlEditText.setText(CommonUtils.NODEGRID_SERVER_URL);
        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.NODEGRID_SERVER_URL = nodeGridUrlEditText.getText().toString();
                appCommonUtils.setSharedPref(context, "nodegrid_server_url", CommonUtils.NODEGRID_SERVER_URL);
                Toast.makeText(context, "NodeGrid URL saved", Toast.LENGTH_LONG).show();
            }
        });
    }
}
