package com.example.psychologicaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.xml.transform.Result;

public class TestActivity extends AppCompatActivity {
    Typewriter tv_Question;
    TextView tv_progress;
    Button btn_Ans1, btn_Ans2;
    ProgressBar progressBar;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    MediaPlayer mediaPlayer, startPlayer;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    int savedCount, savedI, savedN, savedF, savedP, savedDanger;

    private int curCount = 0;
    private int Icount, Ncount, Fcount, Pcount, DangerCount;
    // INFP <-> ESTJ 요소를 카운트합니다.

    String[] I_data = {"게임이 시작됐다. 어디로 갈까?/사람들이 몰려있는 곳으로 간다/혼자서 임무를 하러 간다.",
            "토론이 시작됐다. 무슨 말을 먼저 하지?/신고자에게 물어본다/일단 신고자의 말을 듣는다.",
            "원자로 임무 중인데 2명이 들어왔다. 이때 당신의 행동은?/들어온 사람의 색깔을 기억한다./하던 임무에 집중한다.",
            "범인으로 의심되는 승무원이 있을 때 할 일은?/일단 사람들을 모아 선동한다/그 사람의 동선에 집중한다.",
            "임무 중 쉬는 시간에는 무엇을 할까?/동료와 이야기한다./컴퓨터 게임을 한다."};
    String[] N_data = {"임포스터일 때 승무원과 마주쳤다. 언제 죽여야 할까?/왠지 주위에 아무도 없을 것 같을 때 죽인다./승무원의 동선을 제한한 후 살해한다.",
    "토론에서 용의자가 말을 더듬고 있다. 어떻게 해야 할까?/범인이라고 몰아간다/당황하고 있다고 속으로만 생각한다.",
    "비상사태가 발생했다! 당신이 할 행동은?/비상사태를 해결하러 간다/비상사태가 있는 반대편으로 시체를 발견하러 가본다.",
    "당신은 방금 우주선에 올라탔다. 무엇을 할까?/빨리 임무를 마치고 쉰다/일단 우주선의 구조를 파악한다",
    "동료의 시체가 의료실에서 발견됐다! 어떻게 할까?/즉시 신고한다/주변에 누가 있는지 확인 후 신고한다"};
    String[] F_data = {"승무원이 의심을 받고 있다. 당신은 무슨 말을 할까?/딱 봐도 저놈이네./잠시만, 일단 얘기를 들어보자.",
    "당신은 임포스터다. 토론 도중 당신에게 어디 있었는지 말하라고 한다. 어떻게 대답할까?/일단 그럴듯하게 거짓말 한다./사실대로 말하되 중요한 건 말하지 않는다.",
    "승무원이 수상하다. 토론때 당신은.../의심이 간다고 선동한다./일단 가만히 있는다.",
    "우주복을 꾸밀 시간이다. 당신은 무슨 색 우주복을 입을 것인가?/우주선과 비슷한 색깔/화려하게 꾸민다.",
    "방금 임포스터가 혀로 동료를 찔렀다! 당신은.../" +
            "신고한다/도망친다"};
    String[] P_data = {"모자를 고를 때 당신은.../옷과 동일한 색깔로 깔맞춤한다./아무거나 집는다.",
    "게임이 시작됐다. 어떻게 임무를 수행할 것인가?/주어진 임무를 어떤 순서로 할지 생각한다./눈에 들어오는 임무를 먼저 시작한다.",
    "청소 업무와 소행성 업무 중 당신이 하고싶은 임무는?/청소/소행성",
    "카드키가 잘 안긁힐 때 어떻게 해야할까?/일단 계속 시도해본다/다른 임무를 먼저 한다.",
    "당신은 임포스터다. 어떻게 행동할 것인가?/어떻게 죽일지 계획을 세운다./아무나 따라가서 기회를 노린다."
    };
    String[] Danger_data = {"어두운 곳이 밝은 곳 보다 마음이 편하다/그렇다/아니다",
            "임포스터가 에어컨을 고장냈다. 더울 때는 옷을 어떻게 입어야 할까?/팔을 보여주기 싫어서 긴팔을 입는다/더우니까 반팔을 입는다.",
    "당신이 범인으로 의심받고 있다. 빠르게 진정 하기 위해 할 행동은?/손톱을 물어뜯으며 진정한다/심호흡을 하며 진정한다",
    "후배 승무원이 실수를 했을 때 어떻게 해야 할까?/욕과 나쁜말을 섞어서 혼낸다/다시 가르쳐주고 다신 그러지 말라고 한다.",
    "동료 승무원과 갈등이 생겼을 때 어떻게 해야 할까?/주먹을 휘둘러 상대를 위협한다/대화로 해결하려 한다."
    };
    ArrayList<String> I_list = new ArrayList<>(Arrays.asList(I_data));
    ArrayList<String> N_list = new ArrayList<>(Arrays.asList(N_data));
    ArrayList<String> F_list = new ArrayList<>(Arrays.asList(F_data));
    ArrayList<String> P_list = new ArrayList<>(Arrays.asList(P_data));
    ArrayList<String> Danger_list = new ArrayList<>(Arrays.asList(Danger_data));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tv_Question = findViewById(R.id.tv_Question);
        btn_Ans1 = findViewById(R.id.btn_Ans1);
        btn_Ans2 = findViewById(R.id.btn_Ans2);
        tv_progress = findViewById(R.id.tv_progress);
        progressBar = findViewById(R.id.progressBar);

