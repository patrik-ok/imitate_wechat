package com.wechat.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wechat.app.R;

public class AssortView extends View{
	
	private String[] assort = { "↑", "☆", "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z", "#"};

	private Paint paint;
	private int selectIndex = -1;
	private OnTouchAssortListener mTouch;
	private DiyToast mToast;
	private boolean pressed = false;
	
	public AssortView(Context context) {
		this(context, null);
	}
	
	public AssortView(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}
	
	public AssortView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		
		super.onDraw(canvas);
		
		int height = getHeight();
		int width = getWidth();
		int interval = height / assort.length;

		for (int i = 0, length = assort.length; i < length; i++) {

			paint.setAntiAlias(true);
			paint.setTextSize(40);
			paint.setColor(getContext().getResources().getColor(R.color.contacts_assort_text));
			
			float xPos = width / 2 - paint.measureText(assort[i]) / 2;
			float yPos = interval * i + interval;
			canvas.drawText(assort[i], xPos, yPos, paint);
			paint.reset();
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float y = event.getY();
		int index = (int) (y / getHeight() * assort.length);
		if (index >= 0 && index < assort.length) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				// 如果滑动改变
				if (selectIndex != index) {
					selectIndex = index;
					
					assortPress(assort[selectIndex]);
					if (mTouch != null) {
						mTouch.onTouchAssortListener(assort[selectIndex]);
					}

				}
				break;
			case MotionEvent.ACTION_DOWN:
				selectIndex = index;
				
				assortPress(assort[selectIndex]);
				if (mTouch != null) {
					mTouch.onTouchAssortListener(assort[selectIndex]);
				}

				break;
			case MotionEvent.ACTION_UP:
				
				assortUP();
				if (mTouch != null) {
					mTouch.onTouchAssortUP();
				}
				selectIndex = -1;
				break;
			}
		} else {
			selectIndex = -1;
			
			assortUP();
			if (mTouch != null) {
				mTouch.onTouchAssortUP();
			}
		}
		invalidate();

		return true;
	}

	public void assortUP() {

		pressed = false;
		
		if(mToast != null) {

			mToast.hide();
			mToast = null;
			setSelected(false);
		}
	}
	
	public void assortPress(String index) {
		
		pressed = true;
		
		if(mToast == null) {
			
			mToast = DiyToast.makeText(getContext(), index, -1);
			mToast.setTextSize(48);
			mToast.setShowLayout(0.25f);
			mToast.show();
		} else {
			
			mToast.setText(index);
		}
		
		setSelected(true);
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public void setOnTouchAssortListener(OnTouchAssortListener listener) {
		
		mTouch = listener;
	}
	
	public interface OnTouchAssortListener {
		public void onTouchAssortListener(String s);
		public void onTouchAssortUP();
	}
}
