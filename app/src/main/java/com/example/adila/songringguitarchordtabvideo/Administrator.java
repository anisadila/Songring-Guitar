package com.example.adila.songringguitarchordtabvideo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Administrator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);
    }

    //intent to show request page
    public void showreq(View view) {
        Intent i = new Intent(this,ShowRequest.class);
        startActivity(i);
    }

    //intent to add song form
    public void addsong(View view) {
        Intent i = new Intent(this,AddSong.class);
        startActivity(i);
    }
}
