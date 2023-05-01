package sg.edu.nus.iss.csf_bgg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;


import sg.edu.nus.iss.csf_bgg.repositories.BoardGameRepo;
import sg.edu.nus.iss.csf_bgg.services.BoardGameService;

@SpringBootApplication
public class CsfBggApplication implements CommandLineRunner{

	@Autowired
	BoardGameRepo bgRepo;

	@Autowired
	BoardGameService bgSvc;

	public static void main(String[] args) {
		SpringApplication.run(CsfBggApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// bgRepo.findByName("Die Macher");
		// bgSvc.mongoFindByName("die macher");
	}

}
