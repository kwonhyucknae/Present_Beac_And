package com.androidtown.beacon_estimote_attend.Fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kwonhyucknae on 2017-05-07.
 */


public class FragmentA extends Fragment{

    private static String sLog="Time_Table";
    public String Yoil;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.firstpage_fragment,container,false);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);
        MainAjax ajax=new MainAjax(this.getActivity());

        SharedPreferences prefs = this.getActivity().getSharedPreferences("Login",MODE_PRIVATE);
        String strid=prefs.getString("id","");
/*
        long now=System.currentTimeMillis();

        Date date=new Date(now);
        // 시간 포맷 지정

        SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat CurTimeFormat = new SimpleDateFormat("HH시 mm분");
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
        SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");


// 지정된 포맷으로 String 타입 리턴
        String strCurDate = CurDateFormat.format(date);
        String strCurTime = CurTimeFormat.format(date);
        String strCurYear = CurYearFormat.format(date);
        String strCurMonth = CurMonthFormat.format(date);
        String strCurDay = CurDayFormat.format(date);
        String strCurHour = CurHourFormat.format(date);
        String strCurMinute = CurMinuteFormat.format(date);

        String Yoil;

        Calendar cal=Calendar.getInstance();
        int year=Integer.parseInt(strCurYear);
        int month=Integer.parseInt(strCurMonth);
        int datt=Integer.parseInt(strCurDay);

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE, datt);


        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case 1:
                Yoil="SUN";
                break;
            case 2:
                Yoil="Mon";
                break;
            case 3:
                Yoil="Tue";
                break;
            case 4:
                Yoil="Wed";
                break;
            case 5:
                Yoil="Thu";
                break;
            case 6:
                Yoil="Fri";
                break;
            case 7:
                Yoil="Sat";
                break;
        }
*/
        ajax.getTimeTable(strid,new AjaxCallback<JSONArray>()
        {
            @Override
            public void callback(String url, JSONArray object, AjaxStatus status)
            {

                if(object!=null)
                {
                    try {
                        int leng=object.length();
                        TextView nowcourse=(TextView)getView().findViewById(R.id.NowCourseTx);
                        for(int i=0;i<leng;i++) {

                            long now=System.currentTimeMillis();
                            now=now+32400000;
                            Date date=new Date(now);
                            // 시간 포맷 지정

                            SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                            SimpleDateFormat CurTimeFormat = new SimpleDateFormat("HH시 mm분");
                            SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
                            SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
                            SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
                            SimpleDateFormat CurHourFormat = new SimpleDateFormat("HH");
                            SimpleDateFormat CurMinuteFormat = new SimpleDateFormat("mm");


                            // 지정된 포맷으로 String 타입 리턴
                            String strCurDate = CurDateFormat.format(date);
                            String strCurTime = CurTimeFormat.format(date);
                            String strCurYear = CurYearFormat.format(date);
                            String strCurMonth = CurMonthFormat.format(date);
                            String strCurDay = CurDayFormat.format(date);
                            String strCurHour = CurHourFormat.format(date);
                            String strCurMinute = CurMinuteFormat.format(date);



                            Calendar cal=Calendar.getInstance(Locale.KOREAN);
                            int year=Integer.parseInt(strCurYear);
                            int month=Integer.parseInt(strCurMonth);
                            int datt=Integer.parseInt(strCurDay);

                            cal.set(Calendar.YEAR, year);
                            cal.set(Calendar.MONTH, month-1);
                            cal.set(Calendar.DATE, datt);


                            switch (cal.get(Calendar.DAY_OF_WEEK)){
                                case 1:
                                    Yoil="SUN";
                                    break;
                                case 2:
                                    Yoil="Mon";
                                    break;
                                case 3:
                                    Yoil="Tue";
                                    break;
                                case 4:
                                    Yoil="Wed";
                                    break;
                                case 5:
                                    Yoil="Thu";
                                    break;
                                case 6:
                                    Yoil="Fri";
                                    break;
                                case 7:
                                    Yoil="Sat";
                                    break;
                            }

                            String test = object.getJSONObject(i).get("Course_Name").toString();
                            String sta = object.getJSONObject(i).get("Start_Time").toString();
                            String end = object.getJSONObject(i).get("End_Time").toString();
                            int startt = Integer.parseInt(sta);
                            int endt = Integer.parseInt(end);
                            String Day = object.getJSONObject(i).get("Day").toString();
                            if (Day.equals(Yoil)) {
                                if(strCurHour.equals(sta)||strCurHour.equals(end))
                                {
                                    nowcourse.setText(test);
                                }
                                else
                                {

                                }
                            }
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
    }
}
