package com.example.volleyapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<MovieModel> movies;

    public MovieAdapter(Context context, ArrayList<MovieModel> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.item_movie, viewGroup, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, final int position) {
        String imgUrl = movies.get(position).getMovieImageLink();
        if (imgUrl != null) {
            Glide.with(context)
                    .load(Constants.IMAGE_BASE_URL + imgUrl)
                    .into(holder.movieIv);
        }


        holder.movieNameTv.setText(movies.get(position).getMovieName());
        holder.moviePopularityTv.setText(Double.toString(movies.get(position).getPopularity()));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movieNameTv)
        TextView movieNameTv;
        @BindView(R.id.moviePopularityTv)
        TextView moviePopularityTv;
        @BindView(R.id.movieIv)
        ImageView movieIv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
