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

public class LoginActivity extends AppCompatActivity {

    private String fileName="config";
    public static final int MSG_LOGIN_ERR = 1; //登录错误 2
    public static final int MSG_CONNET_ERR = 2; //网络链接错误 3

    private Context context;

    private EditText et_number;
    private EditText et_password;
    private Button bt_login;
    private ImageView iv_weixin;
    private ImageView iv_qq;
    private ImageView iv_weibo;
    private LoginHandler login_handler;
    private CheckBox autolg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        InitView();
        login_handler = new LoginHandler();
        Init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initContent();
    }

    private void InitView()
    {
        et_number = (EditText)findViewById(R.id.et_number);
        et_password = (EditText)findViewById(R.id.et_password);
        bt_login = (Button)findViewById(R.id.bt_login);
        iv_weixin = (ImageView)findViewById(R.id.iv_weixin);
        iv_qq = (ImageView)findViewById(R.id.iv_qq);
        iv_weibo = (ImageView)findViewById(R.id.iv_weibo);
        autolg=(CheckBox)findViewById(R.id.autolg);
    }

    private void Init() {
        //设置提示的颜色
        et_number.setHintTextColor(getResources().getColor(R.color.black));
        et_password.setHintTextColor(getResources().getColor(R.color.black));
        //登录 42
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (judge()) {
                    bt_login.setEnabled(false);//点了登录后不可以再点，避免用户乱点
                    loginInfo();
                }
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
        //微博
        iv_weibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "微博", Toast.LENGTH_SHORT).show();
            }
        });

        autolg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    SharedPreferences sp = getSharedPreferences(fileName, Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("autologin","yes");
                    editor.commit();
                }
                else{
                    SharedPreferences sp = getSharedPreferences(fileName, Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("autologin","no");
                    editor.commit();
                }
            }
        });

    }

    private void initContent(){
        SharedPreferences sp = getSharedPreferences(fileName, Activity.MODE_PRIVATE);
        et_number.setText(sp.getString("userName",""));
        et_password.setText(sp.getString("password",""));
        if(sp.getString("autologin","").equals("yes")){
            autolg.setChecked(true);
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bt_login.performClick();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
                Toast.makeText(this, "自动登录中...", Toast.LENGTH_SHORT).show();
        }
    }

            /**登录*/
    private void loginInfo() {
        String userId = et_number.getText().toString();
        String userPassword = et_password.getText().toString();
        if(userId.equals("czl")&&userPassword.equals("123")) {
                SharedPreferences sp = getSharedPreferences(fileName, Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userName",userId);
                editor.putString("password",userPassword);
                editor.commit();
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("Staffun",userId);
                startActivity(intent);
                finish();
            }else {
                Message msg = new Message();
                msg.what = MSG_LOGIN_ERR;
                login_handler.sendMessage(msg);
            }
//        //get 请求
//        String login_check_url = Constant.LOGIN_URL +"?username="+userId+"&password="+userPassword;
//        okhttp3.Callback callback = new okhttp3.Callback()
//        {
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//            String responseData = response.body().string();
//            Log.e("LoginActivity","onResponse");
//            Log.e("LoginActivity",responseData);
//            if(responseData.equals("success")) {
//                SharedPreferences sp = getSharedPreferences(fileName, Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString("userName",userId);
//                editor.putString("password",userPassword);
//                editor.commit();
//                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
//                intent.putExtra("Staffun",userId);
//                startActivity(intent);
//                finish();
//            }
//        else {
//                Message msg = new Message();
//                msg.what = MSG_LOGIN_ERR;
//                login_handler.sendMessage(msg);
//            }
//        }
//            @Override
//            public void onFailure(Call call, IOException e) {
//            String responseData = "Error!";
//            Log.e("LoginActivity","Failure");
//            Log.e("LoginActivity",responseData);
//            Message msg = new Message();
//            msg.what = MSG_CONNET_ERR;
//            login_handler.sendMessage(msg);
//        }
//        };
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(login_check_url)
//                .build();
//        //发送请求
//        client.newCall(request).enqueue(callback);
    }

    //判断登录信息是否合法
    private boolean judge() {
        if (TextUtils.isEmpty(et_number.getText().toString()) ) {
            Toast.makeText(context, "用户名不能为空",Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(et_password.getText().toString())) {
            Toast.makeText(context, "密码不能为空",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
            /**
             * 事件捕获
             */
    class LoginHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case MSG_LOGIN_ERR:
                    et_number.setText("");
                    et_password.setText("");
                    et_number.requestFocus();
                    new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("注意")
                        .setMessage("用户名或密码输入不正确，请重新输入")
                        .setPositiveButton("确定",null)
                        .create()
                        .show();
                    bt_login.setEnabled(true);
                    break;
                case MSG_CONNET_ERR:
                    new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("注意")
                        .setMessage("网络连接错误，请检查网络")
                        .setPositiveButton("确定",null)
                        .create()
                        .show();
                    break;
            }
        }
    }
}