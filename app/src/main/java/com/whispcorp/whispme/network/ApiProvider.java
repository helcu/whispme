package com.whispcorp.whispme.network;

import com.whispcorp.whispme.network.apiService.FollowersService;
import com.whispcorp.whispme.network.apiService.FollowingService;
import com.whispcorp.whispme.network.apiService.TrendService;
import com.whispcorp.whispme.network.apiService.UserService;
import com.whispcorp.whispme.network.apiService.WhispService;

public class ApiProvider {

    private static final String BASE_URL = "https://whispme-202021.appspot.com/api/";


    public static UserService getUserService() {
        return NetworkClient.getClient(BASE_URL).create(UserService.class);
    }

    public static TrendService getTrendService() {
        return NetworkClient.getClient(BASE_URL).create(TrendService.class);
    }

    public static WhispService getWhispService() {
        return NetworkClient.getClient(BASE_URL).create(WhispService.class);
    }

    public static FollowersService getFollowerService() {
        return NetworkClient.getClient(BASE_URL).create(FollowersService.class);
    }

    public static FollowingService getFollowingService() {
        return NetworkClient.getClient(BASE_URL).create(FollowingService.class);
    }
}
