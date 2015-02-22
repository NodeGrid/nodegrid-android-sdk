package com.nodegrid.android.sdk.services.connections;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by kavi707 on 2/22/15.
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class ApiConnector {

    private String getRequestUrl;
    private String postRequestUrl;
    private JSONObject reqParams;
    private String httpCommonResponse  = "NULL";

    /**
     * method for sending http get requests to api
     * @param requestUrl
     * @return String object (returns a json string)
     */
    protected String sendHttpGet(String requestUrl) {

        Log.d("ApiConnector", "ApiConnector:sendHttpGet");
        this.getRequestUrl = requestUrl;

        try {
            sendHttpGetTask sendHttpGetTask = new sendHttpGetTask();
            httpCommonResponse = sendHttpGetTask.execute().get();
        }  catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return httpCommonResponse;
    }

    /**
     * Background Task for send HTTP GET
     */
    private class sendHttpGetTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            Log.d("ApiConnector", "ApiConnector:sendHttpGetTask");
            String responseResult = null;

            HttpClient httpclient = new DefaultHttpClient();
            Log.d("ApiConnector:sendHttpGetTask / Req Url : ", getRequestUrl);
            HttpGet request = new HttpGet(getRequestUrl);
            ResponseHandler<String> handler = new BasicResponseHandler();
            try {
                responseResult = httpclient.execute(request, handler);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            httpclient.getConnectionManager().shutdown();

            return responseResult;
        }
    }
}
