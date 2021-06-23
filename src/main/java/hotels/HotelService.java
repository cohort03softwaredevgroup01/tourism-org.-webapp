package hotels;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HotelService {
	@Autowired
	private HotelRepo repo;
	
	public List<Hotels> getListHotels(){
		return repo.findAll();
	}	
	public void save(Hotels hotel) {
		repo.save(hotel);
	
	}
	
	public Hotels getHotel(int id) {
		return repo.findById(id).get();
		
		
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
		
	}
	


