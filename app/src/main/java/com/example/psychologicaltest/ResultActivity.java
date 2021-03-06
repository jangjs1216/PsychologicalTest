package com.example.psychologicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    Button btn_share1, btn_share2;

    TextView tv_result;
    ImageView iv_character;

    MediaPlayer mp;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1. Shared Preference 초기화
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        tv_result = findViewById(R.id.tv_result);
        iv_character = findViewById(R.id.iv_character);

        btn_share1 = findViewById(R.id.btn_share1);
        btn_share2 = findViewById(R.id.btn_share2);

        editor.putBoolean("isSaved", false);
        editor.putInt("savedCount", 0);
        editor.putInt("savedI", 0);
        editor.putInt("savedN", 0);
        editor.putInt("savedF", 0);
        editor.putInt("savedP", 0);
        editor.putInt("savedDanger", 0);
        editor.apply(); // 저장

        String[] nameData = {
                "ENFJ/정의로운 승무원",
                "ENFP/활기찬 벤트마스터",
                "ENTJ/용감한 지도자 승무원",
                "ENTP/자유분방한 임포스터",
                "ESFJ/친근한 승무원",
                "ESFP/자유로운 영혼의 승무원",
                "ESTJ/주도면밀한 임포스터",
                "ESTP/암살 전문 임포스터",
                "INFJ/임포스터 저격러 승무원",
                "INFP/평화주의자 승무원",
                "INTJ/임무 최단시간 해결 승무원",
                "INTP/비상소집 팩트폭격 승무원",
                "ISFJ/내 할건 잘하는 승무원",
                "ISFP/움직이기 귀찮은 승무원",
                "ISTJ/팩트체크 전문 승무원",
                "ISTP/임무하러온 승무원"
        };

        Intent intent = getIntent();
        String result = intent.getExtras().getString("result");
        Boolean isDanger = intent.getExtras().getBoolean("isDanger");

        Log.e("###","결과값 = "+result+" "+isDanger);

        for(String s : nameData)
        {
            String[] splitData = s.split("/");
            if(splitData[0].equals(result)) {
                Log.e("###", "결과값 = " + splitData[1]);
                tv_result.setText("당신은 "+splitData[1]+" 입니다!");
            }
        }

        if(result.equals("ENTP") || result.equals("ESTJ") || result.equals("ESTP"))
        {
            mp = MediaPlayer.create(this, R.raw.impowin);
            mp.start();
        }else{
            mp = MediaPlayer.create(this, R.raw.crewwin);
            mp.start();
        }

        switch(result)
        {
            case "ENFJ":
                iv_character.setImageResource(R.drawable.enfj);
                break;
            case "ENFP":
                iv_character.setImageResource(R.drawable.enfp);
                break;
            case "ENTJ":
                iv_character.setImageResource(R.drawable.entj);
                break;
            case "ENTP":
                iv_character.setImageResource(R.drawable.entp);
                break;
            case "ESFJ":
                iv_character.setImageResource(R.drawable.esfj);
                break;
            case "ESFP":
                iv_character.setImageResource(R.drawable.esfp);
                break;
            case "ESTJ":
                iv_character.setImageResource(R.drawable.estj);
                break;
            case "ESTP":
                iv_character.setImageResource(R.drawable.estp);
                break;
            case "INFJ":
                iv_character.setImageResource(R.drawable.infj);
                break;
            case "INFP":
                iv_character.setImageResource(R.drawable.infp);
                break;
            case "INTJ":
                iv_character.setImageResource(R.drawable.intj);
                break;
            case "INTP":
                iv_character.setImageResource(R.drawable.intp);
                break;
            case "ISFJ":
                iv_character.setImageResource(R.drawable.isfj);
                break;
            case "ISFP":
                iv_character.setImageResource(R.drawable.isfp);
                break;
            case "ISTJ":
                iv_character.setImageResource(R.drawable.istj);
                break;
            case "ISTP":
                iv_character.setImageResource(R.drawable.istp);
                break;
        }

        btn_share1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "카카오톡 공유 링크가 복사되었습니다.", Toast.LENGTH_LONG).show();
            }
        });

        btn_share2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "페이스북 공유 링크가 복사되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }
}