package com.example.hotelmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanager.InformationActivity;

public class InformationActivity extends AppCompatActivity {
    private TextView user_name;
    private TextView id;

    private TextView pwd;
    private TextView qq;
    private TextView wechat;
    private TextView telephone;

    private Button btn_change;
    private String mParam1;
    private String mParam2;
    private View view=null;
    public InformationActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        initView();
        init();
    }
    public void initView(){
        id=(TextView)findViewById(R.id.tv_id);
        user_name=(TextView)findViewById(R.id.tv_user);
        qq=(TextView)findViewById(R.id.tv_qq);
        wechat=(TextView)findViewById(R.id.tv_wechat);
        telephone=(TextView)findViewById(R.id.tv_phone);
        btn_change=(Button)findViewById(R.id.sort);
    }
    public void init(){
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InformationActivity.this, LoginActivity.class);
                InformationActivity.this.startActivity(intent);
                finish();
            }
        });
    }
}
