import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class Databaze {
	public Databaze(int pocetPrvku)
	{
		prvkyDatabaze = new Student[pocetPrvku];
		sc = new Scanner(System.in);
	}

	public void setStudent() throws IndexOutOfBoundsException // Zde je výjimka vyvolána automaticky, takže metoda neobsahuje throw
	{
		System.out.println("Zadejte jmeno studenta, rok narozeni");
		String jmeno = sc.next();
		int rok = Test.pouzeCelaCisla(sc); // Použití metody pouzeCelaCisla() z třídy Test
		prvkyDatabaze[posledniStudent++] = new Student(jmeno,rok);
	}

	public Student getStudent(int idx) throws IndexOutOfBoundsException // Zde je výjimka vyvolána automaticky, takže metoda neobsahuje throw
	{
		return prvkyDatabaze[idx];
	}

	public void setPrumer(int idx, float prumer) throws IndexOutOfBoundsException, StudentException // Zde jsou výjimky vyvolány automaticky, takže metoda neobsahuje throw
	{
		prvkyDatabaze[idx].setStudijniPrumer(prumer);
	}

	public void vypisDatabazi() throws StudentException
	{
		for (Student student : prvkyDatabaze) {
			if (student == null) // Pokud student není nastaven, přeskočíme ho
				continue;

			float studijniPrumer;

			try { // Zkusíme získat studijní průměr přes try, jelikož vyvolává výjimku StudentException, pokud ji vyvolá, nastavíme průměr na 0
				studijniPrumer = student.getStudijniPrumer();
			} catch (StudentException e) {
				studijniPrumer = 0;
			}

			System.out.println("Jmeno: " + student.getJmeno() +
					", rok narozeni: " + student.getRocnik() +
					", studijni prumer: " + (studijniPrumer == 0 ? "-" : studijniPrumer)); // Místo hodnoty 0 vypíšeme znak - (pouze estetická úprava)
		}
	}

	public void ulozDoSouboru(String nazevSouboru)
	{
		// Vytvoření instance souboru a jeho cesty. Pokud řetězec neobsahuje tečku, byl název soboru zadán bez typu souboru a použijeme .txt
		String cesta = System.getProperty("user.dir") + "\\" + nazevSouboru + (nazevSouboru.contains(".") ? "" : ".txt");
		File soubor = new File(cesta);

		try {
			if (soubor.exists()) soubor.delete(); // Pokud soubor již existuje, vymažeme ho a vytvoříme znovu, aby se obsah resetoval

			if (!soubor.createNewFile()) {
				System.out.println("Chyba pri vytvareni souboru");
			}

			try (FileWriter writer = new FileWriter(soubor)) { // Pro každého studenta vypisujeme data do souboru
				for (Student student : prvkyDatabaze) {
					if (student == null) // Přeskočíme studenta, který nebyl nastaven
						continue;

					float studijniPrumer;

					try { // Zkusíme získat studijní průměr přes try, jelikož vyvolává výjimku StudentException, pokud ji vyvolá, nastavíme průměr na 0
						studijniPrumer = student.getStudijniPrumer();
					} catch (StudentException e) {
						studijniPrumer = 0;
					}

					// Vypíšeme data do souboru. Jednotlivá data jsou odděleny znakem '|' a na konec je třeba umístit znak nového řádku \n
					writer.write(student.getJmeno() + '|' + student.getRocnik() + '|' + studijniPrumer + '\n');
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void nactiZeSouboru(String nazevSouboru)
	{
		// Vytvoření instance souboru a jeho cesty
		String cesta = System.getProperty("user.dir") + "\\" + nazevSouboru + (nazevSouboru.contains(".") ? "" : ".txt");
		File soubor = new File(cesta);

		// Pokud soubor neexistuje, nemůžeme pokračovat
		if (!soubor.exists()) {
			System.out.println("Soubor neexistuje!");
			return;
		}

		// Zjistíme, kolik řádků je v databázi, abychom mohli správně vytvořit pole
		int pocetStudentu = pocetRadku(cesta);
		if (pocetStudentu < 0) {
			System.out.println("Chyba pri nacitani databaze");
			return;
		}

		// Vytvoření pole studentů
		prvkyDatabaze = new Student[pocetStudentu];

		try {
			Scanner reader = new Scanner(soubor);
			int index = 0;

			// Postupné čtení řádků
			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				String[] data = line.split("\\|"); // Rozdělení řetězce na znaku |, který v mé databázi odděluje data
				prvkyDatabaze[index] = new Student(data[0], parseInt(data[1])); // Vytvoření studenta

				float studijniPrumer = parseFloat(data[2].trim()); // Pokud není nastaven studijní průměr, necháme ho nenastaven
				if (studijniPrumer != 0) prvkyDatabaze[index].setStudijniPrumer(studijniPrumer);

				index++; // Další student
			}

			System.out.println("Nacitani databaze hotovo");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private int pocetRadku(String soubor)
	{
		int lines;

		try {
			BufferedReader reader = new BufferedReader(new FileReader(soubor));
			lines = 0;
			while (reader.readLine() != null) lines++; // Dokud reader čte řádky, přičítá 1 k promněnné lines
			reader.close();
			return lines; // Vrací počet řádků
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1; // Při chybě vrací -1
		}
	}

	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int posledniStudent;
}