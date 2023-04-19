
public class Hranol extends Ctverec{ // typo

	// konstruktor se zadanim delky hrany, vysky a materialu
	Hranol(float podstava,float vyska, boolean drevena){
		super(podstava); // super
		zeDreva=drevena;
		this.vyska = vyska;
		pocetHranolu++;
	}
	
	// vypocet objemu hranolu
	float vypoctiObjem() {
		return vypoctiObsah() * vyska; // opraveny vzorec
	}
	
	// nastaveni materialu
	void setDreveny(boolean dreveny){
		zeDreva=dreveny;
	}
	
	// zjisteni materialu
	boolean jeDreveny(){
		return zeDreva;
	}
	
	// zjisteni celkoveho poctu existujicich kostek
	static int getPocetHranolu(){
		return pocetHranolu;
	}
	
	// zjisteni vysky hranolu
	float getVyska(){ // getHrana -> getVyska
		return vyska;
	}
	// nastaveni vysky hranolu
	void setVyska(float vyska){	 // setHrana => setVyska
		this.vyska=vyska;
	}
		
	private boolean zeDreva; // private
	private float vyska; // chybela vyska
	static int pocetHranolu=0; // final -> static
}
