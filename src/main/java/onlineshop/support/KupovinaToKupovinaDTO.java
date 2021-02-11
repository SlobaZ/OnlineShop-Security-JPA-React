package onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import onlineshop.dto.KupovinaDTO;
import onlineshop.models.Kupovina;
import onlineshop.utils.PomocnaKlasa;


@Component
public class KupovinaToKupovinaDTO implements Converter<Kupovina, KupovinaDTO>{

	@Override
	public KupovinaDTO convert(Kupovina kupovina) {
		
		KupovinaDTO retValue = new KupovinaDTO();
		
		retValue.setId(kupovina.getId());
		retValue.setSifra(kupovina.getSifra());
		retValue.setUkupnacena(kupovina.getUkupnacena());
		if(kupovina.getDatumvreme()==null) {
			retValue.setDatumvreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
			retValue.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
		}
		else {
			retValue.setDatumvreme(kupovina.getDatumvreme());
			retValue.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(kupovina.getDatumvreme()));
		}
		
		retValue.setUserId(kupovina.getUser().getId());
		retValue.setUserUsername(kupovina.getUser().getUsername());

		return retValue;
	}

	public List<KupovinaDTO> convert(List<Kupovina> kupovine){
		List<KupovinaDTO> ret = new ArrayList<>();
		
		for(Kupovina kupovina : kupovine){
			ret.add(convert(kupovina));
		}
		
		return ret;
	}

}
