package com.example.jetpack.ui.android;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jetpack.R;
import com.example.jetpack.ui.front.FrontFragment;

public class AndroidWebFragment extends Fragment {
    public static int androidIndex=-1;

    private AndroidWebViewModel mViewModel;

    public static AndroidWebFragment newInstance() {
        return new AndroidWebFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.android_web_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AndroidWebViewModel.class);
        // TODO: Use the ViewModel
        WebView webView=(WebView)getActivity().findViewById(R.id.android_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(AndroidFragment.urlList.get(androidIndex));
    }

}
