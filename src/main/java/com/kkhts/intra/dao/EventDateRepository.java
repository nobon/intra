package com.kkhts.intra.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkhts.intra.entity.EventDate;

public interface EventDateRepository extends JpaRepository<EventDate, Long> {
	/** イベントID検索 */
	List<EventDate> findByEventId(Long id);
}