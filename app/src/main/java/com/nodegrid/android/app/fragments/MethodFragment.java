package com.nodegrid.android.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodegrid.android.app.R;

import java.util.Map;

/**
 * Created by kwijewardana on 5/18/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class MethodFragment extends Fragment {

    private String endPointUrl;
    private String requestMethod;

    public MethodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_method, container, false);
    }

    public void setEndPointUrl(String endPointUrl) {
        this.endPointUrl = endPointUrl;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
