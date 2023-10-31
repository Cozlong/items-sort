package com.example.hotelmanager.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelmanager.OrderDetailActivity;
import com.example.hotelmanager.R;
import com.example.hotelmanager.bean.Hotel;
import com.example.hotelmanager.bean.Room;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> implements Filterable{
    private Context mContext;
    private List<Room> sourceRoomlist;
    private List<Room> filterRoomlist;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    //没有过滤的内容，则使用源数据
                    filterRoomlist = sourceRoomlist;
                }else {
                    Log.e("RoomAdapter", "json: "+charString);
                    Log.e("RoomAdapter", "sourcesize: "+sourceRoomlist.size());
                    Gson gson=new Gson();
                    Room room=gson.fromJson(charString,new TypeToken<Room>(){}.getType());
                    List<Room> filteredList = new ArrayList<>();
                    if(room.getRoomNo()!=null){
                        for (int i = 0; i < sourceRoomlist.size(); i++) {
                            Log.e("RoomAdapter", "soroomnum: "+sourceRoomlist.get(i).getRoomNo());
                            if (sourceRoomlist.get(i).getRoomNo().equals(room.getRoomNo())) {
                                filteredList.add(sourceRoomlist.get(i));
                                Log.e("RoomAdapter", "filterroomnum: "+filteredList.get(0).getRoomNo());
                                break;
                            }
                        }
                    }else {
                        filteredList=filteredListGetValue();
                        for(int i=0;i<filteredList.size();i++){
                            if(!room.getRoomType().equals("")){
                                Log.e("RoomAdapter", "filterroomtype: "+filteredList.get(0).getRoomType());
                                if(!filteredList.get(i).getRoomType().equals(room.getRoomType())){
                                    filteredList.remove(i);
                                    i--;
                                    continue;
                                }
                            }
                            if(!room.getIsfree().equals("")){
                                if(!filteredList.get(i).getIsfree().equals(room.getIsfree())){
                                    filteredList.remove(i);
                                    i--;
                                    continue;
                                }
                            }
                            if(!room.getRoomprice().equals(BigDecimal.ZERO)){
                                if(filteredList.get(i).getRoomprice().compareTo(room.getRoomprice())==1){
                                    filteredList.remove(i);
                                    i--;
                                    continue;
                                }
                            }
                        }
                    }
                    Log.e("RoomAdapter", "filterroomsize: "+filteredList.size());
                    filterRoomlist = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterRoomlist;
                return filterResults;
            }

            public List<Room> filteredListGetValue(){
                List<Room> roomList=new ArrayList<>();
                for (int i=0;i<sourceRoomlist.size();i++){
                    roomList.add(sourceRoomlist.get(i));
                }
                return roomList;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterRoomlist = (List<Room>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView room_num;
        TextView roomType;
        TextView roomPrice;
        TextView roomFreeState;
        TextView resident_name;
        TextView start_during;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.main);
            this.room_num=(TextView)itemView.findViewById(R.id.room_num);
            this.roomType = (TextView) itemView.findViewById(R.id.room_type);
            this.roomPrice = (TextView) itemView.findViewById(R.id.room_price);
            this.roomFreeState = (TextView) itemView.findViewById(R.id.free_state);
            this.resident_name=(TextView) itemView.findViewById(R.id.resident_name);
            this.start_during=(TextView) itemView.findViewById(R.id.start_during);
        }
    }

    public RoomAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Room> RoomList) {
        this.sourceRoomlist = RoomList;
        this.filterRoomlist= RoomList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = filterRoomlist.get(position);
        Glide.with(mContext).load(room.getRoomPic()).error(R.mipmap.ic_launcher).into(holder.img);
        holder.room_num.setText(room.getRoomNo());
        holder.roomType.setText(room.getRoomType());
        holder.roomPrice.setText("￥"+room.getRoomprice()+"元/天");
        holder.roomFreeState.setText(room.getIsfree());
        if(!room.getResident_name().equals("null")&&!room.getIsfree().equals("空闲")) {
            holder.resident_name.setText(room.getResident_name());
            holder.start_during.setText("入住时间：" + room.getStart_time());
        }else {
            holder.resident_name.setText("");
            holder.start_during.setText("");
        }
        Log.e("RoomAdapter", "onBindViewHolder" + position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.roomlist_item, parent,false);
        final ViewHolder holder = new ViewHolder(view);
        Log.e("RoomAdapter", "onCreateViewHolder");
        return holder;
    }

    @Override
    public int getItemCount() {
        return filterRoomlist== null ? 0 : filterRoomlist.size();
    }
}
