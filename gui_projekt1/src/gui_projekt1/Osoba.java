package gui_projekt1;

import java.util.ArrayList;

public class Osoba {
	static int idosoby = 0;
	static int iloscosob = 0;
	String imie;
	String nazwisko;
	long pesel;
	String adres;
	String dataur;
	String datapierwszego;
	ArrayList<Pomieszczenie> wynajete = new ArrayList<Pomieszczenie>();
	
	public Osoba(String imie, String nazwisko, long pesel, String adres, String dataur) {
		idosoby++;
		iloscosob++;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.adres = adres;
		this.dataur = dataur;
		datapierwszego = null;
	}
	
	public String toString() {
		return "Imie™: " + imie + "\nNazwisko: " + nazwisko + "\nPESEL: " + pesel + "\nAdres: " + adres + "\nData Urodzenia: " + dataur + "\n";
	}
	
	public void czytajListePomieszczen() {
		for(int i = 0; i < wynajete.size(); i++) {
			System.out.println(wynajete.get(i));
			wynajete.get(i).czytajListePrzedmiotow();
		}
	}
	
	public void czytajDatePierwszego() throws NeverRentException {
		if(datapierwszego == null) {
			throw new NeverRentException("Osoba nigdy nic nie wynajela\n");
		} else {
			System.out.println("Data pierwszego wypoÅ¼yczenia: " + datapierwszego + "\n");
		}
	}
	
}