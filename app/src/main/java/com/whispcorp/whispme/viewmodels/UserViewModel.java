package com.whispcorp.whispme.viewmodels;

import android.content.Intent;
import android.widget.Toast;

import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.apiService.UserService;
import com.whispcorp.whispme.network.modelService.BaseResponse;
import com.whispcorp.whispme.network.modelService.OwnerResponse;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;
import com.whispcorp.whispme.view.activities.LoginActivity;
import com.whispcorp.whispme.view.activities.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel {


    public void followUser(String followedId, UserViewModelCallback callback) {

        String userId = SharedPreferencesUtil.getValue(Constants.SharedPreferencesConstant.USER_ID, "0");

        ApiProvider.getUserService().followUser(userId, followedId).enqueue(new Callback<BaseResponse<OwnerResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<OwnerResponse>> call, Response<BaseResponse<OwnerResponse>> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {
                        callback.onSucess();
                    }
                } else {
                    callback.onFailed("FALLÓ");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<OwnerResponse>> call, Throwable t) {
                callback.onFailed("ERROR");
            }
        });
    }

    public interface UserViewModelCallback {
        void onSucess();

        void onFailed(String message);
    }
}
