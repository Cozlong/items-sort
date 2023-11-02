package com.example.hotelmanager;

import static com.example.hotelmanager.data.ItemDBHelper.getTime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelmanager.data.ItemDao;

import java.util.Date;

public class AddProjectActivity extends AppCompatActivity {
    private ImageButton add_return;
    private ImageButton add_scan;
    private ImageButton add_pic;
    private TextView add_name;
    private EditText add_input_name;
    private TextView add_sort;
    private Spinner add_input_sort;
    private TextView add_place;
    private EditText add_input_place;
    private TextView add_time;
    private TextView add_freshnessDate;
    private EditText add_input_freshnessDate;
    private TextView add_warn;
    private EditText add_input_warn;
    private TextView add_productionDate;
    private EditText add_input_productionDate;
    private TextView add_storage;
    private EditText add_input_storage;
    private Button button;
    private String input_item_name,input_item_type,input_item_position,input_term_type;
    private String input_date_of_manufacture;
    private int input_item_number,input_remind_days,input_quality_guarantee_period;
    public AddProjectActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_add);
        InitView();
        Init();
    }

    private void Init() {
        add_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddProjectActivity.this, MainActivity.class);
                AddProjectActivity.this.startActivity(intent);
                finish();
            }
        });
        add_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });
        add_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_item_name = add_input_name.getText().toString();
                input_item_type = add_input_sort.getSelectedItem().toString();
                input_item_position = add_input_place.getText().toString();
                input_term_type = "未到期";
                input_item_number = Integer.parseInt(add_input_storage.getText().toString());
                input_remind_days = Integer.parseInt(add_input_warn.getText().toString());
                input_quality_guarantee_period = Integer.parseInt(add_input_freshnessDate.getText().toString());
                input_date_of_manufacture = add_input_productionDate.getText().toString();

                ItemDao.insert(input_item_name,input_item_type,input_item_position,input_term_type,input_item_number,
                        input_date_of_manufacture,input_quality_guarantee_period,input_remind_days);
                Intent intent = new Intent(AddProjectActivity.this, MainActivity.class);
                AddProjectActivity.this.startActivity(intent);
                finish();
            }
        });
    }

    private void InitView() {
        add_return = (ImageButton) findViewById(R.id.add_return);
        add_scan = (ImageButton) findViewById(R.id.add_scan);
        add_pic = (ImageButton) findViewById(R.id.add_pic);
        add_name = (TextView) findViewById(R.id.add_name);
        add_input_name = (EditText) findViewById(R.id.add_input_name);
        add_sort = (TextView) findViewById(R.id.add_sort);
        add_input_sort = (Spinner) findViewById(R.id.add_input_sort);
        add_place = (TextView) findViewById(R.id.add_place);
        add_input_place = (EditText) findViewById(R.id.add_input_place);
        add_time = (TextView) findViewById(R.id.add_time);
        add_freshnessDate = (TextView) findViewById(R.id.add_freshnessDate);
        add_input_freshnessDate = (EditText) findViewById(R.id.add_input_freshnessDate);
        add_warn = (TextView) findViewById(R.id.add_warn);
        add_input_warn = (EditText) findViewById(R.id.add_input_warn);
        add_productionDate = (TextView) findViewById(R.id.add_productionDate);
        add_input_productionDate = (EditText) findViewById(R.id.add_input_productionDate);
        add_storage = (TextView) findViewById(R.id.add_storage);
        add_input_storage = (EditText) findViewById(R.id.add_input_storage);
        button = (Button) findViewById(R.id.button);
    }
}
