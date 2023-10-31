package com.example.hotelmanager.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HotelListView extends RecyclerView {
    public HotelListView(@NonNull Context context) {
        super(context);
    }

    public HotelListView(@NonNull Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public HotelListView(@NonNull Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
