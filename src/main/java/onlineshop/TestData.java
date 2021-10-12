package onlineshop;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import onlineshop.models.Role;
import onlineshop.models.ERole;
import onlineshop.models.User;
import onlineshop.repository.RoleRepository;
import onlineshop.repository.UserRepository;
import onlineshop.service.KupovinaService;
import onlineshop.service.ProizvodService;
import onlineshop.service.StavkaService;
import onlineshop.utils.DodeliKategoriju;
import onlineshop.models.Kupovina;
import onlineshop.models.Proizvod;
import onlineshop.models.Stavka;




@Component
public class TestData {
	
	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ProizvodService proizvodService;
	
	@Autowired
	private KupovinaService kupovinaService;
	
	@Autowired
	private StavkaService stavkaService;
	
	

	
	@PostConstruct
	public void init() {
		
		DodeliKategoriju dk = new DodeliKategoriju();
				
		Role role1 = new Role();
		role1.setName(ERole.ROLE_ADMIN);
		role1 = roleRepository.save(role1);
		
		Role role2 = new Role();
		role2.setName(ERole.ROLE_USER);
		role2 = roleRepository.save(role2);
		
		User user1 = new User();
		user1.setUsername("Admin");
		user1.setEmail("admin@gmail.com");
		user1.setPassword("$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS");     // admin
		user1 = userRepository.save(user1);

		User user2 = new User();
		user2.setUsername("VasaVasic");
		user2.setEmail("vasa@gmail.com");
		user2.setPassword("$2a$10$bwQVsArIQJtmkPckmfRZGOEMAGBXcHaziXIEgstc9ePsPG6sYEFK.");     // 654321
		user2 = userRepository.save(user2);
		
		User user3 = new User();
		user3.setUsername("PeraPeric");
		user3.setEmail("pera@gmail.com");
		user3.setPassword("$2a$10$Locf9fRBO84ejEc/bQFEROChVsd2ixjv4M2kYX6KSLp74iacK.N3W");     // 123456
		user3 = userRepository.save(user3);
		
	
		user1.addRole(role1);
		user1.addRole(role2);
		user2.addRole(role2);
		user3.addRole(role2);
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
		
		Proizvod proizvod1 = new Proizvod();
		proizvod1.setNaziv("Televizor");
		proizvod1.setMarka("Sony");
		proizvod1.setKolicina(50);
		proizvod1.setCena(25000.0);
		proizvod1.setPhoto("SlikaTV.jpg");
		proizvod1 = proizvodService.save(proizvod1);
		
		Proizvod proizvod2 = new Proizvod();
		proizvod2.setNaziv("Telefon");
		proizvod2.setMarka("Samsung");
		proizvod2.setKolicina(70);
		proizvod2.setCena(15000.0);
		proizvod2.setPhoto("SlikaTelefon.jpg");
		proizvod2 = proizvodService.save(proizvod2);
		
		Proizvod proizvod3 = new Proizvod();
		proizvod3.setNaziv("Frizider");
		proizvod3.setMarka("Gorenje");
		proizvod3.setKolicina(30);
		proizvod3.setCena(35000.0);
		proizvod3.setPhoto("SlikaFrizider.jpg");
		proizvod3 = proizvodService.save(proizvod3);
		
		Proizvod proizvod4 = new Proizvod();
		proizvod4.setNaziv("Ves masina");
		proizvod4.setMarka("Beko");
		proizvod4.setKolicina(40);
		proizvod4.setCena(30000.0);
		proizvod4.setPhoto("SlikaVesMasina.jpg");
		proizvod4 = proizvodService.save(proizvod4);
		
		Proizvod proizvod5 = new Proizvod();
		proizvod5.setNaziv("Usisivac");
		proizvod5.setMarka("Vox");
		proizvod5.setKolicina(35);
		proizvod5.setCena(7000.0);
		proizvod5.setPhoto("SlikaUsisivac.jpg");
		proizvod5 = proizvodService.save(proizvod5);
		
		Proizvod proizvod6 = new Proizvod();
		proizvod6.setNaziv("Pegla");
		proizvod6.setMarka("Gorenje");
		proizvod6.setKolicina(60);
		proizvod6.setCena(4000.0);
		proizvod6.setPhoto("SlikaPegla.jpg");
		proizvod6 = proizvodService.save(proizvod6);
		
		Proizvod proizvod7 = new Proizvod();
		proizvod7.setNaziv("Sporet");
		proizvod7.setMarka("Alfa Plam");
		proizvod7.setKolicina(25);
		proizvod7.setCena(30000.0);
		proizvod7.setPhoto("SlikaSporet.jpg");
		proizvod7 = proizvodService.save(proizvod7);
		
		dk.dodeli(proizvod1);
		dk.dodeli(proizvod2);
		dk.dodeli(proizvod3);
		dk.dodeli(proizvod4);
		dk.dodeli(proizvod5);
		dk.dodeli(proizvod6);
		dk.dodeli(proizvod7);
		proizvodService.save(proizvod1);
		proizvodService.save(proizvod2);
		proizvodService.save(proizvod3);
		proizvodService.save(proizvod4);
		proizvodService.save(proizvod5);
		proizvodService.save(proizvod6);
		proizvodService.save(proizvod7);
		
		
		Kupovina kupovina1 = new Kupovina();
		kupovina1.setSifra("A 123");
		kupovina1.setDatumvreme(java.sql.Timestamp.valueOf("2019-09-15 10:10:10"));
		kupovina1.setDatetime("15.09.2019. 10:10");
		kupovina1.setUkupnacena(194000.0);
		kupovina1.setUser(user2);
		kupovina1 = kupovinaService.save(kupovina1);
		
		Kupovina kupovina2 = new Kupovina();
		kupovina2.setSifra("B 456");
		kupovina2.setDatumvreme(java.sql.Timestamp.valueOf("2020-04-15 08:25:20"));
		kupovina2.setDatetime("15.04.2020. 08:25");
		kupovina2.setUkupnacena(96000.0);
		kupovina2.setUser(user3);
		kupovina2 = kupovinaService.save(kupovina2);
		

		Stavka stavka1 = new Stavka();
		stavka1.setKolicinastavke(2);
		stavka1.setCenastavke(50000.0);
		stavka1.setProizvod(proizvod1);
		stavka1 = stavkaService.save(stavka1);
		
		Stavka stavka2 = new Stavka();
		stavka2.setKolicinastavke(3);
		stavka2.setCenastavke(45000.0);
		stavka2.setProizvod(proizvod2);
		stavka2 = stavkaService.save(stavka2);
		
		Stavka stavka3 = new Stavka();
		stavka3.setKolicinastavke(1);
		stavka3.setCenastavke(35000.0);
		stavka3.setProizvod(proizvod3);
		stavka3 = stavkaService.save(stavka3);
		
		Stavka stavka4 = new Stavka();
		stavka4.setKolicinastavke(0);
		stavka4.setCenastavke(0.0);
		stavka4.setProizvod(proizvod4);
		stavka4 = stavkaService.save(stavka4);

		Stavka stavka5 = new Stavka();
		stavka5.setKolicinastavke(0);
		stavka5.setCenastavke(0.0);
		stavka5.setProizvod(proizvod5);
		stavka5 = stavkaService.save(stavka5);
		
		Stavka stavka6 = new Stavka();
		stavka6.setKolicinastavke(1);
		stavka6.setCenastavke(4000.0);
		stavka6.setProizvod(proizvod6);
		stavka6 = stavkaService.save(stavka6);
		
		Stavka stavka7 = new Stavka();
		stavka7.setKolicinastavke(2);
		stavka7.setCenastavke(60000.0);
		stavka7.setProizvod(proizvod7);
		stavka7 = stavkaService.save(stavka7);
		
		kupovina1.addStavka(stavka1);
		kupovina1.addStavka(stavka2);
		kupovina1.addStavka(stavka3);
		kupovina1.addStavka(stavka4);
		kupovina1.addStavka(stavka5);
		kupovina1.addStavka(stavka6);
		kupovina1.addStavka(stavka7);
		kupovinaService.save(kupovina1);
		
		
		Stavka stavka8 = new Stavka();
		stavka8.setKolicinastavke(1);
		stavka8.setCenastavke(25000.0);
		stavka8.setProizvod(proizvod1);
		stavka8 = stavkaService.save(stavka8);
		
		Stavka stavka9 = new Stavka();
		stavka9.setKolicinastavke(1);
		stavka9.setCenastavke(15000.0);
		stavka9.setProizvod(proizvod2);
		stavka9 = stavkaService.save(stavka9);
		
		Stavka stavka10 = new Stavka();
		stavka10.setKolicinastavke(1);
		stavka10.setCenastavke(45000.0);
		stavka10.setProizvod(proizvod3);
		stavka10 = stavkaService.save(stavka10);
		
		Stavka stavka11 = new Stavka();
		stavka11.setKolicinastavke(0);
		stavka11.setCenastavke(0.0);
		stavka11.setProizvod(proizvod4);
		stavka11 = stavkaService.save(stavka11);

		Stavka stavka12 = new Stavka();
		stavka12.setKolicinastavke(1);
		stavka12.setCenastavke(7000.0);
		stavka12.setProizvod(proizvod5);
		stavka12 = stavkaService.save(stavka12);
		
		Stavka stavka13 = new Stavka();
		stavka13.setKolicinastavke(1);
		stavka13.setCenastavke(4000.0);
		stavka13.setProizvod(proizvod6);
		stavka13 = stavkaService.save(stavka13);
		
		Stavka stavka14 = new Stavka();
		stavka14.setKolicinastavke(0);
		stavka14.setCenastavke(0.0);
		stavka14.setProizvod(proizvod7);
		stavka14 = stavkaService.save(stavka14);
		
		
		kupovina2.addStavka(stavka8);
		kupovina2.addStavka(stavka9);
		kupovina2.addStavka(stavka10);
		kupovina2.addStavka(stavka11);
		kupovina2.addStavka(stavka12);
		kupovina2.addStavka(stavka13);
		kupovina2.addStavka(stavka14);
		kupovinaService.save(kupovina2);
		
		


		

	}

}
