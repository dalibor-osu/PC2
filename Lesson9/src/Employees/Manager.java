package Employees;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static Employees.StringHelper.getStringFromCharArray;

public class Manager<T> {
    private char[] nickname;
    private char[] email;
    private char[] password;
    private EmployeeType employeeType;
    private List<Employee> listOfEmployees;
    private T listOfRelationships;

    public Manager(char[] nickname, char[] email, char[] password, EmployeeType employeeType, List<Employee> listOfEmployees, T listOfRelationships) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.employeeType = employeeType;
        this.listOfEmployees = listOfEmployees;
        this.listOfRelationships = listOfRelationships;
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }

    public T getListOfRelationships() {
        return listOfRelationships;
    }

    public void sortByEmployeesByEmail() {
        listOfEmployees.sort(new SortByEmail());
    }

    @Override
    public String toString() {
        return "Manager " + getStringFromCharArray(nickname) + "\n\t" +
                "Password: " + getStringFromCharArray(email) + "\n\t" +
                "Type: " + employeeType.toString();
    }

    // Třída komparátoru, která umožní seřadit List zaměstnanců podle emailu
    static class SortByEmail implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return Arrays.compare(o1.getEmail(), o2.getEmail());
        }
    }
}
