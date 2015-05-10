package com.nodegrid.android.sdk.services.connections;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.data.NodeGridResponse;

import org.json.JSONObject;

/**
 * Created by kavi707 on 4/21/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class OauthApiCalls extends ApiConnector {

    /**
     * Method for Generate accessToken for NodeGrid Api calls.
     * @param authParams
     * @return String Object (returns a json string)
     */
    public NodeGridResponse generateOauthToken(JSONObject authParams) {
        NodeGridResponse resultResponse;
        resultResponse = sendHttpJsonPostReq(CommonUtils.NODEGRID_SERVER_URL + "/system/security/generateToken", null, authParams);
        return resultResponse;
    }
}
