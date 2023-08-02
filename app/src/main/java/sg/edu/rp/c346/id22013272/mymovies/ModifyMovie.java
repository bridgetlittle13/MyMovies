package sg.edu.rp.c346.id22013272.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyMovie extends AppCompatActivity {

    TextView TvID, tvTitle, tvGenre, tvYear, tvRating;
    EditText etID, etTitle, etGenre, etYear;
    Button btnCancel, btnUpdate, btnDelete;
    Spinner spnRate;
    movie ID, title, genre, year, rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify);
        TvID = findViewById(R.id.tvID);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        btnCancel = findViewById(R.id.btnCancel);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        spnRate = findViewById(R.id.spnRating);

        Intent i = getIntent();

        ID = (movie) i.getSerializableExtra(" ID");
        TvID.setText(ID.getId());
        title = (movie) i.getSerializableExtra("Title");
        etTitle.setText(title.getTitle());
        genre = (movie) i.getSerializableExtra("Genre");
        etGenre.setText(genre.getGenre());
        year = (movie) i.getSerializableExtra("Year");
        etYear.setText(year.getYear());
        rate = (movie) i.getSerializableExtra("Rating");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ModifyMovie.this);
                String nTitle = String.valueOf(etTitle.getText());
                String nGenre = String.valueOf(etGenre.getText());
                int nYear = Integer.valueOf(String.valueOf(etYear.getText()));
                String nRate = String.valueOf(spnRate.getId());

                title.setTitle(nTitle);
                genre.setGenre(nGenre);
                year.setYear(nYear);
                rate.setRated(nRate);

                db.updateMovie(title, genre, year, rate);
                db.close();
                finish();

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ModifyMovie.this);
                db.deleteMovie(ID.getId());
                finish();

            }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
