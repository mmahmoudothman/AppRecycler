package com.example.osos.apprecycler;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CutomeInfoWindAdapter implements GoogleMap.InfoWindowAdapter {
    private final View mWindow;
    private Context mContext;
    public CutomeInfoWindAdapter(View mWindow, Context mContext) {
        this.mWindow = mWindow;
        this.mContext = mContext;
    }

    private void rendrawWindowText(Marker marker,View view){
        String Title=marker.getTitle();
        TextView tvTitle=view.findViewById(R.id.windoow_title);

        if(!Title.equals("")){
            tvTitle.setText(Title);
        }

        String Snippet=marker.getSnippet();
        TextView tvSnippet=view.findViewById(R.id.windoow_location);

        if(!Snippet.equals("")){
            tvTitle.setText((CharSequence) tvSnippet);
        }

    }



    @Override
    public View getInfoWindow(Marker marker) {
        rendrawWindowText(marker,mWindow);

        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendrawWindowText(marker,mWindow);
        return null;
    }
}
