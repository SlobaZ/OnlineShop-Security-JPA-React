package onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import onlineshop.service.StavkaService;
import onlineshop.models.Kupovina;
import onlineshop.models.Proizvod;
import onlineshop.models.Stavka;
import onlineshop.repository.ProizvodRepository;
import onlineshop.repository.StavkaRepository;

@Service
public class JpaStavkaService implements StavkaService {
	
	@Autowired
	private StavkaRepository stavkaRepository;
	
	@Autowired
	private ProizvodRepository proizvodRepository;

	@Override
	public Stavka getOne(Integer id) {
		return stavkaRepository.getOne(id);
	}

	@Override
	public List<Stavka> findAll() {
		return stavkaRepository.findAll();
	}

	@Override
	public Page<Stavka> findAll(int pageNum) {
		PageRequest pageable = PageRequest.of(pageNum, 20);
		return stavkaRepository.findAll(pageable);
	}

	@Override
	public Stavka save(Stavka stavka) {
		return stavkaRepository.save(stavka);
	}

	@Override
	public Stavka delete(Integer id) {
		Stavka stavka = stavkaRepository.getOne(id);
		if(stavka != null) {
			stavkaRepository.delete(stavka);
		}
		return stavka;
	}


	@Override
	public List<Stavka> findByIdKupovine(Integer kupovinaId) {
		return stavkaRepository.findByIdKupovine(kupovinaId);
	}

	@Override
	public Stavka kupiStavku(Integer id, int kolicinastavke) {
		Stavka stavka = stavkaRepository.getOne(id);
		Proizvod proizvod = stavka.getProizvod();
		Kupovina kupovina = stavka.getKupovina();
		List<Stavka> stavke = stavkaRepository.findByIdKupovine(kupovina.getId());
		Double	x = 0.0 ;
		for (Stavka s: stavke) {
			x += s.getCenastavke();
		  }
		
		if( proizvod.getKolicina()- kolicinastavke >= 0   &&  proizvod.getKolicina() >= kolicinastavke ) {
			
			proizvod.setKolicina( proizvod.getKolicina() - kolicinastavke ); 
			stavka.setKolicinastavke(stavka.getKolicinastavke() + kolicinastavke);
			stavka.setCenastavke(stavka.getCenastavke() + (proizvod.getCena()*kolicinastavke) );

			}
		else {
			return null;
		}
		proizvodRepository.save(proizvod);
		stavkaRepository.save(stavka);
		
		return stavka;
	}

	@Override
	public Stavka resetujStavku(Integer id) {
		Stavka stavka = stavkaRepository.getOne(id);
		Proizvod proizvod = stavka.getProizvod();
		proizvod.setKolicina( proizvod.getKolicina() + stavka.getKolicinastavke() ); 
		stavka.setCenastavke(0.0);
		stavka.setKolicinastavke(0);
		proizvodRepository.save(proizvod);
		stavkaRepository.save(stavka);
		return stavka;
	}
	

}
