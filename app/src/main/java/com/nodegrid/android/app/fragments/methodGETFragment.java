package com.nodegrid.android.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodegrid.android.app.R;

/**
 * Created by kwijewardana on 5/18/15.
 */
public class methodGETFragment extends Fragment {

    public methodGETFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_method, container, false);
    }
}
