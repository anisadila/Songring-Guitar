package com.example.adila.songringguitarchordtabvideo;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Adila on 11-Dec-17.
 */

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.ViewHolder>{

    Context context;
    JSONArray jsonArray;
    JSONObject jsonObject;

    String titledet ;
    String musiciandet;
    String versiondet;
    String youtubedet;
    String websitedet;

    public DownloadAdapter(Context context, JSONArray jsonArray){
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            jsonObject = jsonArray.getJSONObject(position);
            holder.title.setText(jsonObject.getString("titleList"));
            holder.musician.setText(jsonObject.getString("musicianList"));
            holder.version.setText(jsonObject.getString("versionList"));

        } catch (JSONException e){
            e.printStackTrace();
        }

        //put parcel to detaildownload
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        titledet = jsonObject.getString("titleList");
                        musiciandet = jsonObject.getString("musicianList");
                        versiondet = jsonObject.getString("versionList");
                        youtubedet = jsonObject.getString("youtubeList");
                        websitedet = jsonObject.getString("websiteList");

                        Intent i = new Intent(context,DetailDownloaded.class);

                        DetailModel detailModel = new DetailModel();

                        detailModel.setTitle(titledet);
                        detailModel.setMusician(musiciandet);
                        detailModel.setVersion(versiondet);
                        detailModel.setYoutube(youtubedet);
                        detailModel.setWebsite(websitedet);

                        i.putExtra(DetailDownloaded.DETAIL_EXTRA,detailModel);

                        context.startActivity(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
        });
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,musician,version;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title_list);
            musician = (TextView) itemView.findViewById(R.id.musician_list);
            version = (TextView) itemView.findViewById(R.id.version_list);
        }
    }
}
