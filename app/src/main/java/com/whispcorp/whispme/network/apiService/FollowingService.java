package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.followingService.FollowingResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FollowingService {
    @GET("")
    Call<BaseResponse<List<FollowingResponse>>> getFollowings();
}
