package com.androidtown.beacon_estimote_attend.Contents;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidtown.beacon_estimote_attend.Framework.MainAjax;
import com.androidtown.beacon_estimote_attend.MainActivity;
import com.androidtown.beacon_estimote_attend.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;

/**
 * Created by kwonhyucknae on 2017-05-11.
 */

public class Login extends Activity {

    MainAjax ajax;
    EditText IDet;
    EditText Pwet;
    String ID;
    String PW;
    String IDetText;
    String PwetText;
    final Context context=this;

    private static String sLog = "Login_Check";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.login);
        ajax = new MainAjax(this);

        Button loginokbtn = (Button) findViewById(R.id.loginokbtn);
        loginokbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDet = (EditText) findViewById(R.id.idinput);
                Pwet = (EditText) findViewById(R.id.pwinput);

                IDetText = IDet.getText().toString();
                PwetText = Pwet.getText().toString();

                ajax.loginCheck(IDet.getText().toString(), Pwet.getText().toString(), new AjaxCallback<JSONArray>() {
                    @Override
                    public void callback(String url, JSONArray object, AjaxStatus status) {
                        if (object != null) {
                            try {
                                    ID = object.getJSONObject(0).get("Name").toString();
                                    PW = object.getJSONObject(0).get("Password").toString();
                                    if (ID.equals(IDetText) && PW.equals(PwetText)) {
                                        SharedPreferences prefs = getSharedPreferences("Login", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = prefs.edit();

                                        editor.putString("id", ID);
                                        editor.putString("pw", PW);
                                        editor.commit();

                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                        //intent 시키기
                                    }
                                else {


                                    //아이디 비번 틀렷음

                                }

                            } catch (Exception e) {
                                Log.e(sLog, e.getMessage());

                            }

                        } else {
                        }

                    }
                });

                //Intent inte=new Intent(Login.this,MainActivity.class);

                if(ID==null)
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            context);

                    // 제목셋팅
                    alertDialogBuilder.setTitle("알림");

                    // AlertDialog 셋팅
                    alertDialogBuilder
                            .setMessage("아이디 또는 비밀번호가 틀렸습니다.")
                            .setCancelable(false)
                            .setNegativeButton("확인",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(
                                                DialogInterface dialog, int id) {
                                            // 다이얼로그를 취소한다
                                            dialog.cancel();
                                        }
                                    });

                    // 다이얼로그 생성
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // 다이얼로그 보여주기
                    alertDialog.show();

                }


            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


//SharedPreference 가져오는방법
//SharedPreferences prefs=getSharedPreferences("PrefName",MODE_PRIVATE);
//strid=prefs.getString("id","");
//strpw=prefs.getString("pw","");
