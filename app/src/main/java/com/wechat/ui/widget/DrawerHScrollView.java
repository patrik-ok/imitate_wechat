package com.wechat.ui.widget;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.HorizontalScrollView;

public class DrawerHScrollView extends HorizontalScrollView {  
      
    private IDrawerPresenter drawerPresenter = null;  
    private int currentPage = 0;  
    private int totalPages = 1;  
    private HashMap<Integer, Integer> positionLeftTopOfPages;  
  
    public DrawerHScrollView(Context context) {  
        this(context, null);  
    }  
  
    public DrawerHScrollView(Context context, AttributeSet attrs) {  
    	this(context, attrs, 0);  
    }  
  
    @SuppressLint("UseSparseArrays")
	public DrawerHScrollView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        
        positionLeftTopOfPages = new HashMap<Integer, Integer>();
    }  
      
    public void clean(){  
        currentPage = 0;  
        totalPages = 1;  
        drawerPresenter = null;  
        if(positionLeftTopOfPages != null){  
            positionLeftTopOfPages.clear();  
        }  
    }  
      
    public void setParameters(int totalPages, int currentPage, int scrollDisX) {  
    	
        this.totalPages = totalPages;  
        this.currentPage = currentPage;  
        positionLeftTopOfPages.clear();  
        
        for (int i = 0; i < totalPages; i++){  
        	
            int posx = (scrollDisX) * i;  
            positionLeftTopOfPages.put(i, posx);  
        }  
        
        smoothScrollTo(0, 0);  
    }  
      
    public void setPresenter(IDrawerPresenter drawerPresenter ) {  
        this.drawerPresenter = drawerPresenter;  
    }  
      
    @Override  
    public void fling(int velocityX) {  
    	
        Log.v("mmsg", "-->fling velocityX:"+velocityX);  
        
        boolean change_flag = false;  
        if (velocityX > 0 && (currentPage < totalPages - 1)){  
        	
            currentPage++;  
            change_flag = true;  
        } else if (velocityX < 0 && (currentPage > 0)){ 
        	
            currentPage--;  
            change_flag = true;  
        }  
        
        if (change_flag){  
        	
            int postionTo = positionLeftTopOfPages.get(currentPage).intValue(); 
            
            Log.v("mmsg", "------smoothScrollTo posx:" + postionTo);  
            smoothScrollTo(postionTo, 0);  
            drawerPresenter.dispatchEvent(totalPages, currentPage);  
        }    
    }  
//    @Override
//    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
//
//    int sumX = computeHorizontalScrollOffset();
//    sumX = sumX - 1;
//    int destPage;
//    if (sumX <= 0) {
//    destPage = 0;
//    } else {
//    destPage = sumX / mScrollDisX + 1;
//    }
//
//    if (destPage != currentPage) {
//    currentPage = destPage;
//    updatePageIndicator(totalPages, currentPage );
//    //需自己灵活处理
//    }
//
//    super.onScrollChanged(x, y, oldx, oldy);
//    }
    public interface IDrawerPresenter {  

        void dispatchEvent(int totalPages, int currentPage);  
    }
}  
