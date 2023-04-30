package sg.edu.nus.iss.csf_bgg;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;


import sg.edu.nus.iss.csf_bgg.repositories.BoardGameRepo;

@SpringBootApplication
public class CsfBggApplication implements CommandLineRunner{

	@Autowired
	BoardGameRepo bgRepo;

	public static void main(String[] args) {
		SpringApplication.run(CsfBggApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		// bgRepo.findByName("Die Macher");
	}

}
