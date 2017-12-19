package com.example.adila.songringguitarchordtabvideo;


import android.content.Context;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabDownloaded extends Fragment {

    public static String PREF_MHS = "file.fragment.download";
    DownloadAdapter downloadAdapter;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;
    View view;
    public TabDownloaded() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_downloaded, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        //swipe refresh layout
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout_tab2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllData();

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        //recyclerview
        rv = (RecyclerView) view.findViewById(R.id.recyclerView_tab2);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        getAllData();
    }

    @Override
    public void onStart() {
        super.onStart();
        getAllData();
    }

    public void getAllData(){
        SharedPreferences sp = getActivity().getSharedPreferences(PREF_MHS, Context.MODE_APPEND);
        String data = sp.getString("list", "NO_DATA");

        if (data!=null){
            try {
            JSONArray jsonArray = new JSONArray(data);
            downloadAdapter = new DownloadAdapter(getActivity(), jsonArray);

            rv.setAdapter(downloadAdapter);
            downloadAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        }else {
            JSONArray jsonArray = new JSONArray();
            downloadAdapter = new DownloadAdapter(getActivity(), jsonArray);

            rv.setAdapter(downloadAdapter);
            downloadAdapter.notifyDataSetChanged();
        }
    }
}
