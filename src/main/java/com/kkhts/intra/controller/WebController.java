package com.kkhts.intra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/")
public class WebController {

	@RequestMapping(value = {"/" , "/login"})
	public String LoginController(Model model)  {

		return "login";
	}

}
