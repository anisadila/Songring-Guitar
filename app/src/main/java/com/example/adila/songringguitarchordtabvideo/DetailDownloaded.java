package com.example.adila.songringguitarchordtabvideo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailDownloaded extends AppCompatActivity{
    final static public String DETAIL_EXTRA = "";
    TextView title_download,musician_download,version_downlaod;
    String title,musician,version,youtube,website;
    DetailModel detailModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_downloaded);

        title_download = (TextView) findViewById(R.id.title_detail_off);
        musician_download = (TextView) findViewById(R.id.musician_detail_off);
        version_downlaod = (TextView) findViewById(R.id.version_detail_off);

        //get parcel from holder sharedpreference in downloadadapter
        detailModel = getIntent().getParcelableExtra(DETAIL_EXTRA);

        title = detailModel.getTitle();
        musician = detailModel.getMusician();
        version = detailModel.getVersion();
        youtube = detailModel.getYoutube();
        website = detailModel.getWebsite();

        title_download.setText(title);
        musician_download.setText(musician);
        version_downlaod.setText(version);
    }

    public void youtube_off(View view) {
        try {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse(youtube));
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, "Sorry, link unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    public void website_off(View view) {
        try {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse(website));
            startActivity(i);
        }catch (Exception e){
            Toast.makeText(this, "Sorry, link unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}
