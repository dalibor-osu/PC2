package Movie;

import java.util.List;

public abstract class Movie {
    protected String id;
    protected String name;
    protected String director;
    protected int year;
    protected List<String> staff;

    public String getName() {
        return name;
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
}
