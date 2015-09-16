package com.wechat.ui.bean;

import com.wechat.app.R;
import com.wechat.ui.fragment.ContactsFragment;
import com.wechat.ui.fragment.FindFragment;
import com.wechat.ui.fragment.MineFragment;
import com.wechat.ui.fragment.RecentContactsFragment;

public enum MainTab {

	RECENT(0, R.string.main_tab_recent, R.drawable.tab_recent_selector,
			RecentContactsFragment.class),

	CONTACTS(1, R.string.main_tab_contacts, R.drawable.tab_contacts_selector,
			ContactsFragment.class),

	FIND(2, R.string.main_tab_find, R.drawable.tab_find_selector,
			FindFragment.class),

	MINE(3, R.string.main_tab_mine, R.drawable.tab_mine_selector,
			MineFragment.class);

	private int idx;
	private int resName;
	private int resIcon;
	private Class<?> clz;

	private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
		this.idx = idx;
		this.resName = resName;
		this.resIcon = resIcon;
		this.clz = clz;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getResName() {
		return resName;
	}

	public void setResName(int resName) {
		this.resName = resName;
	}

	public int getResIcon() {
		return resIcon;
	}

	public void setResIcon(int resIcon) {
		this.resIcon = resIcon;
	}

	public Class<?> getClz() {
		return clz;
	}

	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
}
