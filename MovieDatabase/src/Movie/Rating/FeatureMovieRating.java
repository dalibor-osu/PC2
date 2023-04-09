package Movie.Rating;

import Movie.MovieException;

public class FeatureMovieRating extends UserRating {
    private final int min = 1;
    private final int max = 5;

    public FeatureMovieRating(String id, int rating, String textReview) throws MovieException {
        super(id, rating, textReview);
        setMinMaxRating(min, max);
        if (checkIfValid()) throw new MovieException("Rating for feature movie must be between " + min + " and " + max + ". Current value: " + rating);
    }

    public FeatureMovieRating(String id, int rating) throws MovieException {
        super(id, rating);
        setMinMaxRating(min, max);
        if (checkIfValid()) throw new MovieException("Rating for feature movie must be between " + min + " and " + max + ". Current value: " + rating);
    }
    public FeatureMovieRating(int rating, String textReview) throws MovieException {
        super(rating, textReview);
        setMinMaxRating(min, max);
        if (checkIfValid()) throw new MovieException("Rating for feature movie must be between " + min + " and " + max + ". Current value: " + rating);
    }

    public FeatureMovieRating(int rating) throws MovieException {
        super(rating);
        setMinMaxRating(min, max);
        if (checkIfValid()) throw new MovieException("Rating for feature movie must be between " + min + " and " + max + ". Current value: " + rating);
    }
}