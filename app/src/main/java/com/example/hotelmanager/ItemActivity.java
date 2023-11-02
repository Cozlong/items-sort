package com.example.hotelmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanager.data.ItemDao;

public class ItemActivity extends AppCompatActivity {
    Intent intent=getIntent();
    private EditText tv_show1;
    private EditText tv_show2;
    private EditText tv_show3;
    private EditText tv_show4;
    private EditText tv_show5;
    private EditText tv_show6;
    private EditText tv_show7;
    private ImageButton btnreturn;
    private ImageButton btnlitter;
    private ImageButton btnEdit1;
    private ImageButton btnEdit2;
    private ImageButton btnEdit3;
    private ImageButton btnEdit4;
    private ImageButton btnEdit5;
    private ImageButton btnEdit6;
    private ImageButton btnEdit7;
    private String input_item_name,input_item_type,input_item_position,input_term_type;
    private String input_date_of_manufacture;
    private int input_item_number,input_remind_days,input_quality_guarantee_period;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        InitView();
        Init();
    }

    private void InitView() {
        btnreturn=findViewById(R.id.add_return);
        btnlitter=findViewById(R.id.add_scan);
        btnEdit1 = findViewById(R.id.btn_name);
        btnEdit2 =findViewById(R.id.btn_sort);
        btnEdit3 =findViewById(R.id.btn_position);
        btnEdit4 =findViewById(R.id.btn_birthday);
        btnEdit5 =findViewById(R.id.btn_shelf);
        btnEdit6 =findViewById(R.id.btn_warn);
        btnEdit7 =findViewById(R.id.btn_amount);
        tv_show1 = findViewById(R.id.tv_name);
        tv_show2 = findViewById(R.id.tv_sort);
        tv_show3 = findViewById(R.id.tv_position);
        tv_show4 = findViewById(R.id.tv_birthday);
        tv_show5 = findViewById(R.id.tv_shelf);
        tv_show6 = findViewById(R.id.tv_warn);
        tv_show7 = findViewById(R.id.tv_amount);
        // 初始化TextView的状态
        tv_show1.setFocusable(false);
        tv_show1.setFocusableInTouchMode(false);
        tv_show2.setFocusable(false);
        tv_show2.setFocusableInTouchMode(false);
        tv_show3.setFocusable(false);
        tv_show3.setFocusableInTouchMode(false);
        tv_show4.setFocusable(false);
        tv_show4.setFocusableInTouchMode(false);
        tv_show5.setFocusable(false);
        tv_show5.setFocusableInTouchMode(false);
        tv_show6.setFocusable(false);
        tv_show6.setFocusableInTouchMode(false);
        tv_show7.setFocusable(false);
        tv_show7.setFocusableInTouchMode(false);
    }

    private void Init() {
        // 设置 Button 的点击事件
        btnEdit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将 TextView 设置为可编辑状态
                tv_show1.setFocusable(true);
                tv_show1.setFocusableInTouchMode(true);
                tv_show1.requestFocus();
                tv_show1.setSelection(tv_show1.getText().length());
            }
        });
        tv_show1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 将 TextView 设置为不可编辑状态
                    tv_show1.setFocusable(false);
                    tv_show1.setFocusableInTouchMode(false);
                    // 获取编辑后的内容并显示到 TextView 上
                    String editedText = tv_show1.getText().toString();
                    tv_show1.setText(editedText);
                }
            }
        });

        btnEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将 TextView 设置为可编辑状态
                tv_show2.setFocusable(true);
                tv_show2.setFocusableInTouchMode(true);
                tv_show2.requestFocus();
                tv_show2.setSelection(tv_show2.getText().length());
            }
        });
        tv_show2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 将 TextView 设置为不可编辑状态
                    tv_show2.setFocusable(false);
                    tv_show2.setFocusableInTouchMode(false);
                    // 获取编辑后的内容并显示到 TextView 上
                    String editedText = tv_show2.getText().toString();
                    tv_show2.setText(editedText);
                }
            }
        });

        btnEdit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将 TextView 设置为可编辑状态
                tv_show3.setFocusable(true);
                tv_show3.setFocusableInTouchMode(true);
                tv_show3.requestFocus();
                tv_show3.setSelection(tv_show3.getText().length());
            }
        });
        tv_show3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 将 TextView 设置为不可编辑状态
                    tv_show3.setFocusable(false);
                    tv_show3.setFocusableInTouchMode(false);
                    // 获取编辑后的内容并显示到 TextView 上
                    String editedText = tv_show3.getText().toString();
                    tv_show3.setText(editedText);
                }
            }
        });

        btnEdit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将 TextView 设置为可编辑状态
                tv_show4.setFocusable(true);
                tv_show4.setFocusableInTouchMode(true);
                tv_show4.requestFocus();
                tv_show4.setSelection(tv_show4.getText().length());
            }
        });
        tv_show4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 将 TextView 设置为不可编辑状态
                    tv_show4.setFocusable(false);
                    tv_show4.setFocusableInTouchMode(false);
                    // 获取编辑后的内容并显示到 TextView 上
                    String editedText = tv_show4.getText().toString();
                    tv_show4.setText(editedText);
                }
            }
        });

        btnEdit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将 TextView 设置为可编辑状态
                tv_show5.setFocusable(true);
                tv_show5.setFocusableInTouchMode(true);
                tv_show5.requestFocus();
                tv_show5.setSelection(tv_show5.getText().length());
            }
        });
        tv_show5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 将 TextView 设置为不可编辑状态
                    tv_show5.setFocusable(false);
                    tv_show5.setFocusableInTouchMode(false);
                    // 获取编辑后的内容并显示到 TextView 上
                    String editedText = tv_show5.getText().toString();
                    tv_show5.setText(editedText);
                }
            }
        });

        btnEdit6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将 TextView 设置为可编辑状态
                tv_show6.setFocusable(true);
                tv_show6.setFocusableInTouchMode(true);
                tv_show6.requestFocus();
                tv_show6.setSelection(tv_show6.getText().length());
            }
        });
        tv_show6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 将 TextView 设置为不可编辑状态
                    tv_show6.setFocusable(false);
                    tv_show6.setFocusableInTouchMode(false);
                    // 获取编辑后的内容并显示到 TextView 上
                    String editedText = tv_show6.getText().toString();
                    tv_show6.setText(editedText);
                }
            }
        });

        btnEdit7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将 TextView 设置为可编辑状态
                tv_show7.setFocusable(true);
                tv_show7.setFocusableInTouchMode(true);
                tv_show7.requestFocus();
                tv_show7.setSelection(tv_show7.getText().length());
            }
        });
        tv_show7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // 将 TextView 设置为不可编辑状态
                    tv_show7.setFocusable(false);
                    tv_show7.setFocusableInTouchMode(false);
                    // 获取编辑后的内容并显示到 TextView 上
                    String editedText = tv_show7.getText().toString();
                    tv_show7.setText(editedText);
                }
            }
        });

        btnlitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个AlertDialog.Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                // 设置对话框的标题和消息
                builder.setTitle("提示");
                builder.setMessage("确定要删除吗？");

                // 添加“确定”和“取消”按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 在这里执行删除操作
                        // ...
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                        // 取消删除操作
                    }
                });

                // 显示对话框
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_item_name = tv_show1.getText().toString();
                input_item_type = tv_show2.getText().toString();
                input_item_position = tv_show3.getText().toString();
                input_term_type = "未到期";
                input_item_number = Integer.parseInt(tv_show7.getText().toString());
                input_remind_days = Integer.parseInt(tv_show6.getText().toString());
                input_quality_guarantee_period = Integer.parseInt(tv_show5.getText().toString());
                input_date_of_manufacture = tv_show4.getText().toString();

                ItemDao.update(input_item_name,input_item_type,input_item_position,input_term_type,input_item_number,
                        input_date_of_manufacture,input_quality_guarantee_period,input_remind_days);
                Intent intent = new Intent(ItemActivity.this, MainActivity.class);
                ItemActivity.this.startActivity(intent);
                finish();
            }
        });
    }

}