import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Databaze {
	private Scanner sc;
	private HashMap<String, Student> prvkyDatabaze;

	public Databaze()
	{
		prvkyDatabaze = new HashMap<>();
		sc = new Scanner(System.in);
	}

	public void setStudent()
	{
		System.out.println("Zadejte jmeno studenta, rok narozeni");
		String jmeno = sc.next();
		int rok = Test.pouzeCelaCisla(sc); // Použití metody pouzeCelaCisla() z třídy Test
		prvkyDatabaze.put(jmeno, new Student(jmeno,rok));
	}
	
	public boolean getStudent(String jmeno, Student[] student)
	{
		if (!prvkyDatabaze.containsKey(jmeno))
			return false; // Vrátí false pokud student neexistuje

		student[0] = prvkyDatabaze.get(jmeno); // Nastaví hodnotu prvního (a jediného) prvku v poli na zvoleného studenta
		return true;
	}
	
	public boolean setPrumer(String jmeno, float prumer) throws StudentException // Zde jsou výjimky vyvolány automaticky, takže metoda neobsahuje throw
	{
		if (!prvkyDatabaze.containsKey(jmeno))
			return false; // Vrátí false pokud student neexistuje

		prvkyDatabaze.get(jmeno).setStudijniPrumer(prumer);
		return true;
	}

	public void vypisStudenty() {
		System.out.println("Jmena Studentu:");
		for (String jmeno : prvkyDatabaze.keySet()) { // Vypíše klíč pro každého uloženého studenta => jméno
			System.out.println("\t" + jmeno);
		}
	}

	public boolean odstranStudenta(String jmeno) {
		if (!prvkyDatabaze.containsKey(jmeno))
			return false; // Vrátí false pokud student neexistuje

		prvkyDatabaze.remove(jmeno); // Odstraní prvek se zadaným klíčem (jménem)
		return true;
	}
}