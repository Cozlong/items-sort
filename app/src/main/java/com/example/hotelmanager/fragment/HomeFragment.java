package com.example.hotelmanager.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelmanager.R;
import com.example.hotelmanager.adapter.ItemAdapter;
import com.example.hotelmanager.bean.Item;
import com.example.hotelmanager.data.ItemDBHelper;
import com.example.hotelmanager.data.ItemDao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Context mContext;
    private List<Item> ItemlList;
    private ItemAdapter adapter;
    private EditText itemName;
    private ImageView search;
    private View view = null;
    private RecyclerView ItemrecyclerView;
    private Button bt_sort;
    private Button bt_term;
    private TextView sort;
    private TextView term;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ItemDao itemDao;

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
        try {
            initRecyclerView();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        android.os.Environment.getExternalStorageDirectory();
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_home, container, false);
            init(view);
            try {
                initRecyclerView();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return view;
    }

    public void init(View view){
        mContext=getActivity();
        itemDao=new ItemDao(new ItemDBHelper(mContext));
        ItemrecyclerView=(RecyclerView) view.findViewById(R.id.item_list);
        sort=(TextView) view.findViewById(R.id.sort_text);
        term=(TextView) view.findViewById(R.id.term_text);
        bt_sort=(Button) view.findViewById(R.id.sort);
        bt_sort.setOnClickListener(this);
        bt_term=(Button) view.findViewById(R.id.term);
        bt_term.setOnClickListener(this);
        ItemlList=new ArrayList<>();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sort:
                startQuerySortList();
                break;
            case R.id.term:
                startQueryTermList();
                break;
            default:
                return;
        }
    }

    public void initRecyclerView() throws ParseException {
        ItemlList=itemDao.query(ItemlList,"date_now desc");
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        ItemrecyclerView.setLayoutManager(layoutManager);
        adapter=new ItemAdapter(mContext);
        adapter.setData(ItemlList);
        ItemrecyclerView.setAdapter(adapter);
    }

    private void startQuerySortList(){

    }

    private void startQueryTermList(){

    }
}