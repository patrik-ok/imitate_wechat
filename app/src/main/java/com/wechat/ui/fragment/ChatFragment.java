package com.wechat.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wechat.app.R;
import com.wechat.ui.bean.ChatMenuItem;
import com.wechat.ui.widget.DrawerHScrollView;
import com.wechat.ui.widget.PasteEditText;
import com.wechat.ui.widget.PasteEditText.OnOtherBtnClickListener;

public class ChatFragment extends BaseFragment implements OnOtherBtnClickListener {

	private static final int CHAT_MENU_PICTURE = 0;
	private static final int CHAT_MENU_VIDEO = CHAT_MENU_PICTURE + 1;
	private static final int CHAT_MENU_RED = CHAT_MENU_VIDEO + 1;
	private static final int CHAT_MENU_TRANSFERS = CHAT_MENU_RED + 1;
	private static final int CHAT_MENU_COLLECT = CHAT_MENU_TRANSFERS + 1;
	private static final int CHAT_MENU_POSITION = CHAT_MENU_COLLECT + 1;
	private static final int CHAT_MENU_VIDEO_CHAT = CHAT_MENU_POSITION + 1;
	private static final int CHAT_MENU_BUSINESS_CARD = CHAT_MENU_VIDEO_CHAT + 1;
	private static final int CHAT_MENU_VOICE = CHAT_MENU_BUSINESS_CARD + 1;
	private static final int CHAT_MENU_INTERPHONE = CHAT_MENU_VOICE + 1;
	
	private static final int[] MENU_IMG_RES = { R.drawable.chat_menu_pic, R.drawable.chat_menu_video, 
		R.drawable.chat_menu_red, R.drawable.chat_menu_transfer, R.drawable.chat_menu_collect, 
		R.drawable.chat_menu_location, R.drawable.chat_menu_videochat, R.drawable.chat_menu_bcard,
		R.drawable.chat_menu_voice, R.drawable.chat_menu_interphone };
	
	private static final String[] MENU_NAME = { "图片", "小视频", "红包",  
		"转账", "我的收藏",  "位置", "视频聊天",  "名片", "语音输入",  "实时对讲机"};
	
	private Button btnVoice;
	private GridView menuView;
	private DrawerHScrollView hscrollview;
	private SwipeRefreshLayout swipeRefreshLayout;
	private List<ChatMenuItem> chatMenuData;
	private OtherMenuAdapter menuAdapter;
	private PasteEditText userEdit;
	
	@Override
	protected boolean holdView() {
		return true;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_chat;
	}

	@Override
	protected void init(View view) {

		userEdit = (PasteEditText) view.findViewById(R.id.chat_edit);
		btnVoice = (Button) view.findViewById(R.id.chat_btn_voice);
		menuView = (GridView) view.findViewById(R.id.chat_other_gridview);
		hscrollview = (DrawerHScrollView) view.findViewById(R.id.chat_other_menu);
		swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.chat_swipe_layout);
		
		btnVoice.setOnClickListener(this);
		view.findViewById(R.id.chat_btn_other).setOnClickListener(this);
		userEdit.setOnOtherBtnClickListener(this);
	}

	@Override
	protected void initData() {

		menuAdapter = new OtherMenuAdapter();
		chatMenuData = new ArrayList<ChatMenuItem>();
		
		menuView.setAdapter(menuAdapter);
		
//		recreateChatMenu(0);
	}

	@Override
	public void onClick(View v) {
		
		switch(v.getId()) {
		case R.id.chat_btn_other:
			
			Log.d("mmsg", "click btn other!");
			break;
		case R.id.chat_btn_voice:

			Log.d("mmsg", "click btn voice!");
			break;
		}
	}

	@Override
	public void onClick() {
		Rect r = new Rect();
		View rootview = getActivity().getWindow().getDecorView(); // this = activity
		rootview.getWindowVisibleDisplayFrame(r);
		
		Log.d("mmsg", " soft hight -> " + getActivity().getWindowManager().getDefaultDisplay().getHeight());
		Log.d("mmsg", " hight -> " + r.height());
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Rect r = new Rect();
		View rootview = getActivity().getWindow().getDecorView(); // this = activity
		rootview.getWindowVisibleDisplayFrame(r);
		
		Log.d("mmsg", " hight -> " + r.height());
		
		return super.onOptionsItemSelected(item);
	}
	
	public void updateDrawerLayout() {  
		
		//no data
	    if ((chatMenuData == null) || (chatMenuData.size() == 0)) {  
	        return;  
	    }   
	    
	    int scrollWid = hscrollview.getWidth();  
	    int scrollHei = hscrollview.getHeight();  

        //scrollWid or scrollHei is less than 0  
	    if (scrollWid <= 0 || scrollHei <= 0){  
	        return;  
	    }  
	      
	    int spaceing = 10;  
	    int colWid = (scrollWid - spaceing * 3) / 2;  
	    int colHei = (scrollHei - spaceing * 3) / 2;  
	    int numCols = (chatMenuData.size() - 1) / 2 + 1;  
	    int gridViewWid = numCols * colWid + (numCols + 1) * spaceing;  
	    
	    // if numCols is odd (like 5), add blank space  
	    if (numCols % 2 == 1){  
	        gridViewWid += colWid + spaceing;  
	    }  
	      
	    GridView.LayoutParams params = new GridView.LayoutParams(gridViewWid, scrollHei);  
	    menuView.setLayoutParams(params);  
	    menuView.setColumnWidth(colWid);  
	    menuView.setHorizontalSpacing(spaceing);  
	    menuView.setVerticalSpacing(spaceing);  
	    menuView.setStretchMode(GridView.NO_STRETCH);  
	    menuView.setNumColumns(numCols);  
	  
//	    adapter = new DrawerListAdapter(this, colWid, colHei);  
//	    listener = new DrawerItemClickListener();  
//	    gridView.setAdapter(adapter);  
//	    gridView.setOnItemClickListener(listener);  
	  
	    int pageNum = (chatMenuData.size() - 1) / 4 + 1;  
	    hscrollview.setParameters(pageNum, 0, scrollWid - spaceing);  
//	    updateDrawerPageLayout(pageNum, 0);  
	}
	
	public void recreateChatMenu(int userType) {
		
		if(!chatMenuData.isEmpty())
			chatMenuData.clear();
		
		for(int i = CHAT_MENU_PICTURE; i < CHAT_MENU_INTERPHONE; i++) 
			chatMenuData.add(new ChatMenuItem(i, MENU_IMG_RES[i], MENU_NAME[i]));
		
		updateDrawerLayout();
	}

	private class OtherMenuAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return chatMenuData.size();
		}

		@Override
		public Object getItem(int position) {
			return chatMenuData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			
			if(convertView == null) {
				
				holder = new ViewHolder();
				
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.chat_menu_item, null);
				
				holder.icon = (ImageView) convertView.findViewById(R.id.chat_menu_icon);
				holder.name = (TextView) convertView.findViewById(R.id.chat_manu_name);
				
				convertView.setTag(holder);
			} else {
				
				holder = (ViewHolder) convertView.getTag();
			}
			
			ChatMenuItem item = chatMenuData.get(position);
			
			holder.icon.setImageResource(item.getMenuImg());
			holder.name.setText(item.getName());
			
			return convertView;
		}
		
		class ViewHolder {
			ImageView icon;
			TextView name;
		}
	}
}