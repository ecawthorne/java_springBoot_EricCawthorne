package p3.init;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import p3.jpa.model.Dog;
import p3.jpa.repo.DogRepository;

@Component
public class InitDogDB implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(InitDogDB.class);

	@Autowired
	private DogRepository dogRepo;
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("BEF InitDogDB will make sure 'dog' table has rows");
			
		long countOfCustomers = dogRepo.count();
		if (countOfCustomers == 0) {
			logger.info("InitDogDB will initialize 'dog' table with 10 rows");
			dogRepo.save(new Dog("white",	"maltinese"));
			dogRepo.save(new Dog("yellow",	"chiwawa"));
			dogRepo.save(new Dog("black",	"germanShepherd"));
			dogRepo.save(new Dog("brown",	"yorki"));
			dogRepo.save(new Dog("milky",	"kangal"));
		} else {
			logger.info("InitDogDB will not do anything since 'dog' table already has rows");
		}
	}
}