package com.androidtown.beacon_estimote_attend;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.androidtown.beacon_estimote_attend.Fragment.FragmentA;
import com.androidtown.beacon_estimote_attend.Fragment.FragmentB;

public class MainActivity extends AppCompatActivity {

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            FragmentA firstFragment= new FragmentA();
            firstFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,firstFragment).commit();
        }

    }
    public void selectFragment(View view)
    {

        Fragment fr=null;
        switch (view.getId())
        {
            case R.id.fragmentbutton:
                fr=new FragmentA();
                break;
            case R.id.fragmentbutton2:
                fr=new FragmentB();
                break;
            case R.id.Okcheckbtn:
                fr=new FragmentB();
                break;
        }

        android.app.FragmentManager fm=getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fr);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
