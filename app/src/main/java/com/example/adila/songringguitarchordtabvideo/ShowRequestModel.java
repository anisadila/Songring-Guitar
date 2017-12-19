package com.example.adila.songringguitarchordtabvideo;

/**
 * Created by Adila on 17-Dec-17.
 */

public class ShowRequestModel {
    String title,musician,version;

    public ShowRequestModel() {
    }

    public ShowRequestModel(String title, String musician, String version) {
        this.title = title;
        this.musician = musician;
        this.version = version;
    }

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
}
