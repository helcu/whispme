package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.FollowingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FollowingService {
    @GET("user/following/{user}")
    Call<BaseResponse<List<FollowingResponse>>> getFollowings(@Path("user") String followId);
}
