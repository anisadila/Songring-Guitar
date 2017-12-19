package com.example.adila.songringguitarchordtabvideo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShowRequest extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference dbref;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_request);

        rv = (RecyclerView) findViewById(R.id.rvShowReq);
        rv.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseDatabase.getInstance();
        dbref = db.getReference("request");

        FirebaseRecyclerAdapter<ShowRequestModel,ShowRequestModelViewHolder> firebaseRecyclerAdapter =

                new FirebaseRecyclerAdapter<ShowRequestModel, ShowRequestModelViewHolder>(
                        ShowRequestModel.class, R.layout.data_list, ShowRequestModelViewHolder.class, dbref
                ) {
                    @Override
                    protected void populateViewHolder(ShowRequestModelViewHolder viewHolder, ShowRequestModel model, int position) {
                        viewHolder.title.setText(model.getTitle());
                        viewHolder.musician.setText(model.getMusician());
                        viewHolder.version.setText(model.getVersion());
                    }
                };
                rv.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ShowRequestModelViewHolder extends RecyclerView.ViewHolder {
        TextView title,musician,version;
        public ShowRequestModelViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_list);
            musician = (TextView) itemView.findViewById(R.id.musician_list);
            version = (TextView) itemView.findViewById(R.id.version_list);
        }
    }
}
