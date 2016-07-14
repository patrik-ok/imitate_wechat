package com.wechat.ui.activity;

import java.lang.ref.WeakReference;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wechat.app.R;
import com.wechat.ui.fragment.BaseFragment;
import com.wechat.util.BackPage;

public class BackActivity extends BaseActivity {
	public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
	public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";
	private static final String TAG = "FLAG_TAG";

	protected WeakReference<Fragment> mFragment;
	protected int mPageValue = -1;
	private int pageMenu = -1;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_simple_fragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		if(pageMenu > 0)
			getMenuInflater().inflate(pageMenu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if(mFragment.get() != null)
			mFragment.get().onOptionsItemSelected(item);
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void init(Bundle savedInstanceState) {
		super.init(savedInstanceState);
		if (mPageValue == -1) {
			mPageValue = getIntent().getIntExtra(BUNDLE_KEY_PAGE, 0);
		}
		initFromIntent(mPageValue, getIntent());
	}

	protected void initFromIntent(int pageValue, Intent data) {
		if (data == null) {
			throw new RuntimeException(
					"you must provide a page info to display");
		}
		
		BackPage page = BackPage.getPageByValue(pageValue);
		if (page == null) {
			throw new IllegalArgumentException("can not find page by value:"
					+ pageValue);
		}

		pageMenu = page.getMenu();
		setActionBarTitle(page.getTitle());

		try {
			Fragment fragment = (Fragment) page.getClz().newInstance();

			Bundle args = data.getBundleExtra(BUNDLE_KEY_ARGS);
			if (args != null) {
				fragment.setArguments(args);
			}

			FragmentTransaction trans = getSupportFragmentManager()
					.beginTransaction();
			trans.replace(R.id.container, fragment, TAG);
			trans.commitAllowingStateLoss();

			mFragment = new WeakReference<Fragment>(fragment);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(
					"generate fragment error. by value:" + pageValue);
		}
	}

	@Override
	protected boolean hasBackButton() {
		return true;
	}

	@Override
	public void onBackPressed() {
		if (mFragment != null && mFragment.get() != null
				&& mFragment.get() instanceof BaseFragment) {
			BaseFragment bf = (BaseFragment) mFragment.get();
			if (!bf.onBackPressed()) {
				super.onBackPressed();
			}
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.ACTION_DOWN
				&& mFragment.get() instanceof BaseFragment) {
			((BaseFragment) mFragment.get()).onBackPressed();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public void initView() {
	}

	@Override
	public void initData() {
	}

}
