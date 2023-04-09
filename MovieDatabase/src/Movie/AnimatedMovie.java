package Movie;

public class AnimatedMovie extends Movie{
    private int age;
    public AnimatedMovie(String title, String director, int year, int age) {
        super(title, director, year);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean isAnimated() {
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\tAge: " + age;
    }
}
