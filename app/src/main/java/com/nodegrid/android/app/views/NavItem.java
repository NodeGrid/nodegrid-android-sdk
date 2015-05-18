package com.nodegrid.android.app.views;

/**
 * Created by kwijewardana on 5/18/15.
 */
public class NavItem {

    private String mTitle;
    private String mSubTitle;

    public NavItem(String title, String subTitle) {
        mTitle = title;
        mSubTitle = subTitle;
    }

    public String getMTitle() {
        return mTitle;
    }

    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getMSubTitle() {
        return mSubTitle;
    }

    public void setMSubTitle(String mSubTitle) {
        this.mSubTitle = mSubTitle;
    }
}
