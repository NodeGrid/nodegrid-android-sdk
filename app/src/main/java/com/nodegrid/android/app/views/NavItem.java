package com.nodegrid.android.app.views;

/**
 * Created by kwijewardana on 5/18/15.
 *
 * @author Kavimal Wijewardana <kavi707@gmail.com>
 */
public class NavItem {

    private String mTitle;
    private String mSubTitle;
    private boolean isTitle;
    private String requestMethod;

    public NavItem(String title, String subTitle, boolean isTitle, String requestMethod) {
        this.mTitle = title;
        this.mSubTitle = subTitle;
        this.isTitle = isTitle;
        this.requestMethod = requestMethod;
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

    public boolean isTitle() {
        return isTitle;
    }

    public void setIsTitle(boolean isTitle) {
        this.isTitle = isTitle;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }
}
