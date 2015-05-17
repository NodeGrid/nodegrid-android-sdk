package com.nodegrid.android.sdk.services.connections;

import android.content.Context;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.data.NodeGridResponse;
import com.nodegrid.android.sdk.services.ActivityUserPermissionServices;

import org.json.JSONObject;

/**
 * Created by kavi707 on 4/21/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class OauthApiCalls extends ApiConnector {

    private Context context;
    private ActivityUserPermissionServices activityUserPermissionServices = new ActivityUserPermissionServices();

    public OauthApiCalls(Context context) {
        this.context = context;
    }

    /**
     * Method for Generate accessToken for NodeGrid Api calls.
     * @param authParams
     * @return String Object (returns a json string)
     */
    public NodeGridResponse generateOauthToken(JSONObject authParams) {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpJsonPostReq(CommonUtils.NODEGRID_SERVER_URL + "/system/security/generateToken",
                    null, authParams);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }
}
