package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.StavkaDTO;
import onlineshop.models.Kupovina;
import onlineshop.models.Proizvod;
import onlineshop.models.Stavka;
import onlineshop.service.KupovinaService;
import onlineshop.service.ProizvodService;
import onlineshop.service.StavkaService;


@Component
public class StavkaDTOToStavka implements Converter<StavkaDTO, Stavka> {
	
	@Autowired
	private StavkaService stavkaService;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private ProizvodService proizvodService;

	@Override
	public Stavka convert(StavkaDTO dto) {
		
		Kupovina kupovina = kupovinaService.getOne(dto.getKupovinaId());
		Proizvod proizvod = proizvodService.getOne(dto.getProizvodId());
		
		if(kupovina!=null && proizvod!=null) {
			
			Stavka stavka = null;
			
			if(dto.getId() != null) {
				stavka = stavkaService.getOne(dto.getId());
			}
			else {
				stavka = new Stavka();
			}
			
			stavka.setId(dto.getId());
			stavka.setKolicinastavke(dto.getKolicinastavke());
			stavka.setCenastavke(dto.getCenastavke());
			
			stavka.setKupovina(kupovina);
			stavka.setProizvod(proizvod);
			
			return stavka;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
		
		
	
	}
	
	public List<Stavka> convert (List<StavkaDTO> dtoTransakcije){
		List<Stavka> transakcije = new ArrayList<>();
		
		for(StavkaDTO dto : dtoTransakcije){
			transakcije.add(convert(dto));
		}
		
		return transakcije;
	}

}
