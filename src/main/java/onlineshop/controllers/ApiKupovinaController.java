package onlineshop.controllers;


import java.util.List;

import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

import onlineshop.models.Kupovina;
import onlineshop.models.Proizvod;
import onlineshop.models.Stavka;
import onlineshop.models.User;
import onlineshop.repository.UserRepository;
import onlineshop.security.services.UserDetailsImpl;
import onlineshop.service.KupovinaService;
import onlineshop.service.ProizvodService;
import onlineshop.service.StavkaService;
import onlineshop.service.UserService;
import onlineshop.support.KupovinaToKupovinaDTO;
import onlineshop.utils.PomocnaKlasa;
import onlineshop.dto.KupovinaDTO;

//@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping(value="/api/kupovine")
public class ApiKupovinaController {
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private KupovinaToKupovinaDTO toDTO;
	 	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProizvodService proizvodService;
	
	@Autowired
	private StavkaService stavkaService;
	
	@Autowired
	private UserService userService;

		

	@GetMapping()
	ResponseEntity<List<KupovinaDTO>> getAllKupovine(
			@RequestParam(required=false) Long userId, 
			@RequestParam(required=false) String sifra, 
			@RequestParam(required=false) Double ukupnaCena, 
			@RequestParam(required=false) String datumvremePocetak,
			@RequestParam(required=false) String datumvremeKraj, 
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){
		
		Page<Kupovina> kupovinaPage = null;
		
		if(userId!=null || sifra!=null || ukupnaCena!=null || datumvremePocetak!=null || datumvremeKraj!=null) {
			kupovinaPage = kupovinaService.search(userId,sifra,ukupnaCena,datumvremePocetak,datumvremeKraj,pageNum);
		}
		else {
			kupovinaPage = kupovinaService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(kupovinaPage.getTotalPages()) );
		
		return new ResponseEntity<>( toDTO.convert(kupovinaPage.getContent()) , headers , HttpStatus.OK);
	}

	
	
	
	
	@GetMapping("/{id}")
	ResponseEntity<KupovinaDTO> getKupovinaById(@PathVariable Integer id){
		Kupovina kupovina = kupovinaService.getOne(id);
		if(kupovina==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(kupovina), HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	ResponseEntity<KupovinaDTO> deleteKupovina(@PathVariable Integer id){
		Kupovina deleted = kupovinaService.delete(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>( toDTO.convert(deleted), HttpStatus.OK);
	}
		
	
	@PutMapping(value="/{id}" , consumes = "application/json")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<KupovinaDTO> editKupovina(@PathVariable Integer id , @Valid @RequestBody KupovinaDTO KupovinaDTO ){
		
		Kupovina persisted = kupovinaService.getOne(id);
		persisted.setSifra(KupovinaDTO.getSifra());
		persisted.setUkupnacena(KupovinaDTO.getUkupnacena());
		persisted.setDatetime(KupovinaDTO.getDatetime());
		
		User user = userRepository.getOne(KupovinaDTO.getUserId());
		persisted.setUser(user);
		
		kupovinaService.save(persisted);
		
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	

	
	@PostMapping(value="/kreirajkupovinu")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<KupovinaDTO> kreirajKupovinu(@RequestBody String userName){ 
		
//		String username = userName.substring(0, userName.length()-1);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = String.valueOf ( userDetails.getUsername() );
		User user = userService.findbyUsername(username);

		Kupovina kupovina = new Kupovina();
		kupovina.setSifra(PomocnaKlasa.DodeliSifru());
		kupovina.setDatumvreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql());
		kupovina.setDatetime(PomocnaKlasa.PrikaziTekstualnoDatumIVreme(PomocnaKlasa.UpisiSadasnjiDatumIVremeSql()));
		kupovina.setUkupnacena(0.0);
		kupovina.setUser(user);
		kupovinaService.save(kupovina);
		
		List<Proizvod> proizvodi = proizvodService.findAll();
		for(Proizvod proizvod:proizvodi) {
			Stavka stavka = new Stavka();
			stavka.setKolicinastavke(0);
			stavka.setCenastavke(0.0);
			stavka.setProizvod(proizvod);
			stavkaService.save(stavka);
			kupovina.addStavka(stavka);
		}
		kupovinaService.save(kupovina);
		return new ResponseEntity<>( toDTO.convert(kupovina), HttpStatus.CREATED); 
	}
	
	
	
	
	@PostMapping(value="/{id}/kupi")
	public ResponseEntity<KupovinaDTO> kupi( @PathVariable Integer id) {
		
		Kupovina kupovina = kupovinaService.kupi(id);
		if(kupovina==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>( toDTO.convert(kupovina) , HttpStatus.CREATED);
	}
	
	
	

	

}
