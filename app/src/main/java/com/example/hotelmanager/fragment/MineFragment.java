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
import com.example.hotelmanager.InformationActivity;
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
    private ImageView avatar;
    private TextView user_name;
    private TextView user_val;
    private ImageView line;
    private ImageView pie;
    private Button sort;
    private TextView user_id;
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
            view = inflater.inflate(R.layout.personal, container, false);
            init(view);
        }
        return view;
    }
    public void init(View view){
        avatar=(ImageView)view.findViewById(R.id.avatar);
        user_name=(TextView)view.findViewById(R.id.tv_user);
        user_val=(TextView)view.findViewById(R.id.tv_qq);
        line=(ImageView)view.findViewById(R.id.line);
        pie=(ImageView)view.findViewById(R.id.pie);
        user_id=(TextView)view.findViewById(R.id.userId);
        sort=(Button)view.findViewById(R.id.sort);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InformationActivity.class));
            }
        });
        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_id.setText("yeah！lalalalallalalalallalalallalalalala");
            }
        });
    }
}
