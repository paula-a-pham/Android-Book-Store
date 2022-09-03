package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Vector;

public class Fav_Adapter extends ArrayAdapter {

    private Vector<Fav_data> my_fav;
    private Context context;

    public Fav_Adapter(Context context, int resource, int textViewResourceId, List Items) {
        super(context, resource, textViewResourceId, Items);
        this.my_fav = (Vector<Fav_data>) Items;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_favorite_list,parent,false);
        ImageView icon = view.findViewById(R.id.fav_img);
        TextView name = view.findViewById(R.id.fav_name);
        TextView desc = view.findViewById(R.id.fav_cat);
        icon.setImageResource(my_fav.get(position).getImg());
        name.setText(my_fav.get(position).getName());
        desc.setText(my_fav.get(position).getCategory());
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.anim_one);
        view.setAnimation(animation);
        return view;
    }
}
