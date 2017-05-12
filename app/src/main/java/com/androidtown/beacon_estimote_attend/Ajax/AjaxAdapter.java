package com.androidtown.beacon_estimote_attend.Ajax;

import android.app.Activity;

import com.androidquery.AQuery;

/**
 * Created by kwonhyucknae on 2017-05-11.
 */

public class AjaxAdapter {
    protected AQuery aq;
    protected Activity activity;

    public AjaxAdapter()
    {

    }
    public AjaxAdapter(Activity activity)
    {
        this.activity=activity;
        aq=new AQuery(activity);
    }

    protected String httpUrl()
    {
     return "http://61.84.147.108:8080/WebPageTest";
    }

    protected String httpUrl(String path){

        return "http://61.84.147.108:8080/WebPageTest"+path;
    }
}
