package com.example.adila.songringguitarchordtabvideo;

/**
 * Created by Adila on 11-Dec-17.
 */

public class SongList {

    public SongList(){}

    String title,musician,version,youtube,website;

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

    public SongList(String title, String musician, String version, String youtube, String website) {
        this.title = title;
        this.musician = musician;
        this.version = version;
        this.youtube = youtube;
        this.website = website;
    }
}
