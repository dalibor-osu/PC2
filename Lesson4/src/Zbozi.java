public abstract class Zbozi {
    private String nazev;
    private double cena;
    public static final double DPH = 0.21;

    public Zbozi(String nazev, double cena) {
        this.nazev = nazev;
        this.cena = cena;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public double getCena() {
        return cena + cena * DPH;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    abstract String getJednotka();
}
