package com.nodegrid.android.app;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nodegrid.android.sdk.data.NodeGridResponse;

import java.util.Map;

/**
 * Created by kwijewardana on 5/15/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class AppCommonUtils {

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
        Button dialogCloseButton = (Button) responseViewDialog.findViewById(R.id.viewCloseButton);

        requestMethodTextView.setText(requestObject.get("method"));
        requestEndPointTextView.setText(requestObject.get("endPoint"));

        responseStatusTextView.setText(nodeGridResponse.getStatus());
        responseMsgTextView.setText(nodeGridResponse.getMessage());

        dialogCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseViewDialog.dismiss();
            }
        });

        return responseViewDialog;
    }
}
