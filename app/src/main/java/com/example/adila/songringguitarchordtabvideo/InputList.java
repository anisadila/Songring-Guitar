package com.example.adila.songringguitarchordtabvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputList extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    EditText inp_title,inp_musician,inp_version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_list);

        inp_title = (EditText) findViewById(R.id.title_req);
        inp_musician = (EditText) findViewById(R.id.musician_req);
        inp_version = (EditText) findViewById(R.id.version_req);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("request");
    }

    public void send(View view) {
        addData();
        clearText();
    }

    //add request to table request firebase
    private void addData() {
        String post_title = inp_title.getText().toString().trim();
        String post_musician = inp_musician.getText().toString().trim();
        String post_version = inp_version.getText().toString().trim();
        if (TextUtils.isEmpty(post_title)){
            Toast.makeText(this, "Please Input Title", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(post_musician)){
            Toast.makeText(this, "Please Input Singer/Musician", Toast.LENGTH_SHORT).show();
        }else{
            String id = reference.push().getKey();
            InputModel input = new InputModel(post_title,post_musician,post_version);

            reference.child(id).setValue(input);
//            reference.child(id).child("user").setValue(post_user);
//            reference.child(id).child("pesan").setValue(post_pesan);
            Toast.makeText(this, "Request Sent", Toast.LENGTH_LONG).show();
//            finish();
        }
    }

    //clear form after sent
    public void clearText(){
        inp_title.setText("");
        inp_musician.setText("");
        inp_version.setText("");
    }
}
