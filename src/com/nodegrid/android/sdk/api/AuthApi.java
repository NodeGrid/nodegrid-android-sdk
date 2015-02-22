package com.nodegrid.android.sdk.api;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.services.connections.ApiConnector;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kavi707 on 2/22/15.
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class AuthApi extends ApiConnector {
    
    public JSONObject getAccessToken(String username, String password) {
        JSONObject tokenObject = new JSONObject();
        
        try {
            JSONObject userParams = new JSONObject();
            userParams.put("username", username);
            userParams.put("password", password);
            
            String resultString = sendHttpJsonPostReq(CommonUtils.NODEGRID_SERVER_URL + "/system/security/generateToken", userParams);
            if (resultString != null) {
                JSONObject jsonResponse = new JSONObject(resultString);
                tokenObject = jsonResponse;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tokenObject;
    }
}
