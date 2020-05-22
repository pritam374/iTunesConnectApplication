package com.pritamNew.project.iTunesConnectApp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.pritamNew.project.iTunesConnectApp.model.Music;
import com.pritamNew.project.iTunesConnectApp.model.MusicRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MusicRepository musicRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        musicRepository = new MusicRepository(application);
    }

    public LiveData<List<Music>> getAllMusic(String artistName){

        if (isNetworkAvailable()) {
            return musicRepository.getMutableLiveData(artistName);
        }else{
            return musicRepository.getMusicOff(artistName);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
