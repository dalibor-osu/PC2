package Employees;

// Třída usnadňující práci s polem charakterů
public class StringHelper {
    // Metoda tvořící String z pole charakterů
    public static String getStringFromCharArray(char[] array) {
        StringBuilder str = new StringBuilder();
        for (char c : array) {
            str.append(c);
        }

        return str.toString();
    }
}
