package com.spring.mvcTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

	@RequestMapping("/view")
	public String view(){
		return "view";
	}
	
	@RequestMapping("/content/contentView")
	public String contentView(Model model){
		model.addAttribute("id", "abcddd");
		return "content/contentView";
	}
	
	@RequestMapping("/model/modelAndView")
	public ModelAndView modelAndView(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", "dddd");
		mv.setViewName("/model/modelAndView");
		return mv;
	}
	
	@RequestMapping("/command")
	public String command(Member member){
		
		return "command";
	}
}
