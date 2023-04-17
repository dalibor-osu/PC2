package Movie;

import java.util.ArrayList;
import java.util.List;

public abstract class Movie {
    protected String id = "";
    protected String title;
    protected String director;
    protected int year;
    protected List<Person> staff;

    public Movie(String title, String director, int year) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.staff = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public List<Person> getStaff() {
        return staff;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStaff(List<Person> staff) {
        this.staff = staff;
    }

    public void setId(String id) throws MovieException {
        if (!this.id.isEmpty()) {
            throw new MovieException("ID is already set");
        }

        this.id = id;
    }

    public abstract boolean isAnimated();

    @Override
    public String toString() {
        return title + "\n\tID: " + id + "\n\tDirector: " + director + "\n\tYear: " + year + getPrintableStaffString();
    }

    private String getPrintableStaffString() {
        String staffType = (isAnimated() ? "Animators" : "Actors");

        if (staff.size() == 0) {
            return "\n\t" + staffType + ": No " + staffType + " set";
        }

        StringBuilder builder = new StringBuilder("\n\t" + staffType + ":");
        for (Person person : staff) {
            builder.append("\n\t\t").append(person);
        }

        return builder.toString();
    }
}
