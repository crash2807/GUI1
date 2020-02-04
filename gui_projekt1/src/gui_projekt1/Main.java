package gui_projekt1;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
	
	private static Scanner wybor;
	private static Scanner wybor2;

	public static void main(String[] args) throws InputMismatchException{
		
		Magazyn mag = new Magazyn();
		mag.dodajOsoby();
		mag.dodajWszystkie();
		
		wybor = new Scanner(System.in);
		int liczba;
		int os = 0;
		
		while(true) {
			System.out.println("Magazyn - zalogowany jako osoba nr: " + os + " - wybierz opcję:\n");
			
			System.out.println("1 - wybór osoby");
			System.out.println("2 - wyświetlenie swoich danych");
			System.out.println("3 - umieszczanie przedmiotów/pojazdów");
			System.out.println("4 - wyjmowanie przedmiotów/pojazdów");
			System.out.println("5 - wyświetlanie wolnych pomieszczeń");
			System.out.println("6 - wynajęcie wolnego pomieszczenia");
			System.out.println("7 - wyświetlanie danych/pomieszczeń/przedmiotów danej osoby");
			System.out.println("8 - zapis stanu magazynu do pliku");
			System.out.println("9 - tworzenie nowych przedmiotów");
			System.out.println("\n0 - wyjście z programu\n");
			
			liczba = wybor.nextInt();

			switch(liczba) {
				default: {
					System.out.println("Niepoprawna opcja, wybierz ponownie");
				};
				break;
				
				case 0: {
					System.out.println("Wyjście z programu, dziękujemy :)");
					System.exit(0);
				};
				break;
				
				case 1: {
					System.out.print("Wpisz identyfikator osoby: ");
					os = wybor.nextInt();
				};
				break;
				
				case 2: {
					if(os !=0) {
						try {
							mag.wyswietlOsobe(os);
						} catch (NeverRentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				};
				break;
				
				case 3: {
					int id_wybranego_pom;
					int id_wybranego_przedmiotu;
					System.out.println("Wybierz id pomieszczenia do którego chcesz dodać przedmiot: ");
					wybor2 = new Scanner(System.in);
					id_wybranego_pom = wybor2.nextInt();
					System.out.println("Wybierz id przedmiotu który chcesz umieścić: ");
					wybor2 = new Scanner(System.in);
					id_wybranego_przedmiotu = wybor2.nextInt();
					try {
						mag.dodajPrzedmiot(os, id_wybranego_pom, id_wybranego_przedmiotu);
					} catch (TooManyThingsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				};
				break;
				
				case 4: {
					int id_wybranego_pom;
					int id_wybranego_przedmiotu;
					System.out.println("Wybierz id pomieszczenia z którego chcesz wyjąć przedmiot: ");
					wybor2 = new Scanner(System.in);
					id_wybranego_pom = wybor2.nextInt();
					System.out.println("Wybierz id przedmiotu który chcesz wyjąć: ");
					wybor2 = new Scanner(System.in);
					id_wybranego_przedmiotu = wybor2.nextInt();
					mag.wyjmijPrzedmiot(os, id_wybranego_pom, id_wybranego_przedmiotu);
				};
				break;
				
				case 5: {
					mag.czySaWolne();
				};
				break;
				
				case 6: {
					if(os !=0) {
						int id_pom;
						System.out.println("Wpisz id pomieszczenia, które chcesz wynająć: ");
						wybor2 = new Scanner(System.in);
						id_pom = wybor2.nextInt();
						mag.wynajmij(os, id_pom);
						if(mag.osoby.get(os-1).datapierwszego == null) {
							System.out.println("Pierwsze wypożyczenie, wprowadź datę: ");
							wybor2 = new Scanner(System.in);
							mag.osoby.get(os-1).datapierwszego = wybor2.next();
						}
					}
				};
				break;
				
				case 7: {
					int idwybranej = 0;
					System.out.println("Wybierz id osoby do sprawdzenia: ");
					wybor2 = new Scanner(System.in);
					idwybranej = wybor2.nextInt();
					
					if(idwybranej != 0  && idwybranej <= mag.osoby.size()) {
						try {
							mag.wyswietlOsobe(idwybranej);
						} catch (NeverRentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						System.out.println("Zły identyfikator wybranej osoby");
					}
				};
				break;
				
				case 8: {
					mag.zapiszStan();
				};
				break;
				
				case 9: {
					mag.tworzeniePrzedmiotow();
				};
				break;
				
			}
		}
	}
}
