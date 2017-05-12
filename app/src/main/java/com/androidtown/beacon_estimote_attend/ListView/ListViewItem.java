package com.androidtown.beacon_estimote_attend.ListView;

import android.graphics.drawable.Drawable;

/**
 * Created by kwonhyucknae on 2017-05-13.
 */

public class ListViewItem {

    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String cstx;
    private String gstx;
    private String jgtx;
    private String jttx;


    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setCstx(String cs)
    {
        cstx=cs;
    }
    public void setGstx(String gs)
    {
        gstx=gs;
    }
    public void setJgtx(String jg)
    {
        jgtx=jg;
    }
    public void setJttx(String jt)
    {
        jttx=jt;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getCstx()
    {
        return this.cstx;
    }
    public String getGstx()
    {
        return this.gstx;
    }
    public String getJgtx()
    {
        return this.jgtx;
    }
    public String getJttx()
    {
        return this.jttx;
    }


}
