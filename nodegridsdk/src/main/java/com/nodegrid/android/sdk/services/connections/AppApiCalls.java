package com.nodegrid.android.sdk.services.connections;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.data.NodeGridResponse;

import java.util.Map;

/**
 * Created by kwijewardana on 4/8/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class AppApiCalls extends ApiConnector {

    /**
     *
     * @param collectionName
     * @param headerParams
     * @return
     */
    public NodeGridResponse readAllCollectionObjects (String collectionName, Map<String, String> headerParams) {
        NodeGridResponse resultResponse;
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/app/" + collectionName,
                CommonUtils.HTTP_GET, headerParams);
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
        resultResponse = sendHttpRequest(CommonUtils.NODEGRID_SERVER_URL + "/app/" + collectionName +
                        "/" + objectId, CommonUtils.HTTP_GET, headerParams);
        return resultResponse;
    }
}
