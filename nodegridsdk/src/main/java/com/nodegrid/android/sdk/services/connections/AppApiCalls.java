package com.nodegrid.android.sdk.services.connections;

import android.content.Context;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.data.NodeGridResponse;
import com.nodegrid.android.sdk.services.ActivityUserPermissionServices;

import java.util.Map;

/**
 * Created by kwijewardana on 4/8/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class AppApiCalls extends ApiConnector {

    private Context context;
    private ActivityUserPermissionServices activityUserPermissionServices = new ActivityUserPermissionServices();

    public AppApiCalls(Context context) {
        this.context = context;
    }

    /**
     *
     * @param collectionName
     * @param headerParams
     * @return
     */
    public NodeGridResponse readAllCollectionObjects (String collectionName, Map<String, String> headerParams) {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/app/" + collectionName,
                    CommonUtils.HTTP_GET, headerParams);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }

    /**
     *
     * @param collectionName
     * @param objectId
     * @param headerParams
     * @return
     */
    public NodeGridResponse readCollectionObjectFromId (String collectionName, String objectId, Map<String, String> headerParams) {
        NodeGridResponse resultResponse;
        if (activityUserPermissionServices.isOnline(context)) {
            resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/app/" + collectionName +
                    "/" + objectId, CommonUtils.HTTP_GET, headerParams);
        } else {
            resultResponse = new NodeGridResponse();
            resultResponse.setStatus("ERROR");
            resultResponse.setMessage("Network is not available");
        }
        return resultResponse;
    }
}
