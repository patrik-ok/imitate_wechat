package com.wechat.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.wechat.app.AppManager;
import com.wechat.app.R;
import com.wechat.ui.bean.MainTab;
import com.wechat.ui.widget.MyFragmentTabHost;

public class MainActivity extends ActionBarActivity implements OnTabChangeListener {

	private MyFragmentTabHost mTabHost;
	private ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);

		AppManager.getAppManager().addActivity(this);
		
		init();
		initTabs();
	}

	private void init() {
		mTabHost = (MyFragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager());
		mTabHost.getTabWidget().setShowDividers(0);
		mTabHost.setCurrentTab(0);
		mTabHost.setOnTabChangedListener(this);
		
		ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.main_view_pager);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(adapter);
	}

	@SuppressLint("InflateParams")
	private void initTabs() {
		MainTab[] tabs = MainTab.values();
		final int size = tabs.length;

		for (int i = 0; i < size; i++) {
			MainTab mainTab = tabs[i];
			TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
			View indicator = LayoutInflater.from(getApplicationContext())
					.inflate(R.layout.tab_indicator, null);
			TextView title = (TextView) indicator.findViewById(R.id.tab_title);
			Drawable drawable = this.getResources().getDrawable(
					mainTab.getResIcon());
			drawable.setBounds(0, 0, drawable.getMinimumWidth() / 2, drawable.getMinimumHeight() / 2);
			title.setCompoundDrawables(null, drawable, null, null);

			TextView notice = (TextView) indicator.findViewById(R.id.tab_mes);
			indicator.setTag(notice);
			
			String titleBarContent = getString(mainTab.getResName());
			title.setText(titleBarContent);
			
			tab.setIndicator(indicator);
			tab.setContent(new TabContentFactory() {

				@Override
				public View createTabContent(String tag) {
					return new View(MainActivity.this);
				}
			});
			mTabHost.addTab(tab, mainTab.getClz(), null);
		}
	}
	
	@Override
	public void onTabChanged(String tabId) {
		
		final int size = mTabHost.getTabWidget().getTabCount();

		for (int i = 0; i < size; i++) {
			View v = mTabHost.getTabWidget().getChildAt(i);
			if (i == mTabHost.getCurrentTab()) {
				v.setSelected(true);
			} else {
				v.setSelected(false);
			}
			
			View notice = (View)v.getTag();
			if(notice != null && notice.getVisibility() == View.VISIBLE)
				notice.setVisibility(View.GONE);
				
		}
		
		supportInvalidateOptionsMenu();
		
		int position = mTabHost.getCurrentTab();
		viewPager.setCurrentItem(position);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main_activity_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.title_add:
			
			break;
		case R.id.title_search:

			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void setShowTabNotice(int count) {
		TextView notice = (TextView) mTabHost.getTabWidget()
				.getChildAt(mTabHost.getCurrentTab()).getTag();
		notice.setVisibility(View.VISIBLE);
		notice.setText("" + count);
	}
	
	private class ViewpagerAdapter extends FragmentPagerAdapter implements OnPageChangeListener {

		public ViewpagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			
			MainTab[] tabs = MainTab.values();
			
			return Fragment.instantiate(MainActivity.this, tabs[arg0].getClz().getName(),
					null);
		}

		@Override
		public int getCount() {
			return MainTab.values().length;
		}

		@Override
		public void onPageSelected(int arg0) {
			mTabHost.setCurrentTab(arg0);
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
	}
}
