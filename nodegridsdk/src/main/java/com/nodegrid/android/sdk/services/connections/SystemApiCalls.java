package com.nodegrid.android.sdk.services.connections;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.data.NodeGridResponse;

import org.json.JSONObject;

/**
 * Created by kwijewardana on 4/7/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class SystemApiCalls extends ApiConnector{

    public NodeGridResponse checkSystemStatus() {
        NodeGridResponse resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/status", CommonUtils.HTTP_GET, null);
        return resultResponse;
    }

    public NodeGridResponse createUser(JSONObject userParams) {
        NodeGridResponse resultResponse;
        resultResponse = sendHttpJsonPostReq(CommonUtils.NODEGRID_SERVER_URL + "/system/user", null, userParams);
        return resultResponse;
    }

    public NodeGridResponse searchUserFromUserId(String userId) {
        NodeGridResponse resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + userId, CommonUtils.HTTP_GET, null);
        return resultResponse;
    }

    public NodeGridResponse searchUserFromUsername(String username) {
        NodeGridResponse resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + username, CommonUtils.HTTP_GET, null);
        return resultResponse;
    }

    public NodeGridResponse deleteUserFromUserId(String userId) {
        NodeGridResponse resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/system/user/" + userId, CommonUtils.HTTP_DELETE, null);
        return resultResponse;
    }
}
