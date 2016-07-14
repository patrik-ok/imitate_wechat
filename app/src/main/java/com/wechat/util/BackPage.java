package com.wechat.util;

import com.wechat.app.R;
import com.wechat.ui.fragment.ChatFragment;


public enum BackPage {
	
	CHAT(1, R.string.title_chat, ChatFragment.class, R.menu.chat_menu);
	
    private int title;
    private Class<?> clz;
    private int value;
    private int menu;

    private BackPage(int value, int title, Class<?> clz, int menu) {
        this.value = value;
        this.title = title;
        this.clz = clz;
        this.menu = menu;
    }
    
    public int getMenu() {
		return menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
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
