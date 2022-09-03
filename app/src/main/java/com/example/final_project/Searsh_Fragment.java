package com.example.final_project;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.AdaptorPack.topBooksAdaptor;
import com.example.final_project.Model.TopBooks;
import com.example.final_project.AdaptorPack.topBooksAdaptor;
import com.example.final_project.Book_Database;
import com.example.final_project.Book_Templete;
import com.example.final_project.Model.TopBooks;
import com.example.final_project.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Searsh_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Searsh_Fragment extends Fragment implements RecyclerViewInterface {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
     List<TopBooks> itemList=new ArrayList<TopBooks>();
     topBooksAdaptor h;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Searsh_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Searsh_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Searsh_Fragment newInstance(String param1, String param2) {
        Searsh_Fragment fragment = new Searsh_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_searsh, container, false);
        Book_Database b=new Book_Database(getActivity());
        itemList=b.fetchOneBook("all");
        RecyclerView recyclerView=view.findViewById(R.id.searchOutput);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
//        b.insertBooks(17,"romantic","fantasy","feb","english",10,540,5,0,R.drawable.gg,"x");
//        b.insertAuthor("tony","m",5);
//        b.insertWrite("tony",17);
        SearchView searchView=view.findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }

        });
        h=new topBooksAdaptor(getActivity(),itemList,this,"all");
        recyclerView.setAdapter(h);
        return view;
    }

    private void filterList(String text) {
        List<TopBooks> filteredList=new ArrayList<TopBooks>();
        for(TopBooks topBooks:itemList){
            if(topBooks.getBookName().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(topBooks);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }else{
            h.setFilteredList(filteredList);
        }
    }

    @Override
    public void onItemClick(int position, String category) {
        Intent intent = new Intent(getActivity(), Book_Templete.class);
        Book_Database b=new Book_Database(getActivity());
            Cursor c1=b.fetchOneAuthor(b.fetchOneBook("all").get(position).getAuthorName());
            Cursor c2=b.fetchBooksByName(b.fetchOneBook("all").get(position).getBookName());
            intent.putExtra("bookId",c2.getString(0));
            intent.putExtra("bookName", b.fetchOneBook("all").get(position).getBookName());
            intent.putExtra("bookCategory",c2.getString(2));
            intent.putExtra("publishDate",c2.getString(3));
            intent.putExtra("description",c2.getString(4));
            intent.putExtra("language",c2.getString(5));
            intent.putExtra("chapters",c2.getString(6));
            intent.putExtra("pages",c2.getString(7));
            intent.putExtra("parts",c2.getString(8));
            intent.putExtra("counter",c2.getString(9));
            intent.putExtra("bookLink",c2.getString(11));
            intent.putExtra("imageLink", b.fetchOneBook("all").get(position).getImage());
            intent.putExtra("authorName",b.fetchOneBook("all").get(position).getAuthorName());
            intent.putExtra("authorNationality",c1.getString(1));
            intent.putExtra("noOfBooks",c1.getString(2));
        startActivity(intent);
    }
}