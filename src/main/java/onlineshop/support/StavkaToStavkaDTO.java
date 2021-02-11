package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.StavkaDTO;
import onlineshop.models.Stavka;



@Component
public class StavkaToStavkaDTO implements Converter<Stavka, StavkaDTO> {

	@Override
	public StavkaDTO convert(Stavka stavka) {
		if(stavka==null){
			return null;
		}
		
		StavkaDTO dto = new StavkaDTO();
			
		dto.setId(stavka.getId());
		dto.setKolicinastavke(stavka.getKolicinastavke());
		dto.setCenastavke(stavka.getCenastavke());
		
		dto.setKupovinaId(stavka.getKupovina().getId());
		dto.setKupovinaSifra(stavka.getKupovina().getSifra());
		dto.setKupovinaUkupnacena(stavka.getKupovina().getUkupnacena());
		dto.setKupovinaDatumvreme(stavka.getKupovina().getDatumvreme());
		dto.setKupovinaDatetime(stavka.getKupovina().getDatetime());
		
		dto.setProizvodId(stavka.getProizvod().getId());
		dto.setProizvodNaziv(stavka.getProizvod().getNaziv());
		dto.setProizvodKolicina(stavka.getProizvod().getKolicina());
		dto.setProizvodCena(stavka.getProizvod().getCena());
		
		return dto;
	}
	
	public List<StavkaDTO> convert(List<Stavka> transakcije){
		List<StavkaDTO> ret = new ArrayList<>();
		
		for(Stavka s: transakcije){
			ret.add(convert(s));
		}
		
		return ret;
	}

}
