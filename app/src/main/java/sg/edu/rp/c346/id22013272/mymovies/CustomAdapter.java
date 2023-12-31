package sg.edu.rp.c346.id22013272.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<movie> movieList;
    public CustomAdapter(Context context, int resource, ArrayList<movie> objects){
        super(context,resource,objects);
        parent_context=context;
        layout_id=resource;
        movieList=objects;
    }
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) parent_context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=inflater.inflate(layout_id,parent,false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        TextView tvRating = rowView.findViewById(R.id.tvRate);
        ImageView ivRated=rowView.findViewById(R.id.imageView2);


        // Obtain the Android Version information based on the position
        movie currentVersion = movieList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvGenre.setText(currentVersion.getGenre());
        tvYear.setText(String.valueOf(currentVersion.getYear()));
        tvRating.setText(currentVersion.getRated());
        if (currentVersion.getRated().equalsIgnoreCase("g")){
            ivRated.setImageResource(R.drawable.rating_g);
        } else if (currentVersion.getRated().equalsIgnoreCase("m18")) {
            ivRated.setImageResource(R.drawable.rating_m18);
        } else if (currentVersion.getRated().equalsIgnoreCase("nc16")) {
            ivRated.setImageResource(R.drawable.rating_nc16);
        } else if (currentVersion.getRated().equalsIgnoreCase("pg")) {
            ivRated.setImageResource(R.drawable.rating_pg);
        } else if (currentVersion.getRated().equalsIgnoreCase("pg13")) {
            ivRated.setImageResource(R.drawable.rating_pg13);
        }else {
            ivRated.setImageResource(R.drawable.rating_r21);
        }


        return rowView;


    }
}
