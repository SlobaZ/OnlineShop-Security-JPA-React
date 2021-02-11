package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ProizvodDTO;
import onlineshop.models.Proizvod;



@Component
public class ProizvodToProizvodDTO implements Converter<Proizvod, ProizvodDTO> {

	@Override
	public ProizvodDTO convert(Proizvod proizvod) {
		if(proizvod==null){
			return null;
		}
		
		ProizvodDTO dto = new ProizvodDTO();
		
		dto.setId(proizvod.getId());
		dto.setNaziv(proizvod.getNaziv());
		dto.setMarka(proizvod.getMarka());
		dto.setKolicina(proizvod.getKolicina());
		dto.setCena(proizvod.getCena());
		dto.setPhoto(proizvod.getPhoto());
		
		if(proizvod.getKategorija()!=null) {
		dto.setKategorija(proizvod.getKategorija());
		}
		
		return dto;
	}
	
	public List<ProizvodDTO> convert(List<Proizvod> proizvodi){
		List<ProizvodDTO> ret = new ArrayList<>();
		
		for(Proizvod p: proizvodi){
			ret.add(convert(p));
		}
		
		return ret;
	}

}
