package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Book_Database database = new Book_Database(this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.Profile:
            {
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
                break;
            }
            case R.id.about_option:
            {
                Intent intent = new Intent(MainActivity.this,about.class);
                startActivity(intent);
                break;
            }
            case R.id.share_option:
            {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"Download Book Strore Program For More Fantastic Books .\n\n link"+getPackageName());
                startActivity(Intent.createChooser(intent,"Share With ."));
                break;
            }
            case R.id.support_option:
            {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"+"paula.a.pham@gmail.com"));
                startActivity(intent);
                break;
            }
            case R.id.logout_btn:
            {
                Cursor cursor7 = database.fetch_flag();
                database.remove_local();
                database.inser_local(1,0,cursor7.getString(2));
                Intent intent = new Intent(MainActivity.this,Login_and_Signup.class);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent h = new Intent(MainActivity.this , Login_and_Signup.class);
        //startActivity(h);


        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new Home_Fragment()).commit();

//        ListView p = (ListView) findViewById(R.id.lv);
////        ListView test = (ListView) findViewById(R.id.show);
//        final ArrayAdapter<String> a = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
//        p.setAdapter(a);
        //final Book_Helper new_book = new Book_Helper(this);
//
       // new_book.insert_data();
        //Cursor c = new_book.retrive();
        //p.setText(c.getString(0));
//        while(!c.isAfterLast())
//        {
//            //a.add(c.getString(1));
//            p.setText(c.getString(1));
//            c.moveToNext();
//        }

        //create refrence for my tools
        BottomNavigationView bottom_bar = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //select botton action
        bottom_bar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment new_fragment = null;
                replace_fragment(new_fragment , item);
                return true;
            }
        });




    }
    private void replace_fragment(Fragment new_fragment , @NonNull MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.home_button:
            {
                new_fragment = new Home_Fragment();
                break;
            }
            case R.id.fav_button:
            {
                new_fragment = new Favorite_Fragment();
                break;
            }
            case R.id.search_button:
            {
                new_fragment = new Searsh_Fragment();
                break;
            }

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment,new_fragment).commit();
    }
}