package com.nodegrid.android.sdk.services.connections;

import com.nodegrid.android.sdk.CommonUtils;

/**
 * Created by kwijewardana on 4/7/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class SystemApiCalls extends ApiConnector{

    public String checkSystemStatus() {
        String resultResponse;
        resultResponse = sendHttpGet(CommonUtils.NODEGRID_SERVER_URL + "/system/status", null);
        return resultResponse;
    }
}
