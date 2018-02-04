package com.kkhts.intra.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kkhts.intra.dao.EventRepository;
import com.kkhts.intra.dao.UserRepository;
import com.kkhts.intra.entity.Event;
import com.kkhts.intra.form.EventForm;
import com.kkhts.intra.services.EventService;
import com.kkhts.intra.services.UserService;

@Controller
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	UserService userService;

	@Autowired
	EventService eventService;

	@Autowired
	UserRepository userRepo;

	@Autowired
	EventRepository eventRepo;


	@RequestMapping(value = "/")
	public String IndexController(Model model)  {
		model.addAttribute(eventService.findAll());
		return "event/index";
	}

	@RequestMapping(value = "/{eventId}")
	public String DetailController(@PathVariable("eventId") Long eventId, Model model)  {
		model.addAttribute(eventService.find(eventId));
		return "event/detail";
	}

	@RequestMapping(value = "/edit/{eventId}")
	public String EditController(@PathVariable("eventId") Long eventId, Model model)  {
		EventForm eventForm = new EventForm();
		BeanUtils.copyProperties(eventService.find(eventId), eventForm);
		model.addAttribute("eventForm", eventForm);
		return "event/edit";
	}

	@RequestMapping(value = "/new")
	public String NewController(Model model)  {
		model.addAttribute("eventForm", new EventForm());
		return "event/edit";
	}

	@RequestMapping(value = "/register")
	public String RegisterController(Model model, EventForm eventForm, BindingResult result)  {
		if (result.hasErrors()) {
			model.addAttribute(eventForm);
			return "event/edit";
		}

		Event event = new Event(eventForm);
		eventService.save(event);

		return "forward:/event/";
	}

}
