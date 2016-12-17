package com.doukou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="demo")
public class DemoController {
	@RequestMapping(value="index")
	public String index(Model model) {
		model.addAttribute("doukou", "豆蔻");
		return "index";
	}
}
