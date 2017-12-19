package com.example.adila.songringguitarchordtabvideo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Adila on 15-Dec-17.
 */

public class DetailModel implements Parcelable {
    String title,musician,version,youtube,website;

    public DetailModel(){
        super();
    }

    protected DetailModel(Parcel in) {
        title = in.readString();
        musician = in.readString();
        version = in.readString();
        youtube = in.readString();
        website = in.readString();
    }

    public static final Creator<DetailModel> CREATOR = new Creator<DetailModel>() {
        @Override
        public DetailModel createFromParcel(Parcel in) {
            return new DetailModel(in);
        }

        @Override
        public DetailModel[] newArray(int size) {
            return new DetailModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(musician);
        dest.writeString(version);
        dest.writeString(youtube);
        dest.writeString(website);
    }
}
