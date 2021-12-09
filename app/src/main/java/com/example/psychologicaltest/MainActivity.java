package com.example.psychologicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Shared Preference 초기화
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        // 2. 저장해둔 값 불러오기 ("식별값", 초기값) -> 식별값과 초기값은 직접 원하는 이름과 값으로 작성.
        Boolean isSaved = pref.getBoolean("isSaved", false);
        int savedCount = pref.getInt("savedCount", 0);
        int savedI = pref.getInt("savedI", 0);
        int savedN = pref.getInt("savedN", 0);
        int savedF = pref.getInt("savedF", 0);
        int savedP = pref.getInt("savedP", 0);
        int savedDanger = pref.getInt("savedDanger", 0);

        if(isSaved)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("이전에 저장된 내용이 있습니다.").setMessage("진행하던 곳부터 계속 진행하시겠습니까?");

            builder.setPositiveButton("예", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id)
                {
                    Intent it = new Intent(getApplicationContext(), TestActivity.class);
                    it.putExtra("savedCount",savedCount);
                    it.putExtra("savedI",savedI);
                    it.putExtra("savedN",savedN);
                    it.putExtra("savedF",savedF);
                    it.putExtra("savedP",savedP);
                    it.putExtra("savedDanger",savedDanger);
                    startActivity(it);
                }
            });

            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id)
                {
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


        btn_start = findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), TestActivity.class);
                it.putExtra("savedCount",0);
                it.putExtra("savedI",0);
                it.putExtra("savedN",0);
                it.putExtra("savedF",0);
                it.putExtra("savedP",0);
                it.putExtra("savedDanger",0);
                startActivity(it);
            }
        });
    }
}