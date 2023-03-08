
public class Student {
	public Student(String jmeno, int rocnik)
	{
		this.jmeno=jmeno;
		this.rocnik=rocnik;
	}
	
	public String getJmeno()
	{
		return jmeno;
	}
	
	public int getRocnik()
	{
		return rocnik;
	}

	public float getStudijniPrumer() throws StudentException {
		// Metoda vyvolá výjimku, pokud není nastaven studijní průměr.
		if (studijniPrumer == 0) throw new StudentException("Studijni prumer studenta jeste nebyl nastaven");
		return studijniPrumer;
	}

	public void setStudijniPrumer(float studijniPrumer) throws StudentException {
		// Metoda vyvolá výjimku, pokud zadaný studijní průměr není v rozsahu 1 - 5.
		if (studijniPrumer < 1 || studijniPrumer > 5) throw new StudentException("Studijni prumer musi byt v rozsahu 1 - 5");
		this.studijniPrumer = studijniPrumer;
	}

	private String jmeno;
	private int rocnik;
	private float studijniPrumer;
}