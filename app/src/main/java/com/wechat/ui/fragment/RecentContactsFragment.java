package com.wechat.ui.fragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wechat.app.R;
import com.wechat.ui.bean.ContantsListItem;
import com.wechat.util.BackPage;
import com.wechat.util.StringUtil;
import com.wechat.util.UiHelper;

public class RecentContactsFragment extends BaseFragment {

	private List<ContantsListItem> mData;
	
	@Override
	protected boolean holdView() {
		return true;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_recent_contacts;
	}

	@Override
	protected void init(View view) {

		mData = new ArrayList<ContantsListItem>();
		
		ListView recentList = (ListView) view.findViewById(R.id.recent_list);
		recentList.setAdapter(mAdapter);
	}

	@Override
	protected void initData() {
		super.initData();
	}
	
	@Override
	public void onResume() {
		
		refreshData();
		
		super.onResume();
	}
	
	public void refreshData() {
		
		mData = new ArrayList<ContantsListItem>();
		
		long curr = System.currentTimeMillis();
		
		for(int i = 0; i < 100; i++) {
			
			long time = curr - 86400000l * i;
			ContantsListItem item = new ContantsListItem();
			item.setName("好友" + i);
			item.setLast("好友" + i + "的最近的一句话！");
			item.setLastTime(time); 
			
			mData.add(item);
		}
		
		mAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		int childPos = Integer.valueOf(v.getContentDescription().toString());
		
		menu.add(0, childPos, 0, "标为未读");
		menu.add(1, childPos, 0, "置顶聊天");
		menu.add(2, childPos, 0, "删除该聊天");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		Log.d("mmsg", "click longclick menu ,  group position -> " + item.getGroupId() 
				+ " , child position -> " + item.getItemId());
		
		return true;
	}

	@Override
	public void onClick(View v) {

		int position = Integer.valueOf(v.getContentDescription().toString());
		
		Log.d("mmsg", " click position -> " + position);
		
		UiHelper.showSimpleBack(getActivity(), BackPage.CHAT);
	}
	
	private BaseAdapter mAdapter = new BaseAdapter() {
		
		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			
			if(convertView == null) {
				
				holder = new ViewHolder();
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.recent_list_item, null);
				
				holder.name = (TextView) convertView.findViewById(R.id.recent_item_name);
				holder.last = (TextView) convertView.findViewById(R.id.recent_item_last);
				holder.time = (TextView) convertView.findViewById(R.id.recent_item_time);
				holder.face = (ImageView) convertView.findViewById(R.id.recent_item_face);
				
				convertView.setTag(holder);
				convertView.setOnClickListener(RecentContactsFragment.this);
				
				registerForContextMenu(convertView);
			} else {
				
				holder = (ViewHolder) convertView.getTag();
			}
			
			ContantsListItem item = mData.get(position);
			holder.name.setText(item.getName());
			holder.face.setContentDescription("");
			holder.last.setText(item.getLast());
			holder.time.setText(StringUtil.friendlyTime3(new Date(item.getLastTime())));
			
			convertView.setContentDescription(String.valueOf(position));
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public Object getItem(int position) {
			return mData.get(position);
		}
		
		@Override
		public int getCount() {
			return mData.size();
		}
	};
	
	private class ViewHolder {
		ImageView face;
		TextView name;
		TextView last;
		TextView time;
	}
}
