package com.nodegrid.android.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nodegrid.android.app.R;
import com.nodegrid.android.sdk.data.NodeGridData;

/**
 * Created by kavi707 on 5/16/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class DataListItem extends LinearLayout{

    private TextView dataItemId;
    private TextView dataItemVersion;
    private TextView dataItemJson;

    private NodeGridData nodeGridData;

    public DataListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dataItemId = (TextView) findViewById(R.id.dataItemIdTextView);
        dataItemVersion = (TextView) findViewById(R.id.dataItemVersionTextView);
        dataItemJson = (TextView) findViewById(R.id.dataItemJsonTextView);
    }

    public NodeGridData getNodeGridData() {
        return nodeGridData;
    }

    public void setNodeGridData(NodeGridData nodeGridData) {
        this.nodeGridData = nodeGridData;

        dataItemId.setText(nodeGridData.getId());
        dataItemVersion.setText(nodeGridData.getVersion());
        dataItemJson.setText(nodeGridData.getData().toString());
    }
}
