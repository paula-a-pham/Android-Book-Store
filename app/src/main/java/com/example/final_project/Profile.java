package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Book_Database database = new Book_Database(this);
        TextView mail = (TextView) findViewById(R.id.profile_email_label);
        TextView email = (TextView) findViewById(R.id.profile_email_edit);
        Button edit = (Button) findViewById(R.id.profile_edit_btn);
        Cursor cursor6 = database.fetch_flag();
        mail.setText(cursor6.getString(2));
        email.setText(cursor6.getString(2));

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,Edit_data.class);
                startActivity(intent);
            }
        });

    }
}