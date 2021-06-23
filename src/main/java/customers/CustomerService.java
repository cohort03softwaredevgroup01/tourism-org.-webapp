package customers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




	
@Service
@Transactional
public class CustomerService {
	
	
		@Autowired
		private CustomerRepo repo;
		
		public List<Customers> getListCustomers(){
			return repo.findAll();
		}	
		public void save(Customers customers) {
			repo.save(customers);
		
		}
		
		public Customers getCustomer(int id) {
			return repo.findById(id).get();
			
			
		}
		
		public void delete(int id) {
			repo.deleteById(id);
		}
			
}
		

