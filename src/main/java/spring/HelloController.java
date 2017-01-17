package spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HelloController {
	@GetMapping("/hello")
	public String hello(String name, int age, Model model){
		System.out.println("name "+name);
		model.addAttribute("name", name);
		
		System.out.println("age "+age);
		model.addAttribute("age", age);
		
		return "hello";
	}
}
