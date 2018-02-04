package com.kkhts.intra.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkhts.intra.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {


}