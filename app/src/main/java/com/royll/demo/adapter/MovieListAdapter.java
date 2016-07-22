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
public class MovieListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    private List<MovieBean.SubjectsBean> mMovieList;
    private Context mContext;


    public MovieListAdapter(Context context, MovieBean movieBean){
        mContext=context;
        mMovieList=movieBean.getSubjects();
    }
    public void setMovieList(List<MovieBean.SubjectsBean> movieList) {
        this.mMovieList = movieList;
    }
    public void addMovieList(List<MovieBean.SubjectsBean> movieList) {
        this.mMovieList.addAll(movieList);
    }
    public void refreshMovieList(List<MovieBean.SubjectsBean> movieList) {
        setMovieList(movieList);
        notifyDataSetChanged();
    }

    public void loadMoreMovieList(List<MovieBean.SubjectsBean> movieList) {
        addMovieList(movieList);
        notifyDataSetChanged();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        TextView movieCasts;
        TextView movieTop;
        TextView movieRate;
        ImageView moviePic;
        public ItemViewHolder(View view) {
            super(view);
            movieTitle= (TextView) view.findViewById(R.id.movie_title);
            movieCasts= (TextView) view.findViewById(R.id.movie_casts);
            movieTop= (TextView) view.findViewById(R.id.movie_top);
            moviePic= (ImageView) view.findViewById(R.id.movie_pic);
            movieRate= (TextView) view.findViewById(R.id.movie_rate);
        }
    }

    public static class FootViewHolder extends RecyclerView.ViewHolder {
        //TextView footText;
        TextView foot_view_text;
        public FootViewHolder(View view) {
            super(view);
            foot_view_text= (TextView) view.findViewById(R.id.foot_view_text);
          //  footText= (TextView) view.findViewById(R.id.foot_view_text);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder holder1=(ItemViewHolder) holder;
            MovieBean.SubjectsBean movie=mMovieList.get(position);
            if(null==movie)
                return;
            holder1.movieTop.setText("Top "+position);
            holder1.movieTitle.setText(movie.getTitle());
            holder1.movieRate.setText("豆瓣评分:"+movie.getRating().getAverage());
            StringBuilder casts=new StringBuilder();
            for(MovieBean.SubjectsBean.CastsBean cast:movie.getCasts()){
                casts.append(cast.getName()+" ");
            }
            holder1.movieCasts.setText(casts.toString());
            Glide.with(mContext).load(movie.getImages().getMedium()).into(holder1.moviePic);
        }else if(holder instanceof FootViewHolder){
            FootViewHolder holder2=(FootViewHolder)holder;
            //holder2.footText.setText("加载更多中...");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_ITEM){
            return new MovieListAdapter.ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_movielist_layout,null,false));
        }else if(viewType==TYPE_FOOTER){
            //return new MovieListAdapter.FootViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_foot_view,null,false));
            return new MovieListAdapter.FootViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_foot_view,null,false));
        }else {
            return null;
        }

    }

    @Override
    public int getItemCount() {
        return mMovieList.size()==0?0:mMovieList.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }
}
