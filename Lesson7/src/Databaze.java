import java.util.Scanner;

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
	
	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int posledniStudent;
}