package users;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@GetMapping("/user")
	public List<Users> list() {
		
		return service.getListUsers();
	}
	
	@PostMapping("/users")
	public void add(@RequestBody Users addUser) {
	    service.save(addUser);
	    
	}
	
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@RequestBody Users updateUser, @PathVariable Integer id) {
	    try {
	         service.getUser(id);
	        service.save(updateUser);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }      
	}
	
	@DeleteMapping("/user/{id}")
	public void delete(@PathVariable Integer id) {
	    service.delete(id);
	}


}
