package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.Joker;
import com.example.brianroper.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by brianroper on 5/11/16.
 */
public class EndpointsTask extends AsyncTask<Context, Void, String> {

    private static MyApi sMyApi = null;
    private Context mContext;

    @Override
    protected String doInBackground(Context... params) {

        if(sMyApi == null){

            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {

                            request.setDisableGZipContent(true);
                        }
                    });

            sMyApi = builder.build();
        }

        mContext = params[0];

        try{

            Joker joker = new Joker();
            String joke = joker.getJoke();

            return sMyApi.showJoke(joke).execute().getData();
        }
        catch (IOException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
    }
}
