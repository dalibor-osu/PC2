public class StudentException extends Exception{
    // Konstruktory pro vyjímku
    public StudentException() {
        super("Student Exception");
    }

    public StudentException(String s) {
        super(s);
    }
}
