package com.example.hotelmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.hotelmanager.bean.Type;

import java.util.List;

public class TypeAdapter extends BaseAdapter {
    private List<Type> list;
    private Context context;
    public TypeAdapter(Context context, List<Type> list) {
        super();
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
