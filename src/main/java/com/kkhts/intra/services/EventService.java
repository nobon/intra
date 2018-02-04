package com.kkhts.intra.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkhts.intra.dao.EventRepository;
import com.kkhts.intra.entity.Event;
import com.kkhts.intra.entity.EventDate;

@Service
@Transactional
public class EventService {

	@Autowired
	EventRepository eventRepo;
	@Autowired
	EventDateService eventDateService;

	@Transactional(readOnly = true, timeout = 10)
	public List<Event> findAll() {
		return eventRepo.findAll();
	}

	@Transactional(readOnly = true, timeout = 10)
	public Event find(final Long id) {
		return eventRepo.findOne(id);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public Event save(final Event event) {
		for (EventDate eventDate : event.getEventDates()) {
			eventDateService.save(eventDate);
		}
		return eventRepo.save(event);
	}

	@Transactional(rollbackFor = {Exception.class}, timeout = 3)
	public void delete(final Long id) {
		eventRepo.delete(id);
	}
}