package com.example.hotelmanager.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelmanager.OrderDetailActivity;
import com.example.hotelmanager.bean.Hotel;
import com.example.hotelmanager.R;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    private Context mContext;
    private List<Hotel> Hotellist;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView HotelType;
        TextView HotelPrice;
        TextView HotelFreeState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.main);
            this.HotelFreeState = (TextView) itemView.findViewById(R.id.free_state);
        }
    }

    public HotelAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Hotel> HotelList) {
        this.Hotellist = HotelList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel hotel = Hotellist.get(position);
        Glide.with(mContext).load(hotel.getHotelPic()).error(R.mipmap.ic_launcher).into(holder.img);
        holder.HotelType.setText(hotel.getHotelType());
        holder.HotelPrice.setText("￥" + hotel.getPrice() + "元/天");
        holder.HotelFreeState.setText(hotel.getIsfree());
        Log.e("HotelAdapter", "onBindViewHolder" + position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int position = holder.getAdapterPosition();
                Hotel bean = Hotellist.get(position);
                if (bean == null) return;
                if (bean.getIsfree().equals("无空闲")) {
                    new AlertDialog.Builder(mContext)
                            .setMessage("无空闲房间！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).show();
                    return;
                }
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                //把店铺的详细信息传递到店铺详情界面
                intent.putExtra("hotelInfo", bean);
                mContext.startActivity(intent);
            }
        });
        Log.e("HotelAdapter", "onCreateViewHolder");
        return holder;
    }

    @Override
    public int getItemCount() {
        return Hotellist == null ? 0 : Hotellist.size();
    }
}
