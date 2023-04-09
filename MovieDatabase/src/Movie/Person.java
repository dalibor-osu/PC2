package Movie;

public class Person {
    private String name;
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Person)) {
            return false;
        }

        Person objPerson = (Person)o;

        return name.equals(objPerson.name) && surname.equals(objPerson.surname);
    }
    @Override
    public String toString() {
        return name + " " + surname;
    }
}
