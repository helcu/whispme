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
import com.whispcorp.whispme.network.WhispRemoteProvider;
import com.whispcorp.whispme.repositories.WhispRepository;
import com.whispcorp.whispme.view.activities.LoginActivity;
import com.whispcorp.whispme.view.activities.MainActivity;

import org.json.JSONObject;

import java.util.List;

public class TrendViewModel extends ViewModel {

    private MutableLiveData<List<Trend>> trendList = new MutableLiveData<>();


    public void initData(){

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

        });

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
