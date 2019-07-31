package com.example.jetpack.utils.retrofit;

import retrofit2.http.GET;
import rx.Observable;

public interface ContentInterface {
    @GET("today")
    Observable<ContentEnity> getMessage();
}
