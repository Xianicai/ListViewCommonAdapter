package com.example.xianicai.listviewcommonadapter.commonadapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.example.xianicai.listviewcommonadapter.GlideImageView;

/**
 * Created by Zhanglibin on 2017/5/8.
 */

public class ViewHolder {
    private View mConvertView;
    private SparseArray<View> mViews;

    public ViewHolder(View convertView) {
        this.mConvertView = convertView;
        this.mViews = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T get(int id) {
        T v = (T) mViews.get(id);
        if (v == null) {
            v = (T) mConvertView.findViewById(id);
            mViews.put(id, v);
        }
        return v;
    }

    public void setText(int id, CharSequence text) {
        ((TextView) get(id)).setText(text);
    }

    public void setImage(int id, String url) {
        ((GlideImageView) get(id)).setImage(url);
    }

    public View show(int id) {
        View view = get(id);
        view.setVisibility(View.VISIBLE);
        return view;
    }

    public View hide(int id) {
        View view = get(id);
        view.setVisibility(View.GONE);
        return view;
    }

    public View getConvertView() {
        return mConvertView;
    }
}

