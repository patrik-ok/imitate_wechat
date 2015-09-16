package com.wechat.app;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 全局应用程序类
 * 
 */
public class AppContext extends Application {

	private static AppContext instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		init();

	}

	private void init() {

	}

	/**
	 * 获得当前app运行的AppContext
	 * 
	 * @return
	 */
	public static AppContext getInstance() {
		return instance;
	}

	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}
}
