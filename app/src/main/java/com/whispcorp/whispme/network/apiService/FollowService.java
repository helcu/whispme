package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.FollowResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FollowService {
    @GET("")
    Call<BaseResponse<List<FollowResponse>>> getFollowers();

    @GET("")
    Call<BaseResponse<List<FollowResponse>>> getFollowings();
}
