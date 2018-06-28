package com.whispcorp.whispme.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.whispcorp.whispme.database.entities.Trend;
import com.whispcorp.whispme.network.ApiProvider;
import com.whispcorp.whispme.network.WhispRemoteProvider;
import com.whispcorp.whispme.network.apiService.TrendService;
import com.whispcorp.whispme.network.apiService.UserService;
import com.whispcorp.whispme.network.modelService.trendService.TrendResponse;
import com.whispcorp.whispme.repositories.WhispRepository;
import com.whispcorp.whispme.view.activities.LoginActivity;
import com.whispcorp.whispme.view.activities.MainActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendViewModel extends ViewModel {

    private MutableLiveData<List<Trend>> trendList = new MutableLiveData<>();

    TrendService service = null;
    public void initData(){

        service = ApiProvider.getTrendService();

        service.getTrends().enqueue(new Callback<List<TrendResponse>>() {
            @Override
            public void onResponse(Call<List<TrendResponse>> call, Response<List<TrendResponse>>
                    response) {

                    List<Trend> list =  new ArrayList<>();
                    for (TrendResponse obj : response.body() ){

                        Trend item = new Trend();
                        item.setUserName("uknow");
                        item.setTitle(obj.getTitle());
                        item.setHashtags("hashtag");
                        item.setLikes(obj.getMeta().getLikes());
                        item.setPlace("palce");
                        item.setTime( new Date());

                        list.add(item);

                    }

                    trendList.postValue(list);
            }

            @Override
            public void onFailure(Call<List<TrendResponse>> call, Throwable t) {

            }
        });

/*
        WhispRemoteProvider.getTrends(new WhispRemoteProvider.ProviderRequestListener< MutableLiveData<List<Trend>>>() {
            @Override
            public void onResponse( MutableLiveData<List<Trend>> response) {
                Log.d("LLAMADADEOBERSERRRRRRR", "jalo data inirdata");
                trendList = response;
            }

            @Override
            public void onError(String message) {

                Log.d("", "onError: error al traeer info en view model");

            }

        });*/

    }

    public TrendViewModel (){
    //puede haber application como parametro

        WhispRemoteProvider.getTrends(new WhispRemoteProvider.ProviderRequestListener< MutableLiveData<List<Trend>>>() {
            @Override
            public void onResponse( MutableLiveData<List<Trend>> response) {
                Log.d("LLAMADADEOBERSERRRRRRR", "trajo data constructor");
                trendList = response;
            }

            @Override
            public void onError(String message) {

                Log.d("", "onError: error al traeer info en view model");

            }

        });


    }


    public MutableLiveData<List<Trend>> getTrendList() {
        Log.d("LLAMADADEOBERSERRRRRRR", "peticion de get vm");

        return trendList;
    }

    public void setTrendList(MutableLiveData<List<Trend>> trendList) {
        this.trendList = trendList;
    }
}
