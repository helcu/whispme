package com.whispcorp.whispme.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.NetworkClient;
import com.whispcorp.whispme.network.apiService.FollowersService;
import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.FollowersResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersViewModel extends ViewModel {
    MutableLiveData<List<FollowersResponse>> followersLiveData = new MutableLiveData<List<FollowersResponse>>();

    FollowersService service = null;

    public void initFollowers(String followId) {
        service = ApiProvider.getFollowerService();

        service.getFollowers(followId).enqueue(new Callback<BaseResponse<List<FollowersResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FollowersResponse>>> call, Response<BaseResponse<List<FollowersResponse>>> response) {
                List<FollowersResponse> followlist = new ArrayList<>();
                if (response.body() != null) {
                    followlist.addAll(response.body().getData());
                }

                followersLiveData.postValue(followlist);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FollowersResponse>>> call, Throwable t) {

            }
        });
    }

    public FollowersViewModel() {
    }

    public MutableLiveData<List<FollowersResponse>> getFollowersLiveData() {
        return followersLiveData;
    }

    public void setFollowersLiveData(MutableLiveData<List<FollowersResponse>> followersLiveData) {
        this.followersLiveData = followersLiveData;
    }
}
