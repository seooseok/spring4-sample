package com.woowahan.sample.spring4.service;

import com.woowahan.sample.spring4.entity.User;
import com.woowahan.sample.spring4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 샘플 소스
 */
@Service
public class SampleService {

	@Autowired
	private UserRepository userRepository;

	public void save(String name, int count){

		User user = new User();

		user.setName(name);
		user.setCount(count);

		userRepository.save(user);
	}

	public User select(int id){
		return userRepository.findOne(id);
	}

}
