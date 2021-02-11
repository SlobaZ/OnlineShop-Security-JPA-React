package onlineshop.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="kupovina")
public class Kupovina {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column
	private String sifra;
	@Column
	private Double ukupnacena;
	@Column
	private Timestamp datumvreme;
	
	private String datetime;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "user")
	private User user;
	
	
	@OneToMany(mappedBy="kupovina", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Stavka> stavke = new ArrayList<>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public Double getUkupnacena() {
		return ukupnacena;
	}
	public void setUkupnacena(Double ukupnacena) {
		this.ukupnacena = ukupnacena;
	}
	public Timestamp getDatumvreme() {
		return datumvreme;
	}
	public void setDatumvreme(Timestamp datumvreme) {
		this.datumvreme = datumvreme;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
		if(!user.getKupovine().contains(this)){
			user.getKupovine().add(this);
		}
	}
	
	
	public List<Stavka> getStavke() {
		return stavke;
	}
	public void setStavke(List<Stavka> stavke) {
		this.stavke = stavke;
	}
	public void addStavka(Stavka stavka) {
		if(stavka.getKupovina() != this) {
			stavka.setKupovina(this);
		}
		stavke.add(stavka);
	}
	
	
	
	

}
