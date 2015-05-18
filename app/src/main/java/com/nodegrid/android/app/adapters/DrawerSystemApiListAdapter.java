package com.nodegrid.android.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nodegrid.android.app.R;
import com.nodegrid.android.app.views.NavItem;

import java.util.ArrayList;

/**
 * Created by kwijewardana on 5/18/15.
 */
public class DrawerSystemApiListAdapter extends BaseAdapter{

    Context mContext;
    ArrayList<NavItem> mNavItems;

    public DrawerSystemApiListAdapter(Context context, ArrayList<NavItem> navItems) {
        mContext = context;
        mNavItems = navItems;
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.mainTitle);
        TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);

        titleView.setText( mNavItems.get(position).getMTitle());
        if (mNavItems.get(position).getMSubTitle() != null) {
            subtitleView.setText(mNavItems.get(position).getMSubTitle());
        } else {
            subtitleView.setVisibility(View.GONE);
            titleView.setPadding(30, 0, 0, 0);
        }

        return view;
    }
}
