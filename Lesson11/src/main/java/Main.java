public class Main {
    public static void main(String[] args) {
        Pole pole = new Pole(3);

        pole.zadejHranol(23, 43, false);
        pole.zadejHranol(25, 52, false);
        pole.zadejHranol(1, 2, true);
        pole.zadejHranol(235, 34, true);
        System.out.println("Nejmensi objem: " + pole.najdiNejmensiObjem());
        System.out.println("Pocet drevenych: " + pole.zjistiPocetDrevenych());
        pole.vypisObjemy();
        pole.vypisObsahyPodstav();

        Pole pole2 = new Pole(5);
        pole2.zadejHranol(43, 53, true);
        pole2.zadejHranol(43, 43, false);
        System.out.println("Nejmensi objem: " + pole2.najdiNejmensiObjem());
        System.out.println("Pocet drevenych: " + pole2.najdiNejmensiObjem());
        pole2.vypisObjemy();
        pole2.vypisObsahyPodstav();
    }
}