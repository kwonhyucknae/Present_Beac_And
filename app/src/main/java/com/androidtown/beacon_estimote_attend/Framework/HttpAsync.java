package com.androidtown.beacon_estimote_attend.Framework;

import android.app.Activity;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by kwonhyucknae on 2017-05-11.
 */

public class HttpAsync extends AsyncTask<String, Void, String> {
    public HttpAsync()
    {
    }
    public HttpAsync(Activity activity) {
        super();
    }

    @Override
    protected String doInBackground(String...params){
        String result = "";
        try {

            DefaultHttpClient client = new DefaultHttpClient(); //HttpClient 통신을 합니다.

            String postURL = "http://61.84.147.108:8080/WebPageTest/JSPAndroid/TimeTable.jsp";
            HttpPost post = new HttpPost(postURL + "?Course_Name=bigdata");

            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            //String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return null;
        return result;
    }

}
