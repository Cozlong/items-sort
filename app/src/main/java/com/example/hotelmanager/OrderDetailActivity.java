package com.example.hotelmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanager.bean.Hotel;
import com.example.hotelmanager.bean.Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OrderDetailActivity extends AppCompatActivity  implements View.OnClickListener{
    private Hotel hotel;
    private Order order;
    private ImageView tv_back;
    private TextView tv_title,order_create_time;
    private EditText book_days,resident_name,resident_id,resident_phone,resident_remark;
    private BigDecimal totalMoney; //订单总价格
    private RelativeLayout rl_title_bar;
    private Spinner free_home_num;
    private RadioGroup resident_sex;
    private Button submit_order;
    private SubmitHandler submitHandler;
    public static final int MSG_LOGIN_ERR = 1; //订单提交失败
    public static final int MSG_CONNET_ERR = 2; //网络链接错误
    public static final int MSG_LOGIN_SUCCESS=3;//订单提交成功

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        hotel = (Hotel) getIntent().getSerializableExtra("hotelInfo"); //获取酒店数据
        if (hotel == null) return;
        totalMoney = new BigDecimal(0.0);//初始化变量 totalMoney
        initView(); //初始化界面控件
        setData(); //设置界面数据
    }
            /**
             * 初始化界面控件
             */

    private void initView() {
        rl_title_bar=(RelativeLayout)findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.black));
        tv_back = (ImageView) findViewById(R.id.tv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText("订单创建");
        order_create_time=(TextView)findViewById(R.id.order_create_time);
        free_home_num=(Spinner)findViewById(R.id.free_home_num);
        book_days=(EditText)findViewById(R.id.book_days);
        resident_name=(EditText)findViewById(R.id.resident_name);
        resident_id=(EditText)findViewById(R.id.resident_id);
        resident_sex=(RadioGroup)findViewById(R.id.resident_sex);
        resident_phone=(EditText)findViewById(R.id.resident_phone);
        resident_remark=(EditText)findViewById(R.id.resident_remark);
        submit_order=(Button)findViewById(R.id.submit_order);

        //设置返回键、提交订单按钮的点击监听事件
        tv_back.setOnClickListener(this);
        submit_order.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back: //返回按钮的点击事件
                finish();
                break;
            case R.id.submit_order:
                submitOrder();
                break;
        }
   }

    public void submitOrder(){
        String time=order_create_time.getText().toString();
        String homenum=free_home_num.getSelectedItem().toString();
        String bookdays=book_days.getText().toString();
        String residentname=resident_name.getText().toString();
        String residentid=resident_id.getText().toString();
        String residentsex=((RadioButton)findViewById(resident_sex.getCheckedRadioButtonId())).getText().toString();
        String residentphone=resident_phone.getText().toString();
        String residentremark=resident_remark.getText().toString();
        if(bookdays.equals("")||residentname.equals("")||residentid.equals("")||residentphone.equals("")){
            new AlertDialog.Builder(this)
                    .setMessage("除备注外其他信息都不能为空！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
            return;
        }
        submitHandler=new SubmitHandler();
        order=new Order();
        order.setOrder_create_time(time);
        order.setOrder_homenum(homenum);
        order.setBook_days(new BigDecimal(bookdays));
        order.setResident_name(residentname);
        order.setResident_id(residentid);
        order.setResident_sex(residentsex);
        order.setResident_phone(residentphone);
        order.setResident_remark(residentremark);
        order.setOrder_price(order.getBook_days().multiply(hotel.getPrice()));
        String submit_check_url = Constant.SUBMIT_URL +"?order_create_time="+order.getOrder_create_time()
                +"&order_homenum="+order.getOrder_homenum()
                +"&book_days="+order.getBook_days()
                +"&resident_name="+order.getResident_name()
                +"&resident_id="+order.getResident_id()
                +"&resident_sex="+order.getResident_sex()
                +"&resident_phone="+order.getResident_phone()
                +"&resident_remark="+order.getResident_remark()
                +"&resident_price="+order.getOrder_price();
        okhttp3.Callback callback = new okhttp3.Callback()
        {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.e("OrderDetailActivity","onResponse");
                Log.e("OrderDetail",responseData);
                if(responseData.equals("success")) {
                    Message msg = new Message();
                    msg.what = MSG_LOGIN_SUCCESS;
                    submitHandler.sendMessage(msg);
                    try{
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(OrderDetailActivity.this, OrderActivity.class);
                    intent.putExtra("hotelInfo", hotel);
                    intent.putExtra("orderInfo",order);
                    startActivity(intent);
                    finish();
                }
                else {
                    Message msg = new Message();
                    msg.what = MSG_LOGIN_ERR;
                    submitHandler.sendMessage(msg);
                }
            }
            @Override
            public void onFailure(Call call, IOException e) {
                String responseData = "Error!";
                Log.e("OrderDetailActivity","Failure");
                Log.e("OrderDetailActivity",responseData);
                Message msg = new Message();
                msg.what = MSG_CONNET_ERR;
                submitHandler.sendMessage(msg);
            }
        };
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(submit_check_url)
                .build();
        //发送请求
        client.newCall(request).enqueue(callback);

    }
            /**
             * 设置界面数据
             */

    private void setData() {
        if (hotel == null) return;
        String[] homenum=hotel.getHotelnumList();
        ArrayAdapter<String> adpter = new ArrayAdapter(this,R.layout.spinner_home_num,homenum);
        free_home_num.setAdapter(adpter);
        order_create_time.setText(getFormatDate());
    }

    public String getFormatDate() {
        Calendar c = Calendar.getInstance();
        Date newDate = c.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDt = simpleDateFormat.format(newDate);
        return strDt;
    }

    class SubmitHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_LOGIN_ERR:
                    new androidx.appcompat.app.AlertDialog.Builder(OrderDetailActivity.this)
                            .setTitle("注意")
                            .setMessage("订单提交失败")
                            .setPositiveButton("确定",null)
                            .create()
                            .show();
                    break;
                case MSG_CONNET_ERR:
                    new androidx.appcompat.app.AlertDialog.Builder(OrderDetailActivity.this)
                            .setTitle("注意")
                            .setMessage("网络连接错误，请检查网络")
                            .setPositiveButton("确定",null)
                            .create()
                            .show();
                    break;
                case MSG_LOGIN_SUCCESS:
                    Toast.makeText(OrderDetailActivity.this, "订单提交成功！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
