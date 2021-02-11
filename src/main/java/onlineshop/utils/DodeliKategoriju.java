package onlineshop.utils;

import onlineshop.models.Kategorija;
import onlineshop.models.Proizvod;

public class DodeliKategoriju {
	
	public Proizvod dodeli(Proizvod proizvod) {
		
		if(proizvod.getNaziv().equalsIgnoreCase("Televizor") || proizvod.getNaziv().equalsIgnoreCase("TV")) {
			proizvod.setKategorija(Kategorija.VIDEO);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Telefon") || proizvod.getNaziv().equalsIgnoreCase("Mobilni Telefon")) {
			proizvod.setKategorija(Kategorija.TELEFONI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Frizider") || proizvod.getNaziv().equalsIgnoreCase("Zamrzivac")) {
			proizvod.setKategorija(Kategorija.BELA_TEHNIKA);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Ves masina") || proizvod.getNaziv().equalsIgnoreCase("Vesmasina")) {
			proizvod.setKategorija(Kategorija.BELA_TEHNIKA);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Usisivac")) {
			proizvod.setKategorija(Kategorija.MALI_KUCNI_APARATI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Pegla")) {
			proizvod.setKategorija(Kategorija.MALI_KUCNI_APARATI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Sporet")) {
			proizvod.setKategorija(Kategorija.BELA_TEHNIKA);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Fen")) {
			proizvod.setKategorija(Kategorija.MALI_KUCNI_APARATI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Foto aparat") || proizvod.getNaziv().equalsIgnoreCase("Fotoaparat")) {
			proizvod.setKategorija(Kategorija.VIDEO);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Kamera")) {
			proizvod.setKategorija(Kategorija.VIDEO);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Kompjuter") || proizvod.getNaziv().equalsIgnoreCase("Racunar") || proizvod.getNaziv().equalsIgnoreCase("Desktop")) {
			proizvod.setKategorija(Kategorija.RACUNARI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Lap top") || proizvod.getNaziv().equalsIgnoreCase("Laptop")) {
			proizvod.setKategorija(Kategorija.RACUNARI);
		}
		else if(proizvod.getNaziv().equals("Mikrotalasna")) {
			proizvod.setKategorija(Kategorija.KUHINJSKI_APARATI);
		}
		else if(proizvod.getNaziv().equals("Mikser")) {
			proizvod.setKategorija(Kategorija.KUHINJSKI_APARATI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Muzicki stub") || proizvod.getNaziv().equalsIgnoreCase("Muzicka linija")) {
			proizvod.setKategorija(Kategorija.AUDIO);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Sat")) {
			proizvod.setKategorija(Kategorija.MALI_KUCNI_APARATI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Tablet")) {
			proizvod.setKategorija(Kategorija.RACUNARI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Toster")) {
			proizvod.setKategorija(Kategorija.KUHINJSKI_APARATI);
		}
		else if(proizvod.getNaziv().equalsIgnoreCase("Ventilator")) {
			proizvod.setKategorija(Kategorija.MALI_KUCNI_APARATI);
		}
		else {
			proizvod.setKategorija(Kategorija.TEHNIKA);
		}
		return proizvod;
	}


}
