package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.OwnerResponse;

import java.security.acl.Owner;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @GET("category")
    Call<Integer> getCategory();


    @POST("user/login")
    @FormUrlEncoded
    Call<BaseResponse<OwnerResponse>> loginUser(@Field("username") String username, @Field("password") String password);
}
