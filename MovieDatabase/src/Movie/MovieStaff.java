package Movie;

public class MovieStaff {
    private String id;
    private String movieId;
    private String personId;

    public MovieStaff(String movieId, String personId) {
        this.movieId = movieId;
        this.personId = personId;
    }

    public MovieStaff(String id, String movieId, String personId) {
        this(movieId, personId);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setId(String id) {
        this.id = id;
    }
}
