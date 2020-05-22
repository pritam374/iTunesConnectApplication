package com.pritamNew.project.iTunesConnectApp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pritamNew.project.iTunesConnectApp.R;
import com.pritamNew.project.iTunesConnectApp.databinding.MusicListItemBinding;
import com.pritamNew.project.iTunesConnectApp.model.Music;

import java.util.ArrayList;
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder>{

    private Context context;
    private ArrayList<Music> MusicArrayList;

    public MusicAdapter(Context context, ArrayList<Music> MusicArrayList) {
        this.context = context;
        this.MusicArrayList = MusicArrayList;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         MusicListItemBinding MusicListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
         ,R.layout.music_list_item,parent,false);

        return new MusicViewHolder(MusicListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {

        Music Music=MusicArrayList.get(position);
        holder.musicListItemBinding.setMusic(Music);

    }

    @Override
    public int getItemCount() {
        return MusicArrayList.size();
    }


    public class MusicViewHolder extends RecyclerView.ViewHolder {
     private MusicListItemBinding musicListItemBinding;


        public MusicViewHolder(@NonNull MusicListItemBinding musicListItemBinding) {
            super(musicListItemBinding.getRoot());
            this.musicListItemBinding=musicListItemBinding;

            musicListItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();

                    if(position!=RecyclerView.NO_POSITION) {

                        Music selctedMusic = MusicArrayList.get(position);

//                        Intent intent=new Intent(context, MO.class);
//                        intent.putExtra("Music",selctedMusic);
//                        context.startActivity(intent);



                    }


                }
            });


        }
    }
}
