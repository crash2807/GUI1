package gui_projekt1;

import java.util.ArrayList;

public class Pomieszczenie {
	
	ArrayList<Przedmiot> listaPrzedmiotow = new ArrayList<Przedmiot>();
	static int ilosc_pom = 0;
	int id;
	double objetosc;
	double dl;
	double szer;
	double wys;
	double wolnemiejsce;
	boolean wolne;
	boolean wylaczone;
	
	public Pomieszczenie(double objetosc) {
		this.id = ilosc_pom + 1;
		ilosc_pom++;
		this.objetosc = objetosc;
		this.wolnemiejsce = objetosc;
		wolne = true;
		wylaczone = false;
	}
	
	public Pomieszczenie(double dl, double szer, double wys) {
		this.id = ilosc_pom + 1;
		ilosc_pom++;
		this.dl = dl;
		this.szer = szer;
		this.wys = wys;
		objetosc = dl * szer * wys;
		this.wolnemiejsce = objetosc;
		wolne = true;
		wylaczone = false;
	}
	
	public String toString() {
		return "Id: " + id + "\nObjętość: " + objetosc + "\nWolne miejsce: " + wolnemiejsce + "\n";
	}
	
	public void czytajListePrzedmiotow() {
		for(int i = 0; i < listaPrzedmiotow.size(); i++) {
			System.out.println(listaPrzedmiotow.get(i));
		}
	}
	
}