        Icount = Ncount = Fcount = Pcount = DangerCount = 0;

        // 시작
        startPlayer = MediaPlayer.create(this, R.raw.main);
        startPlayer.start();

        // 1. Shared Preference 초기화
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        Intent intent = getIntent();
        savedCount = intent.getExtras().getInt("savedCount");
        savedI = intent.getExtras().getInt("savedI");
        savedN = intent.getExtras().getInt("savedN");
        savedF = intent.getExtras().getInt("savedF");
        savedP = intent.getExtras().getInt("savedP");
        savedDanger = intent.getExtras().getInt("savedDanger");
        Boolean isDanger = intent.getExtras().getBoolean("isDanger");

        Log.e("###","curCount = "+savedCount);
        if(savedCount > 0)
        {
            //저장 된 정보가 있는 경우
            curCount = savedCount;
            Icount = savedI;
            Ncount = savedN;
            Fcount = savedF;
            Pcount = savedP;
            DangerCount = savedDanger;
            GoNextQuestion();
        }else {
            Random ran = new Random();
            int idx = ran.nextInt(I_data.length);
            String[] curData = I_list.remove(idx).split("/");
            tv_Question.setText("");
            tv_Question.setCharacterDelay(60);
            tv_Question.animateText(curData[0]);
            btn_Ans1.setText(curData[1]);
            btn_Ans2.setText(curData[2]);
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.outlong);
        mediaPlayer.start();
        tv_progress.setText(curCount+"/15");
        double perCent = (double)curCount/15*100.0;
        progressBar.setProgress((int)perCent);

        btn_Ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // I[0 1 2] D[3] N[4 5 6] D[7] F[8 9 10] D[11] P[12 13 14]
                curCount++;
                GoNextQuestion();
                SavePreferenceData();

                if(curCount == 15) {
                    startPlayer.stop();
                    startPlayer.release();
                    GoNextIntent();
                }
            }
        });

        btn_Ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // I[0 1 2] D[3] N[4 5 6] D[7] F[8 9 10] D[11] P[12 13 14]
                curCount++;
                GoNextQuestion();

                if(curCount <= 2)
                    Icount++;
                else if(4 <= curCount && curCount <= 6)
                    Ncount++;
                else if(8 <= curCount && curCount <= 10)
                    Fcount++;
                else if(12 <= curCount && curCount <= 14)
                    Pcount++;
                else
                    DangerCount++;

                if(curCount == 15) {
                    startPlayer.stop();
                    startPlayer.release();
                    GoNextIntent();
                }
            }
        });
    }

    private void GoNextQuestion()
    {
        // I[0 1 2] D[3] N[4 5 6] D[7] F[8 9 10] D[11] P[12 13 14]
        Random ran = new Random();
        String[] curData;
        if(curCount <= 2)
        {
            int idx = ran.nextInt(I_list.size());
            curData = I_list.remove(idx).split("/");
        }else if(4 <= curCount && curCount <= 6)
        {
            int idx = ran.nextInt(N_list.size());
            curData = N_list.remove(idx).split("/");
        }else if(8 <= curCount && curCount <= 10)
        {
            int idx = ran.nextInt(F_list.size());
            curData = F_list.remove(idx).split("/");
        }else if(12 <= curCount && curCount <= 14)
        {
            int idx = ran.nextInt(P_list.size());
            curData = P_list.remove(idx).split("/");
        }else{
            int idx = ran.nextInt(Danger_list.size());
            curData = Danger_list.remove(idx).split("/");
        }

        Handler handler = new Handler();

        mediaPlayer = MediaPlayer.create(this, R.raw.outlong);
        mediaPlayer.start();

        tv_Question.setText("");
        tv_Question.setCharacterDelay(60);
        tv_Question.animateText(curData[0]);

        //tv_Question.setText(curData[0]);
        btn_Ans1.setText(curData[1]);
        btn_Ans2.setText(curData[2]);
        tv_progress.setText(curCount+"/15");
        double perCent = (double)curCount/15*100.0;
        progressBar.setProgress((int)perCent);
    }

    private void GoNextIntent()
    {
        Intent it = new Intent(getApplicationContext(), ResultActivity.class);
        String result = "";
        if(Icount >= 2)
            result += "I";
        else
            result += "E";

        if(Ncount >= 2)
            result += "N";
        else
            result += "S";

        if(Fcount >= 2)
            result += "F";
        else
            result += "T";

        if(Pcount >= 2)
            result += "P";
        else
            result += "J";

        Log.e("###", result);
        it.putExtra("result",result);
        it.putExtra("isDanger",DangerCount>=2);
        startActivity(it);
    }

    private void SavePreferenceData()
    {
        editor.putBoolean("isSaved", true);
        editor.putInt("savedCount", curCount);
        editor.putInt("savedI", Icount);
        editor.putInt("savedN", Ncount);
        editor.putInt("savedF", Fcount);
        editor.putInt("savedP", Pcount);
        editor.putInt("savedDanger", DangerCount);
        editor.apply(); // 저장
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(this, "뒤로 버튼 한번 더 누르면 돌아갑니다.", Toast.LENGTH_SHORT).show();
        }
    }
}