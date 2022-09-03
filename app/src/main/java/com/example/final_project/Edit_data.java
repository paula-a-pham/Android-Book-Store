package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        Book_Database database = new Book_Database(this);
        Cursor cursor = database.fetch_flag();
        EditText email = (EditText) findViewById(R.id.edit_page_mail);
        EditText pass1 = (EditText) findViewById(R.id.edit_page_pass1);
        EditText pass2 = (EditText) findViewById(R.id.edit_page_pass2);
        Button save = (Button) findViewById(R.id.edit_page_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email.getText().toString().equals("")||pass1.getText().toString().equals("")||pass2.getText().toString().equals(""))
                {
                    Toast.makeText(Edit_data.this, "EMPTY FIELDS !", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    database.remove_user(cursor.getString(2));
                    boolean check = database.check_user_email(email.getText().toString());
                    if(check == true)
                    {
                        Toast.makeText(Edit_data.this, "THIS EMAIL HAS TAKEN !", Toast.LENGTH_SHORT).show();
                        email.setText("");
                        pass1.setText("");
                        pass2.setText("");
                    }
                    else
                    {
                        if(!pass1.getText().toString().equals(pass2.getText().toString()))
                        {
                            Toast.makeText(Edit_data.this, "2 PASSWORD ARE NOT THE SAME !", Toast.LENGTH_SHORT).show();
                            pass1.setText("");
                            pass2.setText("");
                        }
                        else
                        {
                            boolean add = database.insert_user_data(email.getText().toString(),pass1.getText().toString());
                            if(add == true)
                            {
                                Toast.makeText(Edit_data.this, "DATA UPDATED SUCCESSFULLY !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Edit_data.this,Profile.class);
                            }
                            else
                            {
                                Toast.makeText(Edit_data.this, "DATA NOT UPDATED !", Toast.LENGTH_SHORT).show();
                                email.setText("");
                                pass1.setText("");
                                pass2.setText("");
                            }
                        }
                    }
                }
            }
        });
    }
}