package com.rommansabbir.back4app.livequeryexample.callbacks;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.livequery.ParseLiveQueryClient;
import com.parse.livequery.SubscriptionHandling;

import java.net.URI;
import java.net.URISyntaxException;


public class ParseLiveQueryCallback extends AppCompatActivity {
    private static final String ERROR_MESSAGE = "Retrieved Object Empty";
    private Context context;
    private static String APP_NAME="YOUR_APP_NAME_HERE";
    private static String CLASS_NAME = "YOUR_CLASS_NAME_HERE";
    private static final String TAG = "ParseLiveQueryCallback";
    private ParseLiveQueryInterface parseLiveQueryInterface;


    //construct this class with context
    public ParseLiveQueryCallback(Context context) {
        this.context = context;
    }

    public void getLiveQueryData(){
        //instantiate interface
        parseLiveQueryInterface = (ParseLiveQueryInterface) context;

        ParseLiveQueryClient parseLiveQueryClient = null;
        try {
            //instantiate parse live query client
            parseLiveQueryClient = ParseLiveQueryClient.Factory.getClient(new URI("wss://"+APP_NAME+".back4app.io/"));

            //create a query and add condition if you want
            ParseQuery<ParseObject> accidentListLiveQuery = new ParseQuery(CLASS_NAME);

            //subscribe to the query
            SubscriptionHandling<ParseObject> subscriptionHandling = parseLiveQueryClient.subscribe(accidentListLiveQuery);

            //handle on CREATE subscription
            subscriptionHandling.handleEvent(SubscriptionHandling.Event.CREATE, new SubscriptionHandling.HandleEventCallback<ParseObject>() {
                @Override
                public void onEvent(ParseQuery<ParseObject> query, final ParseObject object) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            if(object != null){
                                //TODO implement your logic here
                                parseLiveQueryInterface.onSuccess(object);
                            }
                            else {
                                //TODO implement your logic here
                                parseLiveQueryInterface.onFailure(ERROR_MESSAGE);
                            }
                        }
                    });
                }
            });

            //handle on UPDATE subscription
            subscriptionHandling.handleEvent(SubscriptionHandling.Event.UPDATE, new SubscriptionHandling.HandleEventCallback<ParseObject>() {
                @Override
                public void onEvent(ParseQuery<ParseObject> query, final ParseObject object) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            if(object != null){
                                //TODO implement your logic here
                                parseLiveQueryInterface.onSuccess(object);
                            }
                            else {
                                //TODO implement your logic here
                                parseLiveQueryInterface.onFailure(ERROR_MESSAGE);
                            }
                        }
                    });
                }
            });

            //handle on DELETE subscription
            subscriptionHandling.handleEvent(SubscriptionHandling.Event.DELETE, new SubscriptionHandling.HandleEventCallback<ParseObject>() {
                @Override
                public void onEvent(ParseQuery<ParseObject> query, final ParseObject object) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            //TODO implement your logic here
                            parseLiveQueryInterface.onSuccess(object);
                        }

                    });
                }
            });

        }
        //catch any exception if occurs
        catch (URISyntaxException e) {
            e.printStackTrace();
            parseLiveQueryInterface.onFailure(e.getMessage());
        }
    }


    public interface ParseLiveQueryInterface {
        void onSuccess(ParseObject retrievedObject);
        void onFailure (String errorMessage);
    }
}
