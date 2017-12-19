package com.example.adila.songringguitarchordtabvideo;

/**
 * Created by Adila on 11-Dec-17.
 */

public class InputModel {

    String title,musician,version;

    public InputModel() {
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

    public InputModel(String title, String musician, String version) {
        this.title = title;
        this.musician = musician;
        this.version = version;
    }
}
