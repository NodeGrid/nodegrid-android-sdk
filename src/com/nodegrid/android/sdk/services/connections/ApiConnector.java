package com.nodegrid.android.sdk.services.connections;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    /**
     * Calling the background task to sending HTTP Post using JSON data
     * @param url
     * @param reqParams
     */
    protected String sendHttpJsonPostReq(String url, JSONObject reqParams) {
        Log.d("ApiConnector", "ApiConnector:sendHttpJsonPostReq");
        this.postRequestUrl = url;
        this.reqParams = reqParams;

        try {
            SendHttpJsonPostTask sendHttpJsonPostTask = new SendHttpJsonPostTask();
            httpCommonResponse = sendHttpJsonPostTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return httpCommonResponse;
    }

    /**
     * Background Task for send HTTP POST with JSON data
     */
    private class SendHttpJsonPostTask extends AsyncTask<Void, Void, String> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(Void... params) {

            String responseResult = null;
            Log.d("ApiConnector", "ApiConnector:SendHttpJSONPostTask");

            HttpClient client = new DefaultHttpClient();
            HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000);
            HttpResponse response;

            try {
                Log.d("ApiConnector:SendHttpJSONPostTask / Req Url : ", postRequestUrl);
                HttpPost post = new HttpPost(postRequestUrl);
                Log.d("ApiConnector:SendHttpJSONPostTask / Req Params : ", reqParams.toString());
                StringEntity se = new StringEntity(reqParams.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                post.setEntity(se);
                response = client.execute(post);

                if(response != null) {
                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();

                    if(statusCode == 200) {
                        HttpEntity entity = response.getEntity();
                        InputStream in = entity.getContent();
                        BufferedReader bfr = new BufferedReader(new InputStreamReader(in));
                        String line;
                        StringBuilder builder = new StringBuilder("");
                        while ((line = bfr.readLine()) != null){
                            builder.append(line + "\n");
                        }
                        in.close();
                        String result = builder.toString();
                        responseResult = result;
                        Log.d("ApiConnector:SendHttpJSONPostTask / Status", "Success Response : " + result);
                    } else {
                        Log.d("ApiConnector:SendHttpJSONPostTask / Error status code", String.valueOf(statusCode));
                        responseResult = "error";
                    }
                } else {
                    Log.d("ApiConnector:SendHttpJSONPostTask / Error", "null response after sending http req");
                    responseResult = "error";
                }

            } catch (Exception ex) {
                Log.d("ApiConnector:SendHttpJSONPostTask / Exception", ex.toString());
            }

            return responseResult;
        }
    }
}
