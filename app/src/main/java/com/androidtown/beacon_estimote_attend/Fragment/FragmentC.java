package com.androidtown.beacon_estimote_attend.Fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidtown.beacon_estimote_attend.Framework.MainAjax;
import com.androidtown.beacon_estimote_attend.ListView.ListViewAdapter;
import com.androidtown.beacon_estimote_attend.R;

import org.json.JSONArray;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kwonhyucknae on 2017-05-07.
 */

public class FragmentC extends Fragment{

    private static String sLog="ListView";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.thirdpage_fagment,container,false);

    }
    @Override
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);

        MainAjax ajax=new MainAjax(this.getActivity());


        SharedPreferences prefs = this.getActivity().getSharedPreferences("Login",MODE_PRIVATE);
        String strid=prefs.getString("id","");

        ajax.getListView(strid,new AjaxCallback<JSONArray>()
        {
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status)
            {
                if(object!=null)
                {
                    try {
                        ListView listview ;
                        ListViewAdapter adapter;

                        // Adapter 생성
                        adapter = new ListViewAdapter() ;

                        // 리스트뷰 참조 및 Adapter달기
                        listview = (ListView) getView().findViewById(R.id.ListView1);
                        listview.setAdapter(adapter);
                        //if(object.length()!=0)
                        //{
                        int leng=object.length();
                        for(int i=0;i<leng;i++) {


                            String title = object.getJSONObject(i).get("Course_Name").toString();
                            String present = object.getJSONObject(i).get("present").toString();
                            String gyulsuk = object.getJSONObject(i).get("gyulsuk").toString();
                            String jigak = object.getJSONObject(i).get("jigak").toString();
                            String jotae = object.getJSONObject(i).get("jotae").toString();
                            //int endt = Integer.parseInt(end);

                            adapter.addItem(title, present,gyulsuk,jigak,jotae) ;
                        }

                    }catch (Exception e)
                    {
                        Log.e(sLog,e.getMessage());

                    }

                }else
                {
                    //monday[0].setText("object가 null이야");
                    //monday[0].setBackgroundColor(Color.LTGRAY);
                }
            }
        });

        // 첫 번째 아이템 추가.
        //adapter.addItem("BigData", "1","1","1","1") ;
        // 두 번째 아이템 추가.
        //adapter.addItem("FoodHabit", "1","1","1","2") ;
        // 세 번째 아이템 추가.
        //adapter.addItem("WebServer", "2","2","3","4") ;

    }
}
