package com.androidtown.beacon_estimote_attend.Fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidtown.beacon_estimote_attend.Framework.MainAjax;
import com.androidtown.beacon_estimote_attend.R;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kwonhyucknae on 2017-05-07.
 */


public class FragmentA extends Fragment{

    private static String sLog="Time_Table";
    private BeaconManager beaconManager;
    private Region region;

    private TextView bcte;
    public boolean isConnected=false;
    public boolean[] LectureCheck=new boolean[10];
    public int Lecturenum=0;
    public String Yoil;


    public String nowLecture=null;
    public String nowEnd;
    public String sta;
    public String end;
    public String test;
    public String strCurHour;
    public boolean OKcheck=false;

    public int nowtime=0;
    public int etime=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.firstpage_fragment,container,false);

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onActivityCreated(Bundle b)
    {
        super.onActivityCreated(b);
        MainAjax ajax=new MainAjax(this.getActivity());
        final ImageView okbtn=(ImageView)getView().findViewById(R.id.Okcheckbtn);
        SharedPreferences prefs = this.getActivity().getSharedPreferences("Login",MODE_PRIVATE);
        String strid=prefs.getString("id","");

        bcte=(TextView)getView().findViewById(R.id.bctest);

        beaconManager=new BeaconManager(this.getActivity());
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
                            strCurHour = CurHourFormat.format(date);
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


                            test = object.getJSONObject(i).get("Course_Name").toString();
                            sta = object.getJSONObject(i).get("Start_Time").toString();
                            end = object.getJSONObject(i).get("End_Time").toString();
                            int startt = Integer.parseInt(sta);
                            int endt = Integer.parseInt(end);

                            nowtime=Integer.parseInt(strCurHour);         //출석체크 확인 유지를 해제해줌
                            etime=Integer.parseInt(nowEnd);

                            String Day = object.getJSONObject(i).get("Day").toString();
                            if (Day.equals(Yoil)) {
                                if(strCurHour.equals(sta)||strCurHour.equals(end))
                                {
                                    LectureCheck[Lecturenum]=true;                 //LectureChect 가 true 라면 현재 시간에 강의가 있다는 뜻
                                    nowLecture=test;
                                    nowEnd=end;
                                    nowcourse.setText(test);
                                }
                                else
                                {
                                    LectureCheck[Lecturenum]=false;
                                }
                            }
                            Lecturenum++;
                        }
                        Lecturenum=0;
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

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                if(!list.isEmpty())
                {
                    Beacon nearestBeacon=list.get(0);
                    Log.d("Airport","Nearest places: "+nearestBeacon.getRssi());
                    bcte.setText(nearestBeacon.getRssi()+"");
                    if( !isConnected && nearestBeacon.getRssi() > -70 ) {   //비콘과의 통신이 가능할때 , 현재 강의가 있을때 &&now
                        okbtn.setImageResource(R.drawable.beforecheck);
                        okbtn.setEnabled(true);
                        isConnected=true;

                    }
                    else if( isConnected && nearestBeacon.getRssi() < -70 )  // 비콘에서 멀어졌을때
                    {
                        okbtn.setImageResource(R.drawable.gogot1real);
                        okbtn.setEnabled(false);
                        isConnected=false;

                    }


                }
            }
        });
        region=new Region("ranged region", UUID.fromString("E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"),40001,10562);



        if(nowtime>etime)
        {
            OKcheck=false;

        }
        okbtn.setOnClickListener(new View.OnClickListener()                  //활성화 된 ok버튼을 클릭했을때 이벤트
        {
            public void onClick(View v) {
                if(OKcheck==false)
                {
                    okbtn.setImageResource(R.drawable.check);
                }
            }
        });

        /*ajax.getTimeTable(strid,new AjaxCallback<JSONArray>()
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
                                    LectureCheck[Lecturenum]=true;                 //LectureChect 가 true 라면 현재 시간에 강의가 있다는 뜻

                                    nowcourse.setText(test);
                                }
                                else
                                {
                                    LectureCheck[Lecturenum]=false;
                                }
                            }
                        Lecturenum++;
                        }
                    Lecturenum=0;
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
        });*/
    }
    @Override
    public void onResume()
    {
        super.onResume();

        SystemRequirementsChecker.checkWithDefaultDialogs(this.getActivity());

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }
    public void OkbtnClickR(View view)
    {
        ImageView okbtn=(ImageView)getView().findViewById(R.id.Okcheckbtn);
        if(OKcheck==false)
        {
            okbtn.setImageResource(R.drawable.check);
        }
    }
}
