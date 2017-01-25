package spring.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.domain.User;
import spring.domain.UserRepository;
import spring.utils.HttpSessionUtils;

@Controller
@RequestMapping("/users") // 중복 없애기 위함
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);// 클래스마다
																					// 따로
																					// 로그
	private List<User> users = new ArrayList<>();

	@Autowired // 그냥 쓰면 null pointer. 변수에 null 값 밖에 안들어감
	private UserRepository userRepository;

	@PostMapping("/checklogin")
	public String login(String userId, String password, HttpSession session) {

		User user = userRepository.findByUserId(userId);

		if (user == null) {
			return "redirect:/users/login.html";
		}

		if (!user.matchPassword(password)) {
			return "redirect:/users/login.html";

		}

		log.debug("Login Success");
		session.setAttribute("loginUser", user);

		return "redirect:/";
	}

	@GetMapping("/form")
	public String form() {
	
		return "/users/form";
	}

	@GetMapping("/login")
	public String login() {

		return "/users/login";
	}

	@PostMapping("create")
	public String create(User user) {
		userRepository.save(user);
		log.debug("user " + users);
		// log.info("user size "+users.size());

		return "redirect:/users/list";
	}

	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		checkOwner(id, session);
		
		
		model.addAttribute("user", userRepository.findOne(id));
	
		return "/users/updateForm";
	}

	private void checkOwner(Long id, HttpSession session) {
		if(HttpSessionUtils.isLoginUser(session)){
			throw new IllegalStateException("로그인하지 않은 사용자");
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(loginUser.matchId(id)){
			throw new IllegalStateException("다른 사용자 정보 수정 불가능");
		}
	}

	@PutMapping("/{id}/update")
	public String update(@PathVariable Long id, User user, HttpSession session) {
		checkOwner(id, session);
		
		log.debug("user : "+user);
		User dbUser = userRepository.findOne(id);
		dbUser.update(user);
		userRepository.save(dbUser);
		return "redirect:/users/list";
	}
	
	
	
	@GetMapping("/list") // 브라우저의 요청 url
	public String list(Model model) {

		model.addAttribute("users", userRepository.findAll());// "users"가
																// list.html에서
																// 사용하는 변수명 ,
		return "/users/list";// 디렉토리의 list에 접근해서 html 읽어서 뿌린것 =
							// localhost:9090/user/list.html
	}
}
