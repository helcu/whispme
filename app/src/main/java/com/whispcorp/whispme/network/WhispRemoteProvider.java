package com.whispcorp.whispme.network;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseAndJSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.google.gson.Gson;
import com.whispcorp.whispme.database.entities.User;
import com.whispcorp.whispme.util.Constants;
import com.whispcorp.whispme.util.SharedPreferencesUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by hugo_ on 01/05/2018.
 */

public class WhispRemoteProvider {
    private static final String TAG = "WhispRemoteProvider";
    private static final String URL = "https://whispme-202021.appspot.com/api";
    private static String USER_URL() {
        return URL + "/user";
    };
    private String WHISP_URL() {
        return URL + "/whisp";
    }

    public static void getAllUsers(ProviderRequestListener callback) {
        AndroidNetworking.get(USER_URL())
                .setPriority(Priority.LOW)
                .setTag("TAG")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: OK");
                        try {
                            if (response != null) {
                                /*List<Hero> heroes = Hero.ProviderBuilder
                                        .from(response).buildAll();
                                callback.onResponse(heroes);*/
                                Log.w(TAG, response.getString(0));
                            }
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                            e.printStackTrace();
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onError(anError.getMessage());
                    }
                });
    }

    public static void login(String username, String password, ProviderRequestListener<JSONObject> callback) {
        JSONObject jsonObject =  new JSONObject();
        try {
            jsonObject.put("username", username);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }

        AndroidNetworking.post(USER_URL() + "/login")
                .addJSONObjectBody(jsonObject)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response != null) {
                                SharedPreferencesUtil.setValue(Constants.Auth.TOKEN, response.getString("token"));
                                callback.onResponse(response);
                            } else {
                                callback.onError("No login");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError(e.getMessage());
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onError(anError.getMessage());
                    }
                });
    }

    public static void registerUser(User user, ProviderRequestListener<JSONObject> callback) {
        try {
            Gson gson= new Gson();
            String userClass = gson.toJson(user);
            JSONObject jsonObject = new JSONObject(userClass);

            AndroidNetworking.post(USER_URL())
                    .addJSONObjectBody(jsonObject)
                    .setTag(TAG)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            callback.onResponse(response);
                        }

                        @Override
                        public void onError(ANError anError) {
                            callback.onError(anError.getMessage());
                        }
                    });
        } catch (JSONException e) {
            e.printStackTrace();
            callback.onError(e.getMessage());
        }
    }

    public interface ProviderRequestListener<T> {
        void onResponse(T entities);

        void onError(String message);
    }
}
