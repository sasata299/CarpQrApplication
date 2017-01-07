package com.example.sasata299.carpqrapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasata299.carpqrapplication.R;

/**
 * Created by sasata299 on 2016/11/04.
 */

public class OtherFragment extends Fragment {
    public OtherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.other_fragment, container, false);
        return view;
    }
}
