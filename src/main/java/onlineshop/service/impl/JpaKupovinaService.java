package onlineshop.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.service.KupovinaService;
import onlineshop.utils.PomocnaKlasa;
import onlineshop.models.Kupovina;
import onlineshop.models.Stavka;
import onlineshop.repository.KupovinaRepository;
import onlineshop.repository.StavkaRepository;

@Service
public class JpaKupovinaService implements KupovinaService{
	
	@Autowired
	private KupovinaRepository kupovinaRepository; 
	
	@Autowired
	private StavkaRepository stavkaRepository;
	
	@Override
	public Kupovina getOne(Integer id) {
		return kupovinaRepository.getOne(id);
	}

	@Override
	public List<Kupovina> findAll() {
		return kupovinaRepository.findAll();
	}
	
	@Override
	public Page<Kupovina> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return kupovinaRepository.findAll(pageable);
	}

	@Override
	public Kupovina save(Kupovina kupovina) {
		return kupovinaRepository.save(kupovina);
	}

	@Override
	public Kupovina delete(Integer id) {
		Kupovina kupovina = kupovinaRepository.getOne(id);
		if(kupovina != null) {
			kupovinaRepository.delete(kupovina);
		}
		return kupovina;
	}

	
	@Override
	public Page<Kupovina> search(Long userId, String sifra, Double ukupnacena, 
			String datumvremepocetak, String datumvremekraj, int pageNum) {
		
		Timestamp datumVremePocetak = null;
		Timestamp datumVremeKraj = null;
		if( sifra != null) {
			sifra = '%' + sifra + '%';
		}

		if(datumvremepocetak != null) { 
		datumVremePocetak = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremepocetak);
		}
		if(datumvremekraj !=null) {
			 datumVremeKraj = PomocnaKlasa.KonvertujStringUSqlDatumIVreme(datumvremekraj);
		}
	
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return kupovinaRepository.search(userId, sifra, ukupnacena, datumVremePocetak, datumVremeKraj,  pageable);
	}

	
	@Override
	public Kupovina kupi(Integer id) {
		Kupovina kupovina = kupovinaRepository.getOne(id);
		List<Stavka> stavke = stavkaRepository.findByIdKupovine(id);
		Double	x = 0.0 ;
		for (Stavka stavka: stavke) {
			x += stavka.getCenastavke();
		  }
		kupovina.setUkupnacena(x);
		kupovinaRepository.save(kupovina);
		return kupovina;
	}




}
