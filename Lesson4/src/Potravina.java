public class Potravina extends Zbozi {
    private int trvanlivost;

    public Potravina(String nazev, double cena, int trvanlivost) {
        super(nazev, cena);
        this.trvanlivost = trvanlivost;
    }

    public int getTrvanlivost() {
        return trvanlivost;
    }

    public void setTrvanlivost(int trvanlivost) {
        this.trvanlivost = trvanlivost;
    }

    @Override
    String getJednotka() {
        return "dnů";
    }
}
