package com.service2;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MovisService {
	@Autowired
	private MovisRepository movisRepository;
	
	public List<MovisModel> listAll(){
		return movisRepository.findAll();
	}
	
	public void alta(MovisModel movismodel) {
		movisRepository.save(movismodel);
	}
	
}
