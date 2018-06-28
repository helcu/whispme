package com.whispcorp.whispme.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.whispcorp.whispme.database.entities.Follow;
import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.apiService.FollowerService;
import com.whispcorp.whispme.network.apiService.FollowingService;
import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.followerService.FollowerResponse;
import com.whispcorp.whispme.network.modelService.followingService.FollowingResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowingViewModel extends ViewModel {
    private MutableLiveData<List<Follow>> followList = new MutableLiveData<List<Follow>>();

    FollowingService service = null;

    public void initData() {
        service = ApiProvider.getFollowingService();

        service.getFollowings().enqueue(new Callback<BaseResponse<List<FollowingResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FollowingResponse>>> call, Response<BaseResponse<List<FollowingResponse>>> response) {
                List<Follow> follows = new ArrayList<>();
                for (FollowingResponse obj : response.body().getData()) {
                    Follow follow = new Follow();

                    follows.add(follow);
                }

                followList.postValue(follows);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FollowingResponse>>> call, Throwable t) {

            }
        });
    }

    public FollowingViewModel() {
    }

    public MutableLiveData<List<Follow>> getFollowList() {
        return followList;
    }

    public void setFollowList(MutableLiveData<List<Follow>> followList) {
        this.followList = followList;
    }
}
