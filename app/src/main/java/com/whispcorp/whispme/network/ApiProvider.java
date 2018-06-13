package com.whispcorp.whispme.network;

import com.whispcorp.whispme.network.apiService.TrendService;
import com.whispcorp.whispme.network.apiService.UserService;

public class ApiProvider {


    private static final String BASE_URL = "https://whispme-202021.appspot.com/api/";

    public static UserService getUserService() {
        return NetworkClient.getClient(BASE_URL).create(UserService.class);
    }


    public static TrendService getTrendService() {
        return NetworkClient.getClient(BASE_URL).create(TrendService.class);
    }

}
