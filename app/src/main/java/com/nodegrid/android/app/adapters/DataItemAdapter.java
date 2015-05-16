package com.nodegrid.android.app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.nodegrid.android.app.R;
import com.nodegrid.android.app.views.DataListItem;
import com.nodegrid.android.sdk.data.NodeGridData;

import java.util.List;

/**
 * Created by kavi707 on 5/16/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class DataItemAdapter extends BaseAdapter {

    private List<NodeGridData> nodeGridDataList;
    private Context context;

    public DataItemAdapter(List<NodeGridData> nodeGridDataList, Context context) {
        this.nodeGridDataList = nodeGridDataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nodeGridDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return (nodeGridDataList == null)? null: nodeGridDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataListItem dataListItem;
        if (convertView == null) {
            dataListItem = (DataListItem) View.inflate(context, R.layout.data_list_item, null);
        } else {
            dataListItem = (DataListItem) convertView;
        }

        dataListItem.setNodeGridData(nodeGridDataList.get(position));
        return dataListItem;
    }
}
