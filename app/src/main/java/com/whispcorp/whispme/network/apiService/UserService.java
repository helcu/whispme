package com.whispcorp.whispme.network.apiService;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("category")
    Call<Integer> getCategory();

}
