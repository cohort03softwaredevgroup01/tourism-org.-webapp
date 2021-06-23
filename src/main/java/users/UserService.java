package users;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
	
	
		@Autowired
		private UserRepo repo;
		
		public List<Users> getListUsers(){
			return repo.findAll();
		}	
		public void save(Users users) {
			repo.save(users);
		
		}
		
		public Users getUser(int id) {
			return repo.findById(id).get();
			
			
		}
		
		public void delete(int id) {
			repo.deleteById(id);
		}
			
}