package com.wechat.app;

import java.util.Map;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 全局应用程序类
 * 
 */
public class AppContext extends Application {

	public static final String CONFIG_SOFTWARE_HIGHT = "keyboard_hight";
	
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
	
	public static SharedPreferences getConfig() {
		return instance.getSharedPreferences("wechat", Context.MODE_PRIVATE);
	}
	
	public static void saveString(Map.Entry<String, String>...data) {
		Editor editor = instance.getSharedPreferences("wechat", Context.MODE_PRIVATE).edit();
		
		for(Map.Entry<String, String> entry : data)
			editor.putString(entry.getKey(), entry.getValue());
		
		editor.commit();
	}
	
	public static void saveLong(Map.Entry<String, Long>...data) {
		Editor editor = instance.getSharedPreferences("wechat", Context.MODE_PRIVATE).edit();
		
		for(Map.Entry<String, Long> entry : data)
			editor.putLong(entry.getKey(), entry.getValue());
		
		editor.commit();
	}
	
	public static void saveInt(Map.Entry<String, Integer>...data) {
		Editor editor = instance.getSharedPreferences("wechat", Context.MODE_PRIVATE).edit();
		
		for(Map.Entry<String, Integer> entry : data)
			editor.putInt(entry.getKey(), entry.getValue());
		
		editor.commit();
	}
	
	public static void saveBoolean(Map.Entry<String, Boolean>...data) {
		Editor editor = instance.getSharedPreferences("wechat", Context.MODE_PRIVATE).edit();
		
		for(Map.Entry<String, Boolean> entry : data)
			editor.putBoolean(entry.getKey(), entry.getValue());
		
		editor.commit();
	}
}
