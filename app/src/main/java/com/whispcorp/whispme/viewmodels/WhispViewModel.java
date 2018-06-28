package com.whispcorp.whispme.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.whispcorp.whispme.database.entities.User;
import com.whispcorp.whispme.database.entities.Whisp;
import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.apiService.WhispService;
import com.whispcorp.whispme.network.modelService.OwnerResponse;
import com.whispcorp.whispme.network.modelService.whispService.WhispLocResponse;
import com.whispcorp.whispme.network.modelService.whispService.WhispResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WhispViewModel extends ViewModel {

    private WhispService service = null;
    private MutableLiveData<List<Whisp>> whispMutableList = new MutableLiveData<>();

    public void initData() {
        service = ApiProvider.getWhispService();

        service.getWhisps().enqueue(new Callback<List<WhispLocResponse>>() {
            @Override
            public void onResponse(Call<List<WhispLocResponse>> call, Response<List<WhispLocResponse>> response) {

                List<Whisp> list = new ArrayList<>();
                for (WhispLocResponse whispResponse : response.body()) {

                    try {
                        Whisp whisp = new Whisp();
                        whisp.setServerId(whispResponse.getId());
                        whisp.setLongitude(whispResponse.getLoc().get(0));
                        whisp.setLatitude(whispResponse.getLoc().get(1));
                        /*whisp.setLongitude(whispResponse.getCordenate().getLongitude());
                        whisp.setLatitude(whispResponse.getCordenate().getLatitude());

                        OwnerResponse ownerResponse = whispResponse.getOwner();
                        User owner = new User();
                        owner.setServerId(ownerResponse.getId());
                        owner.setUsername(ownerResponse.getUsername());
                        owner.setBio(ownerResponse.getBio());
                        owner.setEmail(ownerResponse.getEmail());
                        owner.setPhoto(ownerResponse.getPhoto());
                        owner.setFollowers(ownerResponse.getFollowers());
                        owner.setFollowing(ownerResponse.getFollowers());
                        whisp.setOwner(owner);
                        whisp.setOwnerServerId(ownerResponse.getId());*/

                        whisp.setTitle(whispResponse.getTitle());
                        whisp.setContent(whispResponse.getContent());
                        whisp.setType(whispResponse.getTypewhisp());

                        whisp.setLikes(whispResponse.getMeta().getLikes());
                        whisp.setViews(whispResponse.getMeta().getViews());
                        whisp.setComments(whispResponse.getMeta().getComments());

                        list.add(whisp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                whispMutableList.postValue(list);
            }

            @Override
            public void onFailure(Call<List<WhispLocResponse>> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<List<Whisp>> getWhispMutableList() {
        return whispMutableList;
    }
}
