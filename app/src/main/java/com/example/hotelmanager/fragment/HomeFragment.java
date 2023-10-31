package com.example.hotelmanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanager.Constant;
import com.example.hotelmanager.R;
import com.example.hotelmanager.adapter.HotelAdapter;
import com.example.hotelmanager.bean.Hotel;
import com.example.hotelmanager.utils.JsonParse;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Context mContext;
    private List<Hotel> HotelList;
    private HotelAdapter adapter;
    public static final int MSG_SHOP_OK=1;
    private MHandler mHandler;
    private TextView tv_title;
    private ImageView tv_back;
    private View view = null;
    private RecyclerView recyclerView;
    private RelativeLayout rl_title_bar;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
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
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public void onStart(){
        super.onStart();
        initRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        android.os.Environment.getExternalStorageDirectory();
        // Inflate the layout for this fragment
        if (view == null) {
            mHandler=new MHandler();
            view = inflater.inflate(R.layout.fragment_home1, container, false);
            init(view);
            initRecyclerView();
        }
        return view;
    }

    public void init(View view){
        mContext=getActivity();
        rl_title_bar=(RelativeLayout)view.findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(mContext.getResources().getColor(R.color.light_gray));
        recyclerView=(RecyclerView) view.findViewById(R.id.hotel_select_list);
        tv_back= (ImageView) view.findViewById(R.id.tv_back);
        tv_title=(TextView) view.findViewById(R.id.tv_title);
        tv_title.setText("酒店房型");
        tv_title.setTextColor(mContext.getResources().getColor(R.color.black));
        tv_back.setVisibility(View.GONE);

    }

    public void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new HotelAdapter(mContext);
        recyclerView.setAdapter(adapter);
        String mineinfo_url = Constant.Hotel_URL;
        okhttp3.Callback callback = new okhttp3.Callback()
        {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res=response.body().string();
                Message message=new Message();
                message.what=MSG_SHOP_OK;
                message.obj=res;
                mHandler.sendMessage(message);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        };
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(mineinfo_url)
                .build();
        //发送请求
        client.newCall(request).enqueue(callback);
    }

    class MHandler extends Handler{
        @Override
        public void dispatchMessage(@NonNull Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what){
                case MSG_SHOP_OK:
                    if(msg.obj!=null){
                        String result=(String) msg.obj;
                        HotelList= JsonParse.getInstance().getHotelList(result);
                        adapter.setData(HotelList);
                    }
                    break;
            }
        }
    }
}
