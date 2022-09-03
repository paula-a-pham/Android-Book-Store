package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class Book_Templete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_templete);
        Book_Database database = new Book_Database(Book_Templete.this);
        TextView b_name = (TextView) findViewById(R.id.book_name);
        TextView cat = (TextView) findViewById(R.id.book_category);
        TextView lang = (TextView) findViewById(R.id.language);
        TextView chapter = (TextView) findViewById(R.id.chapters);
        TextView pages = (TextView) findViewById(R.id.pages);
        TextView parts = (TextView) findViewById(R.id.parts);
        TextView desc = (TextView) findViewById(R.id.describtion);
        TextView a_name = (TextView) findViewById(R.id.author_name);
        TextView a_nat = (TextView) findViewById(R.id.nationality);
        TextView nob = (TextView) findViewById(R.id.nob);
        ImageView img = (ImageView) findViewById(R.id.book_image);
        CheckBox fav_btn = (CheckBox) findViewById(R.id.add_to_fav);
        Button read = (Button) findViewById(R.id.read_btn);
        b_name.setText(getIntent().getExtras().getString("bookName"));
        cat.setText(getIntent().getExtras().getString("bookCategory"));
        lang.setText(getIntent().getExtras().getString("language"));
        chapter.setText(getIntent().getExtras().getString("chapters"));
        pages.setText(getIntent().getExtras().getString("pages"));
        parts.setText(getIntent().getExtras().getString("parts"));
        desc.setText(getIntent().getExtras().getString("description"));
        a_name.setText(getIntent().getExtras().getString("authorName"));
        a_nat.setText(getIntent().getExtras().getString("authorNationality"));
        nob.setText(getIntent().getExtras().getString("noOfBooks"));
        img.setImageResource(getIntent().getExtras().getInt("imageLink"));
        boolean check_fav = database.check_favorites(getIntent().getExtras().getInt("bookId"));
        if(check_fav)
        {
            fav_btn.setChecked(true);
        }
        else
        {
            fav_btn.setChecked(false);
        }

        Cursor cursor = database.fetch_flag();
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fav_btn.isChecked())
                {

                    boolean add = database.insert_favorite(cursor.getString(2),getIntent().getExtras().getInt("bookId"));
                    if(add)
                    {
                        Toast.makeText(Book_Templete.this, "BOOK ADDED TO FAVORITE !", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Book_Templete.this, "BOOK NOT ADDED !", Toast.LENGTH_SHORT).show();
                        fav_btn.setChecked(false);
                    }
                }
                else
                {
                    if(database.check_favorites(getIntent().getExtras().getInt("bookId")))
                    {
                        boolean remove = database.remove_favorite(cursor.getString(2),getIntent().getExtras().getInt("bookId"));
                        if(remove)
                        {
                            Toast.makeText(Book_Templete.this, "BOOK REMOVED FROM FAVORITE !", Toast.LENGTH_SHORT).show();
                            fav_btn.setChecked(false);
                        }
                        else
                        {
                            Toast.makeText(Book_Templete.this, "BOOK NOT REMOVED FROM FAVORITE !", Toast.LENGTH_SHORT).show();
                            fav_btn.setChecked(true);
                        }
                    }
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proceedUrl(getIntent().getExtras().getString("bookLink"));
            }
        });
    }

    public void proceedUrl(String url)
    {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}