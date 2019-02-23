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


public class ParseLiveQueryCallback {
    private static final String ERROR_MESSAGE = "Retrieved Object Empty";
    private Context context;
    private static final String TAG = "ParseLiveQueryCallback";
    private ParseLiveQueryInterface parseLiveQueryInterface;
    private ParseLiveQueryClient parseLiveQueryClient;

    /**
     * Get parent context through constructor.
     * @param context
     */
    public ParseLiveQueryCallback(Context context) {
        this.context = context;
        /**
         * Instantiate interface
         */
        parseLiveQueryInterface = (ParseLiveQueryInterface) context;
        /**
         * Instantiate ParseLiveQueryClient = null
         */
        parseLiveQueryClient = null;
    }

    public void getLiveQueryData(String APP_NAME, String CLASS_NAME){
        try {
            /**
             * Instantiate ParseLiveQueryClient with wss
             * @param APP_NAME
             */
            parseLiveQueryClient = ParseLiveQueryClient.Factory.getClient(new URI("wss://"+APP_NAME+".back4app.io/"));

            /**
             * Create a parse query with db class name
             * Add some constraints if you want
             * Just do as usual what you do in parse query
             */
            ParseQuery<ParseObject> accidentListLiveQuery = new ParseQuery(CLASS_NAME);


            /**
             * Simply subscribe to the query
             */
            SubscriptionHandling<ParseObject> subscriptionHandling = parseLiveQueryClient.subscribe(accidentListLiveQuery);

            /**
             * Handle the subscription, if any new CREATE event occur
             */
            subscriptionHandling.handleEvent(SubscriptionHandling.Event.CREATE, new SubscriptionHandling.HandleEventCallback<ParseObject>() {
                @Override
                public void onEvent(ParseQuery<ParseObject> query, final ParseObject object) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            if(object != null){
                                /**
                                 * Notify interface if any data received from db
                                 */
                                parseLiveQueryInterface.onSuccess(object);
                            }
                            else {
                                /**
                                 * Notify interface if any error occur
                                 */
                                parseLiveQueryInterface.onFailure(ERROR_MESSAGE);
                            }
                        }
                    });
                }
            });

            /**
             * Handle the subscription, if any new UPDATE event occur
             */
            subscriptionHandling.handleEvent(SubscriptionHandling.Event.UPDATE, new SubscriptionHandling.HandleEventCallback<ParseObject>() {
                @Override
                public void onEvent(ParseQuery<ParseObject> query, final ParseObject object) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            if(object != null){
                                /**
                                 * Notify interface if any data received from db
                                 */
                                parseLiveQueryInterface.onSuccess(object);
                            }
                            else {
                                /**
                                 * Notify interface if any error occur
                                 */
                                parseLiveQueryInterface.onFailure(ERROR_MESSAGE);
                            }
                        }
                    });
                }
            });

            /**
             * Handle the subscription, if any new DELETE event occur
             */
            subscriptionHandling.handleEvent(SubscriptionHandling.Event.DELETE, new SubscriptionHandling.HandleEventCallback<ParseObject>() {
                @Override
                public void onEvent(ParseQuery<ParseObject> query, final ParseObject object) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        public void run() {
                            /**
                             * Notify interface if any data deleted from db
                             */
                            parseLiveQueryInterface.onSuccess(object);
                        }

                    });
                }
            });

        }
        /**
         * Catch if any exception occur.
         */
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
