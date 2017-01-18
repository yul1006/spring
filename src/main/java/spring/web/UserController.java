package spring.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.domain.User;

@Controller
@RequestMapping("/user")//중복 없애기 위함
public class UserController {
	
	
	

	private static final Logger log = LoggerFactory.getLogger(UserController.class);//클래스마다 따로 로그
	private List<User> users = new ArrayList<>();
	@PostMapping("/create")
	public String create(User user){
		
		users.add(user);
		log.debug("user "+users);
		log.info("user size "+users.size());
		//System.out.println("user "+users);
		//System.out.println("user size "+users.size());
		return "redirect:/user/list";
	}
	
	
	@GetMapping("list")//브라우저의 요청 url
	public String list(Model model){
		model.addAttribute("users",users);//"users"가 list.html에서 사용하는 변수명 , 
		
		return "/user/list";//디렉토리의 list에 접근해서 html 읽어서 뿌린것 = localhost:9090/user/list.html
	}
}
