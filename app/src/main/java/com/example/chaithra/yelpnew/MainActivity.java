package com.example.chaithra.yelpnew;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Place>> {
    private static final String PLACE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    private static final int MEO_LOADER_ID = 1;

    Button button;
    ListView listView;
    PlaceAdapter placeAdapter;
    EditText editText;
    TextView textView;
    double latitude = 37.347907;
    double longitude = -121.998382;
    private static final String GOOGLE_API_KEY = "AIzaSyAUmxbolGpzD8zTaduewM55zIPbvzyFDzU";
    StringBuilder googlePlacesUrl;
    LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.search);
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.text_to_search);
        listView = (ListView) findViewById(R.id.list);
        placeAdapter = new PlaceAdapter(MainActivity.this, 0);
        getSupportLoaderManager();
        listView.setAdapter(placeAdapter);
        loaderManager = getLoaderManager();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isonline = isOnline();
                if (isonline == false) {
                    textView.setText("No network connected");
                } else {
                    textView.setVisibility(View.INVISIBLE);
                    editText.clearFocus();
                    placeAdapter.clear();
                    googlePlacesUrl = new StringBuilder(PLACE_URL);
                    googlePlacesUrl.append("location=" + latitude + "," + longitude);
                    googlePlacesUrl.append("&radius=500");
                    googlePlacesUrl.append("&sensor=true");

                    String searchText = editText.getText().toString();

                    googlePlacesUrl.append("&keyword=" + (TextUtils.isEmpty(searchText) ? "test_result" : searchText));
                    Toast.makeText(MainActivity.this, "" + editText.getText().toString(), Toast.LENGTH_LONG).show();
                    googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);
                    Log.e("MainActivity", String.valueOf(googlePlacesUrl));



                    if (loaderManager == null) {
                        loaderManager.initLoader(MEO_LOADER_ID, null, MainActivity.this);
                    } else {
                        loaderManager.restartLoader(MEO_LOADER_ID, null, MainActivity.this);
                    }
                }
            }


        });


    }

    private String generateQueryParameters(int volume, String searchText) {
        return "maxVolume=" + volume + "&q=" + (TextUtils.isEmpty(searchText) ? "test_result" : searchText);
    }

    @Override
    public Loader<ArrayList<Place>> onCreateLoader(int id, Bundle args) {

        return new placeLoader(this, String.valueOf(googlePlacesUrl));

    }


    public void updateUi() {

        listView.setAdapter(placeAdapter);
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Place>> loader, ArrayList<Place> data) {


        placeAdapter.clear();
        if (data != null) {
            placeAdapter.addAll(data);
        }
        placeAdapter.notifyDataSetChanged();
        updateUi();


    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Place>> loader) {
        if (placeAdapter != null) {
            placeAdapter.clear();
        }

    }
}

