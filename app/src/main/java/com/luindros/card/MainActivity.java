package com.luindros.card;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;
    private int counter=0;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies=getMovies();

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                removeMovie(position);
            }
        });
        mRecyclerView.setHasFixedSize(true);
        //Si queremos anadir una animacion por defecto
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void removeMovie(int position){
        movies.remove(position);
        mAdapter.notifyItemRemoved(position);
        mLayoutManager.scrollToPosition(position);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_name:
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New Movie"+(++counter), R.drawable.nueva));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }
    private List<Movie> getMovies(){
        return new ArrayList<Movie>(){{
            add(new Movie("Acero Puro", R.drawable.aceropuro));
            add(new Movie("Divergente", R.drawable.divergent));
            add(new Movie("Harry Poter", R.drawable.harrypotter));
            add(new Movie("Piratas del Caribe", R.drawable.piratescaribe));
        }};
    }
}
