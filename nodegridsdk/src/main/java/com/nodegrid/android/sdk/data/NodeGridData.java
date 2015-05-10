package com.nodegrid.android.sdk.data;

import org.json.JSONObject;

/**
 * Created by kwijewardana on 4/29/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class NodeGridData {

    private String id;
    private JSONObject data;
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
