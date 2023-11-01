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
import com.example.hotelmanager.R;
import com.example.hotelmanager.bean.Staff;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final int MSG_SHOP_OK=1;
    private MHandler mHandler;

    private TextView Id;

    private TextView pwd;

    private TextView qq;

    private TextView wechat;
    private TextView telephone;

    private Button change_id;
    private String mParam1;
    private String mParam2;
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
            mHandler=new MHandler();
            view = inflater.inflate(R.layout.information, container, false);
            init(view);
        }
        return view;
    }
    public void init(View view){
        change_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getActivity().getSharedPreferences("config", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("autologin", "no");
                editor.commit();
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
        String mineinfo_url = Constant.MineInfo_URL +"?Staffun="+staffun;
        Callback callback = new Callback()
        {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData= response.body().string();
//                infojson=responseData;
                Log.e("LoginActivity","onResponse");
                Log.e("LoginActivity",responseData);
                Gson gson=new Gson();
                staff = gson.fromJson(responseData, Staff.class);//设置到对应的控件上显示信息
                StaffNo=(TextView)view.findViewById(R.id.StaffNo);
                StaffId=(TextView)view.findViewById(R.id.StaffId);
                StaffName=(TextView)view.findViewById(R.id.StaffName);
                Staffun=(TextView)view.findViewById(R.id.Staffun);
                Staffpwd=(EditText)view.findViewById(R.id.Staffpwd);
                StaffWork=(TextView)view.findViewById(R.id.StaffWork);
                StaffSla=(TextView)view.findViewById(R.id.StaffSla);
                StaffDate=(TextView)view.findViewById(R.id.StaffDate);
                telephone=(EditText)view.findViewById(R.id.telephone);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        StaffNo.setText(staff.getStaffNo());
                        StaffId.setText(staff.getStaffId());
                        StaffName.setText(staff.getStaffName());
                        StaffWork.setText(staff.getStaffWork());
                        Staffpwd.setText(staff.getStaffpwd());
                        Staffun.setText(staff.getStaffun());
                        StaffSla.setText(String.valueOf(staff.getStaffSla()));
                        StaffDate.setText(staff.getStaffDate());
                        telephone.setText(staff.getTelephone());
                    }
                });
            }
            @Override
            public void onFailure(Call call, IOException e) {
                String responseData = "Error!";
                Log.e("LoginActivity","Failure");
                Log.e("LoginActivity",responseData);
            }
        };
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(mineinfo_url)
                .build();
        //发送请求
        client.newCall(request).enqueue(callback);

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