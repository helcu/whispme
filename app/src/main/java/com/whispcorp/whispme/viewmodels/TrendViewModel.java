package com.whispcorp.whispme.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.whispcorp.whispme.database.entities.Trend;
import com.whispcorp.whispme.network.WhispRemoteProvider;
import com.whispcorp.whispme.repositories.WhispRepository;

import java.util.List;

public class TrendViewModel extends ViewModel {

    private MutableLiveData<List<Trend>> trendList;


    public TrendViewModel (){
    //puede haber application como parametro



        trendList = WhispRemoteProvider.getTrends(WhispRemoteProvider.ProviderRequestListener);


    }



}
