class Ctverec {

	protected float hrana; // protected misto private
	// vypocet obsahu ctverce
	float vypoctiObsah(){ // int => float
		return hrana*hrana;
	}
	// zjisteni delky hrany ctverce
	float getHrana(){					
		return hrana;
	}
	// nastaveni delky hrany ctverce
	void setHrana(float delka){			
		hrana=delka;
	}
	// konstruktor se zadanim delky hrany ctverce
	Ctverec(float hrana){
		this.hrana=hrana; // this
	}
}
