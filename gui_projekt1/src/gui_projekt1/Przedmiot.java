package gui_projekt1;

public class Przedmiot {
	static int ilosc_przedmiotow = 0;
	int id;
	
	String nazwa;
	double objetosc;
	double dl;
	double szer;
	double wys;
	
	public Przedmiot(String nazwa, double objetosc) {
		this.id = ilosc_przedmiotow + 1;
		ilosc_przedmiotow++;
		this.nazwa = nazwa;
		this.objetosc = objetosc;
	}
	public Przedmiot(String nazwa, double dl, double szer, double wys) {
		this.id = ilosc_przedmiotow + 1;
		ilosc_przedmiotow++;
		this.nazwa = nazwa;
		this.dl = dl;
		this.szer = szer;
		this.wys = wys;
		objetosc = dl * szer * wys;
	}
	
	public String toString() {
		return "Nazwa: " + nazwa + "\nObjetosc: " + objetosc;
	}
	
}
