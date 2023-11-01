package com.example.hotelmanager.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hotelmanager.Constant;
import com.example.hotelmanager.LoginActivity;
import com.example.hotelmanager.MainActivity;
import com.example.hotelmanager.R;
import com.example.hotelmanager.RegisterActivity;
import com.example.hotelmanager.bean.Staff;
import com.example.hotelmanager.bean.User;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int MSG_SHOP_OK=1;
//    private MHandler mHandler;
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
    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
//            mHandler=new MHandler();
            view = inflater.inflate(R.layout.information, container, false);
            init(view);
        }
        return view;
    }
    public void init(View view){
        //        id=(TextView)view.findViewById(R.id.tv_id);
//        user_name=(TextView)view.findViewById(R.id.tv_user);
//        qq=(TextView)view.findViewById(R.id.tv_qq);
//        wechat=(TextView)view.findViewById(R.id.tv_wechat);
//        telephone=(TextView)view.findViewById(R.id.tv_phone);
        btn_change=(Button)view.findViewById(R.id.sort);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences sp = getActivity().getSharedPreferences("config", Activity.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putString("autologin", "no");
//                editor.commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });



//        bt_update.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                String Staffpwddata = Staffpwd.getText().toString();
//                String telephonedata= telephone.getText().toString();
//                String StaffNodata= StaffNo.getText().toString();
//                Callback callback = new Callback()
//                {
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String responseData = response.body().string();
//                        Log.e("LoginActivity","onResponse");
//                        Log.e("LoginActivity",responseData);
//                        if(responseData.equals("success")) {
//                            Looper.prepare();
//                            Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }else{
//                            Looper.prepare();
//                            Toast.makeText(getActivity(), "修改失败", Toast.LENGTH_SHORT).show();
//                            Looper.loop();
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        String responseData = "Error!";
//                        Log.e("LoginActivity","Failure");
//                        Log.e("LoginActivity",responseData);
//                    }
//                };
//                OkHttpClient client = new OkHttpClient();
//                Request request = new Request.Builder()
//                        .url(login_check_url)
//                        .build();
//                //发送请求
//                client.newCall(request).enqueue(callback);
//            }
//        });
//        Callback callback = new Callback()
//        {
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String responseData= response.body().string();
////                infojson=responseData;
//                Log.e("LoginActivity","onResponse");
//                Log.e("LoginActivity",responseData);
//                Gson gson=new Gson();
////                staff = gson.fromJson(responseData, Staff.class);//设置到对应的控件上显示信息

//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        id.setText(User.getId());
//                        user_name.setText(User.getUser_name());
//                        qq.setText(staff.getStaffName());
//                        wechat.setText(staff.getStaffWork());
//                        telephone.setText(User.getPhone_number());
//                    }
//                });
//            }
//            @Override
//            public void onFailure(Call call, IOException e) {
//                String responseData = "Error!";
//                Log.e("LoginActivity","Failure");
//                Log.e("LoginActivity",responseData);
//            }
//        };
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(mineinfo_url)
//                .build();
//        //发送请求
//        client.newCall(request).enqueue(callback);
//
    }
    class MHandler extends Handler {
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_SHOP_OK:
                    if(msg.obj!=null){
                        String result=(String) msg.obj;
                    }
                    break;
            }
        }
    }
}
