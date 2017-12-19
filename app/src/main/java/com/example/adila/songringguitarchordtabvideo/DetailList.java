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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailList extends AppCompatActivity {

    SharedPreferences sp;
    SharedPreferences.Editor spEdit;

    String title,musician,version,youtube,website;
    TextView titleTxt,musicianTxt,versionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);

        //get extra from tablist
        title = getIntent().getStringExtra("title").toString();
        musician = getIntent().getStringExtra("musician").toString();
        version = getIntent().getStringExtra("version").toString();
        youtube = getIntent().getStringExtra("youtube").toString();
        website = getIntent().getStringExtra("website").toString();

        titleTxt = (TextView) findViewById(R.id.title_detail);
        musicianTxt = (TextView) findViewById(R.id.musician_detail);
        versionTxt = (TextView) findViewById(R.id.version_detail);

        titleTxt.setText(title.toString());
        musicianTxt.setText(musician.toString());
        versionTxt.setText(version.toString());

        sp = getSharedPreferences(TabDownloaded.PREF_MHS,MODE_PRIVATE);
        spEdit = sp.edit();
    }

    public void youtube(View view) {
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

    public void website(View view) {
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

    //put to sharedpreference
    public void download(View view) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("titleList",title);
            jsonObject.put("musicianList",musician);
            jsonObject.put("versionList",version);
            jsonObject.put("youtubeList",youtube);
            jsonObject.put("websiteList",website);
        } catch (JSONException e){
            e.printStackTrace();
        }

        if (sp.contains("list")){
            String data = sp.getString("list","NO_DATA");

            try {
                JSONArray jsonArray = new JSONArray(data);
                jsonArray.put(jsonObject);
                spEdit.putString("list",jsonArray.toString());
                spEdit.apply();
            } catch (JSONException e){
                e.printStackTrace();
            }
        } else {
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(jsonObject);
            spEdit.putString("list",jsonArray.toString());
            spEdit.apply();
        }

        Toast.makeText(this, "song list downloaded", Toast.LENGTH_SHORT).show();

        finish();
    }
}
