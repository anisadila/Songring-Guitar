package com.example.adila.songringguitarchordtabvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSong extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    EditText title,musician,version,youtube,website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        title = (EditText) findViewById(R.id.title_add);
        musician = (EditText) findViewById(R.id.musician_add);
        version = (EditText) findViewById(R.id.version_add);
        youtube = (EditText) findViewById(R.id.youtube_add);
        website = (EditText) findViewById(R.id.website_add);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("songlist");
    }

    public void sentAdd(View view) {
        addSong();
        clearForm();
    }

    //clear form after send form
    private void clearForm() {
        title.setText("");
        musician.setText("");
        version.setText("");
        youtube.setText("");
        website.setText("");
    }

    //add to table songlist in firebase
    private void addSong() {
        String post_title = title.getText().toString().trim();
        String post_musician = musician.getText().toString().trim();
        String post_version = version.getText().toString().trim();
        String post_yt = youtube.getText().toString().trim();
        String post_web = website.getText().toString().trim();

        if (TextUtils.isEmpty(post_title)){
            Toast.makeText(this, "Please Input Title", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(post_musician)){
            Toast.makeText(this, "Please Input Singer/Musician", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(post_version)){
            Toast.makeText(this, "Please Input Version", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(post_yt)){
//            Toast.makeText(this, "Please Input Youtube url", Toast.LENGTH_SHORT).show();
//        }else if (TextUtils.isEmpty(post_web)){
//            Toast.makeText(this, "Please Input Website url", Toast.LENGTH_SHORT).show();
        }else{
            String id = reference.push().getKey();
            SongList input = new SongList(post_title,post_musician,post_version,post_yt,post_web);

            reference.child(id).setValue(input);
            Toast.makeText(this, "Form Sent", Toast.LENGTH_LONG).show();
//            finish();
        }
    }
}
