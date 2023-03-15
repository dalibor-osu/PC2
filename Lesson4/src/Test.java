public class Test {
    public static void main(String[] args) {
        Zbozi[] zbozi = {
                new Potravina("Rohlík", 1.5, 1),
                new Naradi("Kleště", 278.0, 24),
                new Potravina("Chleba", 20.8, 6),
                new Potravina("Jablko", 51.0, 20)
        };

        for (Zbozi prvek : zbozi) {
            if (Potravina.class.isAssignableFrom(prvek.getClass())) {
                Potravina potravina = (Potravina)prvek;
                System.out.println(potravina.getNazev() + ", cena: " + potravina.getCena() + ", trvanlivost: " + potravina.getTrvanlivost());
            } else {
                System.out.println(prvek.getNazev() + ", cena: " + prvek.getCena());
            }
        }
    }
}