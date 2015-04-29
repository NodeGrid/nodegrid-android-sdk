package com.nodegrid.android.sdk.data;

/**
 * Created by kwijewardana on 4/29/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class NodeGridData {

    private String id;
    private String data;
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
