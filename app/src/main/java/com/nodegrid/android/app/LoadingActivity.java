package com.nodegrid.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by kwijewardana on 4/22/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class LoadingActivity extends Activity {

    private Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        setUpView();
    }

    private void setUpView() {
        continueBtn = (Button) findViewById(R.id.continueBtn);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });
    }
}
