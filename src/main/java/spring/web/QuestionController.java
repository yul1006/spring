package spring.web;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import spring.domain.Question;
import spring.domain.QuestionRepository;

@Controller
public class QuestionController {

	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);// 클래스마다
																						// 따로
																						// 로그
	private List<Question> questions = new ArrayList<>();

	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("/qna/question")
	public String create(Question question) {
		questionRepository.save(question);
		log.debug("question " + questions);
		log.info("question size " + questions.size());
		return "redirect:/";
	}

	
	@GetMapping("/qna/form")
	public String qeustion() {

		return "/qna/form";
	}


	
	@PostMapping("/qna/index")
	public String qeustionIndex() {
		return "/qna/index";
	}

	
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("questions", questionRepository.findAll());
		return "/qna/index";
	}
	
	
	

}
