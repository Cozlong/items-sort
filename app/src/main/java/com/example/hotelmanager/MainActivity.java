package com.example.hotelmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hotelmanager.fragment.HomeFragment;
import com.example.hotelmanager.fragment.MineFragment;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fragment> mFragments;
    private LinearLayout mTabHome;

    private LinearLayout mTabMine;

    //四个Tab对应的ImageButton
    private ImageView mImgHome;

    private ImageView mImgMine;
    private String Staffun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        Staffun=intent.getStringExtra("Staffun");
        initFragments(); //初始化数据
        initViews(); //初始化控件
        initEvents(); //初始化事件
        initFirstRun(0);//第一次运行初始化界面，第一个碎片
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        //将四个Fragment加入集合中
        mFragments.add(new HomeFragment());
        mFragments.add(new MineFragment());
    }

    private void initViews() {
        mTabHome = (LinearLayout) findViewById(R.id.id_tab_home);
        mImgHome = (ImageView) findViewById(R.id.id_tab_home_img);
        mTabMine = (LinearLayout) findViewById(R.id.id_tab_mine);
        mImgMine = (ImageView) findViewById(R.id.id_tab_mine_img);
    }

    private void initEvents() {
        //设置四个Tab的点击事件
        mTabHome.setOnClickListener(onClickListener);
        mTabMine.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            //先将四个ImageButton置为灰色
//            resetImgs();
            //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
            switch (v.getId()) {
                case R.id.id_tab_home: selectTab(0); break;
                case R.id.id_tab_mine: selectTab(1); break;
            }
        }};

//    private void resetImgs() {
//        mImgHome.setImageResource(R.drawable.tab_home_normal);
//        mImgMine.setImageResource(R.drawable.tab_home_normal);
//    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
//        switch (i) {
//            case 0: mImgHome.setImageResource(R.drawable.tab_home_pressed); break;
//        }
        //设置当前点击的Tab所对应的页面
        setCurrentFragment(i);
    }

    private void setCurrentFragment(int i)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.frag_layout, mFragments.get(i));
        trans.commit();
    }

    private void initFirstRun(int i)
    {
//        resetImgs(); //重置所有Tab
        selectTab(i); //显示第i个碎片
    }
}