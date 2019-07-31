package com.example.jetpack.ui.recommand;

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

public class RecommandWebFragment extends Fragment {
    public static int recommandIndex=-1;

    private RecommandWebViewModel mViewModel;

    public static RecommandWebFragment newInstance() {
        return new RecommandWebFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recommand_web_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RecommandWebViewModel.class);
        // TODO: Use the ViewModel
        WebView webView=(WebView)getActivity().findViewById(R.id.recommand_web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(RecommandFragment.urlList.get(recommandIndex));
    }

}
