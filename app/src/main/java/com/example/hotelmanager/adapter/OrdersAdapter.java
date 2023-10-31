package com.example.hotelmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanager.R;
import com.example.hotelmanager.bean.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Context mContext;
    private List<Order> ordersList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView OrderNo;
        TextView OrderStatus;
        TextView ResName;
        TextView StartDate;
        TextView RoomNo;
        TextView remark;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.OrderNo = (TextView)itemView.findViewById(R.id.OrderNo);
            this.OrderStatus = (TextView)itemView.findViewById(R.id.OrderStatus);
            this.ResName =  (TextView)itemView.findViewById(R.id.ResName);
            this.StartDate =  (TextView) itemView.findViewById(R.id.StartDate);
            this.RoomNo=(TextView) itemView.findViewById(R.id.RoomNo);
            this.remark=(TextView) itemView.findViewById(R.id.remark);
        }
    }

    public void setData(List<Order> ordersList){
        this.ordersList=ordersList;
        notifyDataSetChanged();
    }
    public OrdersAdapter(Context context) {
        this.mContext=context;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = ordersList.get(position);
        holder.OrderNo.setText(order.getOrder_id());
        holder.ResName.setText("住客:"+order.getResident_name());
        holder.OrderStatus.setText(order.getOrder_state());
        holder.StartDate.setText(order.getOrder_create_time());
        holder.RoomNo.setText("房间号:"+order.getOrder_homenum());
        holder.remark.setText(order.getResident_remark());
        Log.e("OrdersAdapter","onBindViewHolder"+position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.orderslist_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        Log.e("RecyclerWaimaiAdapter","onCreateViewHolder");
        return holder;
    }

    @Override
    public int getItemCount() {
        return ordersList==null?0:ordersList.size();
    }
}