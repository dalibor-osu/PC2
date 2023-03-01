// Třída pro předmětu BPC2
public class BPC2 implements Predmet {
    // Promněnné pro jednotlivé oblasti bodů
    private int bodyProjekt = 0;
    private int bodyPulsemestralka = 0;
    private int bodyZkouska = 0;

    // Konstanty pro maximální počet bodů z každé oblasti
    private final int maxBodyProjekt = 30;
    private final int maxBodyPulsemestralka = 20;
    private final int maxBodyZkouska = 50;

    // Implemetace metod rozhraní Predmet
    @Override
    public String getNazev() {
        return "BPC2";
    }

    @Override
    public int getBody() {
        return bodyProjekt + bodyZkouska + bodyPulsemestralka;
    }

    @Override
    public boolean getZapocet() {
        return bodyPulsemestralka + bodyProjekt >= minimalniBody; // Pro ověření můžeme použít konstantu minimalniBody z rozhraní Predmet
    }

    // Metoda pro udělení bodů za projekt
    public void udelBodyProjekt(int body) {
        if (body > maxBodyProjekt) {
            System.out.println("Body nebyly udeleny, protoze prekracuji maximalni moznou hodnotu (" + body + "/" + maxBodyProjekt + ").");
            return;
        }

        bodyProjekt = body;
    }

    // Metoda pro udělení bodů za půlsemestrální zkoušku
    public void udelBodyPulsemestralka(int body) {
        if (body > maxBodyPulsemestralka) {
            System.out.println("Body nebyly udeleny, protoze prekracuji maximalni moznou hodnotu (" + body + "/" + maxBodyPulsemestralka + ").");
            return;
        }

        bodyPulsemestralka = body;
    }

    // Metoda pro udělení bodů za zkoušku
    public void udelBodyZkouska(int body) {
        if (body > maxBodyZkouska) {
            System.out.println("Body nebyly udeleny, protoze prekracuji maximalni moznou hodnotu (" + body + "/" + maxBodyZkouska + ").");
            return;
        }

        bodyZkouska = body;
    }
}
