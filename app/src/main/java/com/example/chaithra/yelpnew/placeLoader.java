package com.example.chaithra.yelpnew;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class placeLoader extends AsyncTaskLoader<ArrayList<Place>> {
    public static final String LOG_TAG = placeLoader.class.getSimpleName();
    private String url;

    public placeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Place> loadInBackground() {
        if (url == null) {
            return null;
        }
        ArrayList<Place> newslist = Utils.fetchMeowData(url);
        if (newslist.size() == 0) {
            Log.d(LOG_TAG, "new list is empty");
        }
        return newslist;
    }
}
