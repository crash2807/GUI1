package gui_projekt1;

public class Rower extends Pojazd {

	int przerzutki;
	
	public Rower(String nazwa, double objetosc, int przerzutki) {
		super(nazwa, objetosc);
		this.przerzutki = przerzutki;
	}
	
	public String toString() {
		return "Nazwa: " + nazwa + "\nObjetosc: " + objetosc + "\nLiczba przerzutek: " + przerzutki;
	}
	
}
