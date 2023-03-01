// Třída předmětu BPC1
public class BPC1 implements Predmet {
    // Promněnné pro jednotlivé oblasti bodů
    private int bodyCviceni = 0;
    private int bodyZkouska = 0;

    // Konstanty pro maximální počet bodů z každé oblasti
    private final int maxBodyCviceni = 20;
    private final int maxBodyZkouska = 80;

    // Implemetace metod rozhraní Predmet
    @Override
    public String getNazev() {
        return "BPC1";
    }

    @Override
    public int getBody() {
        return bodyZkouska + bodyCviceni;
    }

    @Override
    public boolean getZapocet() {
        return bodyCviceni >= minimalniBody;
    }

    // Metoda pro udělení bodů za jednotlivá cvičení
    public void udelBodyZaCviceni(int body) {
        if (bodyCviceni + body > maxBodyCviceni) {
            System.out.println("Body nebyly udeleny, protoze by prekracovaly maximalni moznou hodnotu (" + (bodyCviceni + body) + "/" + maxBodyCviceni + ").");
            return;
        }

        bodyCviceni += body; // Body se přičítají, jelikož může být metoda volána víckrát pro každé cvičení
    }

    // Metoda pro udělení bodů za zkoušku
    public void udelBodyZaZkousku(int body) {
        if (body > maxBodyZkouska) {
            System.out.println("Body nebyly udeleny, protoze prekracujou maximalni moznou hodnotu (" + body + "/" + maxBodyZkouska + ").");
            return;
        }

        bodyZkouska = body;
    }
}
