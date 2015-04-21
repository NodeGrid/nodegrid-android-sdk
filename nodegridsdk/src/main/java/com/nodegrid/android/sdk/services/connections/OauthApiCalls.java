package com.nodegrid.android.sdk.services.connections;

import com.nodegrid.android.sdk.CommonUtils;

import org.json.JSONObject;

/**
 * Created by kavi707 on 4/21/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class OauthApiCalls extends ApiConnector {

    public String generateOauthToken(JSONObject authParams) {
        String resultResponse;
        resultResponse = sendHttpJsonPostReq(CommonUtils.NODEGRID_SERVER_URL + "/system/security/generateToken", null, authParams);
        return resultResponse;
    }
}
