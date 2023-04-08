package Movie;

public class AnimatedMovieRating extends UserRating {
    private final int min = 1;
    private final int max = 10;
    public AnimatedMovieRating(String id, int rating, String textReview) throws MovieException {
        super(id, rating, textReview);
        setMinMaxRating(min, max);
        if (!checkIfValid()) throw new MovieException("Rating for animated movie must be between " + min + " and " + max + ". Current value: " + rating);
    }

    public AnimatedMovieRating(String id, int rating) throws MovieException {
        super(id, rating);
        setMinMaxRating(min, max);
        if (!checkIfValid()) throw new MovieException("Rating for animated movie must be between " + min + " and " + max + ". Current value: " + rating);
    }

    public AnimatedMovieRating(int rating, String textReview) throws MovieException {
        super(rating, textReview);
        setMinMaxRating(min, max);
        if (!checkIfValid()) throw new MovieException("Rating for animated movie must be between " + min + " and " + max + ". Current value: " + rating);
    }

    public AnimatedMovieRating(int rating) throws MovieException {
        super(rating);
        setMinMaxRating(min, max);
        if (!checkIfValid()) throw new MovieException("Rating for animated movie must be between " + min + " and " + max + ". Current value: " + rating);
    }
}
