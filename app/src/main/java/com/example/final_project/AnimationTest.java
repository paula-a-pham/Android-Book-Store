package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class AnimationTest extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    ImageButton button;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        Book_Database database = new Book_Database(this);
        Cursor cursor = database.fetch_flag();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(cursor.getCount() > 0)
                {
                    if(cursor.getInt(1) == 0)
                    {
                        Intent intent = new Intent(AnimationTest.this, Login_and_Signup.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(AnimationTest.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                else
                {

                    database.delete();
                    database.inser_local(1,0,"");
                    database.set_all();
                    Intent intent = new Intent(AnimationTest.this, Login_and_Signup.class);
                    startActivity(intent);
                }
                lottieAnimationView=findViewById(R.id.imageAnimated);
            }
        },2500);






    }
}