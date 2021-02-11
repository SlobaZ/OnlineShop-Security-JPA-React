package onlineshop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import onlineshop.models.Kupovina;

public interface KupovinaService {
	
	Kupovina getOne(Integer id);
	List<Kupovina> findAll();
	Page<Kupovina> findAll(int pageNum);
	Kupovina save(Kupovina kupovina);
	Kupovina delete(Integer id);
		
	Page<Kupovina> search(
			@Param("userid") Long userId, 
			@Param("sifra") String sifra, 
			@Param("ukupnacena") Double ukupnacena,
			@Param("datumvremepocetak") String datumvremepocetak,
			@Param("datumvremekraj") String datumvremekraj,
			 int pageNum);

	
	Kupovina kupi(Integer id);


}
