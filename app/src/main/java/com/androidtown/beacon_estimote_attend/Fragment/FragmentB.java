package com.androidtown.beacon_estimote_attend.Fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidtown.beacon_estimote_attend.Framework.MainAjax;
import com.androidtown.beacon_estimote_attend.R;

import org.json.JSONArray;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kwonhyucknae on 2017-05-07.
 */

public class FragmentB extends Fragment{


    public TextView monday[]=new TextView[10];
    private TextView tuesday[]=new TextView[10];
    private TextView wednesday[]=new TextView[10];
    private TextView thursday[]=new TextView[10];
    private TextView friday[]=new TextView[10];
    private static String sLog="Time_Table";

    public FragmentB() {

    }


    //private Context mContext=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.secondpage_fragment,container,false);

    }

    @Override
    public void onActivityCreated(Bundle b)
    {


//SharedPreference 가져오는방법
//SharedPreferences prefs=getSharedPreferences("PrefName",MODE_PRIVATE);
//strid=prefs.getString("id","");
//strpw=prefs.getString("pw","");

        //Http http=new Http(this.getActivity());
        //HttpAsync httpas=new HttpAsync(this.getActivity());
        MainAjax ajax=new MainAjax(this.getActivity());
        //String s="khn";
        SharedPreferences prefs = this.getActivity().getSharedPreferences("Login",MODE_PRIVATE);
        String strid=prefs.getString("id","");

        super.onActivityCreated(b);

        monday[0]=(TextView)getView().findViewById(R.id.monday0);
        monday[1]=(TextView)getView().findViewById(R.id.monday1);
        monday[2]=(TextView)getView().findViewById(R.id.monday2);
        monday[3]=(TextView)getView().findViewById(R.id.monday3);
        monday[4]=(TextView)getView().findViewById(R.id.monday4);
        monday[5]=(TextView)getView().findViewById(R.id.monday5);
        monday[6]=(TextView)getView().findViewById(R.id.monday6);
        monday[7]=(TextView)getView().findViewById(R.id.monday7);
        monday[8]=(TextView)getView().findViewById(R.id.monday8);
        monday[9]=(TextView)getView().findViewById(R.id.monday9);

        tuesday[0]=(TextView)getView().findViewById(R.id.tuesday0);
        tuesday[1]=(TextView)getView().findViewById(R.id.tuesday1);
        tuesday[2]=(TextView)getView().findViewById(R.id.tuesday2);
        tuesday[3]=(TextView)getView().findViewById(R.id.tuesday3);
        tuesday[4]=(TextView)getView().findViewById(R.id.tuesday4);
        tuesday[5]=(TextView)getView().findViewById(R.id.tuesday5);
        tuesday[6]=(TextView)getView().findViewById(R.id.tuesday6);
        tuesday[7]=(TextView)getView().findViewById(R.id.tuesday7);
        tuesday[8]=(TextView)getView().findViewById(R.id.tuesday8);
        tuesday[9]=(TextView)getView().findViewById(R.id.tuesday9);

        wednesday[0]=(TextView)getView().findViewById(R.id.wednesday0);
        wednesday[1]=(TextView)getView().findViewById(R.id.wednesday1);
        wednesday[2]=(TextView)getView().findViewById(R.id.wednesday2);
        wednesday[3]=(TextView)getView().findViewById(R.id.wednesday3);
        wednesday[4]=(TextView)getView().findViewById(R.id.wednesday4);
        wednesday[5]=(TextView)getView().findViewById(R.id.wednesday5);
        wednesday[6]=(TextView)getView().findViewById(R.id.wednesday6);
        wednesday[7]=(TextView)getView().findViewById(R.id.wednesday7);
        wednesday[8]=(TextView)getView().findViewById(R.id.wednesday8);
        wednesday[9]=(TextView)getView().findViewById(R.id.wednesday9);

        thursday[0]=(TextView)getView().findViewById(R.id.thursday0);
        thursday[1]=(TextView)getView().findViewById(R.id.thursday1);
        thursday[2]=(TextView)getView().findViewById(R.id.thursday2);
        thursday[3]=(TextView)getView().findViewById(R.id.thursday3);
        thursday[4]=(TextView)getView().findViewById(R.id.thursday4);
        thursday[5]=(TextView)getView().findViewById(R.id.thursday5);
        thursday[6]=(TextView)getView().findViewById(R.id.thursday6);
        thursday[7]=(TextView)getView().findViewById(R.id.thursday7);
        thursday[8]=(TextView)getView().findViewById(R.id.thursday8);
        thursday[9]=(TextView)getView().findViewById(R.id.thursday9);

        friday[0]=(TextView)getView().findViewById(R.id.friday0);
        friday[1]=(TextView)getView().findViewById(R.id.friday1);
        friday[2]=(TextView)getView().findViewById(R.id.friday2);
        friday[3]=(TextView)getView().findViewById(R.id.friday3);
        friday[4]=(TextView)getView().findViewById(R.id.friday4);
        friday[5]=(TextView)getView().findViewById(R.id.friday5);
        friday[6]=(TextView)getView().findViewById(R.id.friday6);
        friday[7]=(TextView)getView().findViewById(R.id.friday7);
        friday[8]=(TextView)getView().findViewById(R.id.friday8);
        friday[9]=(TextView)getView().findViewById(R.id.friday9);



        /*      Http로 통신 하는법
        String result=null;
       // Http.MyAsyncTask.execute(result).get();
        try {
            result = httpas.execute("").get();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        String[] result2=http.doJSONParser(result);
        monday[0].setText(result2[2]);
        monday[0].setBackgroundColor(Color.LTGRAY);

        */

        ajax.getTimeTable(strid,new AjaxCallback<JSONArray>()
        {
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status)
            {

                if(object!=null)
                {
                    try {
                        //if(object.length()!=0)
                        //{
                            int leng=object.length();
                            for(int i=0;i<leng;i++) {


                                String test = object.getJSONObject(i).get("Course_Name").toString();
                                String sta = object.getJSONObject(i).get("Start_Time").toString();
                                String end = object.getJSONObject(i).get("End_Time").toString();
                                int startt = Integer.parseInt(sta);
                                int endt = Integer.parseInt(end);
                                String Day = object.getJSONObject(i).get("Day").toString();
                                if (Day.equals("Mon")) {
                                    monday[startt - 8].setText(test);
                                    monday[startt - 8].setBackgroundColor(Color.LTGRAY);
                                } else if (Day.equals("Tue")) {
                                    tuesday[startt - 8].setText(test);
                                    tuesday[startt - 8].setBackgroundColor(Color.LTGRAY);
                                } else if (Day.equals("Wed")) {
                                    wednesday[startt - 8].setText(test);
                                    wednesday[startt - 8].setBackgroundColor(Color.LTGRAY);
                                } else if (Day.equals("Thu")) {
                                    thursday[startt - 8].setText(test);
                                    thursday[startt - 8].setBackgroundColor(Color.LTGRAY);

                                } else if (Day.equals("Fri")) {
                                    friday[startt - 8].setText(test);
                                    friday[startt - 8].setBackgroundColor(Color.LTGRAY);
                                }
                            }
                            //String test=object.get("Course_Name").toString();
                            //monday[0].setText(test);
                            //monday[0].setBackgroundColor(Color.LTGRAY);

                        //}
                        //else
                        //{
                          //  monday[0].setText("결과값이 없다");
                           // monday[0].setBackgroundColor(Color.LTGRAY);
                        //}
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
        //monday[0].setText("수업ㅋㅋㅋㅋㅋ");
        //monday[0].setBackgroundColor(Color.LTGRAY);

    }



}
