package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Login_and_Signup extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_and_signup);

        Book_Database b = new Book_Database(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.login_signup_fragment , new Login_Fragment()).commit();
        
        //Book_Database database = new Book_Database(this);

        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.bottom_login_and_signup);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                replace_fragment(fragment , item);
                return true;
            }
        });
    }
    private void replace_fragment(Fragment fragment , @NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.login_tab:
            {
                fragment = new Login_Fragment();
                break;
            }
            case R.id.signup_tab:
            {
                fragment = new Signup_Fragment();
                break;
            }
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.login_signup_fragment , fragment).commit();
    }

    @Override
    public Resources.Theme getTheme() {
        return super.getTheme();
    }
}