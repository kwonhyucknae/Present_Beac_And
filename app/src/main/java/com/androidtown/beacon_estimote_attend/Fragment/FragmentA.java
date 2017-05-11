package com.androidtown.beacon_estimote_attend.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidtown.beacon_estimote_attend.R;

/**
 * Created by kwonhyucknae on 2017-05-07.
 */

public class FragmentA extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.firstpage_fragment,container,false);
    }
}
