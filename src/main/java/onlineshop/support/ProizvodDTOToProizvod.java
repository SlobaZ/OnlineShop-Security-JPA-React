package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.ProizvodDTO;
import onlineshop.models.Proizvod;
import onlineshop.service.ProizvodService;



@Component
public class ProizvodDTOToProizvod implements Converter<ProizvodDTO, Proizvod> {
	
	@Autowired
	private ProizvodService proizvodService;

	@Override
	public Proizvod convert(ProizvodDTO dto) {
		Proizvod proizvod = null;
		
		if(dto.getId()!=null){
			proizvod = proizvodService.getOne(dto.getId());
			
			if(proizvod == null){
				throw new IllegalStateException("Tried to "
						+ "modify a non-existant Proizvod");
			}
		}
		else {
			proizvod = new Proizvod();
		}
		
		proizvod.setId(dto.getId());
		proizvod.setNaziv(dto.getNaziv());
		proizvod.setMarka(dto.getMarka());
		proizvod.setKolicina(dto.getKolicina());
		proizvod.setCena(dto.getCena());
		proizvod.setPhoto(dto.getPhoto());
		
		if(dto.getKategorija()!=null) {
		proizvod.setKategorija(dto.getKategorija());
		}
		return proizvod;
	}
	
	public List<Proizvod> convert (List<ProizvodDTO> dtoProizvods){
		List<Proizvod> proizvodi = new ArrayList<>();
		
		for(ProizvodDTO dto : dtoProizvods){
			proizvodi.add(convert(dto));
		}
		
		return proizvodi;
	}

}
