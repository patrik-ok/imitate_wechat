package com.wechat.util;

import android.annotation.SuppressLint;

import com.wechat.app.AppContext;
import com.wechat.ui.widget.DiyToast;

@SuppressLint("ShowToast")
public class ToastTool {
	private static DiyToast mToast;

	private ToastTool() {
	}

	public static void showToastLong(String text) {
		if (mToast == null) 
			mToast = DiyToast.makeText(AppContext.getInstance(), "", 5000);
			
		mToast.setText(text);
		mToast.setDuration(5000);
		mToast.show();
	}

	public static void showToast(String text) {
		if (mToast == null) 
			mToast = DiyToast.makeText(AppContext.getInstance(), "", 2000);
			
		mToast.setText(text);
		mToast.setDuration(2000);
		mToast.show();
	}

	public static void showToast(int text) {
		if (mToast == null) 
			mToast = DiyToast.makeText(AppContext.getInstance(), "", 2000);
			
		mToast.setText(text);
		mToast.setDuration(2000);
		mToast.show();
	}

	public void cancelToast() {
		if (mToast != null) {
			mToast.hide();
			mToast = null;
		}
	}
}
