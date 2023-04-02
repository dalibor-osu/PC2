package Movie;

public abstract class UserRating {
    protected int rating;
    protected String textReview;
    protected int minRating;
    protected int maxRating;

    protected UserRating(int rating, String textReview) {
        this.rating = rating;
        this.textReview = textReview;
    }

    protected UserRating(int rating) {
        this.rating = rating;
        textReview = "";
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

    protected boolean checkIfValid(){
        return rating >= minRating && rating <= maxRating;
    }

    protected void setMinMaxRating(int minRating, int maxRating) {
        this.minRating = minRating;
        this.maxRating = maxRating;
    }
}
