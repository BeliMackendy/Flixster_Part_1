package com.mypath.flixster.adapters;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.mypath.flixster.R;
import com.mypath.flixster.models.Movie;

import com.bumptech.glide.request.target.Target;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Movie> movieList ;

    private final int IMAGE = 1;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == IMAGE) {// Inflate the custom layout
            View movieView2 = inflater.inflate(R.layout.item_movie_star, parent, false);

            // Return a new holder instance
            return new ViewHolder2(movieView2);
        }// Inflate the custom layout
        else {
            View movieView1 = inflater.inflate(R.layout.item_movie, parent, false);

            // Return a new holder instance
            return new ViewHolder(movieView1);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        if (holder.getItemViewType() == IMAGE) {
            ViewHolder2 holder2 = (ViewHolder2) holder;

            int orientation =holder2.view.getResources().getConfiguration().orientation;

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide.with(holder2.view.getContext())
                        .load(movie.getPosterPath())
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .placeholder(R.drawable.ic_baseline_local_movies_24)
                        .transition(DrawableTransitionOptions.withCrossFade(4000))
                        .transform(new RoundedCorners(20))
                        .into(holder2.ivMovie);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Glide.with(holder2.view.getContext())
                        .load(movie.getBackdropPath())
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .placeholder(R.drawable.ic_baseline_local_movies_24)
                        .transition(DrawableTransitionOptions.withCrossFade(4000))
                        .transform(new RoundedCorners(20))
                        .into(holder2.ivMovie);
            }

        } else {

            ViewHolder holder1 = (ViewHolder) holder;

            holder1.tvTitle.setText(movie.getTitle());
            holder1.tvOverview.setText(movie.getOverview());

            holder1.tvTitle.setTypeface(ResourcesCompat.getFont(holder1.view.getContext(),R.font.berkshire_swash));
            holder1.tvOverview.setTypeface(ResourcesCompat.getFont(holder1.view.getContext(),R.font.aclonica));

            int orientation =holder1.view.getResources().getConfiguration().orientation;

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                Glide.with(holder1.view.getContext())
                        .load(movie.getPosterPath())
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .placeholder(R.drawable.ic_baseline_local_movies_24)
                        .transition(DrawableTransitionOptions.withCrossFade(4000))
                        .transform(new RoundedCorners(20))
                        .into(holder1.ivMovie);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Glide.with(holder1.view.getContext())
                        .load(movie.getBackdropPath())
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .placeholder(R.drawable.ic_baseline_local_movies_24)
                        .transition(DrawableTransitionOptions.withCrossFade(4000))
                        .transform(new RoundedCorners(20))
                        .into(holder1.ivMovie);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(movieList.get(position).getVoteAverage()>5){
            return IMAGE;
        }
        else
        {
            return 0;
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

    public static class ViewHolder2 extends RecyclerView.ViewHolder{
       View view ;

       ImageView ivMovie;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            view = itemView;

            ivMovie = view.findViewById(R.id.ivMovieStar);

        }
    }
}
