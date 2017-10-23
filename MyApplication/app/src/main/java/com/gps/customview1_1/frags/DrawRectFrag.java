package com.gps.customview1_1.frags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gps.customview1_1.R;

/**
 * @Author : zhoukan
 * @CreateDate : 2017/10/23 0023
 * @Descriptiong : xxx
 */

public class DrawRectFrag extends Fragment {

    View view;

    public static DrawRectFrag newInstance() {
        
        Bundle args = new Bundle();
        
        DrawRectFrag fragment = new DrawRectFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_drawrect,container,false);
        return view;
    }
    
}
