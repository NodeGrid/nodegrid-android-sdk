package com.nodegrid.android.sdk.api;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.services.connections.ApiConnector;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kavi707 on 2/22/15.
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class SystemApi extends ApiConnector{
    
    public JSONObject checkNodeGridStatus() {
        JSONObject status = new JSONObject();
        
        String resultString = sendHttpGet(CommonUtils.NODEGRID_SERVER_URL + "/system/status");
        try {
            if (resultString != null) {
                JSONObject jsonResponse = new JSONObject(resultString);
                status = jsonResponse;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return status;
    }
    
    public JSONObject createUser() {
        JSONObject user = new JSONObject();
        return user;
    }
    
    public JSONObject getUserFromUserId() {
        JSONObject user = new JSONObject();
        return user;
    }
    
    public JSONObject getUserFromUsername() {
        JSONObject user = new JSONObject();
        return user;
    }
    
    public JSONObject deleteUser() {
        JSONObject deleteStatus = new JSONObject();
        return deleteStatus;
    }
}
