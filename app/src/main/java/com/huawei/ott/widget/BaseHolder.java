package com.huawei.ott.widget;

import android.view.View;

public abstract class BaseHolder<T> {

	private View view;

	private T data;
	public BaseHolder() {
		view = initView();
		view.setTag(this);
	}

	public void setData(T data){
		this.data = data;
		refreshView();
	}
	
	public abstract void refreshView();

	public T getData(){
		return data;
	}
	
	public View getRootView(){
		return view;
	}
	
	public abstract View initView();


	
}
