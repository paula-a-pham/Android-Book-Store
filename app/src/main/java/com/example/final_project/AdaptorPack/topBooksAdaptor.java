package com.example.final_project.AdaptorPack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.Model.TopBooks;
import com.example.final_project.R;
import com.example.final_project.RecyclerViewInterface;

import java.util.List;

public class topBooksAdaptor extends RecyclerView.Adapter<topBooksAdaptor.topBooksHolder> implements RecyclerViewInterface {
    private List<TopBooks> booksList;
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    String category;
    public void setFilteredList(List<TopBooks> filteredList){
        this.booksList=filteredList;
        notifyDataSetChanged();
    }
    public topBooksAdaptor(Context context, List<TopBooks> booksList, RecyclerViewInterface recyclerViewInterface,String category){
        this.context=context;
        this.booksList=booksList;
        this.category=category;
        this.recyclerViewInterface = recyclerViewInterface;
    }
    @NonNull
    @Override
    public topBooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
     return new topBooksHolder(view,recyclerViewInterface,category);
    }

    @Override
    public void onBindViewHolder(@NonNull topBooksHolder holder, int position) {
        holder.authorName.setText(booksList.get(position).getAuthorName());
        holder.bookName.setText(booksList.get(position).getBookName());
        holder.mImageView.setImageResource(booksList.get(position).getImage());
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),
                R.anim.anim_one));
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    @Override
    public void onItemClick(int position,String category) {

    }


    public static class topBooksHolder extends RecyclerView.ViewHolder{
        private TextView authorName;
        private TextView bookName;
        private ImageView mImageView;
        private CardView cardView;
        public topBooksHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface,String category) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card1);
            authorName=itemView.findViewById(R.id.textView3);
            bookName=itemView.findViewById(R.id.textView4);
            mImageView=itemView.findViewById(R.id.imageView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface!=null){
                        int pos=getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos,category);
                        }
                    }
                }
            });
        }
    }
}
