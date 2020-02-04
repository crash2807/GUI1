package gui_projekt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Magazyn {
	
	private Scanner wybor;
	private Scanner wybor2;
	
	ArrayList<Pomieszczenie> wszystkie = new ArrayList<Pomieszczenie>();
	ArrayList<Osoba> osoby = new ArrayList<Osoba>();
	ArrayList<Przedmiot> przedmioty = new ArrayList<Przedmiot>();
	
	Osoba o1 = new Osoba("Jan1", "Kowalski", 12345678901L, "adres", "20-03-1989");
	Osoba o2 = new Osoba("Jan2", "Kowalski", 12345678901L, "adres", "20-03-1989");
	Osoba o3 = new Osoba("Jan3", "Kowalski", 12345678901L, "adres", "20-03-1989");
	Osoba o4 = new Osoba("Jan4", "Kowalski", 12345678901L, "adres", "20-03-1989");
	Osoba o5 = new Osoba("Jan5", "Kowalski", 12345678901L, "adres", "20-03-1989");
	
	Pomieszczenie a = new Pomieszczenie(50.7);
	Pomieszczenie b = new Pomieszczenie(4.2, 5.0, 4.6);
	Pomieszczenie c = new Pomieszczenie(100);
	Pomieszczenie d = new Pomieszczenie(50.4);
	Pomieszczenie e = new Pomieszczenie(10.2, 5.6, 3.9);
	
	public void dodajOsoby() {
		osoby.add(o1);
		osoby.add(o2);
		osoby.add(o3);
		osoby.add(o4);
		osoby.add(o5);
	}
	
	public void wyswietlOsobe(int id) throws NeverRentException {
		System.out.println(osoby.get(id-1));
		osoby.get(id-1).czytajDatePierwszego();
		osoby.get(id-1).czytajListePomieszczen();
	}
	
	public void dodajWszystkie() {
		wszystkie.add((a.id-1), a);
		wszystkie.add((b.id-1), b);
		wszystkie.add((c.id-1), c);
		wszystkie.add((d.id-1), d);
		wszystkie.add((e.id-1), e);
	}
	
	public void czySaWolne() {
		int counter = 0;
		for(int i = 0; i < wszystkie.size(); i++) {
			if(wszystkie.get(i).wolne == true && wszystkie.get(i).wylaczone == false) {
				System.out.println(wszystkie.get(i));
				counter++;
			}
		}
		if(counter == 0) {
			System.out.println("Nie ma wolnych pomieszczeń");
		}
	}
	
	public void wynajmij(int id_os, int id_pom) {
		wszystkie.get(id_pom-1).wolne = false;
		osoby.get(id_os-1).wynajete.add(wszystkie.get(id_pom-1));
	}
	
	public void dodajPrzedmiot(int id_os, int id_pom, int id_przedmiotu) throws TooManyThingsException{
		int glowny_id;
		glowny_id = wszystkie.get(id_pom-1).id;
		for(int i = 0; i < osoby.get(id_os-1).wynajete.size(); i++) {
			if(glowny_id == osoby.get(id_os-1).wynajete.get(i).id) {
				if(osoby.get(id_os-1).wynajete.get(i).wolnemiejsce >= przedmioty.get(id_przedmiotu-1).objetosc) {
					osoby.get(id_os-1).wynajete.get(i).listaPrzedmiotow.add(przedmioty.get(id_przedmiotu-1));
					osoby.get(id_os-1).wynajete.get(i).wolnemiejsce -= przedmioty.get(id_przedmiotu-1).objetosc;
				} else {
					throw new TooManyThingsException("Za dużo rzeczy/brak miejsca\n");
				}
			}
		}
	}
	
	public void wyjmijPrzedmiot(int id_os, int id_pom, int id_przedmiotu) {
		int glowny_id;
		glowny_id = wszystkie.get(id_pom-1).id;
		for(int i = 0; i < osoby.get(id_os-1).wynajete.size(); i++) {
			if(glowny_id == osoby.get(id_os-1).wynajete.get(i).id) {
				if(!(osoby.get(id_os-1).wynajete.get(i).listaPrzedmiotow.isEmpty())) {
					osoby.get(id_os-1).wynajete.get(i).listaPrzedmiotow.remove(przedmioty.get(id_przedmiotu-1));
					osoby.get(id_os-1).wynajete.get(i).wolnemiejsce += przedmioty.get(id_przedmiotu-1).objetosc;
				} else {
					System.out.println("Pomieszczenie puste, nie ma co wyjąć");
				}
			}
		}
	}
	
	public void zapiszStan(){
		byte[] tab;
		String info;
		File plik = new File("stan.txt");
		try {
			FileOutputStream fos = new FileOutputStream(plik);
			
			info = "Pomieszczenia:\n\n";
			tab = info.getBytes();
			fos.write(tab);
			
			for(int i = 0; i < wszystkie.size(); i++) {
				tab = wszystkie.get(i).toString().getBytes();
				fos.write(tab);
			}
			
			info = "Osoby:\n\n";
			tab = info.getBytes();
			fos.write(tab);
			
			for(int i = 0; i < osoby.size(); i++) {
				info = "Osoba " + (i+1) + "\n\n";
				tab = info.getBytes();
				fos.write(tab);
				
				tab = osoby.get(i).toString().getBytes();
				fos.write(tab);
				 
				for(int j = 0; j < osoby.get(i).wynajete.size(); j++) {
					info = "Pomieszczenie " + (j+1) + " osoby " + (i+1) +"\n\n";
					tab = info.getBytes();
					fos.write(tab);
					
					tab = osoby.get(i).wynajete.get(j).toString().getBytes();
					fos.write(tab);
						
					for(int k = 0; k < osoby.get(i).wynajete.get(j).listaPrzedmiotow.size(); k++) {
						info = "Przedmiot " + (k+1) + " w pomieszczeniu " + (j+1) + " osoby " + (i+1) + "\n\n";
						tab = info.getBytes();
						fos.write(tab);
						
						tab = osoby.get(i).wynajete.get(j).listaPrzedmiotow.get(k).toString().getBytes();
						fos.write(tab);
					}
				}
			}
			fos.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void tworzeniePrzedmiotow() {
		wybor = new Scanner(System.in);
		int liczba;
		int podwybor;
		
		String nazwa, silnik;
		double dl, szer, wys, objetosc;
		int przerzutki;
		boolean homologacja;
		
		System.out.println("Tworzenie przedmiotów - wybierz opcję:\n");
		System.out.println("1 - samochód");
		System.out.println("2 - motocykl");
		System.out.println("3 - rower");
		System.out.println("4 - inny przedmiot");
		System.out.println("0 - powrót do menu głównego\n");
		
		liczba = wybor.nextInt();
		
		switch(liczba) {
			case 0: {
				break;
			}
			
			case 1: {
				System.out.println("1 - po objętości\n2 - po wymiarach");
				wybor2 = new Scanner(System.in);
				podwybor = wybor2.nextInt();
				if(podwybor == 1) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, objętość, typ silnika");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					objetosc = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					silnik = wybor2.next();
					Przedmiot s = new Samochod(nazwa, objetosc, silnik);
					przedmioty.add(s.id-1, s);
					System.out.println("Samochód dodany, identyfikator przedmiotu: " + s.id);
					
				} else if(podwybor ==2) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, długość, szerokość, wysokość, typ silnika (ułamki wpisujemy z przecinkiem)");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					dl = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					szer = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					wys = wybor2.nextDouble();
					objetosc = dl*szer*wys;
					wybor2 = new Scanner(System.in);
					silnik = wybor2.next();
					Przedmiot s = new Samochod(nazwa, objetosc, silnik);
					przedmioty.add(s.id-1, s);
					System.out.println("Samochód dodany, identyfikator przedmiotu: " + s.id);
				}
			};
			break;
			
			case 2: {
				System.out.println("1 - po objętości\n2 - po wymiarach");
				wybor2 = new Scanner(System.in);
				podwybor = wybor2.nextInt();
				if(podwybor == 1) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, objętość, homologacja (Tak - wpisz 'true' / Nie - wpisz 'false')");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					objetosc = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					homologacja = wybor2.nextBoolean();
					Przedmiot m = new Motocykl(nazwa, objetosc, homologacja);
					przedmioty.add(m.id-1, m);
					System.out.println("Motocykl dodany, identyfikator przedmiotu: " + m.id);
					
				} else if(podwybor ==2) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, długość, szerokość, wysokość (ułamki wpisujemy z przecinkiem), homologacja (Tak - wpisz 'true' / Nie - wpisz 'false')");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					dl = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					szer = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					wys = wybor2.nextDouble();
					objetosc = dl*szer*wys;
					wybor2 = new Scanner(System.in);
					homologacja = wybor2.nextBoolean();
					Przedmiot m = new Motocykl(nazwa, objetosc, homologacja);
					przedmioty.add(m.id-1, m);
					System.out.println("Motocykl dodany, identyfikator przedmiotu: " + m.id);
				}
			};
			break;
			
			case 3: {
				System.out.println("1 - po objętości\n2 - po wymiarach");
				wybor2 = new Scanner(System.in);
				podwybor = wybor2.nextInt();
				if(podwybor == 1) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, objętość, ilość przerzutek");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					objetosc = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					przerzutki = wybor2.nextInt();
					Przedmiot r = new Rower(nazwa, objetosc, przerzutki);
					przedmioty.add(r.id-1, r);
					System.out.println("Rower dodany, identyfikator przedmiotu: " + r.id);
					
				} else if(podwybor ==2) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, długość, szerokość, wysokość (ułamki wpisujemy z przecinkiem), ilość przerzutek");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					dl = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					szer = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					wys = wybor2.nextDouble();
					objetosc = dl*szer*wys;
					wybor2 = new Scanner(System.in);
					przerzutki = wybor2.nextInt();
					Przedmiot r = new Rower(nazwa, objetosc, przerzutki);
					przedmioty.add(r.id-1, r);
					System.out.println("Motocykl dodany, identyfikator przedmiotu: " + r.id);
				}
			};
			break;
			
			case 4: {
				System.out.println("1 - po objętości\n2 - po wymiarach");
				wybor2 = new Scanner(System.in);
				podwybor = wybor2.nextInt();
				if(podwybor == 1) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, objętość");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					objetosc = wybor2.nextDouble();
					Przedmiot p = new Przedmiot(nazwa, objetosc);
					przedmioty.add(p.id-1, p);
					System.out.println("Przedmiot dodany, identyfikator przedmiotu: " + p.id);
					
				} else if(podwybor ==2) {
					System.out.println("Wpisz kolejno potwierdzając enterem: Nazwę, długość, szerokość, wysokość (ułamki wpisujemy z przecinkiem)");
					wybor2 = new Scanner(System.in);
					nazwa = wybor2.next();
					wybor2 = new Scanner(System.in);
					dl = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					szer = wybor2.nextDouble();
					wybor2 = new Scanner(System.in);
					wys = wybor2.nextDouble();
					Przedmiot p = new Przedmiot(nazwa, dl, szer, wys);
					przedmioty.add(p.id-1, p);
					System.out.println("Przedmiot dodany, identyfikator przedmiotu: " + p.id);
				}
			};
			break;
			
		}
	}
	
}