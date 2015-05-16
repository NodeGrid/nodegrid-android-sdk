package com.nodegrid.android.app;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nodegrid.android.app.adapters.DataItemAdapter;
import com.nodegrid.android.sdk.data.NodeGridResponse;

import java.util.Map;

/**
 * Created by kwijewardana on 5/15/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class AppCommonUtils {

    public static final String NODEGRID_PREFS_NAME = "NodeGridPrefFile";

    /**
     * Create NodeGrid response object viewing popup.
     * @param context Context - Activity context object
     * @param requestObject Map<String, String> - Sent request details
     * @param nodeGridResponse NodeGridResponse - Received response object
     * @return Dialog - Android Dialog Object
     */
    public Dialog createViewDialog(Context context, Map<String, String> requestObject, NodeGridResponse nodeGridResponse) {
        final Dialog responseViewDialog = new Dialog(context);
        responseViewDialog.setContentView(R.layout.view_response);
        responseViewDialog.setTitle("NodeGrid");

        TextView requestMethodTextView = (TextView) responseViewDialog.findViewById(R.id.requestMethodTextView);
        TextView requestEndPointTextView = (TextView) responseViewDialog.findViewById(R.id.requestEndPointTextView);
        TextView responseStatusTextView = (TextView) responseViewDialog.findViewById(R.id.responseStatusTextView);
        TextView responseMsgTextView = (TextView) responseViewDialog.findViewById(R.id.responseMsgTextView);
        TextView responseObjectTextView = (TextView) responseViewDialog.findViewById(R.id.responseObjectTextView);
        LinearLayout responseObjectLinearLayout = (LinearLayout) responseViewDialog.findViewById(R.id.responseObjectLinearLayout);
        TextView responseDataTitleTextView = (TextView) responseViewDialog.findViewById(R.id.dataTitleTextView);
        ListView dataListView = (ListView) responseViewDialog.findViewById(R.id.dataListView);
        Button dialogCloseButton = (Button) responseViewDialog.findViewById(R.id.viewCloseButton);

        responseObjectLinearLayout.setVisibility(View.INVISIBLE);
        responseDataTitleTextView.setVisibility(View.INVISIBLE);
        dataListView.setVisibility(View.INVISIBLE);

        requestMethodTextView.setText(requestObject.get("method"));
        requestEndPointTextView.setText(requestObject.get("endPoint"));

        responseStatusTextView.setText(nodeGridResponse.getStatus());
        responseMsgTextView.setText(nodeGridResponse.getMessage());
        if (nodeGridResponse.getResponseObj() != null) {
            responseObjectLinearLayout.setVisibility(View.VISIBLE);
            responseObjectTextView.setText(nodeGridResponse.getResponseObj().toString());
        }

        if (nodeGridResponse.getNodeGridData() != null) {
            responseDataTitleTextView.setVisibility(View.VISIBLE);
            dataListView.setVisibility(View.VISIBLE);
            DataItemAdapter dataItemAdapter = new DataItemAdapter(nodeGridResponse.getNodeGridData(), context);
            dataListView.setAdapter(dataItemAdapter);
        }

        dialogCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseViewDialog.dismiss();
            }
        });

        return responseViewDialog;
    }

    /**
     * Store given value under the given key in shared preferences
     * @param context Activity context
     * @param key Key you need to share your value in shared preferences
     * @param value Value you need to store in share preferences
     */
    public void setSharedPref(Context context, String key, String value) {
        SharedPreferences.Editor sharePrefEditor =
                context.getSharedPreferences(NODEGRID_PREFS_NAME, context.MODE_PRIVATE).edit();
        sharePrefEditor.putString(key, value);
        sharePrefEditor.apply();
    }

    /**
     * Retrieve stored value ahead to given key
     * @param context Activity context
     * @param key Key of the value you store in shared preferences
     * @return String - Stored value in shared preferences under the given key
     */
    public String getSharePref(Context context, String key) {
        String sharedPrefValue = "";
        SharedPreferences preferences = context.getSharedPreferences(NODEGRID_PREFS_NAME, context.MODE_PRIVATE);
        sharedPrefValue = preferences.getString(key, "http://192.168.1.2:3000");

        return sharedPrefValue;
    }
}
