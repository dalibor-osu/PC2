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
		Databaze mojeDatabaze = new Databaze(1);
		int idx;
		float prumer;
		int volba;
		boolean run = true;

		while(run)
		{
			// Výpis možností pro uživatele
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vytvoreni nove databaze");
			System.out.println("2 .. vlozeni noveho studenta");
			System.out.println("3 .. nastaveni prumeru studenta");
			System.out.println("4 .. vypis informace o studentovi");
			System.out.println("5 .. ukonceni aplikace");

			// Získání volby od uživatele
			volba = pouzeCelaCisla(sc);

			switch(volba) {
				case 1:
					System.out.println("Zadejte pocet studentu");
					// Blok try-catch, který zajišťuje správné vytvoření třídy databáze (počet hodnot nesmí být měnší než 0)
					try {
						mojeDatabaze = new Databaze(pouzeCelaCisla(sc));
					}
					catch (NegativeArraySizeException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
						System.out.println("\tPocet studentu musi byt >= 0.");
					}
					break;

				case 2:
					// Blok try-catch, který ošetřuje výjimku IndexOutOfBoundsException.
					// Ta nastane, pokud databáze obsahuje maximální počet studentů a uživatel se pokusí přidat dalšího.
					try {
						mojeDatabaze.setStudent();
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
						System.out.println("\tPole v databazi neni dostatecne velke.");
					}

					break;

				case 3:
					System.out.println("Zadejte index a prumer studenta");
					// Ošetření správného zadání hodnot pomocí předem vytvořených funkcí
					idx = pouzeCelaCisla(sc);
					prumer = pouzeDesetinnaCisla(sc);
					// Blok try-catch ošetřující výjimku IndexOutOfBoundsException.
					// K té dojde, jestliže uživatel zadá index, který databáze neobsahuje.
					// Dále ošetřuje vlastní výjimku StudenException.
					// K té v tomto případě dojde, jestliže zadaný průměr není v rozmezí 1 - 5.
					// Samotný text výjimky je zapsán v souboru Student.java
					try {
						mojeDatabaze.setPrumer(idx, prumer);
					}
					catch (IndexOutOfBoundsException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
						System.out.println("\tDatabaze neobsahuje studenta s timto indexem.");
					}
					catch (StudentException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
					}

					break;

				case 4:
					System.out.println("Zadejte index studenta");
					idx = pouzeCelaCisla(sc);
					Student info;
					// Block try-catch, který opět ošetřuje výjimku IndexOutOfBoundsException. Dále pak i výjimku NullPointerException.
					// K této výjimce dojde v moment, kdy se snažíme získat informace o studentovi, které ještě nebyly zadány.
					// Dále je zde ošetřena výjimka StudenException. V tomto případě k ní dojde, jestliže není nastaven průměr studenta.
					try {
						info = mojeDatabaze.getStudent(idx);
						System.out.println("Jmeno: " + info.getJmeno() + " rok narozeni: " + info.getRocnik() + " prumer: " + info.getStudijniPrumer());
					}
					catch (IndexOutOfBoundsException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
						System.out.println("\tDatabaze obsahuje mene studentu, nez je zadany index.");
					}
					catch (NullPointerException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
						System.out.println("\tStudent s timto indexem nema v databazi zadane zadne, nebo nektere informace.");
					}
					catch (StudentException e) {
						System.out.println("Doslo k vyjimce: " + e.toString());
					}
					break;

				case 5:
					run = false;
					break;
			}
			
		}
	}

}
