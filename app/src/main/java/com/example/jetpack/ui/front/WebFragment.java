package com.example.jetpack.ui.front;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jetpack.R;
import com.example.jetpack.utils.fragment.BaseFragment;

public class WebFragment extends BaseFragment {
    public static int frontIndex=-1;

    private WebViewModel mViewModel;

    public static WebFragment newInstance() {
        return new WebFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.web_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(WebViewModel.class);
        // TODO: Use the ViewModel
        WebView webView=(WebView)getActivity().findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(FrontFragment.urlList.get(frontIndex));
    }

}
