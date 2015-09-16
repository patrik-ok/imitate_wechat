package com.wechat.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.wechat.app.AppContext;
import com.wechat.ui.widget.WaitDialog;


public class BaseFragment extends Fragment implements OnClickListener{

    protected LayoutInflater mInflater;
    protected View holdView;
    
	public AppContext getApplication() {
        return (AppContext) getActivity().getApplication();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.mInflater = inflater;
        
        if(holdView()) {
        	
        	if(holdView == null) {
        		holdView = inflater.inflate(getLayoutId(), container, false);
        		
        		init(holdView);
    			initData();
        	}
        	
        	ViewGroup parent = (ViewGroup) holdView.getParent();
    		if (parent != null) {
    			parent.removeView(holdView);
    		}

    		return holdView;	
        } else {
        	
        	holdView = inflater.inflate(getLayoutId(), container, false);
    		
    		init(holdView);
			initData();
			
			return holdView;
        }
    }

    protected void hideWaitDialog() {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            ((DialogControl) activity).hideWaitDialog();
//        }
    }

    protected WaitDialog showWaitDialog() {
//        return showWaitDialog(R.string.loading);
    	return null;
    }

    protected WaitDialog showWaitDialog(int resid) {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            return ((DialogControl) activity).showWaitDialog(resid);
//        }
        return null;
    }

    protected WaitDialog showWaitDialog(String resid) {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            return ((DialogControl) activity).showWaitDialog(resid);
//        }
        return null;
    }
    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	protected boolean holdView() {
		return false;
	}

    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }

    protected int getLayoutId() {
        return 0;
    }
    
    protected void init(View view) {
		
	}
    
    protected void initData() {
		
	}
}
