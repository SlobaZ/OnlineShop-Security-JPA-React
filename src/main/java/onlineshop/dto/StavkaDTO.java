package onlineshop.dto;

import java.sql.Timestamp;

public class StavkaDTO {
	
	private Integer id;
	private Integer kolicinastavke;
	private Double cenastavke;
	
	
	private Integer kupovinaId;
	private String kupovinaSifra;
	private Double kupovinaUkupnacena;
	private Timestamp kupovinaDatumvreme;
	private String kupovinaDatetime;
	
	
	private Integer proizvodId;
	private String proizvodNaziv;
	private Integer proizvodKolicina;
	private Double proizvodCena;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKolicinastavke() {
		return kolicinastavke;
	}
	public void setKolicinastavke(Integer kolicinastavke) {
		this.kolicinastavke = kolicinastavke;
	}
	public Double getCenastavke() {
		return cenastavke;
	}
	public void setCenastavke(Double cenastavke) {
		this.cenastavke = cenastavke;
	}
	
	
	
	
	
	public Integer getKupovinaId() {
		return kupovinaId;
	}
	public void setKupovinaId(Integer kupovinaId) {
		this.kupovinaId = kupovinaId;
	}
	public String getKupovinaSifra() {
		return kupovinaSifra;
	}
	public void setKupovinaSifra(String kupovinaSifra) {
		this.kupovinaSifra = kupovinaSifra;
	}
	public Double getKupovinaUkupnacena() {
		return kupovinaUkupnacena;
	}
	public void setKupovinaUkupnacena(Double kupovinaUkupnacena) {
		this.kupovinaUkupnacena = kupovinaUkupnacena;
	}
	public Timestamp getKupovinaDatumvreme() {
		return kupovinaDatumvreme;
	}
	public void setKupovinaDatumvreme(Timestamp kupovinaDatumvreme) {
		this.kupovinaDatumvreme = kupovinaDatumvreme;
	}
	public String getKupovinaDatetime() {
		return kupovinaDatetime;
	}
	public void setKupovinaDatetime(String kupovinaDatetime) {
		this.kupovinaDatetime = kupovinaDatetime;
	}
	
	
	
	
	
	public Integer getProizvodId() {
		return proizvodId;
	}
	public void setProizvodId(Integer proizvodId) {
		this.proizvodId = proizvodId;
	}
	public String getProizvodNaziv() {
		return proizvodNaziv;
	}
	public void setProizvodNaziv(String proizvodNaziv) {
		this.proizvodNaziv = proizvodNaziv;
	}
	public Integer getProizvodKolicina() {
		return proizvodKolicina;
	}
	public void setProizvodKolicina(Integer proizvodKolicina) {
		this.proizvodKolicina = proizvodKolicina;
	}
	public Double getProizvodCena() {
		return proizvodCena;
	}
	public void setProizvodCena(Double proizvodCena) {
		this.proizvodCena = proizvodCena;
	}
	
	
	
	
	

}
