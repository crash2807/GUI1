package gui_projekt1;

public class Motocykl extends Pojazd {

	boolean homologacja;
	
	public Motocykl(String nazwa, double objetosc, boolean homologacja) {
		super(nazwa, objetosc);
		this.homologacja = homologacja;
	}
	
	
	public String toString() {
		return "Nazwa: " + nazwa + "\nObjetosc: " + objetosc + "\nHomologacja: " + homologacja;
	}
}
