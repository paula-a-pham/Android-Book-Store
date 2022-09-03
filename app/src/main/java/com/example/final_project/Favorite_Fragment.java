package com.example.final_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favorite_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favorite_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Favorite_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Favorite_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Favorite_Fragment newInstance(String param1, String param2) {
        Favorite_Fragment fragment = new Favorite_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        Book_Database database = new Book_Database(getActivity());
        Cursor cursor5 = database.fetch_flag();

        Vector<Fav_data> items = new Vector<Fav_data>();
        Cursor cursor = database.fetch_all_favorites(cursor5.getString(2));
        Cursor cursor1;
        while(!cursor.isAfterLast())
        {
            cursor1 = database.fetch_one_book(cursor.getInt(1));
            items.add(new Fav_data(cursor1.getString(1),cursor1.getString(2),cursor1.getInt(10)));
            cursor.moveToNext();
        }

        ListView my_list = (ListView) view.findViewById(R.id.favorite_list);
        Fav_Adapter adapter = new Fav_Adapter(getActivity(),R.layout.activity_favorite_list,R.id.fav_name,items);

        my_list.setAdapter(adapter);
        my_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity(), Book_Templete.class);
            String data = adapterView.getItemAtPosition(i).toString();
            Cursor cursor2=database.fetchBooksByName(data);
            Cursor cursor3=database.fetch_writer_name(cursor2.getInt(0));
            Cursor cursor4=database.fetch_author_data(cursor3.getString(0));
            intent.putExtra("bookId",cursor2.getInt(0));
            intent.putExtra("bookName", cursor2.getString(1));
            intent.putExtra("bookCategory",cursor2.getString(2));
            intent.putExtra("publishDate",cursor2.getString(3));
            intent.putExtra("description",cursor2.getString(4));
            intent.putExtra("language",cursor2.getString(5));
            intent.putExtra("chapters",cursor2.getString(6));
            intent.putExtra("pages",cursor2.getString(7));
            intent.putExtra("parts",cursor2.getString(8));
            intent.putExtra("counter",cursor2.getString(9));
            intent.putExtra("bookLink",cursor2.getString(11));
            intent.putExtra("imageLink", cursor2.getInt(10));
            intent.putExtra("authorName",cursor4.getString(0));
            intent.putExtra("authorNationality",cursor4.getString(1));
            intent.putExtra("noOfBooks",cursor4.getString(2));
            startActivity(intent);
            }
        });
        Animation animation = AnimationUtils.loadAnimation(getActivity(),R.anim.anim_one);
        my_list.setAnimation(animation);
        return view;
    }
}