import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Predmet[] predmety = { new BPC1(), new BPC2(), new BPIS() }; // Pole s předměty
        Scanner scanner = new Scanner(System.in);

        // Jednotlivé metody pro nastavení hodnot v každém předmětu (pro větší přehlednost)
        zadejHodnotyBPC1((BPC1) predmety[0], scanner);
        zadejHodnotyBPC2((BPC2) predmety[1], scanner);
        zadejHodnotyBPIS((BPIS) predmety[2], scanner);

        // Vypsání názvu a stavu zápočtu pro každý předmět
        for (Predmet predmet : predmety) {
            System.out.println("Zapocet z predmetu " + predmet.getNazev() + ": " + (predmet.getZapocet() ? ("udelen " + "(" + predmet.getBody() + '/' + 100 + ")") : "neudelen "));
        }
    }

    // Nastavení hodnot předmětu BPIS (pouze udělení / neudělení zápočtu)
    private static void zadejHodnotyBPIS(BPIS predmet, Scanner scanner)
    {
        System.out.println("Zadejte udeleni zapoctu z predmetu BPIS (0 pro neudeleni, 1 pro udeleni:");
        if (scanner.nextInt() == 1)
            predmet.udelZapocet();

        scanner.nextLine();
    }

    // Nastavení hodnot pro předmět BPC2
    private static void zadejHodnotyBPC2(BPC2 predmet, Scanner scanner)
    {
        System.out.println("Zadejte body za projekt z BPC2:");
        predmet.udelBodyProjekt(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Zadejte body za pulsemestralku z BPC2:");
        predmet.udelBodyPulsemestralka(scanner.nextInt());
        scanner.nextLine();

        // Body ze zkoušky lze přidělit pouze pokud je splněn zápočet
        if (predmet.getZapocet()) {
            System.out.println("Zadejte body za zkousku z BPC2:");
            predmet.udelBodyZkouska(scanner.nextInt());
            scanner.nextLine();
        }
    }

    // Nastavení hodnot pro předmět BPC1
    private static void zadejHodnotyBPC1(BPC1 predmet, Scanner scanner)
    {
        System.out.println("Zadejte body za cviceni 1 z BPC1:");
        predmet.udelBodyZaCviceni(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Zadejte body za cviceni 2 z BPC1:");
        predmet.udelBodyZaCviceni(scanner.nextInt());
        scanner.nextLine();

        if (predmet.getZapocet()) {
            System.out.println("Zadejte body za zkousku z BPC1:");
            predmet.udelBodyZaZkousku(scanner.nextInt());
            scanner.nextLine();
        }
    }
}