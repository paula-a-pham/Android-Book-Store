package com.example.final_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.AdaptorPack.topBooksAdaptor;
import com.example.final_project.Model.TopBooks;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Fragment extends Fragment implements RecyclerViewInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<TopBooks> list=new ArrayList<TopBooks>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
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
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        Book_Database b=new Book_Database(getContext());

//        //topBooks
        RecyclerView topBooks=view.findViewById(R.id.topBooks);
        topBooks.setHasFixedSize(true);
        topBooks.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        Cursor cursor=b.fetchBooks();
           for(int i=0;i<5;i++){
               TopBooks obj=new TopBooks();
            obj.setAuthorName(b.fetchOneWrite(Integer.valueOf(cursor.getString(0))).getString(0));
            obj.setImage(Integer.parseInt(cursor.getString(10)));
            obj.setBookName(cursor.getString(1));
            list.add(obj);
            cursor.moveToNext();
           }
        topBooksAdaptor trendingBooks=new topBooksAdaptor(getActivity(),list,this,"all");
        topBooks.setAdapter(trendingBooks);

        //romance
        RecyclerView history=view.findViewById(R.id.romance);
        history.setHasFixedSize(true);
        history.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        topBooksAdaptor h=new topBooksAdaptor(getActivity(),b.fetchOneBook("ROMANCE"),this,"ROMANCE");
        history.setAdapter(h);

        //horror
        RecyclerView drama=view.findViewById(R.id.horror);
        drama.setHasFixedSize(true);
        drama.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        topBooksAdaptor d=new topBooksAdaptor(getActivity(),b.fetchOneBook("HORROR"),this,"HORROR");
        drama.setAdapter(d);


        //fantasy
        RecyclerView fantasy=view.findViewById(R.id.fantasy);
        fantasy.setHasFixedSize(true);
        fantasy.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        topBooksAdaptor f=new topBooksAdaptor(getActivity(),b.fetchOneBook("FANTASY"),this,"FANTASY");
        fantasy.setAdapter(f);

        //mystery
        RecyclerView mystery=view.findViewById(R.id.myStery);
        mystery.setHasFixedSize(true);
        mystery.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        topBooksAdaptor m =new topBooksAdaptor(getActivity(),b.fetchOneBook("MYSTERY"),this,"MYSTERY");
        mystery.setAdapter(m);

        //scienceFiction
        RecyclerView scienceFiction=view.findViewById(R.id.scienceFiction);
        scienceFiction.setHasFixedSize(true);
        scienceFiction.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        topBooksAdaptor s=new topBooksAdaptor(getActivity(),b.fetchOneBook("SCIENCE FICTION"),this,"SCIENCE FICTION");
        scienceFiction.setAdapter(s);


        return view;
    }

    @Override
    public void onItemClick(int position,String category) {
        Intent intent = new Intent(getActivity(), Book_Templete.class);
        Book_Database b=new Book_Database(getActivity());
        if(category=="all"){
            Cursor c1=b.fetch_author_data(list.get(position).getAuthorName());
            Cursor c2=b.fetchBooksByName(list.get(position).getBookName());
            intent.putExtra("bookId",c2.getInt(0));
            intent.putExtra("bookName", list.get(position).getBookName());
            intent.putExtra("bookCategory",c2.getString(2));
            intent.putExtra("publishDate",c2.getString(3));
            intent.putExtra("description",c2.getString(4));
            intent.putExtra("language",c2.getString(5));
            intent.putExtra("chapters",c2.getString(6));
            intent.putExtra("pages",c2.getString(7));
            intent.putExtra("parts",c2.getString(8));
            intent.putExtra("counter",c2.getString(9));
            intent.putExtra("bookLink",c2.getString(11));
            intent.putExtra("imageLink", list.get(position).getImage());
            intent.putExtra("authorName", list.get(position).getAuthorName());
            intent.putExtra("authorNationality",c1.getString(1));
            intent.putExtra("noOfBooks",c1.getString(2));

        }
        else {
            Cursor c1=b.fetch_author_data(b.fetchOneBook(category).get(position).getAuthorName());
            Cursor c2=b.fetchBooksByName(b.fetchOneBook(category).get(position).getBookName());
            intent.putExtra("bookId",c2.getInt(0));
            intent.putExtra("bookName", b.fetchOneBook(category).get(position).getBookName());
            intent.putExtra("bookCategory",c2.getString(2));
            intent.putExtra("publishDate",c2.getString(3));
            intent.putExtra("description",c2.getString(4));
            intent.putExtra("language",c2.getString(5));
            intent.putExtra("chapters",c2.getString(6));
            intent.putExtra("pages",c2.getString(7));
            intent.putExtra("parts",c2.getString(8));
            intent.putExtra("counter",c2.getString(9));
            intent.putExtra("bookLink",c2.getString(11));
            intent.putExtra("imageLink", b.fetchOneBook(category).get(position).getImage());
            intent.putExtra("authorName", b.fetchOneBook(category).get(position).getAuthorName());
            intent.putExtra("authorNationality",c1.getString(1));
            intent.putExtra("noOfBooks",c1.getString(2));

        }
        startActivity(intent);
    }
}