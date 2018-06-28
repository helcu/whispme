package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.whispService.WhispLocResponse;
//import com.whispcorp.whispme.network.modelService.whispService.WhispResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WhispService {

    @GET("whisp/")
    Call<BaseResponse<List<WhispLocResponse>>> getWhisps();

    @GET("whisp/findByCordenate")
    Call<BaseResponse<List<WhispLocResponse>>> getWhisps(@Query("latitude") Double latitude, @Query("longitude") Double longitude);

}
