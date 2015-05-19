package com.nodegrid.android.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodegrid.android.app.R;

/**
 * Created by kwijewardana on 5/19/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class methodPOSTFragment extends Fragment {

    public methodPOSTFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_method, container, false);
    }
}
