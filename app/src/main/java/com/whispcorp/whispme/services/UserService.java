package com.whispcorp.whispme.services;
import com.whispcorp.whispme.network.Config;
import com.whispcorp.whispme.database.entities.ServiceResponse;
import com.whispcorp.whispme.database.entities.User;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Optimus on 1/05/2018.
 */

public class UserService {
    private ServiceResponse serviceResponse = new ServiceResponse();
    private OkHttpClient client = new OkHttpClient();


    public ServiceResponse getProfile(int userID) {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ID", userID);

            String url = Config.URL_SERVICE + Config.USER_SERVICE + Config.GET_PROFILE;

            RequestBody body = RequestBody.create(MediaType.parse(Config.CONTENT_TYPE), jsonObject.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response response = client.newCall(request).execute();
            JSONObject object = new JSONObject(response.body().string());
            //**VALIDACION
            JSONObject objectUser = object.getJSONObject("Usuario");
            serviceResponse.User = new User();

            serviceResponse.User.setId(objectUser.getInt("Id"));
            serviceResponse.User.setPhoto(objectUser.getString("FotoPerfil"));
            serviceResponse.User.setUsername(objectUser.getString("Username"));
            serviceResponse.User.setFollowers(objectUser.getInt("Followers"));
            serviceResponse.User.setFollowing(objectUser.getInt("Following"));
            serviceResponse.User.setEmail(objectUser.getString("Email"));
            serviceResponse.User.setBio(objectUser.getString("Bio"));


        } catch (Exception ex) {
            
        }
        return serviceResponse;
    }

}
