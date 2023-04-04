package Employees;

import static Employees.StringHelper.getStringFromCharArray;

public class Employee {
    private char[] nickname;
    private char[] email;
    private char[] password;
    private EmployeeType employeeType;

    public Employee(char[] nickname, char[] email, char[] password, EmployeeType employeeType) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.employeeType = employeeType;
    }

    public char[] getNickname() {
        return nickname;
    }

    public char[] getEmail() {
        return email;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    @Override
    public String toString() {
        return "Employee " + getStringFromCharArray(nickname) + "\n\t" +
                "Email: " + getStringFromCharArray(email) + "\n\t" +
                "Type: " + employeeType.toString();
    }
}
