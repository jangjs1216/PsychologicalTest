package com.example.psychologicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class TestActivity extends AppCompatActivity {

    TextView tv_Question;
    Button btn_Ans1, btn_Ans2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        btn_Ans1 = findViewById(R.id.btn_Ans1);
        btn_Ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(it);
            }
        });
    }
}