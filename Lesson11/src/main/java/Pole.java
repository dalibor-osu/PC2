import java.util.Scanner;

public class Pole {

	// Konstruktor s velikosti pole
	Pole(int velikost) {
		mojeHranoly=new Hranol[velikost];
	}

	// vlozeni hranolu do pole na prvni volnou pozici
	void zadejHranol(float vyska, float delka, boolean drevena){
		int idx = -1;
		for (int i = 0; i < mojeHranoly.length; i++) {
			if (mojeHranoly[i] == null) {
				idx = i;
				break;
			}
		}

		if (idx < 0) {
			System.out.println("Pole uz je naplneno");
			return;
		}

		mojeHranoly[idx] = new Hranol(delka, vyska, drevena);
	}

	// vypis objemu vsech hranolu

	//float vypoctiObjem(int idx)
	//{
	//	return mojeHranoly[idx].vypoctiObsah();
	//}

	void vypisObjemy() {
		for (Hranol hranol : mojeHranoly) {
			if (hranol != null) {
				System.out.println(hranol.vypoctiObjem());
			}
		}
	}

	// vypis obsahu podstavy vsech hranolu

	//float vypoctiObsahPodstavy(int idx) {
	//	return mojeHranoly[idx].vypoctiObjem();
	//}

	void vypisObsahyPodstav() {
		for (Hranol hranol : mojeHranoly) {
			if (hranol != null) {
				System.out.println(hranol.vypoctiObsah());
			}
		}
	}

	// nalezeni indexu nejmensiho hranolu
	int najdiNejmensiObjem() {
		float min= Float.MAX_VALUE; // max value
		int idx=0;
		for (int i=0;i<mojeHranoly.length;i++) { // null check, mojeHranoly
			if (mojeHranoly[i] == null) {
				continue;
			}

			if (mojeHranoly[i].vypoctiObjem()<min){
				min=mojeHranoly[i].vypoctiObjem();
				idx=i;
			}
		}

		return idx;
	}

	// zjisteni celkoveho poctu drevenych kostek
	int zjistiPocetDrevenych(){
		int pocetDrevenych=0;
		for (Hranol hranol : mojeHranoly) {
			if (hranol == null) {
				continue;
			}

			if (hranol.jeDreveny()) { // jeDreveny misto promenne
				pocetDrevenych++;
			}
		}
		return pocetDrevenych;
	}

	private Hranol []mojeHranoly;
}
