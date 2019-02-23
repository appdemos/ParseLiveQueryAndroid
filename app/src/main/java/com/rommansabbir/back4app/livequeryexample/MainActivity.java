package com.rommansabbir.back4app.livequeryexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rommansabbir.back4app.livequeryexample.callbacks.ParseLiveQueryCallback;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity implements ParseLiveQueryCallback.ParseLiveQueryInterface {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instantiate ParseLiveQueryCallback
        ParseLiveQueryCallback parseLiveQueryCallback = new ParseLiveQueryCallback(this);

        //call getLiveQueryData() from callback to activate liveQuery
        parseLiveQueryCallback.getLiveQueryData("swurvintest", "Accident");

    }

    @Override
    public void onSuccess(ParseObject retrievedObject) {
        //TODO implement your logic here
        Log.d(TAG, "onSuccess: ");

    }

    @Override
    public void onFailure(String errorMessage) {
        //TODO implement your logic here
        Log.d(TAG, "onFailure: ");
    }
}
