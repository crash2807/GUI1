package gui_projekt1;

public abstract class Pojazd extends Przedmiot {

	public Pojazd(String nazwa, double objetosc) {
		super(nazwa, objetosc);
	}
	
	public void zdejmijOpony() {
		objetosc=objetosc/2;
	}
	public void zalozOpony() {
		objetosc=objetosc*2;
	}
	
	
}
