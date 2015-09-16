package com.wechat.ui.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wechat.app.R;
import com.wechat.ui.bean.ContantsListItem;
import com.wechat.ui.widget.AssortView;
import com.wechat.ui.widget.AssortView.OnTouchAssortListener;

public class ContactsFragment extends BaseFragment implements OnTouchAssortListener {

	private static final int GROUP_CHILD_DEVIDING = 100000;
	
	private Map<Integer, ArrayList<ContantsListItem>> mInfoData;
	private ArrayList<String> mGroupData;
	private ExpandableListView contacts;
	private AssortView mAssortView;
	private TextView footer;
	
	@Override
	protected boolean holdView() {
		return true;
	}

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_contacts;
	}

	@Override
	protected void init(View view) {
		contacts = (ExpandableListView) view.findViewById(R.id.contacts_list);
		mAssortView = (AssortView) view.findViewById(R.id.contacts_assort);
	}
	
	@Override
	public void onResume() {
		
		refreshData();
		
		super.onResume();
	}

	@SuppressLint({ "UseSparseArrays", "InflateParams" })
	@Override
	protected void initData() {

		mGroupData = new ArrayList<String>();
		mInfoData = new HashMap<Integer, ArrayList<ContantsListItem>>();
		
		mAssortView.setOnTouchAssortListener(this);
		contacts.setAdapter(contactsAdapter);
		contacts.setGroupIndicator(null);

		contacts.setOnGroupClickListener(new OnGroupClickListener() {
			
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				return true;
			}
		});
		
		for(int i = 1; i <= 4; i++) {
			
			View view = LayoutInflater.from(getActivity()).inflate(
					R.layout.contacts_list_child_item, null);
			
			ChildViewHolder holder = new ChildViewHolder();
			
			holder.name = (TextView) view.findViewById(R.id.contacts_child_name);
			holder.face = (ImageView) view.findViewById(R.id.contacts_child_face);
			holder.divider = view.findViewById(R.id.contacts_child_divider);
			view.setOnClickListener(this);
			
			if(i == 1) {
				
				holder.face.setImageResource(R.drawable.add_new_friend_icon);
				holder.name.setText("新的朋友");
				holder.divider.setVisibility(View.VISIBLE);
				view.setContentDescription("-1");
			} else if(i == 2) {

				holder.face.setImageResource(R.drawable.qun_icon);
				holder.name.setText("群聊");
				holder.divider.setVisibility(View.VISIBLE);
				view.setContentDescription("-2");
			} else if(i == 3) {

				holder.face.setImageResource(R.drawable.tag_icon);
				holder.name.setText("标签");
				holder.divider.setVisibility(View.VISIBLE);
				view.setContentDescription("-3");
			} else if(i == 4) {

				holder.face.setImageResource(R.drawable.gongzhonghao_icon);
				holder.name.setText("公众号");
				holder.divider.setVisibility(View.GONE);
				view.setContentDescription("-4");
			}
				
			view.setTag(holder);
			view.setContentDescription(String.valueOf(i));
			
			contacts.addHeaderView(view);
		}
		
		footer = new TextView(getActivity());
		footer.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
		footer.setPadding(0, 40, 0, 40);
		footer.setGravity(Gravity.CENTER);
		footer.setTextSize(16);
		footer.setTextColor(getActivity().getResources().getColor(R.color.contacts_group_text));
		contacts.addFooterView(footer);
		
		refreshData();
		
		super.initData();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		int position = Integer.valueOf(v.getContentDescription().toString());
		int groupPos = position / GROUP_CHILD_DEVIDING;
		int childPos = position % GROUP_CHILD_DEVIDING;
		
		menu.add(groupPos, childPos, 0, "设置备注及标签");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {

		Log.d("mmsg", "click longclick menu ,  group position -> " + item.getGroupId() 
				+ " , child position -> " + item.getItemId());
		
		return true;
	}
	
	@Override
	public void onClick(View v) {
		
		String contentDescr = v.getContentDescription().toString();
		
		if(contentDescr.contains("-1")) {
			
			//add new friend
		} else if(contentDescr.contains("-2")) {
			
			//qun liao
		} else if(contentDescr.contains("-3")) {
			
			//tag
		} else if(contentDescr.contains("-4")) {
			
			//gong zhong hao
		} else {
			
			int position = Integer.valueOf(v.getContentDescription().toString());
			int groupPos = position / GROUP_CHILD_DEVIDING;
			int childPos = position % GROUP_CHILD_DEVIDING;
			
			Log.d("mmsg", "click ,  group position -> " + groupPos 
					+ " , child position -> " + childPos);
		}
	}
	
	private BaseExpandableListAdapter contactsAdapter = new BaseExpandableListAdapter() {

		@Override
		public int getGroupCount() {
			return mGroupData.size();
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			
			if(mInfoData.isEmpty() || mInfoData.get(groupPosition) == null)
				return 0;
			else
				return mInfoData.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			return mGroupData.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return mInfoData.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			
			if(convertView == null) 
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.contacts_list_item, null);
			
			((TextView) convertView).setText(mGroupData.get(groupPosition));
			
			return convertView;
		}

		@SuppressLint("InflateParams")
		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			
			ChildViewHolder holder = null;
			
			if(convertView == null) {

				holder = new ChildViewHolder();
				convertView = LayoutInflater.from(getActivity()).inflate(
						R.layout.contacts_list_child_item, null);
				
				holder.name = (TextView) convertView.findViewById(R.id.contacts_child_name);
				holder.face = (ImageView) convertView.findViewById(R.id.contacts_child_face);
				holder.divider = convertView.findViewById(R.id.contacts_child_divider);
				
				registerForContextMenu(convertView);
				convertView.setOnClickListener(ContactsFragment.this);
				
				convertView.setTag(holder);
				
			} else {
				
				holder = (ChildViewHolder) convertView.getTag();
			}

			List<ContantsListItem> group = mInfoData.get(groupPosition);
			
			holder.face.setImageResource(R.drawable.user_head_eg);
			holder.name.setText(group.get(childPosition).getName());
			
			if(childPosition == group.size() - 1)
				holder.divider.setVisibility(View.GONE);
			else
				holder.divider.setVisibility(View.VISIBLE);
			
			convertView.setContentDescription(String.valueOf(groupPosition * GROUP_CHILD_DEVIDING + childPosition));
			
			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}
	};

	@Override
	public void onTouchAssortListener(String s) {
		
		if(mGroupData.contains(s)) {
			contacts.setSelectedGroup(mGroupData.indexOf(s));
		} else if(s.equals("↑")) {
			contacts.setSelection(0);
		}
	}

	@Override
	public void onTouchAssortUP() {
		// TODO Auto-generated method stub
		
	}

	public void chechAssortView() {
		
		if(mAssortView.isPressed()) {
			mAssortView.assortUP();
		}
	}
	
	public void refreshData() {
		
		if(!mGroupData.isEmpty())
			mGroupData = new ArrayList<String>();
		
		mGroupData.add("A");
		mGroupData.add("C");
		mGroupData.add("F");
		mGroupData.add("X");
		mGroupData.add("Z");
		
		mInfoData.put(0, new ArrayList<ContantsListItem>(Arrays.asList(
				new ContantsListItem[] {
					new ContantsListItem("阿毛"),
					new ContantsListItem("阿狗"),
					new ContantsListItem("阿栋"),
					new ContantsListItem("阿光"),
				})));
		mInfoData.put(1, new ArrayList<ContantsListItem>(Arrays.asList(
				new ContantsListItem[] {
					new ContantsListItem("吃饭"),
					new ContantsListItem("菜单"),
					new ContantsListItem("测试"),
					new ContantsListItem("插入"),
				})));
		mInfoData.put(2, new ArrayList<ContantsListItem>(Arrays.asList(
				new ContantsListItem[] {
					new ContantsListItem("覆盖"),
					new ContantsListItem("返回"),
					new ContantsListItem("饭店"),
					new ContantsListItem("风"),
				})));
		mInfoData.put(3, new ArrayList<ContantsListItem>(Arrays.asList(
				new ContantsListItem[] {
					new ContantsListItem("下次"),
					new ContantsListItem("修改"),
					new ContantsListItem("陷入"),
					new ContantsListItem("喜欢"),
				})));
		mInfoData.put(4, new ArrayList<ContantsListItem>(Arrays.asList(
				new ContantsListItem[] {
					new ContantsListItem("自动饭"),
					new ContantsListItem("增幅"),
					new ContantsListItem("这个"),
					new ContantsListItem("装逼"),
				})));
		
		contactsAdapter.notifyDataSetChanged();
		
		int count = 0;
		
		for(int i = 0; i < mGroupData.size(); i++) {

			contacts.expandGroup(i);
			
			ArrayList<ContantsListItem> data = mInfoData.get(i);
			
			if(data == null) {
				continue;
			} else {
				count += data.size();
			}
		}
		
		footer.setText(String.format("%d位联系人", count));
	}
	
	class ChildViewHolder {
		ImageView face;
		TextView name;
		View divider;
	}
}
