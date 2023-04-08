package Movie;

import java.util.List;

public abstract class Movie {
    protected String id;
    protected String title;
    protected String director;
    protected int year;
    protected List<String> staff;

    public Movie(String title, String director, int year) {
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public String getName() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getStaff() {
        return staff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "\n\tTitle: " + title + "\n\tDirector: " + director + "\n\tYear: " + year;
    }
}
