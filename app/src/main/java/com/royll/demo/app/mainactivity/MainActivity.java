package com.royll.demo.app.mainactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.royll.demo.AppComponent;
import com.royll.demo.R;
import com.royll.demo.adapter.MovieListAdapter;
import com.royll.demo.base.BaseActivity;
import com.royll.demo.data.model.MovieBean;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    @Inject
    MainPresenter mMainPresenter;
    @Inject
    MovieBean mMovieBean;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.swiperefresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private int mPage = 0;
    private int mPageCount = 20;
    private boolean isRefreshing = false;
    private boolean isLoading =false;


    private MovieListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initViews();
        isRefreshing = true;
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });
        mMainPresenter.getMovies(mPage, mPageCount);
    }

    @Override
    public void updateMovieList(MovieBean movieBean) {
        if (isRefreshing) {
            mAdapter.refreshMovieList(movieBean.getSubjects());
            mRefreshLayout.setRefreshing(false);
            isRefreshing = false;
            showToast("刷新成功!");
        }
        if(isLoading){
            isLoading=false;
            mAdapter.loadMoreMovieList(movieBean.getSubjects());
            showToast("加载完成!");
        }


    }

    @Override
    public void initViews() {
        setSupportActionBar(mToolbar);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        mRecyclerView.setHasFixedSize(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MovieListAdapter(MainActivity.this, mMovieBean);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(isLoading){
                    return;
                }
                if (!isRefreshing) {
                    mPage = 0;
                    mMainPresenter.getMovies(mPage, mPageCount);
                    isRefreshing = true;
                }
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition=mLayoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1==mAdapter.getItemCount()){
                    boolean refreshing=mRefreshLayout.isRefreshing();
                    if(refreshing){
                        mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                        return;
                    }
                    if(!isLoading){
                        isLoading=true;
                        mPage++;
                        mMainPresenter.getMovies(mPage,mPageCount);
                    }
                }
            }
        });
    }


    @OnClick(R.id.fab)
    public void onFabOnclick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void setupActivityCompoent(AppComponent appComponent) {
        DaggerMainComponent.builder().mainPresenterModule(new MainPresenterModule(this)).appComponent(appComponent).build().inject(this);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
