package com.example.psychologicaltest;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Typewriter extends androidx.appcompat.widget.AppCompatTextView {
    public Typewriter(@NonNull Context context) {
        super(context);
    }

    public Typewriter(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Typewriter(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private CharSequence myText;
    private int myIndex;
    private long myDelay = 150;

    private Handler myHandler = new Handler();

    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(myText.subSequence(0, myIndex++));
            if(myIndex <= myText.length())
            {
                myHandler.postDelayed(characterAdder, myDelay);
            }
        }
    };

    public void animateText(CharSequence myTxt){
        myText = myTxt;
        myIndex = 0;

        setText("");

        myHandler.removeCallbacks(characterAdder);

        myHandler.postDelayed(characterAdder, myDelay);
    }

    public void setCharacterDelay(long m)
    {
        myDelay = m;
    }
}
