package com.pritamNew.project.iTunesConnectApp.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicDBResponse implements Parcelable
{

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;
    @SerializedName("results")
    @Expose
    private List<Music> musics = null;
    public final static Parcelable.Creator<MusicDBResponse> CREATOR = new Creator<MusicDBResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MusicDBResponse createFromParcel(Parcel in) {
            return new MusicDBResponse(in);
        }

        public MusicDBResponse[] newArray(int size) {
            return (new MusicDBResponse[size]);
        }

    }
            ;

    protected MusicDBResponse(Parcel in) {
        this.resultCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.musics, (Music.class.getClassLoader()));
    }

    public MusicDBResponse() {
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setResults(List<Music> musics) {
        this.musics = musics;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(resultCount);
        dest.writeList(musics);
    }

    public int describeContents() {
        return 0;
    }

}