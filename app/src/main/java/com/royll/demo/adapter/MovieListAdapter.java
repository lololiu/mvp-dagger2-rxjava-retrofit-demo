package com.royll.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.royll.demo.R;
import com.royll.demo.data.model.MovieBean;

import java.util.List;

/**
 * Created by liulou on 2016/7/20.
 * desc:
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<MovieBean.SubjectsBean> mMovieList;
    private Context mContext;
    public MovieListAdapter(Context context, MovieBean movieBean){
        mContext=context;
        mMovieList=movieBean.getSubjects();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        TextView movieCasts;
        TextView movieTop;
        TextView movieRate;
        ImageView moviePic;
        public ViewHolder(View view) {
            super(view);
            movieTitle= (TextView) view.findViewById(R.id.movie_title);
            movieCasts= (TextView) view.findViewById(R.id.movie_casts);
            movieTop= (TextView) view.findViewById(R.id.movie_top);
            moviePic= (ImageView) view.findViewById(R.id.movie_pic);
            movieRate= (TextView) view.findViewById(R.id.movie_rate);

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieBean.SubjectsBean movie=mMovieList.get(position);
        if(null==movie)
            return;
        holder.movieTop.setText("Top "+position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieRate.setText("豆瓣评分:"+movie.getRating().getAverage());
        StringBuilder casts=new StringBuilder();
        for(MovieBean.SubjectsBean.CastsBean cast:movie.getCasts()){
            casts.append(cast.getName()+" ");
        }
        holder.movieCasts.setText(casts.toString());
        Glide.with(mContext).load(movie.getImages().getMedium()).into(holder.moviePic);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieListAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movielist_layout,null));
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }


}
