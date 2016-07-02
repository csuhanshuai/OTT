package com.huawei.ott.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huawei.ott.holder.ViewHolderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonas on 2015/11/22.
 */
public abstract class BaseListAdapter<T> extends BaseAdapter
{

    protected List<T> mData;

    public BaseListAdapter(List<T> data)
    {
        mData = data;
    }

    public BaseListAdapter(int[] layoutResArrays)
    {
        this(layoutResArrays, null);
    }

    public BaseListAdapter(int[] layoutResArrays, List<T> data)
    {
        mData = data;
    }

    public void setData(List<T> data)
    {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void addData(List<T> data)
    {
        if(data != null)
        {
            this.mData.addAll(data);
        }
        this.notifyDataSetChanged();
    }

    public void addData(T data)
    {
        if(null != mData)
        {
            this.mData.add(data);
            this.notifyDataSetChanged();
        }
    }

    public ArrayList<T> getAllData()
    {
        return (ArrayList<T>)this.mData;
    }

    @Override
    public int getCount()
    {
        if(null == this.mData)
        {
            return 0;
        }
        return this.mData.size();
    }

    @Override
    public T getItem(int position)
    {
        if(position>this.mData.size())
        {
            return null;
        }
        return mData.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * 布局类型有多种时 需要重写该方法
     */
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolderHelper helper = getAdapterHelper(position, convertView, parent);
        T item = getItem(position);
        if(null != item)
        {
            convert(helper, item);
        }
        View rootView = helper.getRootView();
        return rootView;
    }

    protected abstract ViewHolderHelper getAdapterHelper(int position, View convertView, ViewGroup parent);

    protected abstract void convert(ViewHolderHelper helper, T item);

}
