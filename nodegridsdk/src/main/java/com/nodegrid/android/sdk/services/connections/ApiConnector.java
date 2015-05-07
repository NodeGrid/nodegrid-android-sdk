package com.nodegrid.android.sdk.services.connections;

import android.os.AsyncTask;
import android.util.Log;

import com.nodegrid.android.sdk.CommonUtils;
import com.nodegrid.android.sdk.data.NodeGridData;
import com.nodegrid.android.sdk.data.NodeGridResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by kwijewardana on 4/7/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class ApiConnector {

    private String requestUrl;
    private String requestMethod;
    private String postRequestUrl;
    private Map<String, String> getAdditionalHeaders;
    private JSONObject reqParams;
    private String httpCommonResponse  = "NULL";

    /**
     * Method for sending HTTP GET, PUT or DELETE requests to api
     * @param url End point url (String)
     * @param requestMethod Request method HTTP GET, PUT or DELETE (String)
     * @param additionalHeaders Request HTTP headers (Map<String, String> - header key & heade value)
     * @return String object (returns a json string)
     */
    protected String sendHttpRequest(String url, String requestMethod, Map<String, String> additionalHeaders) {

        Log.d("ApiConnector", "ApiConnector:sendHttpRequest");
        this.requestUrl = url;
        this.requestMethod = requestMethod;
        this.getAdditionalHeaders = additionalHeaders;

        try {
            sendHttpRequestTask sendHttpRequestTask = new sendHttpRequestTask();
            httpCommonResponse = sendHttpRequestTask.execute().get();
        }  catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        getNodeGridResponse(httpCommonResponse);
        return httpCommonResponse;
    }

    /**
     * Background Task for send HTTP GET, PUT, or DELETE Request
     */
    private class sendHttpRequestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            Log.d("ApiConnector", "ApiConnector:sendHttpGetTask");
            String responseResult = null;

            HttpClient httpclient = new DefaultHttpClient();
            Log.d("ApiConnector", "ApiConnector:sendHttpGetTask / Req Url : " + requestUrl);

            HttpRequestBase request;

            if (requestMethod.equals(CommonUtils.HTTP_DELETE)) {
                request = new HttpDelete(requestUrl);
            } else if (requestMethod.equals(CommonUtils.HTTP_PUT)) {
                request = new HttpPut(requestUrl);
            } else {
                request = new HttpGet(requestUrl);
            }

            if (getAdditionalHeaders != null) {
                Set<String> headerKeys = getAdditionalHeaders.keySet();
                for (String key: headerKeys) {
                    request.setHeader(key, getAdditionalHeaders.get(key));
                }
            }

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
     * Method for sending HTTP POST requests to api using JSON data
     * @param url End point url (String)
     * @param additionalHeader Request HTTP headers (Map<String, String> - header key & heade value)
     * @param reqParams POST request body parameters (JSONObject)
     * @return String object (returns a json string)
     */
    protected String sendHttpJsonPostReq(String url, Map<String, String> additionalHeader, JSONObject reqParams) {
        Log.d("ApiConnector", "ApiConnector:sendHttpJsonPostReq");
        this.postRequestUrl = url;
        this.getAdditionalHeaders = additionalHeader;
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
                Log.d("ApiConnector", "ApiConnector:SendHttpJSONPostTask / Req Url : " + postRequestUrl);
                HttpPost post = new HttpPost(postRequestUrl);
                Log.d("ApiConnector", "ApiConnector:SendHttpJSONPostTask / Req Params : " + reqParams.toString());
                StringEntity se = new StringEntity(reqParams.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                post.setEntity(se);

                if (getAdditionalHeaders != null) {
                    Set<String> headerKeys = getAdditionalHeaders.keySet();
                    for (String key: headerKeys) {
                        post.setHeader(key, getAdditionalHeaders.get(key));
                    }
                }

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
                        Log.d("ApiConnector", "ApiConnector:SendHttpJSONPostTask / Status: Success Response : " + result);
                    } else {
                        Log.d("ApiConnector", "ApiConnector:SendHttpJSONPostTask / Error status code: " + String.valueOf(statusCode));
                        responseResult = "error";
                    }
                } else {
                    Log.d("ApiConnector", "ApiConnector:SendHttpJSONPostTask / Error: null response after sending http req");
                    responseResult = "error";
                }

            } catch (Exception ex) {
                Log.d("ApiConnector", "ApiConnector:SendHttpJSONPostTask / Exception: " + ex.toString());
            }

            return responseResult;
        }
    }

    private NodeGridResponse getNodeGridResponse(String responseString) {
        NodeGridResponse nodeGridResponse = new NodeGridResponse();
        try {
            JSONObject responseJson = new JSONObject(responseString);
            nodeGridResponse.setStatus(responseJson.getString("status"));
            nodeGridResponse.setMessage(responseJson.getString("msg"));
            if (!responseJson.isNull("data")) {
                JSONArray dataArray = responseJson.getJSONArray("data");
            }

            Log.d(">>>>>>>>>>>>>>>", nodeGridResponse.getStatus());
            Log.d(">>>>>>>>>>>>>>>", nodeGridResponse.getMessage());
            Log.d(">>>>>>>>>>>>>>>", String.valueOf(responseJson.isNull("data")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nodeGridResponse;
    }
}
