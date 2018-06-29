package com.whispcorp.whispme.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.apiService.FollowingService;
import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.FollowingResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingViewModel extends ViewModel {
    MutableLiveData<List<FollowingResponse>> followingLiveData = new MutableLiveData<List<FollowingResponse>>();

    FollowingService service = null;

    public void initFollowing(String followId) {
        service = ApiProvider.getFollowingService();

        service.getFollowings(followId).enqueue(new Callback<BaseResponse<List<FollowingResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FollowingResponse>>> call, Response<BaseResponse<List<FollowingResponse>>> response) {
                List<FollowingResponse> followlist = new ArrayList<>();
                if (response.body() != null) {
                    followlist.addAll(response.body().getData());
                }

                followingLiveData.postValue(followlist);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FollowingResponse>>> call, Throwable t) {
                String gg = null;
            }
        });
    }

    public MutableLiveData<List<FollowingResponse>> getFollowingLiveData() {
        return followingLiveData;
    }

    public void setFollowingLiveData(MutableLiveData<List<FollowingResponse>> followingLiveData) {
        this.followingLiveData = followingLiveData;
    }
}
