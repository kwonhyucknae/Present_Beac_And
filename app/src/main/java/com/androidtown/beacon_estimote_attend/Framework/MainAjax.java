package com.androidtown.beacon_estimote_attend.Framework;

import android.app.Activity;

import com.androidquery.callback.AjaxCallback;
import com.androidtown.beacon_estimote_attend.Ajax.AjaxAdapter;

import org.json.JSONArray;

import java.util.HashMap;


/**
 * Created by kwonhyucknae on 2017-05-11.
 */

public class MainAjax extends AjaxAdapter{

    private String TimeTable_info="/JSPAndroid/TimeTable.jsp";

    public MainAjax()
    {}
    public MainAjax(Activity activity){super(activity);}

    public void getTimeTable(String Course_Name,AjaxCallback callback)
    {
        HashMap<String,Object> map=new HashMap<>();

        map.put("Course_Name",Course_Name);

        aq.ajax(httpUrl(TimeTable_info),map, JSONArray.class,callback);
        
    }


}
