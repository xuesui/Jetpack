package com.example.jetpack.ui.ios;

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

public class IOSWebFragment extends Fragment {

    public static int iosIndex=-1;

    private IoswebViewModel mViewModel;

    public static IOSWebFragment newInstance() {
        return new IOSWebFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.iosweb_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(IoswebViewModel.class);
        // TODO: Use the ViewModel
        WebView webView=(WebView)getActivity().findViewById(R.id.ios_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(IosFragment.urlList.get(iosIndex));
    }

}
