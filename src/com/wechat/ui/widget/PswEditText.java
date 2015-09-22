package com.wechat.ui.widget;

import com.wechat.app.R;
import com.wechat.util.ShowUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

public class PswEditText extends EditText {


	public PswEditText(Context context) {
		this(context, null);
	}

	public PswEditText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public PswEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		Drawable lock = getResources().getDrawable(R.drawable.psw_lock_icon);
		lock.setBounds(0, 0, ShowUtil.dip2px(context, 24), ShowUtil.dip2px(context, 24));
		
		Drawable clear = getResources().getDrawable(R.drawable.clear_icon_selector);
		clear.setBounds(0, 0, ShowUtil.dip2px(context, 24), ShowUtil.dip2px(context, 24));
		setCompoundDrawables(lock, null, clear, null);
	}
	
}
