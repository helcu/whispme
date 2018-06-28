package com.whispcorp.whispme.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.whispcorp.whispme.network.apiService.FollowService;
import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.FollowResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowViewModel extends ViewModel {
    MutableLiveData<List<FollowResponse>> followLiveData = new MutableLiveData<List<FollowResponse>>();

    FollowService service = null;

    public void initFollowers() {
        service.getFollowers().enqueue(new Callback<BaseResponse<List<FollowResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FollowResponse>>> call, Response<BaseResponse<List<FollowResponse>>> response) {
                List<FollowResponse> followlist = new ArrayList<>();
                for (FollowResponse obj : response.body().getData()) {
                    FollowResponse followResponse = new FollowResponse();


                    followlist.add(followResponse);
                }

                followLiveData.postValue(followlist);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FollowResponse>>> call, Throwable t) {

            }
        });
    }

    public void initFollowing() {
        service.getFollowings().enqueue(new Callback<BaseResponse<List<FollowResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<FollowResponse>>> call, Response<BaseResponse<List<FollowResponse>>> response) {
                List<FollowResponse> followlist = new ArrayList<>();
                for (FollowResponse obj : response.body().getData()) {
                    FollowResponse followResponse = new FollowResponse();


                    followlist.add(followResponse);
                }

                followLiveData.postValue(followlist);
            }

            @Override
            public void onFailure(Call<BaseResponse<List<FollowResponse>>> call, Throwable t) {

            }
        });
    }

    public FollowViewModel() {
    }

    public MutableLiveData<List<FollowResponse>> getFollowLiveData() {
        return followLiveData;
    }

    public void setFollowLiveData(MutableLiveData<List<FollowResponse>> followLiveData) {
        this.followLiveData = followLiveData;
    }
}
