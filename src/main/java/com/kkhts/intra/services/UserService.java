package com.kkhts.intra.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkhts.intra.dao.UserRepository;
import com.kkhts.intra.entity.User;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository repository;

	@Transactional(readOnly = true, timeout = 10)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true, timeout = 10)
	public User find(final Long id) {
		return repository.findOne(id);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public User save(final User user) {
		return repository.save(user);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public void delete(final Long id) {
		repository.delete(id);
	}
}