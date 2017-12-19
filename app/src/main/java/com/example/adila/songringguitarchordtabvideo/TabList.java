package com.example.adila.songringguitarchordtabvideo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabList extends Fragment {

    FirebaseDatabase database;
    DatabaseReference DBRef;
    FirebaseRecyclerAdapter<SongList, MainActivity.SongListViewHolder> firebaseRecyclerAdapter;

    RecyclerView rv;
    SwipeRefreshLayout refreshLayout;
    View view;
    ProgressDialog pr;

    public TabList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //progress dialog
        pr = new ProgressDialog(getActivity());
        pr.setMessage("please wait" + "\n" + "internet is connecting");
        pr.show();

        //swipe refresh layout
        refreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rv.setAdapter(firebaseRecyclerAdapter);
                refreshLayout.setRefreshing(false);
            }
        });

       //recyclerview
        rv = (RecyclerView)view.findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        init();
    }

    private void init(){
        //send query to database
        database = FirebaseDatabase.getInstance();
        DBRef = database.getReference("songlist");

        //Adapter firebase
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SongList, MainActivity.SongListViewHolder>(
                SongList.class,R.layout.data_list, MainActivity.SongListViewHolder.class, DBRef) {
            @Override
            protected void populateViewHolder(MainActivity.SongListViewHolder viewHolder, final SongList model, int position) {

                viewHolder.title.setText(model.getTitle());
                viewHolder.musician.setText(model.getMusician());
                viewHolder.version.setText(model.getVersion());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), DetailList.class);
                        i.putExtra("title",model.getTitle());
                        i.putExtra("musician",model.getMusician());
                        i.putExtra("version",model.getVersion());
                        i.putExtra("youtube",model.getYoutube());
                        i.putExtra("website",model.getWebsite());

                        startActivity(i);
                    }
                });
                pr.dismiss();
            }
        };
        rv.setAdapter(firebaseRecyclerAdapter);
    }
}
