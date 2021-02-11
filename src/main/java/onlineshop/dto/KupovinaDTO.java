package onlineshop.dto;

import java.sql.Timestamp;


public class KupovinaDTO {
	
	private Integer id;
	private String sifra;
	private Double ukupnacena;
	private Timestamp datumvreme;
	private String datetime;

	private Long userId;
	private String userUsername;
	
	
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
	
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	

	

}
