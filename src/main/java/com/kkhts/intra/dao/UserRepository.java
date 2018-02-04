package com.kkhts.intra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkhts.intra.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	/** ID検索 */
	User findById(Long id);
	/** メールアドレス検索 */
	User findByMailAddress(String mailAddress);
}