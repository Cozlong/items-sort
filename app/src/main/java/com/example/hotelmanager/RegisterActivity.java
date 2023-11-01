package com.example.hotelmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private String fileName="config";
    public static final int MSG_REGISTER_ERR = 1; //登录错误 2
    private Context context;

    private EditText et_number;
    private EditText et_password;

    private EditText et_passwordagain;
    private Button bt_register;
    private Button bt_back;
    private ImageView iv_weixin;
    private ImageView iv_qq;
    private CheckBox autolg;
    RegisterHandler register_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = this;
        InitView();
        register_handler=new RegisterHandler();
        Init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void InitView()
    {
        et_number = (EditText)findViewById(R.id.et_number);
        et_password = (EditText)findViewById(R.id.et_password);
        et_passwordagain = (EditText)findViewById(R.id.et_passwordagain);
        bt_register = (Button)findViewById(R.id.bt_register);
        bt_back = (Button)findViewById(R.id.bt_back);
        iv_weixin = (ImageView)findViewById(R.id.iv_weixin);
        iv_qq = (ImageView)findViewById(R.id.iv_qq);
        autolg=(CheckBox)findViewById(R.id.autolg);
    }

    private void Init() {
        //设置提示的颜色
        et_number.setHintTextColor(getResources().getColor(R.color.black));
        et_password.setHintTextColor(getResources().getColor(R.color.black));
        et_passwordagain.setHintTextColor(getResources().getColor(R.color.black));
        //登录 42
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (judge()) {
                    bt_register.setEnabled(false);//点了注册后不可以再点，避免用户乱点
                    RegisterInfo();
                }
            }
        });
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
                finish();
            }
        });
        //微信
        iv_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "微信", Toast.LENGTH_SHORT).show();
            }
        });

        //qq 70
        iv_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "QQ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**登录*/
    private void RegisterInfo() {
        String userId = et_number.getText().toString();
        String userPassword = et_password.getText().toString();
        String userPasswordagain = et_passwordagain.getText().toString();
        if (userPassword.equals(userPasswordagain)) {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Message msg = new Message();
            msg.what = MSG_REGISTER_ERR;
            register_handler.sendMessage(msg);
        }
    }
    //判断登录信息是否合法
    private boolean judge() {
        if (TextUtils.isEmpty(et_number.getText().toString()) ) {
            Toast.makeText(context, "用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_password.getText().toString())) {
            Toast.makeText(context, "密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_passwordagain.getText().toString())) {
            Toast.makeText(context, "重复密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /**
     * 事件捕获
     */
    class RegisterHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_REGISTER_ERR:
                    et_password.setText("");
                    et_passwordagain.setText("");
                    et_number.requestFocus();
                    new AlertDialog.Builder(RegisterActivity.this)
                            .setTitle("注意")
                            .setMessage("两次密码输入不同，请重新输入")
                            .setPositiveButton("确定",null)
                            .create()
                            .show();
                    bt_register.setEnabled(true);
                    break;
            }
        }
    }
}
