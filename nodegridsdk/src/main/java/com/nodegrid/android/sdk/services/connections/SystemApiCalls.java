package com.nodegrid.android.sdk.services.connections;

import com.nodegrid.android.sdk.CommonUtils;

import org.json.JSONObject;

/**
 * Created by kwijewardana on 4/7/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class SystemApiCalls extends ApiConnector{

    public String checkSystemStatus() {
        String resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/status", CommonUtils.HTTP_GET, null);
        return resultResponse;
    }

    public String createUser(JSONObject userParams) {
        String resultResponse;
        resultResponse = sendHttpJsonPostReq(CommonUtils.NODEGRID_SERVER_URL + "/system/user", null, userParams);
        return resultResponse;
    }

    public String searchUserFromUserId(String userId) {
        String resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + userId, CommonUtils.HTTP_GET, null);
        return resultResponse;
    }

    public String searchUserFromUsername(String username) {
        String resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + username, CommonUtils.HTTP_GET, null);
        return resultResponse;
    }

    public String deleteUserFromUserId(String userId) {
        String resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + userId, CommonUtils.HTTP_DELETE, null);
        return resultResponse;
    }
}
