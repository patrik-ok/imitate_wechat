package com.wechat.ui.bean;

public class ChatMenuItem {
	private int menuId;
	private int menuImg;
	private String name;

	public ChatMenuItem(int id) {
		menuId = id;
	}
	
	public ChatMenuItem(int id, int imgRes, String name) {
		menuId = id;
		menuImg = imgRes;
		this.name = name;
	}
	
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(int menuImg) {
		this.menuImg = menuImg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
