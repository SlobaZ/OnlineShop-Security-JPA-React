package onlineshop.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name="proizvod")
public class Proizvod {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	@Column(nullable=false)
	@NotEmpty(message = "*Please provide a naziv")
	private String naziv;
	@Column(nullable=false)
	@NotEmpty(message = "*Please provide a marka")
	private String marka;
	@Column(nullable=false)
	@PositiveOrZero(message = "*Only positive number")
	private Integer kolicina;
	@Column(nullable=false)
	@Positive(message = "*Only positive number")
	private Double cena;
	@Column
	private String photo;
	
	@Enumerated(EnumType.STRING)
	private Kategorija kategorija;
	
	@OneToMany(mappedBy="proizvod", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Stavka> stavke = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	public Double getCena() {
		return cena;
	}
	public void setCena(Double cena) {
		this.cena = cena;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	public List<Stavka> getStavke() {
		return stavke;
	}
	public void setStavke(List<Stavka> stavke) {
		this.stavke = stavke;
	}
	public void addStavka(Stavka stavka) {
		if(stavka.getProizvod() != this) {
			stavka.setProizvod(this);
		}
		stavke.add(stavka);
	}
	
	
	public Kategorija getKategorija() {
		return kategorija;
	}
	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	
	
	
	


	
	
	
	
	

}
