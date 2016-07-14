/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wechat.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.EditText;

import com.wechat.app.R;
import com.wechat.util.ShowUtil;

/**
 * 自定义的textview，用来处理复制粘贴的消息
 *
 */
public class PasteEditText extends EditText {

    private OnOtherBtnClickListener mListener;
    
    public PasteEditText(Context context) {
        this(context, null);
    }

    public PasteEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasteEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        
        setGravity(Gravity.BOTTOM | Gravity.LEFT);
        
        Drawable drawable = getResources().getDrawable(R.drawable.chat_btn_face_selector);
		drawable.setBounds(0, 0, ShowUtil.dip2px(context, 24), ShowUtil.dip2px(context, 24));
		setCompoundDrawables(null, null, drawable, null);
    }
    
    @SuppressLint("NewApi")
	@Override
    public boolean onTextContextMenuItem(int id) {
        if(id == android.R.id.paste){
//            ClipboardManager clip = (ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
//            if (clip == null || clip.getText() == null) {
//                return false;
//            }
//            String text = clip.getText().toString();
//            if(text.startsWith(ChatActivity.COPY_IMAGE)){
////                intent.setDataAndType(Uri.fromFile(new File("/sdcard/mn1.jpg")), "image/*");     
//                text = text.replace(ChatActivity.COPY_IMAGE, "");
//                Intent intent = new Intent(context,AlertDialog.class);
//                String str = context.getResources().getString(R.string.Send_the_following_pictures);
//                intent.putExtra("title", str);
//                intent.putExtra("forwardImage", text);
//                intent.putExtra("cancel", true);
//                ((Activity)context).startActivityForResult(intent,ChatActivity.REQUEST_CODE_COPY_AND_PASTE);
////                clip.setText("");
//            }
        }
        return super.onTextContextMenuItem(id);
    }

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		performClick();
		
		if(event.getAction() == MotionEvent.ACTION_UP && 
				event.getX() > getWidth() - ShowUtil.dip2px(getContext(), 37)) {
			
			if(mListener != null)
				mListener.onClick();

			setSelected(!isSelected());
			
			Log.d("mmsg", "click other btn !");
		}
		
		return super.onTouchEvent(event);
	}

	@Override
	public boolean performClick() {
		return super.performClick();
	}

	@Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
//        if(!TextUtils.isEmpty(text) && text.toString().startsWith(ChatActivity.COPY_IMAGE)){
//            setText("");
//        }
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }
	
	public void setOnOtherBtnClickListener(OnOtherBtnClickListener listener) {
		mListener = listener;
	}
	
	public interface OnOtherBtnClickListener {
		
		public void onClick();
	}
}
