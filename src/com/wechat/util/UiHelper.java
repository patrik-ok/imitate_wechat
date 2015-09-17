package com.wechat.util;

import com.wechat.ui.activity.BackActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class UiHelper {
	
	   public static void showSimpleBack(Context context, BackPage page) {
	        Intent intent = new Intent(context, BackActivity.class);
	        intent.putExtra(BackActivity.BUNDLE_KEY_PAGE, page.getValue());
	        context.startActivity(intent);
	    }
	   
		public static void HideKb(Context context, View v) {
			InputMethodManager imm = (InputMethodManager) context
					.getSystemService(Context.INPUT_METHOD_SERVICE);
			if (imm != null && v != null) {
				imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
			}
		}
}
