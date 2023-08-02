package sg.edu.rp.c346.id22013272.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewMovie extends AppCompatActivity {
    ArrayList<movie> movieList;
    ListView lv;
    Button btnPG13;
    CustomAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        movieList = new ArrayList<movie>();
        adapter = new CustomAdapter(this, R.layout.row, movieList);
        lv.setAdapter(adapter);
        Intent i = getIntent();
        DBHelper db = new DBHelper(ViewMovie.this);
        movieList.clear();
        movieList.addAll(db.getMovie());
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_movie);
        btnPG13=findViewById(R.id.btnPG13);
        lv=findViewById(R.id.lv);


        lv.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                movie selectedMovie = movieList.get(position);
                Intent i = new Intent(ViewMovie.this, ModifyMovie.class);
                i.putExtra("ID", selectedMovie.getId());
                i.putExtra("Title", selectedMovie.getTitle());
                i.putExtra("Genre", selectedMovie.getGenre());
                i.putExtra("Year", selectedMovie.getYear());
                i.putExtra("Rate", selectedMovie.getRated());
                startActivity(i);
            }
        });

        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ViewMovie.this);
                movieList.clear();
                movieList.addAll(dbh.getAllMoviesPG13());
                adapter.notifyDataSetChanged();
            }
        });
    }

}