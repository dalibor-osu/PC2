package Movie;

public class FeatureMovie extends Movie {
    public FeatureMovie(String title, String director, int year) {
        super(title, director, year);
    }

    @Override
    public boolean isAnimated() {
        return false;
    }
}
