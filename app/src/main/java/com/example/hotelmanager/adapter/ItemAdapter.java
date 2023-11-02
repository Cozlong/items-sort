package com.example.hotelmanager.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hotelmanager.R;
import com.example.hotelmanager.bean.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Filterable {
    private Context mContext;
    private List<Item> sourceItemlist;
    private List<Item> filterItemlist;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    //没有过滤的内容，则使用源数据
                    filterItemlist = sourceItemlist;
                }else {
                    Gson gson=new Gson();
                    Item item=gson.fromJson(charString,new TypeToken<Item>(){}.getType());
                    List<Item> filteredList = new ArrayList<>();
                    if(item.getItem_name()!=null){
                        for (int i = 0; i < sourceItemlist.size(); i++) {
                            if (sourceItemlist.get(i).getItem_name().equals(item.getItem_name())) {
                                filteredList.add(sourceItemlist.get(i));
                                break;
                            }
                        }
                    }else{
                        filteredList=filteredListGetValue();
                        for(int i=0;i<filteredList.size();i++) {
                            if (!item.getItem_type().equals("")) {
                                if (!filteredList.get(i).getItem_type().equals(item.getItem_type())) {
                                    filteredList.remove(i);
                                    i--;
                                    continue;
                                }
                            }
                            if (!item.getTerm_type().equals("")) {
                                if (!filteredList.get(i).getTerm_type().equals(item.getTerm_type())) {
                                    filteredList.remove(i);
                                    i--;
                                    continue;
                                }
                            }
                        }
                    }
                    filterItemlist = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filterItemlist;
                return filterResults;
            }

            public List<Item> filteredListGetValue(){
                List<Item> itemList=new ArrayList<>();
                for (int i=0;i<sourceItemlist.size();i++){
                    itemList.add(sourceItemlist.get(i));
                }
                return itemList;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filterItemlist = (List<Item>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView item_name;
        TextView date;
        TextView term;
        Button details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.img = (ImageView) itemView.findViewById(R.id.main);
            this.item_name=(TextView) itemView.findViewById(R.id.name);
            this.date=(TextView) itemView.findViewById(R.id.date);
            this.term=(TextView) itemView.findViewById(R.id.term);
            this.details=(Button) itemView.findViewById(R.id.detail);
        }
    }

    public ItemAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<Item> ItemList) {
        this.sourceItemlist = ItemList;
        this.filterItemlist= ItemList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Item item = filterItemlist.get(position);
        Glide.with(mContext).load(R.drawable.homeblack).error(R.mipmap.ic_launcher).into(holder.img);
        holder.item_name.setText(item.getItem_name());
        holder.date.setText(item.getDate_now());
        Date date = new Date(System.currentTimeMillis());
        long stamp =date.getTime()-item.getDate_of_manufacture().getTime();
        int stampDay = (int)(stamp / (24 * 60 * 60 * 1000));
        if(item.getQuality_guarantee_period()-stampDay>0){
             holder.term.setText((item.getQuality_guarantee_period()-stampDay)+"天后");
        }else {
             holder.term.setText("已过期");
        }
        Log.e("ItemAdapter", "onBindViewHolder" + position);
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_item, parent,false);
        final ItemAdapter.ViewHolder holder = new ItemAdapter.ViewHolder(view);
        Log.e("ItemAdapter", "onCreateViewHolder");
        return holder;
    }

    @Override
    public int getItemCount() {
        return filterItemlist== null ? 0 : filterItemlist.size();
    }

}
