package com.pritamNew.project.iTunesConnectApp.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.pritamNew.project.iTunesConnectApp.service.MusicDataService;
import com.pritamNew.project.iTunesConnectApp.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicRepository {
    private MusicDAO musicDAO;
    private LiveData<List<Music>> musicOff;
    private ArrayList<Music> musics = new ArrayList<>();
    private MutableLiveData<List<Music>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MusicRepository(Application application) {
        this.application = application;
        DBHandler dbHandler = DBHandler.getInstance(application);

        musicDAO = dbHandler.musicDAO();
    }

    public MutableLiveData<List<Music>> getMutableLiveData(String artistName) {

        MusicDataService musicDataService = RetrofitInstance.getService();

        Call<MusicDBResponse> call = musicDataService.getPopularMusic(artistName);

        call.enqueue(new Callback<MusicDBResponse>() {
            @Override
            public void onResponse(Call<MusicDBResponse> call, Response<MusicDBResponse> response) {
                MusicDBResponse musicDBResponse = response.body();


                if (musicDBResponse != null && musicDBResponse.getMusics() != null) {

                    musics = (ArrayList<Music>) musicDBResponse.getMusics();
                    insertMusic(musics);
                    mutableLiveData.setValue(musics);
                }
            }

            @Override
            public void onFailure(Call<MusicDBResponse> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }

    public LiveData<List<Music>> getAllMusicOff() {
        return musicDAO.getAllMusic();
    }

    public LiveData<List<Music>> getMusicOff(String artistName) {
        return musicDAO.getMusic(artistName);
    }

    public void insertMusic(List<Music> musics) {
        new InsertMusic(musicDAO).execute(musics);
    }

    private static class InsertMusic extends AsyncTask<List<Music>, Void, Void> {
        private MusicDAO musicDAO;

        public InsertMusic(MusicDAO musicDAO) {
            this.musicDAO = musicDAO;
        }

        @Override
        protected Void doInBackground(List<Music>... musics) {
            for (Music music : musics[0]) {
                musicDAO.insert(music);
            }

            return null;
        }
    }

}
