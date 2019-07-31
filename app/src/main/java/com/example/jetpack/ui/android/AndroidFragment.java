package com.example.jetpack.ui.android;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jetpack.R;
import com.example.jetpack.ui.front.WebFragment;
import com.example.jetpack.utils.fragment.BaseFragment;
import com.example.jetpack.utils.recycler.AndroidRecyclerAdapter;
import com.example.jetpack.utils.recycler.FrontRecyclerAdapter;
import com.example.jetpack.utils.retrofit.ContentEnity;
import com.example.jetpack.utils.retrofit.ContentInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AndroidFragment extends BaseFragment {
    private List<String> contentList = new ArrayList<>();
    private List<String> authorList = new ArrayList<>();
    public static List<String> urlList = new ArrayList<>();
    private static final String BASE_URL = "http://gank.io/api/";

    private AndroidViewModel mViewModel;

    public static AndroidFragment newInstance() {
        return new AndroidFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.android_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AndroidViewModel.class);
        // TODO: Use the ViewModel
        doRequestByRxRetrofit();
    }

    private void doRequestByRxRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .build();
        ContentInterface contentInterface = retrofit.create(ContentInterface.class);
        contentInterface.getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ContentEnity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ContentEnity contentEnity) {
                        for (int i = 0; i < contentEnity.getResults().getAndroid().size(); i++) {
                            contentList.add(contentEnity.getResults().getAndroid().get(i).getDesc());
                            authorList.add(contentEnity.getResults().getAndroid().get(i).getWho());
                            urlList.add(contentEnity.getResults().getAndroid().get(i).getUrl());
                        }
                        Context context = getContext();
                        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_android);
                        AndroidRecyclerAdapter androidRecyclerAdapter=new AndroidRecyclerAdapter(contentList,authorList);
                        androidRecyclerAdapter.setOnItemClickListener(new AndroidRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int position, View view) {
                                AndroidWebFragment.androidIndex=position;
                                Navigation.findNavController(view)
                                        .navigate(R.id.action_AndroidFragment_to_androidWebFragment);
                            }

                            @Override
                            public void onLongClick(int position, View view) {

                            }

                        });
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(androidRecyclerAdapter);
                        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                    }

                });

    }
}
