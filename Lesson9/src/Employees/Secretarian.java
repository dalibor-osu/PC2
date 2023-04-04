package Employees;

import static Employees.StringHelper.getStringFromCharArray;

public class Secretarian {
    private char[] nickname;
    private char[] email;
    private char[] password;
    private EmployeeType employeeType;
    private int age;

    public Secretarian(char[] nickname, char[] email, char[] password, EmployeeType employeeType, int age) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.employeeType = employeeType;
        this.age = age;
    }

    public char[] getNickname() {
        return nickname;
    }

    public char[] getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Secretarian " + getStringFromCharArray(nickname) + "\n\t" +
                "Email: " + getStringFromCharArray(email) + "\n\t" +
                "Type: " + employeeType.toString() + "\n\t" +
                "Age: " + age;
    }
}
