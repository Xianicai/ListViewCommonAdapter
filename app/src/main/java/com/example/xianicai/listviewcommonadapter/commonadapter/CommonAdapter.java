package com.example.xianicai.listviewcommonadapter.commonadapter;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 *
 * 通用的Adapter，实现{@link #convert(int, ViewHolder, Object)}即可快速的构造一个Adapter
 * Created by Zhanglibin on 2017/5/8
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflater;
    protected SparseIntArray mViewTypes;
    protected ViewGroup mParent;

    public CommonAdapter(Context context, List<T> data) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mData = data;
        mViewTypes = new SparseIntArray();
    }

    public CommonAdapter(Context context, List<T> data, int layoutId) {
        this(context, data);
        addViewType(0, layoutId);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mParent == null) {
            this.mParent = parent;
        }
        ViewHolder holder;
        if (convertView == null) {
            System.out.println(getLayoutId(position)+"&&&&&");
            convertView = mInflater.inflate(getLayoutId(position), parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        convert(position, holder, mData.get(position));
        return convertView;
    }

    protected @LayoutRes
    int getLayoutId(int position) {
        int viewType = getItemViewType(position);
        return mViewTypes.get(viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return mViewTypes.size();
    }

    protected void addViewType(int viewType, @LayoutRes int layoutId) {
        mViewTypes.put(viewType, layoutId);
    }

    protected abstract void convert(int position, ViewHolder holder, T model);

    public List<T> getData() {
        return mData;
    }

    public Context getContext() {
        return mContext;
    }

    public ViewGroup getParent() {
        return mParent;
    }

}
