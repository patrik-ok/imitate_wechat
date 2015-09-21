package com.wechat.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class ClearFocusRelativeLayout extends RelativeLayout {

	public ClearFocusRelativeLayout(Context context) {
		this(context, null);
	}

	public ClearFocusRelativeLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ClearFocusRelativeLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		if(ev.getAction() == MotionEvent.ACTION_UP && getFocusedChild() != null) {
			
			Log.d("mmsg", "clear focus! ");
			requestFocus();
		}
		
		return super.dispatchTouchEvent(ev);
	}
}
