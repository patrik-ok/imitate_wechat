package com.wechat.util;

import com.wechat.app.R;


public enum BackPage {
	
	SETTING(1, R.string.title_chat, BackPage.class);
	
    private int title;
    private Class<?> clz;
    private int value;

    private BackPage(int value, int title, Class<?> clz) {
        this.value = value;
        this.title = title;
        this.clz = clz;
    }
    
    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static BackPage getPageByValue(int val) {
        for (BackPage p : values()) {
            if (p.getValue() == val)
                return p;
        }
        return null;
    }
}
