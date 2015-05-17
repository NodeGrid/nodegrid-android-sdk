package com.nodegrid.android.sdk.services.connections;

import android.content.Context;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.data.NodeGridResponse;
import com.nodegrid.android.sdk.services.ActivityUserPermissionServices;

import org.json.JSONObject;

/**
 * Created by kwijewardana on 4/7/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class SystemApiCalls extends ApiConnector{

    private Context context;
    private ActivityUserPermissionServices activityUserPermissionServices = new ActivityUserPermissionServices();

    public SystemApiCalls(Context context) {
        this.context = context;
    }

    public NodeGridResponse checkSystemStatus() {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/status", CommonUtils.HTTP_GET, null);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }

    public NodeGridResponse createUser(JSONObject userParams) {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpJsonPostReq(CommonUtils.NODEGRID_SERVER_URL + "/system/user", null, userParams);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }

    public NodeGridResponse searchUserFromUserId(String userId) {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + userId,
                    CommonUtils.HTTP_GET, null);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }

    public NodeGridResponse searchUserFromUsername(String username) {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + username,
                    CommonUtils.HTTP_GET, null);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }

    public NodeGridResponse deleteUserFromUserId(String userId) {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + userId,
                    CommonUtils.HTTP_DELETE, null);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }
}
