package com.nodegrid.android.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nodegrid.android.app.R;

/**
 * Created by kwijewardana on 5/15/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class SettingsActivity extends Activity {

    private Button cancelBtn;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setUpViews();
    }

    private void setUpViews() {
        cancelBtn = (Button) findViewById(R.id.configCancelBtn);
        saveBtn = (Button) findViewById(R.id.configSaveBtn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
