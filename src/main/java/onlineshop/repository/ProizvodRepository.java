package onlineshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import onlineshop.models.Kategorija;
import onlineshop.models.Proizvod;


@Repository
public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>{
	
	Proizvod findByNazivAndMarka(String naziv, String marka);
	
	@Query("SELECT p FROM Proizvod p WHERE "
			+ "(:naziv IS NULL or p.naziv like :naziv ) AND "
			+ "(:marka IS NULL or p.marka like :marka) AND "
			+ "(:kategorija IS NULL or p.kategorija like :kategorija) AND "
			+ "(:cena IS NULL or p.cena = :cena) "
			)
	Page<Proizvod> search(
			@Param("naziv") String naziv, 
			@Param("marka") String marka,
			@Param("kategorija") Kategorija kategorija,
			@Param("cena") Double cena,
			Pageable pageRequest);
	
	


}
