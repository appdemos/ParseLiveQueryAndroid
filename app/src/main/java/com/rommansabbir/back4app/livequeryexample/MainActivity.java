package com.rommansabbir.back4app.livequeryexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.parse.ParseQuery;
import com.parse.ParseObject;
import com.rommansabbir.parselivequery.ParseLiveQueryListener;

public class MainActivity extends AppCompatActivity implements ParseLiveQueryListener.ParseLiveQueryListenerInterface {
    private static final String TAG = "MainActivity";
    private ParseLiveQueryListener parseLiveQueryListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Instantiate ParseLiveQueryListener
         */
        parseLiveQueryListener = new ParseLiveQueryListener(this);

        /**
         * Create a usual parse query, add some constraint if you want
         */
        ParseQuery<ParseObject> liveQuery = ParseQuery.getQuery("CLASS_NAME_HERE");

        /**
         * Add live event listener to your query
         * Provide the name of your app, pass the liveQuery to the live event listener
         * @APP_NAME
         * @liveQuery
         */
        parseLiveQueryListener.addLiveEventListener("APP_NAME", liveQuery);

    }

    @Override
    public void onEventListenerSuccess(ParseObject retrievedObject) {
        //TODO implement your logic here
        Log.d(TAG, "onEventListenerSuccess: ");

    }

    @Override
    public void onEventListenerFailure(String errorMessage) {
        //TODO implement your logic here
        Log.d(TAG, "onEventListenerFailure: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * Destroy the callback after it usages
         */
        parseLiveQueryListener.destroyCallback();
    }
}
