package com.nodegrid.android.sdk.services.connections;

import com.nodegrid.android.sdk.CommonUtils;

import java.util.Map;

/**
 * Created by kwijewardana on 4/8/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class AppApiCalls extends ApiConnector {

    public String readAllCollectionObjects (String collectionName, Map<String, String> headerParams) {
        String resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/app/" + collectionName,
                CommonUtils.HTTP_GET, headerParams);
        return resultResponse;
    }

    public String readCollectionObjectFromId (String collectionName, String objectId, Map<String, String> headerParams) {
        String resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/app/" + collectionName +
                        "/" + objectId, CommonUtils.HTTP_GET, headerParams);
        return resultResponse;
    }
}
