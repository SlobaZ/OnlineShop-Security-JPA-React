package onlineshop.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import onlineshop.models.Kategorija;
import onlineshop.models.Kupovina;
import onlineshop.models.Proizvod;
import onlineshop.models.Stavka;
import onlineshop.service.KupovinaService;
import onlineshop.service.ProizvodService;
import onlineshop.service.StavkaService;
import onlineshop.support.ProizvodDTOToProizvod;
import onlineshop.support.ProizvodToProizvodDTO;
import onlineshop.utils.DodeliKategoriju;
import onlineshop.dto.ProizvodDTO;


@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/proizvodi")
public class ApiProizvodController {
	
	@Autowired
	private ProizvodService proizvodService;
	
	@Autowired
	private ProizvodToProizvodDTO toDTO;
	
	@Autowired
	private ProizvodDTOToProizvod toProizvod;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private StavkaService stavkaService;
	
	
	DodeliKategoriju dk = new DodeliKategoriju();
	
	
	
	@GetMapping("/kategorije")
	public Kategorija[] getKategorije(){
		return onlineshop.models.Kategorija.values();
	}
	
		
	@GetMapping()
	ResponseEntity<List<ProizvodDTO>> getAllProizvodi(
			@RequestParam (required = false) String naziv,
			@RequestParam (required = false) String marka,
			@RequestParam (required = false) Kategorija kategorija,
			@RequestParam (required = false) Double cena,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Proizvod> proizvodPage = null;
		
		if(naziv != null || marka != null || kategorija!=null || cena != null) {
			proizvodPage = proizvodService.search(naziv, marka, kategorija, cena, pageNum);
		}
		else {
			proizvodPage = proizvodService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(proizvodPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(proizvodPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProizvodDTO> getProizvodById(@PathVariable Integer id){
		Proizvod proizvod = proizvodService.getOne(id);
		if(proizvod==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(proizvod), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProizvodDTO> deleteProizvod(@PathVariable Integer id){
		Proizvod deleted = proizvodService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
	
	
	@PostMapping(consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProizvodDTO> addProizvod(@Valid @RequestBody ProizvodDTO newProizvodDTO ){
		
		if(newProizvodDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Proizvod savedProizvod = proizvodService.save(toProizvod.convert(newProizvodDTO));
		dk.dodeli(savedProizvod);
		List<Kupovina> kupovine = kupovinaService.findAll();
		for( Kupovina kupovina : kupovine ) {
			Stavka stavka = new Stavka();
			stavka.setProizvod(savedProizvod);
			stavkaService.save(stavka);
			kupovina.addStavka(stavka);
			kupovinaService.save(kupovina);
		}
		return new ResponseEntity<>( toDTO.convert(savedProizvod), HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value="/{id}" , consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<ProizvodDTO> updateProizvod( @PathVariable Integer id, @Valid @RequestBody ProizvodDTO proizvodDTO){
				
		Proizvod persisted = proizvodService.getOne(id);
		persisted.setNaziv(proizvodDTO.getNaziv());
		persisted.setMarka(proizvodDTO.getMarka());
		persisted.setKolicina(proizvodDTO.getKolicina());
		persisted.setCena(proizvodDTO.getCena());
		persisted.setPhoto(proizvodDTO.getPhoto());
    	dk.dodeli(persisted);
		
		
		proizvodService.save(persisted);
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	

	
	
}
