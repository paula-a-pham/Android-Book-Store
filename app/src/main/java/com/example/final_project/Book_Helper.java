package com.example.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Book_Helper extends SQLiteOpenHelper {
    private static String database_name = "book_database";
    private static SQLiteDatabase book_database;

    public Book_Helper(Context context) {
        super(context, database_name , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE AUTHOR (author_name text primary key,nationality text,no_of_books integer)");
        sqLiteDatabase.execSQL("CREATE TABLE USER (email text primary key,password text)");
        sqLiteDatabase.execSQL("CREATE TABLE COLLECTIONS (collection_id integer primary key autoincrement,name text,no_of_books integer," +
                "email text,foreign key(email) REFERENCES USER (email) ON DELETE CASCADE)");
        sqLiteDatabase.execSQL("CREATE TABLE BOOKS (book_id integer primary key autoincrement,name text,category text,publish_date text," +
                                "language text,chapters integer," +
                                "pages integer,parts integer,counter integer,image_link text,book_link text)");
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITES (email text,fav text,primary key(email,fav)," +
                                "foreign key(email) REFERENCES USER (email) ON DELETE CASCADE)");
        sqLiteDatabase.execSQL("CREATE TABLE FAVORITES_BOOK (email text,book_id integer,primary key(email,book_id)," +
                                "foreign key(email) REFERENCES USER (email) ON DELETE CASCADE," +
                                "foreign key(book_id) REFERENCES BOOKS (book_id) ON DELETE CASCADE)");
        sqLiteDatabase.execSQL("CREATE TABLE CONSIST_OF (book_id integer,collection_id integer,primary key(book_id,collection_id)," +
                                "foreign key(book_id) REFERENCES BOOKS (book_id) ON DELETE CASCADE," +
                                "foreign key(collection_id) REFERENCES COLLECTIONS (collection_id) ON DELETE CASCADE)");
        sqLiteDatabase.execSQL("CREATE TABLE WRITE (author_name text,book_id integer,primary key(author_name,book_id)," +
                                "foreign key(author_name) REFERENCES AUTHOR (author_name) ON DELETE CASCADE," +
                                "foreign key(book_id) REFERENCES BOOKS (book_id) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists AUTHOR");
        sqLiteDatabase.execSQL("drop table if exists COLLECTIONS");
        sqLiteDatabase.execSQL("drop table if exists BOOKS");
        sqLiteDatabase.execSQL("drop table if exists USER");
        sqLiteDatabase.execSQL("drop table if exists FAVORITES");
        sqLiteDatabase.execSQL("drop table if exists CONSIST_OF");
        sqLiteDatabase.execSQL("drop table if exists WRITE");
        onCreate(sqLiteDatabase);
    }

    public void insert_user(String email , String pass)
    {
        book_database = getWritableDatabase();
        book_database.execSQL("insert into USER VALUES(email,pass)");
        book_database.close();
    }
    public String[] fetch_user(String mail)
    {
        book_database = getReadableDatabase();
        String[] email = {mail};
        //Cursor cursor = null;
        //cursor = book_database.rawQuery("select * from USER where email like ?",email);
        book_database.close();
        return email;
    }

    public Cursor retrive()
    {
        book_database = getReadableDatabase();
        //String[] v = {"collection_id","name","no_of_books"};
//        String[] details = {"book_id" , "name" , "category" , "publish_date" , "language" , "chapters" , "collection_id" , "pages" , "parts" , "counter" , "image_link" ,
//                            "book_link"};
        Cursor cursor = book_database.rawQuery("select name from COLLECTIONS",null);
        if(cursor != null)
            cursor.moveToFirst();
        book_database.close();
        return cursor;
    }
    public void insert_data()
    {

//        ContentValues row = new ContentValues();
//        row.put("book_id",1);
//        row.put("name","test");
//        row.put("category","category");
//        row.put("publish_date","publishDate");
//        row.put("language","language");
//        row.put("chapters",2);
//        row.put("collection_id",2);
//        row.put("pages",5);
//        row.put("parts",1);
//        row.put("counter",1);
//        row.put("image_link","imageLink");
//        row.put("book_link","bookLink");

        book_database = getWritableDatabase();

        book_database.execSQL("INSERT INTO COLLECTIONS (name) VALUES ('Buddy Rich')");

//        book_database.execSQL("insert into COLLECTIONS values (1,'test2',1)");
//        book_database.execSQL("insert into COLLECTIONS values (2,'test3',1)");
        //book_database.insert("BOOKS" , null ,row);
        book_database.close();

    }
    public void insertAuthor(String name,String nationality,int no_of_books){
//        ContentValues row=new ContentValues();
//        row.put("name",name);
//        row.put("nationality",nationality);
//        row.put("no_of_books",no_of_books);
        book_database=getWritableDatabase();
        book_database.execSQL("insert into AUTHOR VALUES ('"+name+"'"+",'"+nationality+"'"+","+String.valueOf(no_of_books)+");");
        book_database.close();
    }

    public String fetchAuthors(){
        String getAll="SELECT * FROM AUTHOR";
        book_database=getReadableDatabase();
        //String[] rowDetails={"name","nationality","no_of_books"};
        Cursor cursor=book_database.rawQuery(getAll,null);
//        if(cursor!=null){
        cursor.moveToFirst();
//        }
        //database.close();
        return cursor.getString(0);
    }
}
