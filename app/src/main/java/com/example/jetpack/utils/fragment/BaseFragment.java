package com.example.jetpack.utils.fragment;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.jetpack.utils.context.MyApplication;

public class BaseFragment extends Fragment {
    private Activity activity;

    public Context getContext(){
        if (activity==null){
            return MyApplication.getInstance();
        }
        return activity;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity=getActivity();
    }
}
