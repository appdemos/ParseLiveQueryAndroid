# ParseLiveQueryAndroid

An example of "How to live query on Back4App from Android client" 

In this repository you will find an example app with Live Query working.

Step 1 - Insert App keys

Don't forget to paste your Back4App App ID and Client Key in the strings.xml file.

Step 2 - Login with existing user

Don’t forget login with a existing user.

Step 3 - Back4App Subdomain

You need to enable your Back4App Subdomain and Live Query, following [this guide](https://www.back4app.com/docs/platform/activating-webhosting).



# Library Usages

1. Implementation: 

public class MyActivity extends AppCompatActivity implements ParseLiveQueryListener.ParseLiveQueryListenerInterface{
......................
}

2. Instantiate ParseLiveQuery Listener: 

ParseLiveQueryListener parseLiveQueryListener = new ParseLiveQueryListener(this);

3. Create a usual parse query, add some constraint if you want: 

ParseQuery<ParseObject> liveQuery = ParseQuery.getQuery("CLASS_NAME_HERE");

4. Add the live event listener to the query: 

parseLiveQueryListener.addLiveEventListener("APP_NAME", liveQuery);



# Library Implementation

1. Add it in your root build.gradle at the end of repositories:

allprojects {
	repositories {
	maven { url 'https://jitpack.io' }
	}
}

2. Add the dependency

implementation 'com.github.rommansabbir:ParseLiveQuery-Android:Tag'

remove the Tag with specific version, currently "v1.0"



