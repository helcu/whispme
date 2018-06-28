package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.trendService.TrendResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrendService {

    @GET("whisp/trend")
    Call<List<TrendResponse>> getTrends();

}
