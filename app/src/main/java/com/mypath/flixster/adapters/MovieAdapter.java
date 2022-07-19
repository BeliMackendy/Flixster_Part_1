package com.mypath.flixster.adapters;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mypath.flixster.R;
import com.mypath.flixster.models.Movie;

import com.bumptech.glide.request.target.Target;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<Movie> movieList ;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        int orientation =holder.view.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(holder.view.getContext())
                    .load(movie.getPosterPath())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .placeholder(R.drawable.ic_baseline_local_movies_24)
                    .transition(DrawableTransitionOptions.withCrossFade(4000))
                    .transform(new RoundedCorners(20))
                    .into(holder.ivMovie);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Glide.with(holder.view.getContext())
                    .load(movie.getBackdropPath())
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .placeholder(R.drawable.ic_baseline_local_movies_24)
                    .transition(DrawableTransitionOptions.withCrossFade(4000))
                    .transform(new RoundedCorners(20))
                    .into(holder.ivMovie);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
       View view ;
       TextView tvTitle;
       TextView tvOverview;
       ImageView ivMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvTitle = view.findViewById(R.id.tvTitle);
            tvOverview = view.findViewById(R.id.tvOverview);
            ivMovie = view.findViewById(R.id.ivMovie);

        }
    }
}
