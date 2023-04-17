package Movie.Rating;

import Movie.MovieException;

public abstract class UserRating {
    protected String id = "";
    protected String movieId;
    protected int rating;
    protected String textReview = null;
    protected int minRating;
    protected int maxRating;

    protected UserRating(String movieId, int rating, String textReview) {
        this.rating = rating;
        this.textReview = textReview;
        this.movieId = movieId;
    }

    protected UserRating(String movieId, int rating) {
        this.rating = rating;
        this.movieId = movieId;
    }

    public boolean hasTextReview() {
        return textReview != null;
    }

    public void setTextReview(String textReview) {
        this.textReview = textReview;
    }

    public String getTextReview() {
        return textReview;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws MovieException {
        if (!this.id.isEmpty()) {
            throw new MovieException("ID is already set");
        }

        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    protected boolean checkIfValid(){
        return rating >= minRating && rating <= maxRating;
    }

    protected void setMinMaxRating(int minRating, int maxRating) {
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    @Override
    public String toString() {
        return id + "\n\tRating: " + rating + (textReview == null ? "" : "\n\tComment:" + "\n\t\t" + textReview);
    }
}
