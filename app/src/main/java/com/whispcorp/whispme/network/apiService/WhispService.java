package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.whispService.WhispLocResponse;
import com.whispcorp.whispme.network.modelService.whispService.WhispResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WhispService {

    @GET("whisp/")
    Call<List<WhispLocResponse>> getWhisps();

}
