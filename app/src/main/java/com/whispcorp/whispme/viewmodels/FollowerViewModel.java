package com.whispcorp.whispme.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.whispcorp.whispme.database.entities.Follow;
import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.apiService.FollowerService;
import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.followerService.FollowerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowerViewModel extends ViewModel {
    private MutableLiveData<List<Follow>> followList = new MutableLiveData<List<Follow>>();

    FollowerService service = null;

    public void initData() {
        service = ApiProvider.getFollowerService();

        service.getFollowers().enqueue(new Callback<BaseResponse<List<FollowerResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FollowerResponse>>> call, Response<BaseResponse<List<FollowerResponse>>> response) {
                List<Follow> follows = new ArrayList<>();
                for (FollowerResponse obj : response.body().getData()) {
                    Follow follow = new Follow();

                    follows.add(follow);
                }

                followList.postValue(follows);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FollowerResponse>>> call, Throwable t) {

            }
        });
    }

    public FollowerViewModel() {

    }

    public MutableLiveData<List<Follow>> getFollowList() {
        return followList;
    }

    public void setFollowList(MutableLiveData<List<Follow>> followList) {
        this.followList = followList;
    }
}
