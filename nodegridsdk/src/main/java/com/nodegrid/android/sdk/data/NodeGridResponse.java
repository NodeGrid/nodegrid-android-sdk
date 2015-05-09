package com.nodegrid.android.sdk.data;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by kwijewardana on 4/29/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class NodeGridResponse {

    private String status;
    private String message;
    private JSONObject responseObj;
    private List<NodeGridData> nodeGridData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getResponseObj() {
        return responseObj;
    }

    public void setResponseObj(JSONObject responseObj) {
        this.responseObj = responseObj;
    }

    public List<NodeGridData> getNodeGridData() {
        return nodeGridData;
    }

    public void setNodeGridData(List<NodeGridData> nodeGridData) {
        this.nodeGridData = nodeGridData;
    }
}
