package com.example.hotelmanager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanager.bean.Hotel;
import com.example.hotelmanager.bean.Order;


public class OrderActivity extends AppCompatActivity {
    private Hotel hotel;
    private Order order;
    private TextView tv_title,home_num,reside_time,book_days,resident_name,resident_id,resident_sex,resident_phone,resident_remark,home_price,order_price,home_type;
    private ImageView tv_back;
    private TextView tv_payment;
    private RelativeLayout rl_title_bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        hotel=(Hotel) getIntent().getSerializableExtra("hotelInfo"); //获取酒店数据
        order=(Order) getIntent().getSerializableExtra("orderInfo"); //获取订单详情信息
        initView();
        setData();
    }
    /**
     * 初始化界面控件
     */
    private void initView(){
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("订单确认");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.black));
        home_num=(TextView)findViewById(R.id.home_num);
        reside_time=(TextView)findViewById(R.id.reside_time);
        book_days=(TextView) findViewById(R.id.book_days);
        resident_name=(TextView)findViewById(R.id.resident_name);
        resident_id=(TextView)findViewById(R.id.resident_id);
        resident_sex=(TextView)findViewById(R.id.resident_sex);
        resident_phone=(TextView)findViewById(R.id.resident_phone);
        resident_remark=(TextView)findViewById(R.id.resident_remark);
        home_price=(TextView)findViewById(R.id.home_price);
        order_price=(TextView)findViewById(R.id.order_total_cost);
        home_type=(TextView)findViewById(R.id.home_type);
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_payment = (TextView) findViewById(R.id.tv_payment);
        // 返回键的点击事件
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //“确认支付”按钮的点击事件
                finish();
            }
        });
    }
    /**
     * 设置界面数据
     */
    private void setData() {
        home_num.setText(order.getOrder_homenum());
        home_type.setText(hotel.getHotelType());
        reside_time.setText(order.getOrder_create_time());
        book_days.setText(order.getBook_days()+"天");
        resident_name.setText(order.getResident_name());
        resident_id.setText(order.getResident_id());
        resident_sex.setText(order.getResident_sex());
        resident_phone.setText(order.getResident_phone());
        resident_remark.setText(order.getResident_remark());
        home_price.setText("￥"+hotel.getPrice()+"元/天");
        order_price.setText(order.getOrder_price()+"元");
    }
}