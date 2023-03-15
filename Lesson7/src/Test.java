import java.util.Scanner;


public class Test {

	// Předem vytvořená funkce
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu " + e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}

	// Upravená funkce pouzeCelaCisla() tak, aby fungovala i na desetinná čísla
	public static float pouzeDesetinnaCisla(Scanner sc)
	{
		float cislo = 0f;
		try
		{
			cislo = sc.nextFloat();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim (desetinne) cislo ");
			sc.nextLine();
			cislo = pouzeDesetinnaCisla(sc);
		}
		return cislo;
	}

	public static void main(String[] args) {
		// Vytvoření promněnných
		Scanner sc = new Scanner(System.in);
		Databaze mojeDatabaze = new Databaze();
		String jmeno;
		float prumer;
		int volba;
		boolean run = true;

		while(run)
		{
			// Výpis možností pro uživatele
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vypis vsechny studenty");
			System.out.println("2 .. vlozeni noveho studenta");
			System.out.println("3 .. nastaveni prumeru studenta");
			System.out.println("4 .. vypis informace o studentovi");
			System.out.println("5 .. odstran studenta");
			System.out.println("6 .. ukonceni aplikace");

			// Získání volby od uživatele
			volba = pouzeCelaCisla(sc);

			switch(volba) {
				case 1:
					mojeDatabaze.vypisStudenty();
					break;

				case 2:
					mojeDatabaze.setStudent();
					break;

				case 3:
					System.out.println("Zadejte jmeno a prumer studenta");
					sc.nextLine();
					jmeno = sc.nextLine(); // Získá jméno studenta
					prumer = pouzeDesetinnaCisla(sc);

					try {
						if (mojeDatabaze.setPrumer(jmeno, prumer)) { // Pokud metoda vrátí true, tak student existuje a informujeme uživatele
							System.out.println("Prumer uspesne zmenen");
						} else {
							System.out.println("Student nenalezen"); // Pokud metoda vrátí false, tak student neexistuje
						}
					}
					catch (StudentException e) {
						System.out.println("Doslo k vyjimce: " + e.toString()); // K této vyjímce dojde při zadání špatného průměru
					}

					break;

				case 4:
					System.out.println("Zadejte jmeno studenta");
					sc.nextLine();
					jmeno = sc.nextLine();

					// Pole studentů o délce 1, které využijeme, abychom mohli v metodě getStudent() měnit hodnotu
					// proměnné z této metody, jelikož metoda vrací hodnotu boolean
					Student[] info = new Student[1];

					try {
						if(mojeDatabaze.getStudent(jmeno, info)) {
							System.out.println("Jmeno: " + info[0].getJmeno() + " rok narozeni: " + info[0].getRocnik() + " prumer: " + info[0].getStudijniPrumer());
						} else {
							System.out.println("Student nenalezen");
						}
					}
					catch (StudentException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
					}
					break;

				case 5:
					System.out.println("Zadejte jmeno studenta");
					sc.nextLine();
					jmeno = sc.nextLine();
					if (mojeDatabaze.odstranStudenta(jmeno)) {
						System.out.println("Student uspesne odstranen");
					} else {
						System.out.println("Student nenalezen");
					}
					break;

				case 6:
					run = false;
					break;
			}
			
		}
	}

}
