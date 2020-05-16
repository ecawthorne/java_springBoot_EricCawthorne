package p3.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import p3.jpa.model.Dog;
import p3.jpa.repo.DogRepository;

@RestController
@RequestMapping(path = "/rest/v1/dogs")
public class DogRestController
{
	@Autowired
	private DogRepository dogRepo;
	@RequestMapping(value = "/echoMessage", method = RequestMethod.GET)
	public String echoMessage(@RequestParam(value = "msg", defaultValue = "Eric Cawthorne") String message)
	{
		return "echoMsg: " + message;
	}
	
	@GetMapping("")
	public Page<Dog> findAll(@RequestParam(defaultValue="0") int page, @RequestParam(value="rowsPerPage", defaultValue="2") int size) {
		Page<Dog> dogPage = dogRepo.findAll(PageRequest.of(page, size));
		return dogPage;
	}
	@GetMapping("/all")
	public  List<Dog> findAll() {
		List<Dog> dogs = dogRepo.findAll();
		return dogs;
	}
	@GetMapping({"/{id}","/findById/{id}"})
	public  Optional<Dog> findById(@PathVariable Long id) {
		Optional<Dog> dog = dogRepo.findById(id);
		return dog;
	}
	@GetMapping("/findByType/{type}")
	public  Optional<List<Dog>> findByType(@PathVariable String type) {
		Optional<List<Dog>> dogs = dogRepo.findByType(type);
		return dogs;
	}
}
