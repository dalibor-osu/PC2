package Movie;

public class Person {
    private String ID;
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public Person(String ID, String name, String surname) {
        this(name, surname);
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
