package sg.edu.rp.c346.id22013272.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="movie.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATE = "rating";
    public DBHelper( Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createSongTableSql = "CREATE TABLE " + TABLE_MOVIE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_GENRE+" TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_RATE + " INTEGER) ";

        // Execute the SQL statement to create the table
        db.execSQL(createSongTableSql);
        Log.i("info", "created tables");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_MOVIE + " ADD COLUMN  module_name TEXT ");
        onCreate(db);

    }
    public long insertMovie(String title,String genre,int year, String rating) {

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Check if the movie title already exists
        String query = "SELECT * FROM " + TABLE_MOVIE + " WHERE " + COLUMN_TITLE + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{title});
        if (cursor.getCount() > 0) {
            // Movie title already exists, return -1 to indicate insertion failure
            cursor.close();
            db.close();
            return -1;
        }
        cursor.close();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_YEAR,year);
        values.put(COLUMN_GENRE,genre);
        values.put(COLUMN_RATE,rating);

        // Insert the row into the TABLE_TASK
        long result = db.insert(TABLE_MOVIE, null, values);
        db.close();
        Log.d("SQL Insert", "ID:" + result); //id returned, shouldn’t be -1
        return result;
    }
    public int updateMovie(movie title, movie genre, movie year, movie rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title.getTitle());
        values.put(COLUMN_GENRE,genre.getGenre());
        values.put(COLUMN_YEAR, year.getYear());
        values.put(COLUMN_RATE, rating.getRated());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(title.getId())};
        int result = db.update(TABLE_MOVIE, values, condition, args);
        db.close();
        return result;
    }
    public int deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_MOVIE, condition, args);
        db.close();
        return result;
    }
    public ArrayList<movie> getAllMovie(String keyword) {
        ArrayList<movie> Movie = new ArrayList<movie>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATE};

        String selection = COLUMN_TITLE + " LIKE ? OR " + COLUMN_GENRE + " LIKE ?";
        String[] args = {"%" + keyword + "%", "%" + keyword + "%"};

        Cursor cursor = db.query(TABLE_MOVIE, columns, selection, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                movie s1 = new movie(id,title, genre, year, rating);
                Movie.add(s1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movie;
    }

    public ArrayList<movie> getAllMoviesPG13() {
        ArrayList<movie> Movie = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATE};

        String selection = COLUMN_RATE + " = ?";
        String[] selectionArgs = {"6"};

        Cursor cursor = db.query(TABLE_MOVIE, columns, selection, selectionArgs,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre= cursor.getString(2);
                int year= cursor.getInt(3);
                String rate= cursor.getString(4);
                movie m2 = new movie(id,title,rate, year,genre);
                Movie.add(m2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movie;
    }
    public ArrayList<movie> getMovie() {
        ArrayList<movie> Movie = new ArrayList<movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATE};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                movie obj = new movie(id,title, genre, year, rating);
                Movie.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return Movie;
    }


}
