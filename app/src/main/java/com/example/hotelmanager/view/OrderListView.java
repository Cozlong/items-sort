package com.example.hotelmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class OrderListView extends RecyclerView {
    public OrderListView(@NonNull Context context) {
        super(context);
    }

    public OrderListView(@NonNull Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public OrderListView(@NonNull Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);

    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        int expandSpec= View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthSpec, expandSpec);
    }
}
