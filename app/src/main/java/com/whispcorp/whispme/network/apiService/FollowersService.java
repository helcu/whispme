package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.FollowersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FollowersService {
    @GET("user/follower/{user}")
    Call<BaseResponse<List<FollowersResponse>>> getFollowers(@Path("user") String followId);
}
