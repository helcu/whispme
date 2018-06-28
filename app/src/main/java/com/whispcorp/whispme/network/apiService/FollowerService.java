package com.whispcorp.whispme.network.apiService;

import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.followerService.FollowerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FollowerService {
    @GET("")
    Call<BaseResponse<List<FollowerResponse>>> getFollowers();
}
