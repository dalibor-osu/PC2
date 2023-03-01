// Třída předmětu BPIS
public class BPIS implements Predmet {
    private boolean zapocet = false; // Promněnná pro zápočet

    // Implementace metod rozhraní Predmet
    @Override
    public String getNazev() {
        return "BPIS";
    }

    @Override
    public int getBody() {
        return 0;
    }

    @Override
    public boolean getZapocet() {
        return zapocet;
    }

    // Metoda pro udělení zápočtu
    public void udelZapocet() {
        zapocet = true;
    }
}
