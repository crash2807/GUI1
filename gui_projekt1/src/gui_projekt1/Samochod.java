package gui_projekt1;

public class Samochod extends Pojazd {

	String silnik;
	
	public Samochod(String nazwa, double objetosc, String silnik) {
		super(nazwa, objetosc);
		this.silnik = silnik;
	}

	public String toString() {
		return "Nazwa: " + nazwa + "\nObjetosc: " + objetosc + "\nTyp silnika: " + silnik;
	}
	
}
