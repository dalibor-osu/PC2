package Movie;

public class AnimatedMovieRating extends UserRating {
    private final int min = 1;
    private final int max = 10;
    protected AnimatedMovieRating(int rating, String textReview) throws MovieException {
        super(rating, textReview);
        setMinMaxRating(min, max);
        if (!checkIfValid()) throw new MovieException("Rating for animated movie must be between " + min + " and " + max + ". Current value: " + rating);
    }

    protected AnimatedMovieRating(int rating) throws MovieException {
        super(rating);
        setMinMaxRating(min, max);
        if (!checkIfValid()) throw new MovieException("Rating for animated movie must be between " + min + " and " + max + ". Current value: " + rating);
    }
}
