package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.KupovinaDTO;
import onlineshop.models.User;
import onlineshop.repository.UserRepository;
import onlineshop.models.Kupovina;

import onlineshop.service.KupovinaService;
import onlineshop.utils.PomocnaKlasa;


@Component
public class KupovinaDTOToKupovina implements Converter<KupovinaDTO, Kupovina>{

	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Kupovina convert(KupovinaDTO kupovinaDTO) {
		
		User user = userRepository.getOne(kupovinaDTO.getUserId());
		if(user!=null) {
		Kupovina kupovina = null;
		
		if(kupovinaDTO.getId() != null){
			kupovina = kupovinaService.getOne(kupovinaDTO.getId());

		}
		else {
			kupovina = new Kupovina();
		}
					
			kupovina.setId(kupovinaDTO.getId());
			kupovina.setSifra(kupovinaDTO.getSifra());
			kupovina.setUkupnacena(0.0);
			if(kupovinaDTO.getDatumvreme()==null) {
				kupovina.setDatumvreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
				kupovina.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
			}
			if(kupovinaDTO.getDatumvreme()!=null) {
				kupovina.setDatumvreme(PomocnaKlasa.KonvertujStringUSqlDatumIVreme(kupovinaDTO.getDatetime()));
				kupovina.setDatetime(kupovinaDTO.getDatetime());
			}
			kupovina.setUser(user);
			return kupovina;
		}
		else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}

	

	public List<Kupovina> convert(List<KupovinaDTO> kupovinaDTOs){
		List<Kupovina> ret = new ArrayList<>();
		
		for(KupovinaDTO kupovinaDTO : kupovinaDTOs){
			ret.add(convert(kupovinaDTO));
		}
		
		return ret;
	}
}
