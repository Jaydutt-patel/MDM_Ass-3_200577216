package com.example.movie3.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie3.Model.Movie;
import com.example.movie3.OnClicked;
import com.example.movie3.R;


import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
   private Context context;
OnClicked onClicked;
    public MovieAdapter(Context context, ArrayList<Movie> model,OnClicked onClicked) {
        this.context = context;
        this.model = model;
        this.onClicked=onClicked;
    }

    private ArrayList<Movie> model;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.movieTitle.setText(model.get(position).getTitle());
       holder.movieStudio.setText(model.get(position).getTitle());
       holder.type.setText(model.get(position).getType());
       holder.year.setText(model.get(position).getYear());
       Glide.with(context).load(Uri.parse(model.get(position).getPoster())).into(holder.poster);
               holder.movieStudio.setText(model.get(position).getTitle());

        holder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicked.on_clicked(model.get(position).getImdbID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView movieTitle,movieStudio,year,type;
        ImageView poster;
        LinearLayout main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            main=itemView.findViewById(R.id.main);
            movieTitle=itemView.findViewById(R.id.mTitle);
            movieStudio=itemView.findViewById(R.id.mStudio);
            year=itemView.findViewById(R.id.mYear);
            poster=itemView.findViewById(R.id.movieImage);
            type=itemView.findViewById(R.id.mType);
        }
    }
}
