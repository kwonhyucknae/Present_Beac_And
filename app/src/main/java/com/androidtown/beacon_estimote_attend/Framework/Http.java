package com.androidtown.beacon_estimote_attend.Framework;

import android.app.Activity;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by kwonhyucknae on 2017-05-11.
 */

public class Http extends Activity {
    public Http() {

    }

    public Http(Activity activity) {
        super();
    }

    public String[] doJSONParser(String json)
    {
        String[] jsona=new String[3];
        try
        {
            JSONArray jarray=new JSONArray(json);
            for(int i=0;i<jarray.length();i++)
            {
                JSONObject jobject=jarray.getJSONObject(i);
                jsona[0]=jobject.getString("Course_Name");
                jsona[1]=jobject.getString("Hakjum");
                //jsona[2]=jobject.getString("Course_Time");
            }
        }catch(JSONException e)
        {
            e.printStackTrace();
        }
    return jsona;
    }



    public class MyAsyncTask extends AsyncTask<String, Void, String> {


        protected String doInBackground(String... param) {
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
}
    /*
    public String result="";
    protected String doInBackground(String... param) {
        try {

            DefaultHttpClient client = new DefaultHttpClient(); //HttpClient 통신을 합니다.

            String postURL = "http://192.168.56.1:8080/WebPageTest/JSPAndroid/TimeTable.jsp";
            HttpPost post = new HttpPost(postURL + "?Course_Name=bigdata");

            HttpResponse response = client.execute(post);
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
            String line = null;
            //String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }

            //return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return null;
        return result;
    }
*/


/*
    public String SendByHttp()
    {
        //if (msg == null) {
          //  msg = "";
        //}

        String URL = "http://192.168.56.1:8080/WebPageTest/JSPAndroid/TimeTable.jsp"; //자신의 웹서버 주소를 저장합니다.
        DefaultHttpClient client = new DefaultHttpClient(); //HttpClient 통신을 합니다.

        try {
            HttpPost post = new HttpPost(URL + "?Course_Name=bigdata");
//웹서버로 데이터를 전송합니다. 요번에 경우에는 get방식으로 데이터를 전송하였습니다.
//간단히 설명하면 주소?데이터명=데이터내용&데이터명=데이터내용   이런식입니다.
            HttpResponse response = client.execute(post);
//데이터를 보내고 바로 데이터 응답을 받습니다.
            BufferedReader bufreader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
//받아온 데이터를 buffer에 넣습니다.
            String line = null;
            String result = "";

            while ((line = bufreader.readLine()) != null) {
                result += line;
            }
//buffer를 읽어와서 result에 넣습니다.
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            client.getConnectionManager().shutdown();
//예외처리입니다.
            return "예외처리";
        }

    }
*/


