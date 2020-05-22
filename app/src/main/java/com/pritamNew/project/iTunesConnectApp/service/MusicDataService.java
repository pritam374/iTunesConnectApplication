package com.pritamNew.project.iTunesConnectApp.service;

import com.pritamNew.project.iTunesConnectApp.model.MusicDBResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MusicDataService {

    @GET("search")
    Call<MusicDBResponse> getPopularMusic(@Query("term") String apiKey);

}
