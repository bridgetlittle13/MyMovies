package sg.edu.rp.c346.id22013272.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvGenre, tvYear;
    EditText etTitle, etGenre, etYear;
    Spinner spnRating;
    String spnRate;
    Button btnInsert, btnSL;
    ArrayList<movie> movieal;
    ArrayAdapter<movie> movieaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert=findViewById(R.id.btnInsert);
        btnSL=findViewById(R.id.btnSL);
        spnRating = findViewById(R.id.spnRating);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Rating, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spnRating.setAdapter(adapter);
        movieal = new ArrayList<movie>();
        movieaa = new ArrayAdapter<movie>(this,
                android.R.layout.simple_list_item_1, movieal);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                String newtitle = etTitle.getText().toString();
                String newGenre = etGenre.getText().toString();
                int newyear = Integer.parseInt(etYear.getText().toString());

                spnRating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Handle item selection here
                        switch (position) {
                            case 0:
                                spnRate = "G";
                                break;
                            case 1:
                                spnRate = "PG";
                                break;
                            case 2:
                                spnRate = "PG13";
                                break;
                            case 3:
                                spnRate = "NC16";
                                break;
                            case 4:
                                spnRate = "M18";
                                break;
                            case 5:
                                spnRate = "R21";
                                break;
                        }
                        String spnRate = spnRating.getSelectedItem().toString();
                        db.insertMovie(newtitle, newGenre, newyear, spnRate);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                btnSL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, ViewMovie.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}

