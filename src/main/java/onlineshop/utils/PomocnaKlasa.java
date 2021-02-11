package onlineshop.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PomocnaKlasa {
	
	
	public static SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
	
	// PUTANJA DO FAJLA (u folderu images) ZA SLIKE
	public static String vratiRelativnuPutanjuDoFajla(String fileName) {
		String separatorPutanje = System.getProperty("file.separator");
		String relativnaPutanja = "." + separatorPutanje + "images" + separatorPutanje + fileName;
			return relativnaPutanja;
		}
	

	// Upisi sadasnji Sql datum i vreme 
	public static java.sql.Timestamp  UpisiSadasnjiDatumIVremeSql() {
		Date date = new Date();  
        Timestamp datumIvremeSada=new Timestamp(date.getTime());     
	  return datumIvremeSada;
	}
	
	
	// Konvertuj Sql datum i vreme u String
	public static String  PrikaziTekstualnoDatumIVreme(Timestamp datumIvreme) {
		String tekst = DATE_TIME_FORMAT.format(datumIvreme);
		// String tekst = datumIvreme.toString(); 
		return tekst;
	}
	
	
	// Konvertuj String u Sql datum i vreme
	public static java.sql.Timestamp  KonvertujStringUSqlDatumIVreme(String tekst){
		java.sql.Timestamp datumIvreme = null;
		try {
			Date date = (Date) DATE_TIME_FORMAT.parse(tekst);
			datumIvreme = new Timestamp(date.getTime());
			return datumIvreme;
		} 
		catch (Exception ex) {
			System.out.println("GRESKA");
		}

		return datumIvreme;
	}
	
	
	// Racunanje broja dana
	public static double BrojDana(String ulaz, String izlaz) {
		Date Datum1 = null;
		Date Datum2 = null;
		try {
			Datum1 = DATE_TIME_FORMAT.parse(ulaz);
			Datum2 = DATE_TIME_FORMAT.parse(izlaz);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		long pocetak = Datum1.getTime();
		long kraj = Datum2.getTime();
		long razlika = kraj - pocetak;
		int brojaDana = (int)(razlika/(1000 * 60 * 60 * 24));
		
		return (double)(brojaDana );
    
  }
	

	
	public static String DodeliSifru() {
		Random r = new Random();
		int min = 100;
		int max = 999;
		
		char randomCharLetter;
		int randomNumber;
		randomCharLetter = (char) (r.nextInt(26) + 'A');
		randomNumber = (int) ((Math.random() * (max - min)) + min);

		if(randomCharLetter=='\u0000' || randomNumber==0 ) {
			randomCharLetter = (char) (r.nextInt(26) + 'A');
			randomNumber = (int) ((Math.random() * (max - min)) + min);
		}
		
		String sifra = randomCharLetter + " " + randomNumber;
		return sifra;
		
	}
	
	
	//  Priprema Stringa za Enum
	public static String PripremiStringZaEnum(String tekst) {
		String velikaSlova = tekst.toUpperCase();
		String ukloniVisakPraznihMesta = velikaSlova.trim();
		String pripremljenString = ukloniVisakPraznihMesta.replace(' ', '_');
		return pripremljenString;	
	}
	

	
}
