package sg.edu.rp.c346.id22013272.mymovies;

import java.util.Calendar;

public class movie {
    private int id;
    private String title;
    private int year;
    private String rated;
    private String genre;


    public movie(int id, String title, String genre, int year, String rated) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", rated='" + rated + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
