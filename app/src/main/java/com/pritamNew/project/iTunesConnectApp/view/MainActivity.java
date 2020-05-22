package com.pritamNew.project.iTunesConnectApp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pritamNew.project.iTunesConnectApp.R;
import com.pritamNew.project.iTunesConnectApp.adapter.MusicAdapter;
import com.pritamNew.project.iTunesConnectApp.databinding.ActivityMainBinding;
import com.pritamNew.project.iTunesConnectApp.model.Music;
import com.pritamNew.project.iTunesConnectApp.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnSearch;
    private ArrayList<Music> musics;
    private RecyclerView recyclerView;
    private MusicAdapter musicAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("iTune Music");

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (etSearch.getText().toString().length() > 0) {
                    getPopularMusics(etSearch.getText().toString());
                } else {
                    if (swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }

            }
        });

        btnSearch = activityMainBinding.btnSearch;
        etSearch = activityMainBinding.etSearch;

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSearch.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), "Please enter artist name", Toast.LENGTH_SHORT).show();
                    return;
                }
                getPopularMusics(etSearch.getText().toString());
            }
        });

//      boolean isMetered = isNetworkAvailable();
//        Toast.makeText(getApplicationContext(),"Connection = "+isMetered,Toast.LENGTH_SHORT).show();

    }

    public void getPopularMusics(String artistName) {

        mainActivityViewModel.getAllMusic(artistName).observe(this, new Observer<List<Music>>() {
            @Override
            public void onChanged(@Nullable List<Music> musicsFromLiveData) {
                musics = (ArrayList<Music>) musicsFromLiveData;
                showOnRecyclerView();
            }
        });


    }

    private void showOnRecyclerView() {


        recyclerView = activityMainBinding.rvMovies;
        musicAdapter = new MusicAdapter(this, musics);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {


            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(musicAdapter);
        musicAdapter.notifyDataSetChanged();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

    }


}
